package com.project.Conrollers;


import com.project.DTOS.LoginDTO;
import com.project.Services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JwtService jwtService;

    @Autowired
    HttpServletRequest request;

    @PostMapping("login")
    public String login(@RequestBody LoginDTO loginDTO ){

        System.out.println("login");
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword());

         authentication =manager.authenticate(authentication);

        String jwt =jwtService.generateJWT(authentication.getName());

        SecurityContextHolder.getContext().setAuthentication(authentication);


        HttpSession session = request.getSession();

        session.setAttribute("token","Bearer "+jwt);

        return "Bearer "+jwt;
    }

    @GetMapping("logout")
    public String logout( ){


        SecurityContextHolder.getContext().setAuthentication(null);


        HttpSession session = request.getSession();

        session.removeAttribute("token");

        return "logged out successfully";
    }


}
