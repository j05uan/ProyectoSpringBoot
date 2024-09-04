package com.survey.survey.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.survey.survey.roles.infraestructure.repository.Rolesrepository;
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
}
