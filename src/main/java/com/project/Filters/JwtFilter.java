package com.project.Filters;

import com.project.Entities.User;
import com.project.Services.JwtService;
import com.project.Services.UserAuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserAuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("filter");


        String path =request.getRequestURL().toString().split("/",4)[3];

        System.out.println("path: "+path);

        if(path.startsWith("api/login") ){
            filterChain.doFilter(request,response);

            return;
        }

        HttpSession session =request.getSession();
        String token = session.getAttribute("token").toString();
        System.out.println( "token: "+token);



        if(jwtService.isExpired(token.substring(7)) || !token.startsWith("Bearer ")){
            response.setStatus(400);

        }
        else{

            token =token.substring(7);

            String username =jwtService.getUsernameFromToken(token);

            UserDetails user = authenticationService.loadUserByUsername(username);
            System.out.println("eh aldnia");
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


            System.out.println("T3ALA YA HABIB A5oukkk");
            filterChain.doFilter(request,response);
        }

    }
}
