package com.streamrank.domain.favoritemovie.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.streamrank.domain.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "favorites_movies")
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

    @ElementCollection(fetch = FetchType.EAGER) @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<String> movieGenre;

    String movieRating;

    String imageCover;

    @ElementCollection(fetch = FetchType.EAGER) @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<String> availableDownloadLinks;

    @ManyToOne @JsonBackReference
    User user;
}