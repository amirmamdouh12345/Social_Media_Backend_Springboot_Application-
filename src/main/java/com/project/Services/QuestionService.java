package com.project.Services;

import com.project.Entities.Questions.Question;
import com.project.Repos.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository QuestionRepository;



    public List<Question> getAllQuestions() {
        return QuestionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return QuestionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public List<Question> getAllQuestionsByUser(Long userID) {
        return QuestionRepository.findByUserId(userID);
    }

    public Question createQuestion(Question Question) {
        return QuestionRepository.save(Question);
    }

    public Question updateQuestion(Long id, String body) {
        Question question = getQuestionById(id);
        question.setBody(body);
        return QuestionRepository.save(question);
    }

    public void deleteQuestion(Long id) {
        QuestionRepository.deleteById(id);
    }


}
