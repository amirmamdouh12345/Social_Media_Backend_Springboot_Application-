package com.project.Entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {

    @Id
    Long authority_id;

    @Column
    String name;


    @ManyToMany(mappedBy = "authorities")
    List<Role> roles;

    @Override
    public String getAuthority() {
        return name;
    }
}
