/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.test;

import ict.db.BookingDB;
import ict.db.CenterDB;
import ict.db.UserDB;

/**
 *
 * @author Hei
 */
public class TestCreateAll {

    public static void main(String[] args) {
        //Create DB
        String url = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "";
        UserDB db = new UserDB(url, username, password);
        db.createDB();

        //Create Table
        url = "jdbc:mysql://localhost:3306/itp4511_assignment";
        username = "root";
        password = "";
        UserDB userDb = new UserDB(url, username, password);
        CenterDB centerDb = new CenterDB(url, username, password);
        BookingDB bookingDb = new BookingDB(url, username, password);
        userDb.CreateUserInfoTable();
        centerDb.CreateCenterTable();
        bookingDb.CreateBookingTable();

        //Add User
        userDb.addUserInfo(0, "M", "Chris", "Wong", "F", "cw@gmail.com", "98765432", 99, "123", "", "");
        userDb.addUserInfo(0, "S", "B", "A", "M", "aa@gmail.com", "23456789", 98, "123", "", "");
        userDb.addUserInfo(0, "C", "Peter", "Parker", "M", "pp@gmail.com", "22223333", 103, "123", "", "");
        userDb.addUserInfo(0, "P", "Wai", "Lik", "F", "lw@gmail.com", "44455566", 22, "123", "T", "11111");

        //Add Center
        centerDb.addCenterInfo("TM", "Tuen Mun", 200, 10, "T", "", "11:00-23:00");
        centerDb.addCenterInfo("ST", "Sha Tin", 220, 10, "T", "", "11:00-23:00");
        centerDb.addCenterInfo("TY", "Tsing Yi", 220, 10, "T", "", "11:00-23:00");
        centerDb.addCenterInfo("LWL", "Lee Wai Lee", 220, 10, "T", "", "11:00-23:00");
        centerDb.addCenterInfo("CW", "Chai Wan", 250, 10, "T", "", "11:00-23:00");

        //Add Booking
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
