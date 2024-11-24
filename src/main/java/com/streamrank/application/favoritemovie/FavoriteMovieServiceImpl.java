package com.streamrank.application.favoritemovie;

import com.streamrank.application.favoritemovie.record.request.FavoriteMovieDTO;
import com.streamrank.domain.favoritemovie.model.FavoriteMovie;
import com.streamrank.domain.favoritemovie.service.FavoriteMovieDomainService;
import com.streamrank.domain.user.model.User;
import com.streamrank.domain.user.service.UserDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FavoriteMovieServiceImpl implements FavoriteMovieService {
    private final FavoriteMovieDomainService favoriteMovieDomainService;
    private final UserDomainService userDomainService;

    @Override
    public Object findFavoriteMovie(String userName) {
        User dbUser = userDomainService.findByUserName(userName);

        if(dbUser == null){
            return "Invalid email or username. Please try again.";
        }

        return favoriteMovieDomainService.findAllByUser_IdUser(dbUser.getIdUser());
    }

    @Override
    public Object saveFavoriteMovie(String userName, FavoriteMovieDTO favoriteMovieDTO) {
        User dbUser = userDomainService.findByUserName(userName);

        if (dbUser == null) {
            return "Invalid email or username. Please try again.";
        }

        // Check if the movie is already saved as a favorite
        List<FavoriteMovie> existingFavorites = favoriteMovieDomainService.findAllByUser_IdUser(dbUser.getIdUser());

        FavoriteMovie foundFavoriteMovie = null;

        for(FavoriteMovie favoriteMovie : existingFavorites){
            if(favoriteMovie.getIdMovie().equals(favoriteMovieDTO.idMovie())) foundFavoriteMovie = favoriteMovie;
        }

        if (foundFavoriteMovie != null) {
            // If it exists, delete it
            favoriteMovieDomainService.delete(foundFavoriteMovie);
            return "Favorite movie removed successfully.";
        } else {
            // If it does not exist, save it
            FavoriteMovie favoriteMovie = FavoriteMovie.builder()
                    .idMovie(favoriteMovieDTO.idMovie())
                    .movieTitle(favoriteMovieDTO.movieTitle())
                    .movieReleaseDate(favoriteMovieDTO.movieReleaseDate())
                    .movieCategory(favoriteMovieDTO.movieCategory())
                    .movieGenre(favoriteMovieDTO.movieGenre())
                    .movieRating(favoriteMovieDTO.movieRating())
                    .imageCover(favoriteMovieDTO.imageCover())
                    .availableDownloadLinks(favoriteMovieDTO.availableDownloadLinks())
                    .user(dbUser)
                    .build();

            return favoriteMovieDomainService.save(favoriteMovie);
        }
    }
}
