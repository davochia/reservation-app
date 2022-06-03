package com.neagen.devtest.rest;

import java.time.Instant;

public class Appointment {
    Instant startTime;
    Instant endTime;
    String description;

    public Appointment(){}
    public Appointment(String description, Instant startTime, Instant endTime){
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
