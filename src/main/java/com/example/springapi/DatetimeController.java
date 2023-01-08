package com.example.springapi;

import com.example.springapi.service.DatetimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.Month;

@RestController
public class DatetimeController {
    private final DatetimeService dateTimeService;

    @Autowired
    public DatetimeController(DatetimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @GetMapping("/datetime/date")
    public ResponseEntity<LocalDateTime> getCurrentDate() {
        return new ResponseEntity<>(dateTimeService.getDatetimeDate(), HttpStatus.FOUND);
    }

    @GetMapping("/datetime/day")
    public ResponseEntity<Integer> getCurrentDay() {
        return new ResponseEntity<>(dateTimeService.getDatetimeDay(), HttpStatus.FOUND);
    }

    @GetMapping("/datetime/month")
    public ResponseEntity<Month> getCurrentMonth() {
        return new ResponseEntity<>(dateTimeService.getDatetimeMonth(), HttpStatus.FOUND);
    }

    @GetMapping("/datetime/year")
    public ResponseEntity<Integer> getCurrentYear() {
        return new ResponseEntity<>(dateTimeService.getDatetimeYear(), HttpStatus.FOUND);
    }
}
