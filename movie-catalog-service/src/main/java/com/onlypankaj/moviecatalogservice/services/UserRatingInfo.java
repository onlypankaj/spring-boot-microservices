package com.onlypankaj.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.onlypankaj.moviecatalogservice.model.RatingResponse;
import com.onlypankaj.moviecatalogservice.model.UserRatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallBackUserRating")
    public UserRatingResponse getUserRating(String userId) {
        return restTemplate.getForObject(
                "http://rating-data-service/ratingsdata/users/" + userId, UserRatingResponse.class);
    }

    public UserRatingResponse getFallBackUserRating(String userId) {
        UserRatingResponse userRatingResponse = new UserRatingResponse();
        userRatingResponse.setUserId(userId);
        userRatingResponse.setUserRating(Arrays.asList(
                new RatingResponse("0", 0)
        ));
        return userRatingResponse;
    }
}
