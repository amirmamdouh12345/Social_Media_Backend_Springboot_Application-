package com.project.Services;

import com.project.Entities.Questions.Comment;
import com.project.Repos.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository CommentRepository) {
        this.commentRepository = CommentRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    public Comment createComment(Comment Comment) {
        return commentRepository.save(Comment);
    }

    public Comment updateComment(Long id, String body) {
        Comment comment = getCommentById(id);
        comment.setBody(body);
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public List<Comment> getAllCommentsByUser(Long userID) {
        return commentRepository.findByUserId(userID);
    }

}
