package com.knoldus;


import java.util.Arrays;
import java.util.Scanner;

public class MovieDriver {

    public static void main(String[] args) {

        Movie movie1 = new Movie(new Long(1), "Night in dark", "12-09-2018", "2018", 9, "Harry", "Mathew");
        Movie movie2 = new Movie(new Long(2), "Night in Sun", "12-09-2017", "2017", 9, "Brad Pitt", "Newton");
        Movie movie3 = new Movie(new Long(3), "Night in Bat", "12-09-2016", "2016", 5, "Justin", "Mathew");
        MovieOperation movieOperation = new MovieOperation(Arrays.asList(movie1, movie2, movie3));
        Scanner scanner = new Scanner(System.in);

        System.out.println("--Film Analysis Application-----------\n ");
        System.out.println("1. Add movie \n 2. Get Movie \n 3. Delete Movie \n 4. Update Movie" +
                "5. get movies \n 6. Movies with limit & offset \n" +
                " 7.filter movie based on director & rating\n" +
                "8. movies per director \n 9. get  movies after given date" +
                "10. get movies in between Years \n 11. get movies in between dates\n\n");
        String continuation="Y";

        while(continuation.equals("Y" )) {
            System.out.println("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("ID: ");
                    Long id = scanner.nextLong();
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
                    String director = scanner.next();
                    movieOperation.addMovie(new Movie(id, name, date, year, rating, actor, director));
                    break;

                case 2:
                    System.out.println("Enter movie id: ");
                    Long movieId = scanner.nextLong();
                    System.out.println(movieOperation.getMovie(movieId));
                    break;

                case 3:
                    System.out.println("Enter movie id you want to delete: ");
                    Long deleteMovieId = scanner.nextLong();
                    System.out.println(movieOperation.deleteMovie(deleteMovieId));
                    break;

                case 4:
                    System.out.println("ID: ");
                    Long updateId = scanner.nextLong();
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
                    String updatedDirector = scanner.next();
                    movieOperation.updateMovie(updateId, updatedName, updatedDate, updatedYear, updatedRating, updatedActor, updatedDirector);
                    break;

                case 5:
                    System.out.println(movieOperation.getMovies().join());
                    break;

                case 6:
                    System.out.println("Enter Offset Value: ");
                    int offset = scanner.nextInt();
                    System.out.println("Enter Limit Value: ");
                    int limit = scanner.nextInt();
                    System.out.println(movieOperation.getMoviesWithLimit(offset, limit).join());
                    break;

                case 7:
                    System.out.println("Enter director: ");
                    String directorName = scanner.next();
                    System.out.println(movieOperation.filterMovieWithDirector(directorName).join());
                    break;

                case 8:
                    System.out.println(movieOperation.moviesPerDirector().join());
                    break;

                case 9:

                    System.out.println("Enter date: ");
                    String givenDate = scanner.next();
                    System.out.println(movieOperation.getMoviesAfterGivenDate(givenDate).join());
                    break;

                case 10:
                    System.out.println("Enter start year: ");
                    String startYear = scanner.next();
                    System.out.println("Enter end year: ");
                    String endYear = scanner.next();
                    System.out.println(movieOperation.getListOfMoviesBetweenYears(startYear, endYear).join());
                    break;

                case 11:
                    System.out.println("Enter start date: ");
                    String startDate = scanner.next();
                    System.out.println("Enter end date: ");
                    String endDate = scanner.next();
                    System.out.println(movieOperation.getListOfMoviesBetweenDates(startDate, endDate).join());
                    break;

                default:
                    System.out.println("invalid operation");
            }
            System.out.println("do u want to continue:Y/N");
            continuation = scanner.nextLine();

        }


    }
}


