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
public class TestAddUser {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/itp4511_assignment";
        String username = "root";
        String password = "";
        UserDB userDb = new UserDB(url, username, password);
        userDb.addUserInfo(0, "M", "Chris", "Wong", "F", "cw@gmail.com", "98765432", 99, "123", "", "");
        userDb.addUserInfo(0, "S", "B", "A", "M", "aa@gmail.com", "23456789", 98, "123", "", "");
        userDb.addUserInfo(0, "C", "Peter", "Parker", "M", "pp@gmail.com", "22223333", 103, "123", "", "");
        userDb.addUserInfo(0, "P", "Wai", "Lik", "F", "lw@gmail.com", "44455566", 22, "123", "T", "11111");
    }
}
