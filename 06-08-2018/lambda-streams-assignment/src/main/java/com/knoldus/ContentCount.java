
package com.knoldus;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContentCount {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader=new BufferedReader(new FileReader("/home/knoldus/Documents/assignments/kip-assignment/06-08-2018/lambda-streams-assignment/File1"));
        String contents="";
        String text="";

        while ((contents = bufferedReader.readLine()) != null) {
            text += contents;
        }

        Map<String, Long> wordFrequency = Stream.of(text.toLowerCase().split(" "))
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()));

        System.out.println(wordFrequency);
    }

}
