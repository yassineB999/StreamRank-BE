package com.streamrank.adapter.web.UserActions;

import com.streamrank.adapter.wrapper.ResponseWrapper;
import com.streamrank.application.favoritemovie.FavoriteMovieService;
import com.streamrank.application.user.UserService;
import com.streamrank.infrastructure.api.StreamRankService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
