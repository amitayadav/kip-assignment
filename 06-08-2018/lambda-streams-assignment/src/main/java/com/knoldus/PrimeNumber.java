
package com.knoldus;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeNumber {

    public static boolean isPrime(int number) {
        return !IntStream.rangeClosed(2, number / 2).anyMatch(i -> number % i == 0);
    }

    public static void main(String[] arr) {

        List<Integer> numberList = Arrays.stream(new Random()
                .ints(100, 1, 100)
                .toArray()).boxed().collect(Collectors.toList());

        System.out.println(numberList.stream()
                .filter( PrimeNumber::isPrime)
                .collect(Collectors.toList()));
    }
}
