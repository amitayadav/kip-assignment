package com.knoldus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class MovieAnalysis{

    public void EvenNumberRating(List<Movies> list){
        List movieList = list.stream().filter(movieName->movieName.getRating()%2==0)
                .map(Movies::getMovieName)
                .collect(Collectors.toList());
        System.out.println( movieList);
    }
    public void ratingEquals7(List<Movies> list){
        List movieList = list.stream()
                .filter((movieName)->movieName.getRating()==7 &&  movieName.getMovieGenre().equals("Sci-Fi"))
                .map(Movies::getMovieName)
                .collect(Collectors.toList());
        System.out.println( movieList);
    }
    public void ratingGreaterthan8(List<Movies> list){
        List movieList = list.stream()
                .filter(movieName->movieName.getRating()>8 && movieName.getMovieGenre().equals("Comedy"))
                .map(Movies::getMovieName)
                .collect(Collectors.toList());
        System.out.println( movieList);
    }
    public void ratinglessthan8(List<Movies> list) {
        List movieList = list.stream()
                .filter(movieName -> movieName.getRating() < 8 && movieName.getReleaseYear() > 2000)
                .map(Movies::getMovieName)
                .collect(Collectors.toList());
        System.out.println( movieList);
    }

    public static void main(String[] args) {
        Movies movie1 = new Movies("Phir Hera Pheri",2014,9,"Comedy");
        Movies movie2 = new Movies("DDLJ",1999,8,"Romance");
        Movies movie3 = new Movies("Sonu ki twitty ki Sweety",2018,7,"Comedy");
        Movies movie4 = new Movies("Life",2017,7,"Sci-Fi");
        Movies movie5 = new Movies("Hate Story",2015,6,"Suspense");
        List<Movies> list= Arrays.asList(movie1,movie2,movie3,movie4,movie5);
        MovieAnalysis testMovie= new MovieAnalysis();
        testMovie.ratingEquals7(list);
        testMovie.EvenNumberRating(list);
        testMovie.ratinglessthan8(list);
        testMovie.ratingGreaterthan8(list);

    }
}
