package com.example.springapi.service;

import com.example.springapi.api.model.Datetime;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;

@Service
public class DatetimeService {

    private Datetime dateTime;

    public LocalDateTime getDatetimeDate() {
        dateTime = new Datetime();
        return dateTime.getCurrentDate();
    }

    public int getDatetimeDay() {
        dateTime = new Datetime();
        return dateTime.getCurrentDay();
    }

    public Month getDatetimeMonth() {
        dateTime = new Datetime();
        return dateTime.getCurrentMonth();
    }

    public int getDatetimeYear() {
        dateTime = new Datetime();
        return dateTime.getCurrentYear();
    }
}
