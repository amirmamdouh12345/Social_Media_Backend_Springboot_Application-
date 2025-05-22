package com.project.Repos;

import com.project.Entities.Questions.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query(value = "SELECT * FROM NEW_DATABASE.LIKES  " +
            "INNER JOIN NEW_DATABASE.USERS" +
            "ON LIKES.USERS_ID = USERS.USERS_ID " +
            "WHERE LIKES.USERS_ID =:userId ",nativeQuery = true)
    List<Like> findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM NEW_DATABASE.LIKES  " +
            "WHERE USER_ID =:userId AND QUESTION_ID =:questionId",nativeQuery = true)
    Optional<Like> isLikePresentByUserId(@Param("userId") Long userId , @Param("questionId") Long questionId );


}
