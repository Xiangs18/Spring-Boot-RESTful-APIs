package com.example.springapi.api.model;


import java.time.LocalDateTime;
import java.time.Month;

public class Datetime {

    private final LocalDateTime currentDate;
    private final int currentDay;
    private final Month currentMonth;
    private final int currentYear;

    public Datetime() {
        this.currentDate = LocalDateTime.now();
        this.currentDay = this.currentDate.getDayOfMonth();
        this.currentMonth = this.currentDate.getMonth();
        this.currentYear = this.currentDate.getYear();
    }

    public LocalDateTime getCurrentDate() {
        return currentDate;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public Month getCurrentMonth() {
        return currentMonth;
    }

    public int getCurrentYear() {
        return currentYear;
    }

}
