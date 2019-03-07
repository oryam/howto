package com.abb.test.sample;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import org.junit.Test;

public class NanoDateTest {

    @Test
    public void test_read_date_nano_from_string() {
        String inputDateWithNanos = "2017-09-19 11:08:38.244000123";
        System.out.println(inputDateWithNanos);

        DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n");
        TemporalAccessor temporal = dateTimePattern.parse(inputDateWithNanos);
        System.out.println(temporal);

        LocalDateTime dateTime = LocalDateTime.from(temporal);
        Timestamp time = Timestamp.valueOf(dateTime);
        System.out.println(time.getNanos());
        System.out.println(time.getTime());
        System.out.println(time);

        assertThat(time.getNanos()).isEqualTo(244000123); // nano of second
        assertThat(time.getTime()).isEqualTo(1505812118244L); // millis of day
        assertThat(time.toString()).isEqualTo(inputDateWithNanos);
    }

    @Test
    public void test_read_date_nano_from_timestamp() {
        DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n");

        LocalDateTime time = LocalDateTime.of(2017, 9, 19, 11, 8, 38, 244000123); // simulate the timestamp from jpa mapping
        System.out.println(time.getNano());
        System.out.println(time.format(dateTimePattern));

        assertThat(time.getNano()).isEqualTo(244000123); // nano of second
        assertThat(time.format(dateTimePattern)).isEqualTo("2017-09-19 11:08:38.244000123");
    }

}
