package com.streamrank.domain.favoritemovie.repository;

import com.streamrank.domain.favoritemovie.model.FavoriteMovie;

import java.util.List;

public interface FavoriteMovieRepository {
    List<FavoriteMovie> findAllByUser_IdUser(Long user_id);
}
