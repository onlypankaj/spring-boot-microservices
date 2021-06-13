package com.onlypankaj.moviecatalogservice.model;

public class MovieResponse {
    private String movieId;
    private String movieTitle;
    private String description;

    public MovieResponse() {
    }

    public MovieResponse(String movieId, String movieTitle, String description) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
}
