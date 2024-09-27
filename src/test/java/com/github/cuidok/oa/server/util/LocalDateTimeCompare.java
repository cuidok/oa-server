package com.github.cuidok.oa.server.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocalDateTimeCompare {

    public boolean compareTimeDifference(LocalDateTime time1, LocalDateTime time2, int seconds) {
        return seconds > Math.abs(time1.toEpochSecond(ZoneOffset.UTC) - time2.toEpochSecond(ZoneOffset.UTC));
    }
}
