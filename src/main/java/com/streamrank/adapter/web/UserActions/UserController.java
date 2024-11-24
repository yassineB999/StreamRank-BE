package com.streamrank.adapter.web.UserActions;

import com.streamrank.adapter.wrapper.ResponseWrapper;
import com.streamrank.application.favoritemovie.FavoriteMovieService;
import com.streamrank.application.favoritemovie.record.request.FavoriteMovieDTO;
import com.streamrank.application.user.UserService;
import com.streamrank.infrastructure.api.StreamRankService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final FavoriteMovieService favoriteMovieService;

    private StreamRankService streamRankService;

    @GetMapping(value = "/getMovies") @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getListMovies(Principal principal) {
        if (principal == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseWrapper.error("User is not authenticated"));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(streamRankService.listMovies()));
    }

    @GetMapping(value = "/getNextMovies") @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getNextListMovies(Principal principal, @PathParam("pageNumber") int pageNumber) {
        if (principal == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseWrapper.error("User is not authenticated"));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(streamRankService.listNextMovies(pageNumber)));
    }

    @GetMapping(value = "/getSuggetionMovies") @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getListSuggetionMovies(Principal principal, @PathParam("movieId") int movieId) {
        if (principal == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseWrapper.error("User is not authenticated"));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(streamRankService.listSuggestionMovies(movieId)));
    }

    @GetMapping(value = "/getDetailMovie") @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getDetailMovie(Principal principal, @PathParam("movieId") int movieId) {
        if (principal == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseWrapper.error("User is not authenticated"));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(streamRankService.listDetailMovie(movieId)));
    }

    @GetMapping(value = "/getFavoriteMovies") @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getFavoriteMovies(Principal principal) {
        if (principal == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseWrapper.error("User is not authenticated"));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(favoriteMovieService.findFavoriteMovie(principal.getName())));
    }

    @GetMapping(value = "/searchMovies") @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> searchMovies(@RequestParam String queryTerm, Principal principal) {
        if (principal == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseWrapper.error("User is not authenticated"));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(streamRankService.searchMovies(queryTerm)));
    }

    @PostMapping(value = "/saveFavoriteMovie") @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getFavoriteMovies(Principal principal, @RequestBody FavoriteMovieDTO favoriteMovieDTO){
        if (principal == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseWrapper.error("User is not authenticated"));

        Object data = favoriteMovieService.saveFavoriteMovie(principal.getName(), favoriteMovieDTO);
        if(data instanceof String) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseWrapper.error((String) data));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(data));
    }

    @GetMapping(value = "/getMyInfo") @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getMyInfo(Principal principal) {
        if (principal == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseWrapper.error("User is not authenticated"));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(userService.getMyInfoByUserName(principal.getName())));
    }
}
