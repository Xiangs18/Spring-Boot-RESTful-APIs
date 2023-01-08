package com.example.springapi.service;

import com.example.springapi.api.model.Event;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EventService {

    private final List<Event> eventList;

    public EventService() {
        eventList = new ArrayList<>();

        Event event1 = new Event(1, "Meeting1", "2022-10-1");
        Event event2 = new Event(2, "Meeting2", "2022-10-2");
        Event event4 = new Event(4, "Meeting4", "2022-10-4");
        Event event3 = new Event(3, "Meeting3", "2022-10-3");

        eventList.addAll(Arrays.asList(event1, event2, event4, event3));
        eventList.sort(Comparator.comparingInt(Event::getId));

    }

    public List<Event> listAllEvents() {
        return eventList;
    }

    public Event getEvent(String datetime) {
        for (Event event: eventList) {
            if (datetime.equals(event.getDate())) {
                return event;
            }
        }
        return null;
    }

    public void deleteEvent(int id) {
        eventList.removeIf(event -> id == event.getId());
    }

    public Event addEvent(String name, String datetime) {
        int id = eventList.get(eventList.size() - 1).getId() + 1;
        Event newEvent = new Event(id, name, datetime);
        eventList.add(newEvent);
        return newEvent;
    }

    public void updateEvent(int id, String name) {
        for (Event event: eventList) {
            if (id == event.getId()) {
                event.setName(name);
            }
        }
    }

    public static boolean isDateValid(String datetime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try {
            format.parse(datetime);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
