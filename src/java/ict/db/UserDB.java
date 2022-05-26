/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.UserInfo;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Hei
 */
public class UserDB {
    
    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";
    
    public UserDB(String dburl, String dbUser, String dbPassword) {
        this.dburl = dburl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }
    
    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return DriverManager.getConnection(dburl, dbUser, dbPassword);
    }
    
    public boolean isValidUser(String email, String pwd)
            throws SQLException, IOException {
        boolean isValid = false;
        //1. get Connection
        Connection cnnct = getConnection();
        String preQueryStatement = "SELECT * FROM userinfo WHERE email = ? and  password = ?";
        //2. get the prepare Statement
        PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
        //3. update the placeholders with username and pwd
        pStmnt.setString(1, email);
        pStmnt.setString(2, pwd);
        //4. execute the query and assign to the result
        ResultSet rs = pStmnt.executeQuery();
        UserInfo ui = new UserInfo();
        if (rs.next()) {
            isValid = true;
            ui.setFname(rs.getString("fname"));
            ui.setLname(rs.getString("lname"));
            ui.setLname(rs.getString("gender"));
            ui.setEmail(rs.getString("email"));
            ui.setPhone(rs.getString("phone"));
            ui.setAge(rs.getInt("age"));
            ui.setAvailability(rs.getString("availability"));
            ui.setTrainerID(rs.getString("trainerID"));
        }
        pStmnt.close();
        cnnct.close();
        return isValid;
    }
    
    public void createDB() {
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "CREATE DATABASE IF NOT EXISTS itp4511_assignment";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.executeUpdate();
            System.out.println("database created");
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void dropDB() {
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "DROP DATABASE itp4511_assignment";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.executeUpdate();
            System.out.println("database dropped");
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void CreateUserInfoTable() {
        try {
            Connection cnnct = getConnection();
            Statement stmnt = cnnct.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS userInfo ("
                    + "userID int(5) NOT NULL AUTO_INCREMENT,"
                    + "type varchar(1) NOT NULL,"
                    + "fname varchar(25) NOT NULL,"
                    + "lname varchar(25) NOT NULL,"
                    + "gender varchar(1) NOT NULL,"
                    + "email varchar(50) NOT NULL,"
                    + "phone varchar(8) NOT NULL,"
                    + "age int(3) NULL,"
                    + "password varchar(25) NOT NULL,"
                    + "availability varchar(1) NULL,"
                    + "trainerID varchar(7) NULL,"
                    + "PRIMARY KEY (userID)"
                    + ")";
            stmnt.execute(sql);
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean addUserInfo(int userID, String type, String fname, String lname,
            String gender, String email, String tel, int age, String pwd,
            String availability, String trainerID) {
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "INSERT INTO userInfo VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, userID);
            pStmnt.setString(2, type);
            pStmnt.setString(3, fname);
            pStmnt.setString(4, lname);
            pStmnt.setString(5, gender);
            pStmnt.setString(6, email);
            pStmnt.setString(7, tel);
            pStmnt.setInt(8, age);
            pStmnt.setString(9, pwd);
            pStmnt.setString(10, availability);
            pStmnt.setString(11, trainerID);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println(lname + fname + " is added");
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public UserInfo queryAccountInfo(String email) throws SQLException, IOException {
        //1. get Connection
        Connection cnnct = getConnection();
        String preQueryStatement = "SELECT * FROM userinfo WHERE email = ?";
        //2. get the prepare Statement
        PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
        //3. update the placeholders with username and pwd
        pStmnt.setString(1, email);
        //4. execute the query and assign to the result
        ResultSet rs = pStmnt.executeQuery();
        UserInfo ui = new UserInfo();
        if (rs.next()) {
            ui.setUserID(rs.getInt("userID"));
            ui.setType(rs.getString("type"));
            ui.setFname(rs.getString("fname"));
            ui.setLname(rs.getString("lname"));
            ui.setGender(rs.getString("gender"));
            ui.setEmail(rs.getString("email"));
            ui.setPhone(rs.getString("phone"));
            ui.setAge(rs.getInt("age"));
            ui.setAvailability(rs.getString("availability"));
            ui.setTrainerID(rs.getString("trainerID"));
        }
        pStmnt.close();
        cnnct.close();
        return ui;
    }
    
    public ArrayList<UserInfo> queryTrainer() {
        ArrayList trainer = new ArrayList();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM userInfo WHERE type = \"P\" AND availability = \"T\"";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                UserInfo ui = new UserInfo();
                //set the record detail to the customer bean
                ui.setFname(rs.getString("fname"));
                ui.setLname(rs.getString("lname"));
                ui.setGender(rs.getString("gender"));
                ui.setEmail(rs.getString("email"));
                ui.setPhone(rs.getString("phone"));
                ui.setAge(rs.getInt("age"));
                ui.setAvailability(rs.getString("availability"));
                ui.setTrainerID(rs.getString("trainerID"));
                trainer.add(ui);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return trainer;
    }
    
    public ArrayList<UserInfo> queryTrainerForStaff() {
        ArrayList trainer = new ArrayList();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM userInfo WHERE type = \"P\"";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                UserInfo ui = new UserInfo();
                //set the record detail to the customer bean
                ui.setFname(rs.getString("fname"));
                ui.setLname(rs.getString("lname"));
                ui.setGender(rs.getString("gender"));
                ui.setEmail(rs.getString("email"));
                ui.setPhone(rs.getString("phone"));
                ui.setAge(rs.getInt("age"));
                ui.setAvailability(rs.getString("availability"));
                ui.setTrainerID(rs.getString("trainerID"));
                trainer.add(ui);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return trainer;
    }
    
    public boolean delRecord(String trainerID) {
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "DELETE FROM userInfo WHERE trainerID = ?";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, trainerID);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println("Remove successful");
            } else {
                System.out.println("No such trainer");
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public UserInfo queryTrainerByID(String trainerID) {
        UserInfo ui = new UserInfo();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM userInfo WHERE trainerID = ?";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, trainerID);
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                //set the record detail to the customer bean
                ui.setFname(rs.getString("fname"));
                ui.setLname(rs.getString("lname"));
                ui.setGender(rs.getString("gender"));
                ui.setEmail(rs.getString("email"));
                ui.setPhone(rs.getString("phone"));
                ui.setAge(rs.getInt("age"));
                ui.setAvailability(rs.getString("availability"));
                ui.setTrainerID(rs.getString("trainerID"));
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ui;
    }
    
    public UserInfo queryTrainerByName(String fname, String lname) {
        UserInfo ui = new UserInfo();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM userInfo WHERE fname = ? AND lname = ?";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, fname);
            pStmnt.setString(2, lname);
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                //set the record detail to the customer bean
                ui.setFname(rs.getString("fname"));
                ui.setLname(rs.getString("lname"));
                ui.setGender(rs.getString("gender"));
                ui.setEmail(rs.getString("email"));
                ui.setPhone(rs.getString("phone"));
                ui.setAge(rs.getInt("age"));
                ui.setAvailability(rs.getString("availability"));
                ui.setTrainerID(rs.getString("trainerID"));
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ui;
    }
    
    public boolean editRecord(UserInfo ui) {
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "UPDATE userInfo SET fname = ?, lname = ?, gender = ?, "
                    + "email = ?, phone = ?, age = ?, availability = ? WHERE trainerID = ?";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, ui.getFname());
            pStmnt.setString(2, ui.getLname());
            pStmnt.setString(3, ui.getGender());
            pStmnt.setString(4, ui.getEmail());
            pStmnt.setString(5, ui.getPhone());
            pStmnt.setInt(6, ui.getAge());
            pStmnt.setString(7, ui.getAvailability());
            pStmnt.setString(8, ui.getTrainerID());
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println("Successful");
            } else {
                System.out.println("Fail");
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public ArrayList<UserInfo> queryUser() {
        ArrayList trainer = new ArrayList();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM userInfo";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                UserInfo ui = new UserInfo();
                //set the record detail to the customer bean
                ui.setUserID(rs.getInt("userID"));
                ui.setType(rs.getString("type"));
                ui.setFname(rs.getString("fname"));
                ui.setLname(rs.getString("lname"));
                ui.setGender(rs.getString("gender"));
                ui.setEmail(rs.getString("email"));
                ui.setPhone(rs.getString("phone"));
                ui.setAge(rs.getInt("age"));
                ui.setAvailability(rs.getString("availability"));
                ui.setTrainerID(rs.getString("trainerID"));
                trainer.add(ui);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return trainer;
    }
    
    public boolean editUserRecord(UserInfo ui) {
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "UPDATE userInfo SET type = ?, fname = ?, lname = ?, gender = ?, "
                    + "email = ?, phone = ?, age = ?, availability = ?, trainerID = ? WHERE userID = ?";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, ui.getType());
            pStmnt.setString(2, ui.getFname());
            pStmnt.setString(3, ui.getLname());
            pStmnt.setString(4, ui.getGender());
            pStmnt.setString(5, ui.getEmail());
            pStmnt.setString(6, ui.getPhone());
            pStmnt.setInt(7, ui.getAge());
            pStmnt.setString(8, ui.getAvailability());
            pStmnt.setString(9, ui.getTrainerID());
            pStmnt.setInt(10, ui.getUserID());
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println("Successful");
            } else {
                System.out.println("Fail");
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public boolean delUserRecord(int userID) {
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "DELETE FROM userInfo WHERE userID = ?";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, userID);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println("Remove successful");
            } else {
                System.out.println("No such trainer");
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public UserInfo queryUserByID(int userID) {
        UserInfo ui = new UserInfo();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM userInfo WHERE userID = ?";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setInt(1, userID);
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                //set the record detail to the customer bean
                ui.setUserID(rs.getInt("userID"));
                ui.setType(rs.getString("type"));
                ui.setFname(rs.getString("fname"));
                ui.setLname(rs.getString("lname"));
                ui.setGender(rs.getString("gender"));
                ui.setEmail(rs.getString("email"));
                ui.setPhone(rs.getString("phone"));
                ui.setAge(rs.getInt("age"));
                ui.setAvailability(rs.getString("availability"));
                ui.setTrainerID(rs.getString("trainerID"));
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ui;
    }
}
