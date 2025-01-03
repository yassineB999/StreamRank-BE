package com.streamrank.infrastructure.api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StreamRankService {
    @Value("${spring.application.moviesApiUrl}")
    private String apiBaseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public Object listMovies(){
        String url = apiBaseUrl + "/list_movies.json";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String responseBody = response.getBody();

        JSONObject jsonResponse = new JSONObject(responseBody);

        JSONObject dataObject = jsonResponse.getJSONObject("data");

        JSONArray moviesArray = dataObject.getJSONArray("movies");

        return moviesArray.toList();
    }

    public Object listNextMovies(Integer pageNumber){
        String url = apiBaseUrl + "/list_movies.json?page=" + pageNumber;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String responseBody = response.getBody();

        JSONObject jsonResponse = new JSONObject(responseBody);

        JSONObject dataObject = jsonResponse.getJSONObject("data");

        JSONArray moviesArray = dataObject.getJSONArray("movies");

        return moviesArray.toList();
    }

    public Object listSuggestionMovies(Integer movieId) {
        String url = apiBaseUrl + "/movie_suggestions.json?movie_id=" + movieId;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String responseBody = response.getBody();

        JSONObject jsonResponse = new JSONObject(responseBody);

        JSONObject dataObject = jsonResponse.getJSONObject("data");

        JSONArray moviesArray = dataObject.getJSONArray("movies");

        return moviesArray.toList();
    }

    public Object listDetailMovie(Integer movieId) {
        String url = apiBaseUrl + "/movie_details.json?movie_id=" + movieId;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String responseBody = response.getBody();

        JSONObject jsonResponse = new JSONObject(responseBody);

        JSONObject dataObject = jsonResponse.getJSONObject("data");

        JSONObject movieObject = dataObject.getJSONObject("movie");

        return movieObject.toMap();
    }

    public Object searchMovies(String queryTerm) {
        String url = apiBaseUrl + "/list_movies.json?query_term=" + queryTerm;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Check if the response is successful
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            JSONObject jsonResponse = new JSONObject(responseBody);

            // Check if the "data" object exists in the response
            if (jsonResponse.has("data")) {
                JSONObject dataObject = jsonResponse.getJSONObject("data");
                JSONArray moviesArray = dataObject.getJSONArray("movies");
                return moviesArray.toList(); // Convert to List and return
            } else {
                throw new RuntimeException("No data found in response");
            }
        } else {
            throw new RuntimeException("Failed to fetch movies: " + response.getStatusCode());
        }
    }
}
