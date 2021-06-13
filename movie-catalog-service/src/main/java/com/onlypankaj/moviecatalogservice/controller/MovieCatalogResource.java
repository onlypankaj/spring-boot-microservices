package com.onlypankaj.moviecatalogservice.controller;

import com.onlypankaj.moviecatalogservice.model.CatalogItem;
import com.onlypankaj.moviecatalogservice.model.MovieResponse;
import com.onlypankaj.moviecatalogservice.model.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
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
        // Based on Userid call Movie info service to find list of Movie Ids


        // For Each Movie Id call rating data service to get rating for each
        List<RatingResponse> ratingResponses = Arrays.asList(
                new RatingResponse("1234", 4),
                new RatingResponse("5678", 3)
        );

        //Put them all together
        return ratingResponses.stream().map(rating -> {

            MovieResponse movieResponse = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), MovieResponse.class);
/*
            MovieResponse movieResponse = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(MovieResponse.class)
                    .block();
*/
            return new CatalogItem(movieResponse.getMovieTitle(), "trans", rating.getRating());
        })
                .collect(Collectors.toList());

//        return Collections.singletonList(
//                new CatalogItem("Transformer", "trans", 3)
//        );

    }
}
