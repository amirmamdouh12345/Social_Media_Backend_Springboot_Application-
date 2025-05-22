package com.project.Entities.Questions;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.Entities.User;
import jakarta.persistence.*;

@Entity
@Table(name = "comments")

public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long commentId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    @JsonBackReference("user-comments")
    User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "questionId",referencedColumnName = "questionId")
    @JsonBackReference("question-comments")
    Question question;

    @Column
    String body;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}
