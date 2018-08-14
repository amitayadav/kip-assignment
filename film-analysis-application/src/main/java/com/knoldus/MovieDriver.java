package com.knoldus;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MovieDriver {

    public static void main(String[] args) {

        Movie movie1 = new Movie(1, "Night in dark", "12-09-2018", "2018", 9, "Harry", "Mathew");
        Movie movie2 = new Movie(2, "Night in Sun", "12-09-2017", "2017", 9, "Brad Pitt", "Newton");
        Movie movie3 = new Movie(3, "Night in Bat", "12-09-2016", "2016", 5, "Justin", "Mathew");
        MovieOperation movieOperation = new MovieOperation(Arrays.asList(movie1, movie2, movie3));
        Scanner scanner=new Scanner(System.in);
        System.out.println("Menu for film analysis application:\n 1.add Movie \n 2.get Movie" +
                "\n 3.delete Movie\n 4.get list of movies\n5.get Movie with limit an offset\n" +
                "6. update Movie\n 7.filter Movie on the basis of rating\n" +
                "8.find movies per director\n9.find movies which have released date greater than passed date\n" +
                "10.find movies between years\n 11.find movies between date");
        int choice=scanner.nextInt();
        switch(choice){
            case 1:
                System.out.println("ID: ");
                int id = scanner.nextInt();
                System.out.println("Name: ");
                String name = scanner.next();
                System.out.println("release date: ");
                String date = scanner.next();
                System.out.println("release year: ");
                String year = scanner.next();
                System.out.println("rating: ");
                Integer rating = scanner.nextInt();
                System.out.println("actor: ");
                String actor = scanner.next();
                System.out.println("director: ");
                String dir = scanner.next();
                Movie movie =new Movie(id, name, date, year, rating, actor, dir);
                movieOperation.addMovie(movie);
                break;


            case 2:
                System.out.println("ID: ");
                int id1 = scanner.nextInt();
                System.out.println(movieOperation.getMovie(new Long(id1)));
                break;

            case 3:
                System.out.println("Id to be removed: ");
                int idToBeRemoved = scanner.nextInt();
                System.out.println(movieOperation.deleteMovie(new Long(idToBeRemoved)));
                break;

            case 4:
                System.out.println("ID: ");
                int id3 = scanner.nextInt();
                System.out.println("Name: ");
                String updatedName = scanner.next();
                System.out.println("release date: ");
                String updatedDate = scanner.next();
                System.out.println("release year: ");
                String updatedYear = scanner.next();
                System.out.println("rating: ");
                Integer updatedRating = scanner.nextInt();
                System.out.println("actor: ");
                String updatedActor = scanner.next();
                System.out.println("director: ");
                String updatedDir= scanner.next();
                movieOperation.updateMovie(id3, updatedName, updatedDate, updatedYear, updatedRating, updatedActor, updatedDir);
                break;

            case 5:
                System.out.println("Enter offset: ");
                int offset =scanner.nextInt();
                System.out.println("Enter limit: ");
                int limit = scanner.nextInt();
                System.out.println(movieOperation.getMovies(offset, limit).join());
                break;

            case 6:
                System.out.println("Enter director: ");
                String dir2 = scanner.next();
                System.out.println(movieOperation.filterMovie(dir2).join());
                break;

            case 7:
                System.out.println(movieOperation.moviesPerDirector().join());
                break;

            case 8:
                System.out.println("Enter date: ");
                String givenDate = scanner.next();
                System.out.println(movieOperation.filterMovie(givenDate).join());
                break;

            case 9:
                System.out.println("Enter start date: ");
                String startDate = scanner.next();
                System.out.println("Enter end date: ");
                String endDate = scanner.next();
                System.out.println(movieOperation.getListOfMoviesBetweenDates(startDate, endDate).join());
                break;

            case 10:
                System.out.println("Enter start year: ");
                String startYear = scanner.next();
                System.out.println("Enter end year: ");
                String endYear = scanner.next();
                System.out.println(movieOperation.getListOfMoviesBetweenYears(startYear, endYear).join());
                break;

            case 11:
                System.out.println(movieOperation.getMovies().join());
                break;

            default:
                System.out.println("invalid option");
                break;
        }
    }
}

