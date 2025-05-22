package com.project.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.Entities.Questions.Comment;
import com.project.Entities.Questions.Like;
import com.project.Entities.Questions.Question;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userId;

    @Column
    String username;

    @Column
    String password;

    @Column
    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-questions")
    List<Question> questions;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-likes")
    List<Like> likes;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-comments")
    List<Comment> comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId",referencedColumnName = "role_id")
    Role role;


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.authorities;
    }



    public void setName(String name) {
        this.username = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
