/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.test;

import ict.db.UserDB;

/**
 *
 * @author Hei
 */
public class TestDropDB {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/itp4511_assignment";
        String username = "root";
        String password = "";
        UserDB userDb = new UserDB(url, username, password);
        userDb.dropDB();
    }
}
