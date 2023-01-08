package com.example.springapi;

import com.example.springapi.api.model.Api;
import com.example.springapi.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    private final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/apis/listAll")
    public ResponseEntity<List<Api>> getAllApis() {
        return new ResponseEntity<>(apiService.listAllApis(), HttpStatus.FOUND);
    }

    @DeleteMapping("/apis/delete")
    public ResponseEntity<Void> deleteApi(@RequestParam int id) {
        apiService.deleteApi(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/apis/add")
    public ResponseEntity<Api> addApi() {
        return new ResponseEntity<>(apiService.addApi(), HttpStatus.CREATED);
    }

    @PutMapping("/apis/update")
    public ResponseEntity<Void> updateApi(@RequestParam int id, String name) {
        apiService.updateApi(id, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
