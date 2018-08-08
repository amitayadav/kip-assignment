
package com.knoldus;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedCurrentTime {

    public void getZoneCurrentTime(String zone) {

        Instant timeStamp = Instant.now();
        ZonedDateTime timestampAtZone = timeStamp.atZone(ZoneId.of(zone));

        System.out.println("Timestamp of zone " + zone + "is " + timestampAtZone);
    }


}
