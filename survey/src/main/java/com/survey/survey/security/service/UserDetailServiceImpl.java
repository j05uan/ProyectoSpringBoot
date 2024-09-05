package com.survey.survey.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.survey.survey.roles.domain.entity.Roles;
import com.survey.survey.roles.infraestructure.repository.Rolesrepository;
import com.survey.survey.security.controller.dto.AuthCreateUser;
import com.survey.survey.security.controller.dto.AuthLoginRequest;
import com.survey.survey.security.controller.dto.AuthResponse;
import com.survey.survey.user.domain.entity.Users;
import com.survey.survey.user.infraestructure.repository.Userrepository;
import com.survey.survey.utils.JwtUtils;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private  Userrepository userrepository;

    @Autowired
    private Rolesrepository rolesrepository;

    

    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        
        Users user = userrepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario "+ username + "no existe ."));

        List<SimpleGrantedAuthority> authoritys = new ArrayList<>();

        user.getRoles()
            .forEach(role -> authoritys.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName()))));


        user.getRoles().stream()
            .flatMap(role ->role.getPermissions().stream())
            .forEach(permission -> authoritys.add(new SimpleGrantedAuthority(permission.getName())));
        return new User(user.getUsername(),
                        user.getPassword(),
                        user.isEnable(),
                        user.isAccountNoExpired(),
                        user.isCredentialNoExpired(),
                        user.isAccountNoLocked(),
                        authoritys);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest){
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accesstoken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "User login succesfuly", accesstoken, true);
        return  authResponse;

    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException(String.format("Invalid username or password"));
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

   public AuthResponse createUser(AuthCreateUser createRoleRequest) {

        String username = createRoleRequest.username();
        String password = createRoleRequest.password();
        List<String> rolesRequest = createRoleRequest.roleRequest().roleListName();

        Set<Roles> roleEntityList = rolesrepository.findByNameIn(rolesRequest).stream().collect(Collectors.toSet());

        if (roleEntityList.isEmpty()) {
            throw new IllegalArgumentException("The roles specified does not exist.");
        }

        Users userEntity = Users.builder().username(username).password(passwordEncoder.encode(password)).roles(roleEntityList).isEnable(true).accountNoLocked(true).accountNoExpired(true).credentialNoExpired(true).build();

        Users userSaved = userrepository.save(userEntity);

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userSaved.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName()))));

        userSaved.getRoles().stream().flatMap(role -> role.getPermissions().stream()).forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        SecurityContext securityContextHolder = SecurityContextHolder.getContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved, null, authorities);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "User created successfully", accessToken, true);
        return authResponse;
    }

}
