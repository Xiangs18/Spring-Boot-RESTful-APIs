package com.example.springapi;

import com.example.springapi.api.model.Event;
import com.example.springapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events/get")
    public ResponseEntity<Event> getEvent(@RequestParam String datetime) {
        Event res = eventService.getEvent(datetime);
        if (res == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.FOUND);
    }

    @GetMapping("/events/listAll")
    public ResponseEntity<List<Event>> getAllEvents() {
        return new ResponseEntity<>(eventService.listAllEvents(), HttpStatus.FOUND);
    }

    @DeleteMapping("/events/delete")
    public ResponseEntity<Void> deleteEvent(@RequestParam int id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/events/add")
    public ResponseEntity<Event> addEvent(@RequestParam String name, String datetime) {
        if (EventService.isDateValid(datetime)) {
            return new ResponseEntity<>(eventService.addEvent(name, datetime), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @PutMapping("/events/update")
    public ResponseEntity<Void> updateEvent(@RequestParam int id, String name) {
        eventService.updateEvent(id, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
