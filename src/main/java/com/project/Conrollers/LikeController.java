package com.project.Conrollers;

import com.project.Entities.Questions.Like;
import com.project.Services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getAllLikes() {
        return likeService.getAllLikes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Like> getLikeById(@PathVariable Long id) {
        return ResponseEntity.ok(likeService.getLikeById(id));
    }

    @PostMapping
    public ResponseEntity<Like> createLike(@RequestBody Like like) {
        return ResponseEntity.status(HttpStatus.CREATED).body(likeService.createLike(like));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("react/{id}")
    public ResponseEntity<Void> getAllLikes(@PathVariable Long id) {
        likeService.getAllLikesByUser(id);
        return ResponseEntity.noContent().build();
    }

}