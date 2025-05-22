package com.project.DTOS;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDTO {
    String username;

    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

