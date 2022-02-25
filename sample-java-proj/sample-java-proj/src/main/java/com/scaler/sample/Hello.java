package com.scaler.sample;

import java.util.Calendar;
import java.util.Locale;

public class Hello {
    public static void main(String[] args) {
        Greet greet = new Greet(Calendar.getInstance(Locale.getDefault()));

        System.out.println(greet.fetchGreeting() + " World!");
    }
}
