package com.project.Entities.Questions;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.Entities.User;
import jakarta.persistence.*;

@Entity
@Table(name = "likes")

public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long likeId;

    @ManyToOne(fetch = FetchType.LAZY ,targetEntity = User.class , cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    @JsonBackReference("user-likes")
    User user;

    @JsonBackReference("question-likes")
    @ManyToOne(fetch = FetchType.LAZY ,targetEntity = Question.class)
    @JoinColumn(name = "questionId",referencedColumnName = "questionId")
    Question question;


    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
