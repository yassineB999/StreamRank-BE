package com.streamrank.application.favoritemovie;

import com.streamrank.domain.favoritemovie.service.FavoriteMovieDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoriteMovieServiceImpl implements FavoriteMovieService {
    private final FavoriteMovieDomainService favoriteMovieDomainService;
}
