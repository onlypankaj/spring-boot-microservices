package com.onlypankaj.moviecatalogservice.controller;

import com.onlypankaj.moviecatalogservice.model.CatalogItem;
import com.onlypankaj.moviecatalogservice.model.MovieResponse;
import com.onlypankaj.moviecatalogservice.model.RatingResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        // Based on Userid call Movie info service to find list of Movie Ids
        RestTemplate movieRestTemplate = new RestTemplate();
//        RestTemplate restTemplate = new RestTemplate();
//        MovieResponse movieResponse = restTemplate.getForObject("http://localhost:8082/resource/12", MovieResponse.class);

        // For Each Movie Id call rating data service to get rating for each
        List<RatingResponse> ratingResponses = Arrays.asList(
                new RatingResponse("1234", 4),
                new RatingResponse("5678", 3)
        );

        //Put them all together
        return ratingResponses.stream().map(rating -> {

            MovieResponse movieResponse = movieRestTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), MovieResponse.class);
            return new CatalogItem(movieResponse.getMovieTitle(), "trans", rating.getRating());
        })
                .collect(Collectors.toList());

//        return Collections.singletonList(
//                new CatalogItem("Transformer", "trans", 3)
//        );

    }
}
