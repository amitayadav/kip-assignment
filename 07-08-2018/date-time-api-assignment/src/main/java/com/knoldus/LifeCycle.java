
package com.knoldus;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

class LifeCycle {
    public void getLifeCycle() {
        LocalDate birthDate=LocalDate.of(1869,10,02);
        LocalDate deathDate=LocalDate.of(1948,01,31);
        LocalDateTime birthDateTime = birthDate.atStartOfDay();
        LocalDateTime deathDateTime = deathDate.atStartOfDay();
        System.out.println("birthDateTime: " + birthDateTime);
        System.out.println("deathDateTime: " + deathDateTime);
        System.out.println("Seconds: " + ChronoUnit.SECONDS.between(birthDateTime, deathDateTime));


//        System.out.println("Mahatma Gandhi's number of lived days"+ ChronoUnit.DAYS.between(birthDate, deathDate));
    }
}
