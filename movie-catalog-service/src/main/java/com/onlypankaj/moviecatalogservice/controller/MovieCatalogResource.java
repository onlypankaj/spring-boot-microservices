package com.onlypankaj.moviecatalogservice.controller;

import com.onlypankaj.moviecatalogservice.model.CatalogItem;
import com.onlypankaj.moviecatalogservice.model.UserRatingResponse;
import com.onlypankaj.moviecatalogservice.services.MovieInfo;
import com.onlypankaj.moviecatalogservice.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private UserRatingInfo userRatingInfo;

    @Autowired
    private MovieInfo movieInfo;


    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        // Based on Userid call Rating data service to find list of Movie Ids and Ratings
        UserRatingResponse ratingsResponses = userRatingInfo.getUserRating(userId);

        return ratingsResponses.getUserRating().stream()
                .map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());
/*
            MovieResponse movieResponse = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(MovieResponse.class)
                    .block();
*/
//        return Collections.singletonList(
//                new CatalogItem("Transformer", "trans", 3)
//        );

    }




}
