package com.project.Configurations;


import com.project.Filters.JwtFilter;
import com.project.Services.UserAuthenticationService;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request)->request
                        .requestMatchers("api/login","api/login/*").permitAll()
                        .anyRequest().authenticated());
            httpSecurity.addFilterBefore( jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserAuthenticationService userAuthenticationService){
        DaoAuthenticationProvider dao =new DaoAuthenticationProvider();
        dao.setPasswordEncoder(new BCryptPasswordEncoder());
        dao.setUserDetailsService(userAuthenticationService);
        return dao;
    }

    @Bean
    public AuthenticationManager providerManager(UserAuthenticationService userAuthenticationService){
        return new ProviderManager(daoAuthenticationProvider(userAuthenticationService));
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> tenantFilterRegistration() {
        FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>(jwtFilter);
        registration.setEnabled(false);
        return registration;
    }




}
