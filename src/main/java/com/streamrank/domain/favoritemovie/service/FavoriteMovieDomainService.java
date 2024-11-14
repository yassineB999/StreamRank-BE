package com.streamrank.domain.favoritemovie.service;

import com.streamrank.infrastructure.persistence.JpaFavoriteMovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoriteMovieDomainService {
    private JpaFavoriteMovieRepository jpaJpaFavoriteMovieRepository;
}