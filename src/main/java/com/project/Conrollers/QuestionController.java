package com.project.Conrollers;


import com.project.Entities.Questions.Question;
import com.project.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService QuestionService) {
        this.questionService = QuestionService;
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question Question) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.createQuestion(Question));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("react/{id}")
    public ResponseEntity<List<Question>> getAllQuestions(@PathVariable Long id) {

        return ResponseEntity.ok( questionService.getAllQuestionsByUser(id));
    }





}
