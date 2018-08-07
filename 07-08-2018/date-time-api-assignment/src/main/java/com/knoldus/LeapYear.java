package com.knoldus;

import java.time.Year;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class LeapYear {

    public void getLeapYearList() {
        Year today = Year.now();
        Year oldYear = Year.of(1900);

        IntStream.rangeClosed(oldYear.getValue(),today.getValue())
                .boxed()
                .filter(year->Year.of(year).isLeap())
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }
}
