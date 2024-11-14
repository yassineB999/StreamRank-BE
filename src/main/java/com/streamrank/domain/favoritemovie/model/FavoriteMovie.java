package com.streamrank.domain.favoritemovie.model;

import com.streamrank.domain.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "favoritesmovies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idFavoriteMovie;
    Long idMovie;
    String movieTitle;
    String movieReleaseDate;

    String movieCategory;

    @ElementCollection
    List<String> movieGenre;

    String movieRating;

    String imageCover;

    @ElementCollection
    List<String> availableDownloadLinks;

    @ManyToOne
    User user;
}