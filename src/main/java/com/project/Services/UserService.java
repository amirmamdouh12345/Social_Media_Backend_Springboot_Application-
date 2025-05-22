package com.project.Services;


import com.project.Entities.*;
import com.project.Entities.Questions.Comment;
import com.project.Entities.Questions.Like;
import com.project.Entities.Questions.Question;
import com.project.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {



    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private LikeService likeService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RoleService roleService;


    public List<User> getAllUsers() {
        return UserRepository.findAll();
    }

    public User getUserById(Long id) {
        return UserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User createUser(User user) {
        user.setRole(roleService.getRoleByID(3L));    // by default all take User Auth
        PasswordEncoder x = new BCryptPasswordEncoder();
        user.setPassword(x.encode(user.getPassword()));

        return UserRepository.save(user);
    }

    public User updateUser(Long id,User new_user) {
        User user = getUserById(id);
        user.setName(new_user.getUsername());

        PasswordEncoder x = new BCryptPasswordEncoder();

        user.setPassword(x.encode(new_user.getPassword()));
        return UserRepository.save(user);
    }

    public String updateRole(String username , Long role_id){
        User user =UserRepository.findByUsername(username);
        Role role =roleService.getRoleByID(role_id);
        user.setRole(role);
        return "User "+username+" became "+role.getName()+".";
    }

    public void deleteUser(Long id) {
        UserRepository.deleteById(id);
    }



    public String createLike(Long questionId ) {

        User user = getLoggedInUser();

        Optional<Like> isLike = likeService.isLikePresentByUserId(user.getUserId(),questionId);

        if(isLike.isPresent()){
            likeService.deleteLike(isLike.get().getLikeId());
            return "Like is removed";
        }
        else{
            Like new_like= new Like();
            new_like.setUser(user);
            new_like.setQuestion(questionService.getQuestionById(questionId));
            likeService.createLike(new_like);
            return "Like is inserted..";
        }
    }


    public String createComment( Long questionId , Comment comment) {
        User user = getLoggedInUser();

        comment.setUser(user);
        comment.setQuestion(questionService.getQuestionById(questionId));
        commentService.createComment(comment);
        return "comment is created";
    }


    public void createQuestion( Question question ) {
        User user = getLoggedInUser();
        question.setUser(user);
        questionService.createQuestion(question);
    }

    public User getLoggedInUser(){
        Authentication x = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String username =  (String) x.getPrincipal();
        System.out.println(username);

        return UserRepository.findByUsername(username);
    }


}
