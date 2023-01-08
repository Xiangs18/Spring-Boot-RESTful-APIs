package com.example.springapi;

import com.example.springapi.api.model.Services;
import com.example.springapi.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/services/listAll")
    public ResponseEntity<List<Services>> getAllServices() {
        return new ResponseEntity<>(serviceService.listAllServices(), HttpStatus.FOUND);
    }

    @DeleteMapping("/services/delete")
    public ResponseEntity<Void> deleteService(@RequestParam int id) {
        serviceService.deleteService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/services/add")
    public ResponseEntity<Services> addService() {
        return new ResponseEntity<>(serviceService.addService(), HttpStatus.CREATED);
    }

    @PutMapping("/services/update")
    public ResponseEntity<Void> updateService(@RequestParam int id, String name) {
        serviceService.updateService(id, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
