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
public class TestCreateDB {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "";
        UserDB db = new UserDB(url, username, password);
        db.createDB();
    }
}
