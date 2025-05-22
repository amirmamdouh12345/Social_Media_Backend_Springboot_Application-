package com.project.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    Long role_id;

    @Column
    String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name= "roles_authorities",
                joinColumns = {@JoinColumn(name = "role_id ",referencedColumnName = "role_id")},
                inverseJoinColumns = {@JoinColumn(name = "authority_id ",referencedColumnName = "authority_id")})
    List<Authority> authorities;

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
