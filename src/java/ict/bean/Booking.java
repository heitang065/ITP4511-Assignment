/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;

/**
 *
 * @author Hei
 */
public class Booking {

    private String date, time, trainerID, centerID, status;
    private int bookingID, userID;

    public Booking() {
    }

    public Booking(int bookingID, String date, String time, String trainerID, String centerID, int userID, String status) {
        this.bookingID = bookingID;
        this.date = date;
        this.time = time;
        this.trainerID = trainerID;
        this.centerID = centerID;
        this.userID = userID;
        this.status = status;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTrainerID(String trainerID) {
        this.trainerID = trainerID;
    }

    public String getTrainerID() {
        return trainerID;
    }

    public void setCenterID(String centerID) {
        this.centerID = centerID;
    }

    public String getCenterID() {
        return centerID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
