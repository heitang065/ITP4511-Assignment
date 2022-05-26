/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.CenterInfo;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Hei
 */
public class CenterDB {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public CenterDB(String dburl, String dbUser, String dbPassword) {
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

    public void CreateCenterTable() {
        try {
            Connection cnnct = getConnection();
            Statement stmnt = cnnct.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS center ("
                    + "centerID varchar(3) NOT NULL,"
                    + "location varchar(25) NOT NULL,"
                    + "hourlyRate int(4) NOT NULL,"
                    + "capacity int(2) NOT NULL,"
                    + "availability varchar(1) NOT NULL,"
                    + "description varchar(50) NULL,"
                    + "openHour varchar(11) NOT NULL,"
                    + "PRIMARY KEY (centerID)"
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

    public boolean addCenterInfo(String centerID, String location, int hourlyRate,
            int capacity, String availability, String description, String openHour) {
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "INSERT INTO center VALUES"
                    + "(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, centerID);
            pStmnt.setString(2, location);
            pStmnt.setInt(3, hourlyRate);
            pStmnt.setInt(4, capacity);
            pStmnt.setString(5, availability);
            pStmnt.setString(6, description);
            pStmnt.setString(7, openHour);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println(location + "(" + centerID + ")" + " is added");
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

    public ArrayList<CenterInfo> queryCenter() {
        ArrayList center = new ArrayList();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM center WHERE availability = \"T\"";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                CenterInfo ui = new CenterInfo();
                //set the record detail to the customer bean
                ui.setCenterID(rs.getString("centerID"));
                ui.setLocation(rs.getString("location"));
                ui.setHourlyRate(rs.getInt("hourlyRate"));
                ui.setCapacity(rs.getInt("capacity"));
                ui.setAvailability(rs.getString("availability"));
                ui.setDescription(rs.getString("description"));
                ui.setOpenHour(rs.getString("openHour"));
                center.add(ui);
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
        return center;
    }

    public ArrayList<CenterInfo> queryCenterForStaff() {
        ArrayList center = new ArrayList();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM center";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                CenterInfo ci = new CenterInfo();
                //set the record detail to the customer bean
                ci.setCenterID(rs.getString("centerID"));
                ci.setLocation(rs.getString("location"));
                ci.setHourlyRate(rs.getInt("hourlyRate"));
                ci.setCapacity(rs.getInt("capacity"));
                ci.setAvailability(rs.getString("availability"));
                ci.setDescription(rs.getString("description"));
                ci.setOpenHour(rs.getString("openHour"));
                center.add(ci);
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
        return center;
    }

    public boolean delRecord(String centerID) {
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "DELETE FROM center WHERE centerID = ?";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, centerID);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println("Remove successful");
            } else {
                System.out.println("No such center");
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

    public CenterInfo queryCenterByID(String centerID) {
        CenterInfo ci = new CenterInfo();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM center WHERE centerID = ?";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, centerID);
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                //set the record detail to the customer bean
                ci.setCenterID(rs.getString("centerID"));
                ci.setLocation(rs.getString("location"));
                ci.setHourlyRate(rs.getInt("hourlyRate"));
                ci.setCapacity(rs.getInt("capacity"));
                ci.setAvailability(rs.getString("availability"));
                ci.setDescription(rs.getString("description"));
                ci.setOpenHour(rs.getString("openHour"));
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
        return ci;
    }

    public boolean editRecord(CenterInfo ci) {
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "UPDATE center SET centerID = ?, location = ?, hourlyRate = ?,"
                    + " capacity = ?, availability = ?, description = ? , openHour = ? WHERE centerID = ?";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, ci.getCenterID());
            pStmnt.setString(2, ci.getLocation());
            pStmnt.setInt(3, ci.getHourlyRate());
            pStmnt.setInt(4, ci.getCapacity());
            pStmnt.setString(5, ci.getAvailability());
            pStmnt.setString(6, ci.getDescription());
            pStmnt.setString(7, ci.getOpenHour());
            pStmnt.setString(8, ci.getCenterID());
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

    public ArrayList<CenterInfo> queryCenterInfo() {
        ArrayList center = new ArrayList();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM center";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                CenterInfo ui = new CenterInfo();
                //set the record detail to the customer bean
                ui.setCenterID(rs.getString("centerID"));
                ui.setLocation(rs.getString("location"));
                ui.setHourlyRate(rs.getInt("hourlyRate"));
                ui.setCapacity(rs.getInt("capacity"));
                ui.setAvailability(rs.getString("availability"));
                ui.setDescription(rs.getString("description"));
                ui.setOpenHour(rs.getString("openHour"));
                center.add(ui);
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
        return center;
    }
}
