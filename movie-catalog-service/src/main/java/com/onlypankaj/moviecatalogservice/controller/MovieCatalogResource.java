package com.onlypankaj.moviecatalogservice.controller;

import com.onlypankaj.moviecatalogservice.model.CatalogItem;
import com.onlypankaj.moviecatalogservice.model.MovieResponse;
import com.onlypankaj.moviecatalogservice.model.UserRatingResponse;
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

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        // Based on Userid call Rating data service to find list of Movie Ids and Ratings
        UserRatingResponse ratingsResponses = restTemplate.getForObject(
                "http://localhost:8083/ratingsdata/users/"+ userId, UserRatingResponse.class);

        return ratingsResponses.getUserRating().stream().map(rating -> {
            // For Each Movie Id call movie info service and get details
            MovieResponse movieResponse = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), MovieResponse.class);
            //Put them all together
            return new CatalogItem(movieResponse.getMovieTitle(), "trans", rating.getRating());
        })
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
