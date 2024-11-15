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
    @Value("${spring.application.apiUrl}")
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
}
