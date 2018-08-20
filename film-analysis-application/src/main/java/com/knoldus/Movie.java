package com.knoldus;

public class Movie {
    private Long id;

    private String movieName;

    private String releaseDate;

    private String releaseYear;

    private Integer rating; // Out of 10

    private String actor;

    private String director;


    public Movie(Long id, String movieName, String releaseDate, String releaseYear, Integer rating, String actor, String director) {
        this.id = new Long(id);
        this.movieName = movieName;
        this.releaseDate = releaseDate;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.actor = actor;
        this.director = director;
    }

    public Long getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
    //{
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "movie Id:\t" + id + "\tmovie name: " + movieName + "\trelease date & year: " + releaseDate + "\t" + releaseYear
                + "\trating: " + rating + "\tactor: " + actor + "\t director: " + director;
    }
}

