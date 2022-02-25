package com.scaler.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

public class GreetTests {

    @Test
    void testFetchGreetingWorks() {
        Calendar cal_10_00am = new Calendar.Builder().setTimeOfDay(10, 0, 0).build();
        Calendar cal_02_00pm = new Calendar.Builder().setTimeOfDay(14, 0, 0).build();
        Calendar cal_07_00pm = new Calendar.Builder().setTimeOfDay(19, 0, 0).build();

        Assertions.assertEquals(Greet.GREET_MORN, new Greet(cal_10_00am).fetchGreeting());
        Assertions.assertEquals(Greet.GREET_AFT, new Greet(cal_02_00pm).fetchGreeting());
        Assertions.assertEquals(Greet.GREET_EVE, new Greet(cal_07_00pm).fetchGreeting());
    }
}
