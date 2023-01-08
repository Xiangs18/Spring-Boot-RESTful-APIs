package com.example.springapi.api.model;

public class System {

    private int version;
    private String name;


    public System(int version, String name) {
        this.version = version;
        this.name = name;

    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
