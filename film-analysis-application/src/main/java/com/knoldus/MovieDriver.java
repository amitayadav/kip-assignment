package com.knoldus;

import java.util.List;
import java.util.Scanner;

public class MovieDriver {
    MovieOperation movieOperation=new MovieOperation();
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Menu for film analysis application:\n 1.add Movie \n 2.get Movie" +
                "\n 3.delete Movie\n 4.get list of movies\n5.get Movie with limit an offset\n" +
                "6. update Movie\n 7.filter Movie on the basis of rating\n" +
                "8.find movies per director\n9.find movies which have released date greater than passed date\n" +
                "10.find movies between years\n 11.find movies between date");
        int choice=scanner.nextInt();
        switch(choice){
            case 1:
                List<Movie> =movieOperation.addMovie();

        }


    }
}
