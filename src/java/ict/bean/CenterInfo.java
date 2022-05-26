/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;

import java.io.Serializable;

/**
 *
 * @author Hei
 */
public class CenterInfo implements Serializable {

    private String centerID, location, availability, description, openHour;
    private int hourlyRate, capacity;

    public CenterInfo() {
    }

    public CenterInfo(String centerID, String location, int hourlyRate, int capacity, String availability, String description, String openHour) {
        this.centerID = centerID;
        this.location = location;
        this.hourlyRate = hourlyRate;
        this.capacity = capacity;
        this.availability = availability;
        this.description = description;
        this.openHour = openHour;
    }

    public void setCenterID(String centerID) {
        this.centerID = centerID;
    }

    public String getCenterID() {
        return centerID;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getAvailability() {
        return availability;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour;
    }

    public String getOpenHour() {
        return openHour;
    }
}
