package com.project.Repos;

import com.project.Entities.Questions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT Q.QUESTION_ID, Q.BODY , Q.USER_ID  " +
            " FROM NEW_DATABASE.QUESTIONS Q " +
            "INNER JOIN NEW_DATABASE.USERS U " +
            "ON Q.USER_ID = U.USER_ID " +
            "WHERE Q.USER_ID =:userId ",nativeQuery = true)
    List<Question> findByUserId(@Param("userId") Long userId);
}
