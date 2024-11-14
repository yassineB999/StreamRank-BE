package com.streamrank.infrastructure.persistence;

import com.streamrank.domain.favoritemovie.model.FavoriteMovie;
import com.streamrank.domain.favoritemovie.repository.FavoriteMovieRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFavoriteMovieRepository extends JpaRepository<FavoriteMovie,Long>, FavoriteMovieRepository {
}


