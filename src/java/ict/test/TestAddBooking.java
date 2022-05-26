/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.test;

import ict.db.BookingDB;

/**
 *
 * @author Hei
 */
public class TestAddBooking {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/itp4511_assignment";
        String username = "root";
        String password = "";
        BookingDB bookingDb = new BookingDB(url, username, password);
        bookingDb.addBooking(0, "28/4/2022", "11:00", "11111", "CW", 3, "A");
        bookingDb.addBooking(0, "28/4/2022", "12:00", "11111", "TM", 3, "A");
        bookingDb.addBooking(0, "28/4/2022", "13:00", "11111", "ST", 3, "W");
        bookingDb.addBooking(0, "28/4/2022", "14:00", "11111", "TM", 3, "W");
        bookingDb.addBooking(0, "28/4/2022", "15:00", "11111", "LWL", 3, "C");
        bookingDb.addBooking(0, "28/4/2022", "16:00", "11111", "CW", 3, "C");
        bookingDb.addBooking(0, "28/4/2022", "17:00", "11111", "LWL", 3, "C");
        bookingDb.addBooking(0, "28/4/2022", "18:00", "11111", "TY", 3, "W");
    }
}
