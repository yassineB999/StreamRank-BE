package com.streamrank.application.favoritemovie;

import com.streamrank.application.favoritemovie.record.request.FavoriteMovieDTO;

public interface FavoriteMovieService {
    Object findFavoriteMovie(String userName);
    Object saveFavoriteMovie(String userName, FavoriteMovieDTO favoriteMovieDTO);
}
