package com.project.Entities.Questions;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.Entities.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long questionId;

    @ManyToOne(cascade = CascadeType.ALL ,targetEntity = User.class )
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @JsonBackReference("user-questions")
    User user;

    @Column
    String body;

    @Column
    @OneToMany(mappedBy = "question")
    @JsonManagedReference("question-likes")
    List<Like> likes;

    @OneToMany(mappedBy = "question")
    @JsonManagedReference("question-comments")
    List<Comment> comments;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }


}
