package com.neagen.devtest.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes="Unique auto generated identifier for the system")
    private Long id;

    private String name;

    Instant startTime;
    Instant endTime;
    String description;

//    public Appointment(){}

//    public Appointment(Long id, String name, Instant startTime, Instant endTime, String description) {
//        this.id = id;
//        this.name = name;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.description = description;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Instant getStartTime() {
//        return startTime;
//    }
//
//    public Instant getEndTime() {
//        return endTime;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setStartTime(Instant startTime) {
//        this.startTime = startTime;
//    }
//
//    public void setEndTime(Instant endTime) {
//        this.endTime = endTime;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    @Override
//    public String toString() {
//        return "Appointment{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", startTime=" + startTime +
//                ", endTime=" + endTime +
//                ", description='" + description + '\'' +
//                '}';
//    }
}
