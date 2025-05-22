package com.project.Services;

import com.project.Entities.Questions.Like;
import com.project.Repos.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    public Like getLikeById(Long id) {
        return likeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Like not found"));
    }

    public Like createLike(Like like) {
        return likeRepository.save(like);
    }



    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }


    public List<Like> getAllLikesByUser(Long userID) {
        return likeRepository.findByUserId(userID);
    }


    public Optional<Like> isLikePresentByUserId(Long userID , Long questionId ) {
        Optional<Like> like= likeRepository.isLikePresentByUserId(userID,questionId);
        return likeRepository.isLikePresentByUserId(userID,questionId);
    }


}
