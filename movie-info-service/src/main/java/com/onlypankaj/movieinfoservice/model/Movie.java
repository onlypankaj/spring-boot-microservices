package com.onlypankaj.movieinfoservice.model;

public class Movie {

    private String movieId;
    private String movieTitle;
    private String description;

    public Movie(String movieId, String movieTitle, String description) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.description = description;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
