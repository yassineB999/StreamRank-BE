package com.streamrank.domain.favoritemovie.service;

import com.streamrank.domain.favoritemovie.model.FavoriteMovie;
import com.streamrank.domain.user.model.User;
import com.streamrank.infrastructure.persistence.JpaFavoriteMovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FavoriteMovieDomainService {
    private JpaFavoriteMovieRepository jpaFavoriteMovieRepository;

    // Fetch all attributes
    public List<FavoriteMovie> findAll() {
        return jpaFavoriteMovieRepository.findAll();
    }

    // Fetch by ID
    public FavoriteMovie findById(Long id) {
        return jpaFavoriteMovieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attribute not found"));
    }

    // Save attribute
    public FavoriteMovie save(FavoriteMovie favoriteMovie) {
        return jpaFavoriteMovieRepository.save(favoriteMovie);
    }

    public void delete(FavoriteMovie favoriteMovie) {
        jpaFavoriteMovieRepository.delete(favoriteMovie);
    }

    // Delete attribute
    public void delete(Long id) {
        jpaFavoriteMovieRepository.deleteById(id);
    }

    public List<FavoriteMovie> findAllByUser_IdUser(Long user_id){
        return jpaFavoriteMovieRepository.findAllByUser_IdUser(user_id);
    }
}