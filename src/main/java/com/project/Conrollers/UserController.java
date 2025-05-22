package com.project.Conrollers;

import com.project.Entities.Questions.Comment;

import com.project.Entities.Questions.Question;
import com.project.Entities.User;
import com.project.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return  userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PreAuthorize("hasRole('CRITICAL')")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User User) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(User));
    }

    @PreAuthorize("hasRole('CRITICAL')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('CRITICAL')")
    @GetMapping("updateRole/{username}/{role_id}")
    public ResponseEntity<String> updateRole(@PathVariable String username , @PathVariable Long role_id) {

        return ResponseEntity.ok(userService.updateRole(username,role_id));
    }


    @PreAuthorize("hasRole('READ')")
    @PostMapping("/question")
    public ResponseEntity<Void> createQuestion( @RequestBody Question question) {
        userService.createQuestion(question);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('READ')")
    @GetMapping("/like/{questionId}")
    public ResponseEntity<String> createLike(@PathVariable Long questionId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createLike(questionId));
    }


    @PreAuthorize("hasRole('READ')")
    @PostMapping("/comment/{questionId}")
    public ResponseEntity<String> createComment(@PathVariable Long userId ,@PathVariable Long questionId ,@RequestBody Comment comment ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createComment(questionId , comment));
    }

}