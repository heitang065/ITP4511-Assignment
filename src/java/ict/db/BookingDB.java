/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.Booking;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Hei
 */
public class BookingDB {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public BookingDB(String dburl, String dbUser, String dbPassword) {
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

    public void CreateBookingTable() {
        try {
            Connection cnnct = getConnection();
            Statement stmnt = cnnct.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS booking ("
                    + "bookingID int(5) NOT NULL AUTO_INCREMENT,"
                    + "date varchar(10) NOT NULL,"
                    + "time varchar(5) NOT NULL,"
                    + "trainerID varchar(7) NOT NULL,"
                    + "centerID varchar(25) NOT NULL,"
                    + "userID int(5) NOT NULL,"
                    + "status varchar(1) NOT NULL,"
                    + "PRIMARY KEY (bookingID)"
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

    public boolean addBooking(int bookingID, String date, String time,
            String trainerID, String centerID, int userID, String status) {
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "INSERT INTO booking VALUES"
                    + "(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, bookingID);
            pStmnt.setString(2, date);
            pStmnt.setString(3, time);
            pStmnt.setString(4, trainerID);
            pStmnt.setString(5, centerID);
            pStmnt.setInt(6, userID);
            pStmnt.setString(7, status);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println("booking added");
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

    public ArrayList<Booking> queryBookingList() {
        ArrayList booking = new ArrayList();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                Booking bk = new Booking();
                //set the record detail to the customer bean
                bk.setBookingID(rs.getInt("bookingID"));
                bk.setDate(rs.getString("date"));
                bk.setTime(rs.getString("time"));
                bk.setTrainerID(rs.getString("trainerID"));
                bk.setCenterID(rs.getString("centerID"));
                bk.setUserID(rs.getInt("userID"));
                bk.setCenterID(rs.getString("centerID"));
                bk.setStatus(rs.getString("status"));
                booking.add(bk);
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
        return booking;
    }

    public ArrayList<Booking> queryBookingListForStaff() {
        ArrayList booking = new ArrayList();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking WHERE status = \"W\"";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                Booking bk = new Booking();
                //set the record detail to the customer bean
                bk.setBookingID(rs.getInt("bookingID"));
                bk.setDate(rs.getString("date"));
                bk.setTime(rs.getString("time"));
                bk.setTrainerID(rs.getString("trainerID"));
                bk.setCenterID(rs.getString("centerID"));
                bk.setUserID(rs.getInt("userID"));
                bk.setCenterID(rs.getString("centerID"));
                bk.setStatus(rs.getString("status"));
                booking.add(bk);
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
        return booking;
    }

    public ArrayList<Booking> queryBookingByTrainerID(String trainerID) {
        ArrayList booking = new ArrayList();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking Where trainerID = ?";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, trainerID);
            //3. update the placehoder with id
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                Booking bk = new Booking();
                //set the record detail to the customer bean
                bk.setBookingID(rs.getInt("bookingID"));
                bk.setDate(rs.getString("date"));
                bk.setTime(rs.getString("time"));
                bk.setTrainerID(rs.getString("trainerID"));
                bk.setCenterID(rs.getString("centerID"));
                bk.setUserID(rs.getInt("userID"));
                bk.setStatus(rs.getString("status"));
                booking.add(bk);
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
        return booking;
    }

    public ArrayList<Booking> queryBookingByCenterID(String centerID) {
        ArrayList booking = new ArrayList();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking Where centerID = ?";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, centerID);
            //3. update the placehoder with id
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                Booking bk = new Booking();
                //set the record detail to the customer bean
                bk.setBookingID(rs.getInt("bookingID"));
                bk.setDate(rs.getString("date"));
                bk.setTime(rs.getString("time"));
                bk.setTrainerID(rs.getString("trainerID"));
                bk.setCenterID(rs.getString("centerID"));
                bk.setUserID(rs.getInt("userID"));
                bk.setStatus(rs.getString("status"));
                booking.add(bk);
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
        return booking;
    }

    public Booking queryBookingByID(int bookingID) {
        Booking bk = new Booking();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking Where bookingID = ?";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, bookingID);
            //3. update the placehoder with id
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                //set the record detail to the customer bean
                bk.setBookingID(rs.getInt("bookingID"));
                bk.setDate(rs.getString("date"));
                bk.setTime(rs.getString("time"));
                bk.setTrainerID(rs.getString("trainerID"));
                bk.setCenterID(rs.getString("centerID"));
                bk.setUserID(rs.getInt("userID"));
                bk.setStatus(rs.getString("status"));
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
        return bk;
    }

    public boolean editBookingStatus(Booking bk) {
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "UPDATE booking SET bookingID = ?, date = ?, time = ?,"
                    + " trainerID = ?, centerID = ?, userID = ?, status = ? WHERE bookingID = ?";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, bk.getBookingID());
            pStmnt.setString(2, bk.getDate());
            pStmnt.setString(3, bk.getTime());
            pStmnt.setString(4, bk.getTrainerID());
            pStmnt.setString(5, bk.getCenterID());
            pStmnt.setInt(6, bk.getUserID());
            pStmnt.setString(7, bk.getStatus());
            pStmnt.setInt(8, bk.getBookingID());
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

    public ArrayList<Booking> queryBookingListForCust(int userID) {
        ArrayList booking = new ArrayList();
        try {
            //1. get Connection
            Connection cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking WHERE userID = ?";
            //2. get the prepare Statement
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, userID);
            //3. update the placehoder with id
            ResultSet rs = pStmnt.executeQuery();
            //4. execute the query and assign to the result
            while (rs.next()) {
                Booking bk = new Booking();
                //set the record detail to the customer bean
                bk.setBookingID(rs.getInt("bookingID"));
                bk.setDate(rs.getString("date"));
                bk.setTime(rs.getString("time"));
                bk.setTrainerID(rs.getString("trainerID"));
                bk.setCenterID(rs.getString("centerID"));
                bk.setUserID(rs.getInt("userID"));
                bk.setStatus(rs.getString("status"));
                booking.add(bk);
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
        return booking;
    }

    public boolean updateBookingByDelTrainer(String trainerID) {
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "UPDATE booking SET status = \"R\" WHERE trainerID = ?";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, trainerID);
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

    public boolean updateBookingByDelCenter(String centerID) {
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "UPDATE booking SET status = \"R\" WHERE centerID = ?";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, centerID);
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

    public boolean updateBookingByDelUser(int userID) {
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "UPDATE booking SET status = \"R\" WHERE userID = ?";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, userID);
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

    public boolean updateBookingByTimeoutTrainer(String trainerID) {
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "UPDATE booking SET status = \"T\" WHERE trainerID = ? AND status = \"W\"";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, trainerID);
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

    public boolean updateBookingByTimeoutCenter(String centerID) {
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "UPDATE booking SET status = \"T\" WHERE centerID = ? AND status = \"W\"";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, centerID);
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
}
