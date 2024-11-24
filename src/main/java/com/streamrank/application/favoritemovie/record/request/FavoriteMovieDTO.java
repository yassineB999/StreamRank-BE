package com.streamrank.application.favoritemovie.record.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record FavoriteMovieDTO(
        Long idMovie,
        String movieTitle,
        String movieReleaseDate,
        String movieCategory,
        List<String> movieGenre,
        String movieRating,
        String imageCover,
        List<String> availableDownloadLinks
) {
}
