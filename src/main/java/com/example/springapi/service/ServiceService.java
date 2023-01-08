package com.example.springapi.service;

import org.springframework.stereotype.Service;
import com.example.springapi.api.model.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class ServiceService {
    private final List<Services> serviceList;

    public ServiceService() {
        serviceList = new ArrayList<>();

        Services service1 = new Services(1, "s1");
        Services service2 = new Services(2, "s2");
        Services service3 = new Services(3, "s3");

        serviceList.addAll(Arrays.asList(service1, service2, service3));
        serviceList.sort(Comparator.comparingInt(Services::getId));

    }

    public List<Services> listAllServices() {
        return serviceList;
    }

    public Services addService() {
        int id = serviceList.get(serviceList.size() - 1).getId() + 1;
        Services newService = new Services(id, "s" + id);
        serviceList.add(newService);
        return newService;
    }

    public void deleteService(int id) {
        serviceList.removeIf(service -> id == service.getId());
    }

    public void updateService(int id, String name) {
        for (Services service: serviceList) {
            if (id == service.getId()) {
                service.setName(name);
            }
        }
    }
}
