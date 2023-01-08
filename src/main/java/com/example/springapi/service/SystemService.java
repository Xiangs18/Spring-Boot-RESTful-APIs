package com.example.springapi.service;

import com.example.springapi.api.model.System;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class SystemService {
    private final List<System> systemList;

    public SystemService() {
        systemList = new ArrayList<>();

        System system1 = new System(1, "v1");
        System system2 = new System(2, "v2");

        systemList.addAll(Arrays.asList(system1, system2));
        systemList.sort(Comparator.comparingInt(System::getVersion));

    }

    public List<System> listAllSystems() {
        return systemList;
    }

    public System addSystem() {
        int version = systemList.get(systemList.size() - 1).getVersion() + 1;
        System newSystem = new System(version, "v" + version);
        systemList.add(newSystem);
        return newSystem;
    }
}
