package com.spring.empdir.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
    
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                          .username("john")
                          .password("{noop}test123")
                          .roles("EMPLOYEE")
                          .build();
        UserDetails mary = User.builder()
                          .username("mary")
                          .password("{noop}test123")
                          .roles("EMPLOYEE", "MANAGER")
                          .build();
        UserDetails susan = User.builder()
                          .username("susan")
                          .password("{noop}test123")
                          .roles("EMPLOYEE", "MANAGER", "ADMIN")
                          .build();
        
        return new InMemoryUserDetailsManager(john, mary, susan);
    }

    //restricting access (end-points) based on roles
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
            configurer
                .requestMatchers(HttpMethod.GET, "magic-api/members").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "magic-api/members/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "magic-api/members").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "magic-api/members/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "magic-api/members/**").hasRole("ADMIN")
        );

        //tell spring security that we are using basic auth
        http.httpBasic(Customizer.withDefaults());

        //diisable csrf, coz we are using stateless http instead of html form
        http.csrf(csrf -> csrf.disable());
        
        return http.build();
    }
}
