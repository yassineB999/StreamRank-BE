package com.streamrank.domain.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.streamrank.domain.favoritemovie.model.FavoriteMovie;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @Column(unique = true)
    private String userName;
    @JsonIgnore
    private String password;
    private String accountType;
    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    @JsonIgnore
    private boolean isAdmin = false;

    @Override @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>() {};
        authorities.add( (isAdmin) ? new SimpleGrantedAuthority("ROLE_ADMIN") : new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<FavoriteMovie> favoriteMovies;


    @Override @JsonIgnore
    public String getUsername() {
        return userName;
    }
}
