package com.onlypankaj.moviecatalogservice.model;

public class CatalogItem {
    private String movieTitle;
    private String desc;
    private int rating;

    public CatalogItem(String movieTitle, String desc, int rating) {
        this.movieTitle = movieTitle;
        this.desc = desc;
        this.rating = rating;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}