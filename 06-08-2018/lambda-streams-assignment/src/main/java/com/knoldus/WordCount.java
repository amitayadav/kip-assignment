
package com.knoldus;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCount {

    public static Map<String,Long> countFrequency(String text){

        Map<String, Long> wordFrequency =Stream.of(text.toLowerCase().split("\\W+"))
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()));

        return wordFrequency;
    }

    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the String");
        String text=scanner.nextLine();


        Map <String,Long> wordCount=WordCount.countFrequency(text);

        System.out.println( wordCount.keySet().stream()
                .map(key -> Arrays.asList(key,wordCount.get(key)))
                .collect(Collectors.toList()));

    }

}