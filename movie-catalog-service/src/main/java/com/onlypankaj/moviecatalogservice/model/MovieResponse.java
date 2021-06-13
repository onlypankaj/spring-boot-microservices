package com.onlypankaj.moviecatalogservice.model;

public class MovieResponse {
    private String movieId;
    private String movieTitle;

    public MovieResponse() {
    }

    public MovieResponse(String movieId, String movieTitle) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
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
