package com.survey.survey.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.survey.survey.user.domain.entity.Users;
import com.survey.survey.user.infraestructure.repository.Userrepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private  Userrepository userrepository;

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

}
