/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.test;

import ict.db.UserDB;
import ict.db.CenterDB;
import ict.db.BookingDB;

/**
 *
 * @author Hei
 */
public class TestCreateTable {

    public static void main(String[] arg) {
        String url = "jdbc:mysql://localhost:3306/itp4511_assignment";
        String username = "root";
        String password = "";
        UserDB userDb = new UserDB(url, username, password);
        CenterDB centerDb = new CenterDB(url, username, password);
        BookingDB bookingDb = new BookingDB(url, username, password);
        userDb.CreateUserInfoTable();
        centerDb.CreateCenterTable();
        bookingDb.CreateBookingTable();
    }
}
