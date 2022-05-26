/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.test;

import ict.db.CenterDB;

/**
 *
 * @author Hei
 */
public class TestAddCenter {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/itp4511_assignment";
        String username = "root";
        String password = "";
        CenterDB centerDb = new CenterDB(url, username, password);
        centerDb.addCenterInfo("TM", "Tuen Mun", 200, 10, "T", "", "11:00-23:00");
        centerDb.addCenterInfo("ST", "Sha Tin", 220, 10, "T", "", "11:00-23:00");
        centerDb.addCenterInfo("TY", "Tsing Yi", 220, 10, "T", "", "11:00-23:00");
        centerDb.addCenterInfo("LWL", "Lee Wai Lee", 220, 10, "T", "", "11:00-23:00");
        centerDb.addCenterInfo("CW", "Chai Wan", 250, 10, "T", "", "11:00-23:00");
    }
}
