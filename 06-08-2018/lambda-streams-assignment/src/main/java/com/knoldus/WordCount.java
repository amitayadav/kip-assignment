
package com.knoldus;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCount {

    public static Map<String,Long> frequencyCount(String text){

        Map<String, Long> wordFrequency =Stream.of(text.toLowerCase().split(" "))
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()));

        return wordFrequency;
    }

    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the String");
        String text=scanner.nextLine();

         WordCount.frequencyCount(text).keySet().stream()
                .map(key -> Arrays.asList(key,WordCount.frequencyCount(text).get(key)))
                .collect(Collectors.toList())
                 .forEach(System.out::println);

    }

}