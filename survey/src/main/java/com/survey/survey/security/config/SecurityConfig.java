package com.survey.survey.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.survey.survey.security.service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
            .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic(Customizer.withDefaults())
            .authorizeHttpRequests(http -> {
                http.requestMatchers(HttpMethod.GET, "/auth/hello").permitAll();
                http.requestMatchers(HttpMethod.GET, "/auth/hello-secured").hasAuthority("CREATE");

                http.anyRequest().authenticated();
            })
            .build();
    }


    public AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailServiceImpl){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(null);
        provider.setUserDetailsService(userDetailServiceImpl);
        return provider;
    }

    // @Bean
    // public UserDetailsService userDetailsService(){
    //     UserDetails userDetails = User.withUsername("JP")
    //         .password("1234")
    //         .roles("ADMIN")
    //         .authorities("READ", "CREATE")
    //         .build();

    //     return new InMemoryUserDetailsManager(userDetails);
    // }

    


    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder(); //es para encriptar
        // return NoOpPasswordEncoder.getInstance();
    }


}
