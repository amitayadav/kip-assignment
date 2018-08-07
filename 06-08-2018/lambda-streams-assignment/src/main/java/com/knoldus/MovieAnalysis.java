package com.knoldus;

import java.util.Arrays;
import java.util.List;
//import java.util.Scanner;
import java.util.stream.Collectors;

class MovieAnalysis{

    public void evenNumberRating(List<Movies> list){
        List movieList = list.stream().filter(movieName->movieName.getRating()%2==0)
                .map(Movies::getMovieName)
                .collect(Collectors.toList());
        System.out.println( movieList);
    }

    public void ratingEqualsSeven(List<Movies> list){
        List movieList = list.stream()
                .filter((movieName)->movieName.getRating()==7 &&  movieName.getMovieGenre().equals("Sci-Fi"))
                .map(Movies::getMovieName)
                .collect(Collectors.toList());
        System.out.println( movieList);
    }

    public void ratingGreaterThanEight(List<Movies> list){
        List movieList = list.stream()
                .filter(movieName->movieName.getRating()>8 && movieName.getMovieGenre().equals("Comedy"))
                .map(Movies::getMovieName)
                .collect(Collectors.toList());
        System.out.println( movieList);
    }

    public void ratingLessThanEight(List<Movies> list) {
        List movieList = list.stream()
                .filter(movieName -> movieName.getRating() < 8 && movieName.getReleaseYear() > 2000)
                .map(Movies::getMovieName)
                .collect(Collectors.toList());
        System.out.println( movieList);
    }

    public static void main(String[] args) {


        Movies movie1 = new Movies("hungama",2014,9,"Comedy");
        Movies movie2 = new Movies("Robot",1999,8,"Sci-Fi");
        Movies movie3 = new Movies("golmaal",2018,7,"Comedy");
        Movies movie4 = new Movies("footpath",2017,7,"Romance");
        Movies movie5 = new Movies("100 Days",2015,6,"Suspense");

        List<Movies> movieList= Arrays.asList(movie1,movie2,movie3,movie4,movie5);
        MovieAnalysis testMovie= new MovieAnalysis();

        testMovie.ratingEqualsSeven(movieList);
        testMovie.evenNumberRating(movieList);
        testMovie.ratingLessThanEight(movieList);
        testMovie.ratingGreaterThanEight(movieList);

    }
}
