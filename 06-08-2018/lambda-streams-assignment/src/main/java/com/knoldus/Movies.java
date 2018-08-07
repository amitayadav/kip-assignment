package com.knoldus;

public class Movies {

    private String movieName;
    private int releaseYear;
    private double rating;
    private String genre;

    public Movies(String name,int releaseYear,double rating,String genre)
    {
        this.movieName=name;
        this.releaseYear=releaseYear;
        this.rating=rating;
        this.genre=genre;
    }

    public Integer getReleaseYear(){
        return this.releaseYear;
    }

    public Double getRating(){
        return this.rating;
    }

    public String getMovieGenre(){
        return this.genre;
    }

    public String getMovieName(){
        return this.movieName;
    }

}

