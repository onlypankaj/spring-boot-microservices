package com.onlypankaj.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.onlypankaj.moviecatalogservice.model.CatalogItem;
import com.onlypankaj.moviecatalogservice.model.MovieResponse;
import com.onlypankaj.moviecatalogservice.model.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallBackCatalogItem")
    public CatalogItem getCatalogItem(RatingResponse rating) {
        // For Each Movie Id call movie info service and get details
        MovieResponse movieResponse = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), MovieResponse.class);
        //Put them all together
        return new CatalogItem(movieResponse.getMovieTitle(), movieResponse.getDescription(), rating.getRating());
    }

    public CatalogItem getFallBackCatalogItem(RatingResponse rating) {
        return new CatalogItem("Movie info is down", "", rating.getRating());
    }

}
