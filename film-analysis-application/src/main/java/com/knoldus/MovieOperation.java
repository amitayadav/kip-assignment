package com.knoldus;

import java.time.Year;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class MovieOperation {

    private CompletableFuture<List<Movie>> movieList;
    public CompletableFuture<List<Movie>>  addMovie(Movie movie){
        movieList..add(movie);
        return movieList;
    }

    public CompletableFuture<Movie>  getMovie(Integer id){
        m


    }

    public CompletableFuture<Movie>  deleteMovie(Movie movie){

        if(movieList.contains(movie))
            movieList.remove(movieList.indexOf(movie));
        return movieList;

    }

    public CompletableFuture<Movie>  updateMovie(){
        movieList.

    }

    public CompletableFuture<List<Movie>>  getMovies(){

    }

    public CompletableFuture<List<Movie>> getMovies(int offset, int limit){

    }

    public CompletableFuture<Movie> filterMovie(Integer rating,String director){

    }

    public CompletableFuture<Map<String,String>> moviesPerDirector(){

    }

    public CompletableFuture<List<Movie>> filterMovie(Date date){
        movieList.stream().

    }

    public CompletableFuture<List<Movie>> getListOfMoviesBetweenYears(Year startYear, Year endYear){

    }

    public CompletableFuture<List<Movie>> getListOfMoviesBetweenDates(Date startDate, Date endDate){

    }












}
