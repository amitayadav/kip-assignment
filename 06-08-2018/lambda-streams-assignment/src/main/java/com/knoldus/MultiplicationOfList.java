
package com.knoldus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.min;

public class MultiplicationOfList {


    private static List<Integer> multiply(List<Integer> firstList, List<Integer> secondList) {

       return IntStream.range(0, min(firstList.size(), secondList.size()))
                .boxed()
                .map(index -> firstList.get(index) * secondList.get(index))
                .collect(Collectors.toList());

    }

    public static void main(String[] args) {
        List<Integer> arrayList1= Arrays.asList(1,5,9,3);
        List<Integer> arrayList2=Arrays.asList(4,5,7,3,9);

        List<Integer> list=multiply(arrayList1,arrayList2);

        list.forEach(System.out::println);

    }

}