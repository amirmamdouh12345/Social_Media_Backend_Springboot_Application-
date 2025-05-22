package com.project.Repos;

import com.project.Entities.Questions.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM NEW_DATABASE.Comment  " +
            "INNER JOIN NEW_DATABASE.USERS" +
            "ON Comment.USERS_ID = USERS.USERS_ID " +
            "WHERE Comment.USERS_ID =:userId ",nativeQuery = true)
    List<Comment> findByUserId(@Param("userId") Long userId);
}
