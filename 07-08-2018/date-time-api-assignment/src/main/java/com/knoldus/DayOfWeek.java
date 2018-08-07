
package com.knoldus;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DayOfWeek {

    public void getDayOfWeekList() {
        LocalDate birthDate = LocalDate.of(1996,9,27);
        int birthYear=birthDate.getYear();
        LocalDate  today = LocalDate.now();
       int currentYear= today.getYear();

        List<java.time.DayOfWeek> dayOfWeekList = IntStream.rangeClosed(birthYear, currentYear)
                .boxed()
                .map(year -> LocalDate.of(year, 9, 27).getDayOfWeek())
                .collect(Collectors.toList());

        System.out.println(dayOfWeekList);
    }
}
