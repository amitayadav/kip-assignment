package com.knoldus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MovieOperation {

    private CompletableFuture<List<Movie>> movieList;

    MovieOperation(List<Movie> listOfMovies) {
        movieList = CompletableFuture.supplyAsync(() -> new ArrayList<Movie>(listOfMovies));
    }

    public CompletableFuture<List<Movie>> addMovie(Movie movie) {
        CompletableFuture<Movie> isExists = getMovie(movie.getId());

        this.movieList = movieList.thenCompose(list -> {
            isExists.thenApply(movieInList -> {
                if (movieInList == null) {
                    list.add(movie);
                    return list;
                } else {
                    throw new RuntimeException("This movie already exists.");
                }
            });
            return movieList;
        });
        return movieList;

    }

    public CompletableFuture<Movie> getMovie(Long movieId) {

        return movieList
                .thenApply((list) -> {
            Movie requiredMovie = list.stream()
                    .filter(movie -> movieId.equals(movie.getId()))
                    .findAny()
                    .orElse(null);
            if (requiredMovie == null)
                throw new MovieNotFind("Movie Not found");
            else
                return requiredMovie;
        })
                .exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });
    }

    public CompletableFuture<List<Movie>> deleteMovie(Long movieId) {


        CompletableFuture<Movie> requiredMovie = getMovie(movieId);

        this.movieList = movieList.thenCompose(list -> {
            requiredMovie.thenApply(movie -> {
                list.remove(list.indexOf(movie));
                return list;
            });
            return movieList;
        });

        return this.movieList;

    }


    public CompletableFuture<List<Movie>> updateMovie(Long id, String name, String releaseDate, String releaseYear, Integer rating, String actor, String director) {

        Movie updatedMovie = new Movie(id, name, releaseDate, releaseYear, rating, actor, director);
        CompletableFuture<Movie> isExists = getMovie(id);

        this.movieList = movieList.thenCompose(list -> {
            isExists.thenApply(movieInList -> {
                if (movieInList != null) {
                    list.remove(list.indexOf(id));
                    this.movieList = addMovie(updatedMovie);
                    return this.movieList;
                } else {
                    throw new RuntimeException("This movie doesn't exists.");
                }
            });
            return movieList;
        });
        return movieList;

    }


    public CompletableFuture<List<Movie>> getMovies() {
        return movieList;

    }

    public CompletableFuture<List<Movie>> getMoviesWithLimit(int offset, int limit) {
        return movieList.thenApply((list) -> {
            int actualLimit;
            if (offset > list.size() - 1)
                throw new RuntimeException("Offset is greater than the size of movie list.");
            else if (offset + limit > list.size())
                actualLimit = list.size();
            else
                actualLimit = offset + limit;

            return IntStream.range(offset, actualLimit)
                    .boxed()
                    .map(index -> list.get(index))
                    .collect(Collectors.toList());
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });
    }

    public CompletableFuture<List<Movie>> filterMovieWithDirector(String director) {

        return movieList.thenApply((List<Movie> list) -> {
            List<Movie> moviesWithHigherRating = list.stream()
                    .filter(movie -> movie.getRating() > 8 && director.equals(movie.getDirector()))
                    .collect(Collectors.toList());
            if (moviesWithHigherRating.size() == 0) {
                throw new MovieNotFind("Required Movies not found");
            } else
                return moviesWithHigherRating;
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });


    }

    public CompletableFuture<Map<String, Long>> moviesPerDirector() {
        return movieList.thenApply(list -> {
            return list.stream()
                    .collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()));
        });
    }


    private LocalDate parseDate(String date) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, parser);
    }

    public CompletableFuture<List<Movie>> getMoviesAfterGivenDate(String date) {
        LocalDate parsedDate = parseDate(date);

        return movieList.thenApply((list) -> {

            List<Movie> moviesReleasedAfterDate = list.stream()
                    .filter(movie -> parseDate(movie.getReleaseDate()).isAfter(parsedDate))
                    .collect(Collectors.toList());

            if (moviesReleasedAfterDate.size() == 0)
                throw new MovieNotFind("Required Movies not found");

            else
                return moviesReleasedAfterDate;



        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });
    }

    public CompletableFuture<List<Movie>> getListOfMoviesBetweenYears(String startYear, String endYear) {
        Integer startYearInt = Integer.valueOf(startYear);
        Integer endYearInt = Integer.valueOf(endYear);

        return movieList.thenApply((list) -> {
            List<Movie> moviesInBetweenYears = list.stream()
                    .filter(movie -> Integer.valueOf(movie.getReleaseYear()) > startYearInt &&
                            Integer.valueOf(movie.getReleaseYear()) < endYearInt)
                    .collect(Collectors.toList());
            if (moviesInBetweenYears.size() == 0)
                // try {
                throw new MovieNotFind("movie not find");
                // } catch (MovieNotFind movieNotFind) {
                //   movieNotFind.printStackTrace();
                //}
            else
                return moviesInBetweenYears;
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });
    }

    public CompletableFuture<List<Movie>> getListOfMoviesBetweenDates(String startDate, String endDate) {
        LocalDate parsedStartDate = parseDate(startDate);
        LocalDate parsedEndDate = parseDate(endDate);

        return movieList.thenApply((list) -> {
            List<Movie> moviesInBetweenDate = list.stream()
                    .filter(movie -> parsedStartDate.isBefore(parseDate(movie.getReleaseDate())) &&
                            parsedEndDate.isAfter(parseDate(movie.getReleaseDate())))
                    .collect(Collectors.toList());

            if (moviesInBetweenDate.size() == 0)
                throw new MovieNotFind("movie not find");
            else
                return moviesInBetweenDate;
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });
    }
}
