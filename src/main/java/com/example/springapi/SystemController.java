package com.example.springapi;

import com.example.springapi.api.model.System;
import com.example.springapi.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SystemController {

    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping("/systems/listAll")
    public ResponseEntity<List<System>> getAllSystems() {
        return new ResponseEntity<>(systemService.listAllSystems(), HttpStatus.FOUND);
    }

    @PostMapping("/systems/add")
    public ResponseEntity<System> addSystem() {
        return new ResponseEntity<>(systemService.addSystem(), HttpStatus.CREATED);
    }
}
