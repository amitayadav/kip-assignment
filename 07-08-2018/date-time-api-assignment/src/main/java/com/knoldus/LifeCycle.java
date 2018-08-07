
package com.knoldus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class LifeCycle {
    public void getLifeCycle() {
        LocalDate birthDate=LocalDate.of(1869,10,02);
        LocalDate deathDate=LocalDate.of(1948,01,31);

        System.out.println("Mahatma Gandhi's number of lived days"+ ChronoUnit.DAYS.between(birthDate,deathDate));
    }
}
