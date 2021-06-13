package com.onlypankaj.moviecatalogservice.model;

import java.util.List;

public class UserRatingResponse {
    private String userId;
    private List<RatingResponse> userRating;

    public UserRatingResponse() {
    }

    public UserRatingResponse(List<RatingResponse> userRating) {
        this.userRating = userRating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<RatingResponse> getUserRating() {
        return userRating;
    }

    public void setUserRating(List<RatingResponse> userRating) {
        this.userRating = userRating;
    }
}
