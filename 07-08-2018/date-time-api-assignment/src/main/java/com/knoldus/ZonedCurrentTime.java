package com.knoldus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ZonedCurrentTime {


    public void getZoneCurrentTime() {

        ZonedDateTime timeInNewYork = ZonedDateTime
                .of(LocalDateTime.now(), ZoneId.ofOffset("UTC", ZoneOffset.UTC));
        System.out.println("Current time in a particular timezone : "
                + timeInNewYork);
    }
}
