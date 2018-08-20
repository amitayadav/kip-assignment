
package com.knoldus;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCount {

    public static Map<String,Long> frequencyCount(String content){

        Map<String, Long> wordFrequency =Stream.of(content.toLowerCase().split(" "))
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()));

        return wordFrequency;
    }

    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the String");
        String content=scanner.nextLine();

        WordCount.frequencyCount(content).keySet().stream()
                .map(key -> Arrays.asList(key, WordCount.frequencyCount(content).get(key)))
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }

}