/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.CenterInfo;
import ict.bean.UserInfo;
import ict.bean.Booking;
import ict.db.CenterDB;
import ict.db.UserDB;
import ict.db.BookingDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hei
 */
@WebServlet(name = "HandleEdit", urlPatterns = {"/handleEdit"})
public class HandleEdit extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            if ("Add Trainer".equalsIgnoreCase(action)) {
                String userID = request.getParameter("UserID");
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String gender = request.getParameter("gender");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                int age = Integer.parseInt(request.getParameter("age"));
                String pwd = request.getParameter("password");
                String trinerID = request.getParameter("trainerID");

                boolean result = udb.addUserInfo(0, "P", fname, lname, gender, email, phone, age, pwd, "T", trinerID);
                response.sendRedirect("handleStaff?action=list");
            } else if ("Edit Trainer".equalsIgnoreCase(action)) {
                // obtain the parameters from request
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String gender = request.getParameter("gender");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                int age = Integer.parseInt(request.getParameter("age"));
                String availability = request.getParameter("availability");
                String trainerID = request.getParameter("trainerID");
                if ("on".equals(availability)) {
                    availability = "T";
                } else {
                    availability = "F";
                    bdb.updateBookingByDelTrainer(trainerID);
                }
                String password = request.getParameter("password");
                String trinerID = request.getParameter("trainerID");
                // call editCustomer to update the database record
                UserInfo ui = new UserInfo(0, "", fname, lname, gender, email, phone, age, password, availability, trinerID);
                boolean result = udb.editRecord(ui);
                // redirect the result to “list” action again
                response.sendRedirect("handleStaff?action=list");
            } else if ("Add Center".equalsIgnoreCase(action)) {
                String centerID = request.getParameter("centerID");
                String location = request.getParameter("location");
                int hourlyRate = Integer.parseInt(request.getParameter("hourlyRate"));
                int capacity = Integer.parseInt(request.getParameter("capacity"));
                String availability = request.getParameter("availability");
                if ("on".equals(availability)) {
                    availability = "T";
                } else {
                    availability = "F";
                }
                String description = request.getParameter("description");
                String openHour = request.getParameter("openHour");

                boolean result = cdb.addCenterInfo(centerID, location, hourlyRate, capacity, availability, description, openHour);
                response.sendRedirect("handleStaff?action=list");
            } else if ("Edit Center".equalsIgnoreCase(action)) {
                // obtain the parameters from request
                String centerID = request.getParameter("centerID");
                String location = request.getParameter("location");
                int hourlyRate = Integer.parseInt(request.getParameter("hourlyRate"));
                int capacity = Integer.parseInt(request.getParameter("capacity"));
                String availability = request.getParameter("availability");
                String description = request.getParameter("description");
                String openHour = request.getParameter("openHour");
                if ("on".equals(availability)) {
                    availability = "T";
                } else {
                    availability = "F";
                    bdb.updateBookingByDelCenter(centerID);
                }
                // call editCustomer to update the database record
                CenterInfo ci = new CenterInfo(centerID, location, hourlyRate, capacity, availability, description, openHour);
                boolean result = cdb.editRecord(ci);
                // redirect the result to “list” action again
                response.sendRedirect("handleStaff?action=list");
            } else if ("Add User".equalsIgnoreCase(action)) {
                String type = request.getParameter("type");
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String gender = request.getParameter("gender");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                int age = Integer.parseInt(request.getParameter("age"));
                String password = request.getParameter("password");
                String trainerID = request.getParameter("trainerID");
                String availability = request.getParameter("availability");
                if ("P".equals(type)) {
                    if ("on".equals(availability)) {
                        availability = "T";
                    } else {
                        availability = "F";
                    }
                } else {
                    trainerID = " ";
                    availability = " ";
                }
                boolean result = udb.addUserInfo(0, type, fname, lname, gender, email, phone, age, password, availability, trainerID);
                response.sendRedirect("handleManager?action=list");
            } else if ("Edit User".equalsIgnoreCase(action)) {
                // obtain the parameters from request
                int userID = Integer.parseInt(request.getParameter("userID"));
                String type = request.getParameter("type");
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String gender = request.getParameter("gender");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                int age = Integer.parseInt(request.getParameter("age"));
                String password = request.getParameter("password");
                String trainerID = request.getParameter("trainerID");
                String availability = request.getParameter("availability");
                System.out.print(type);
                if ("P".equals(type)) {
                    if ("on".equals(availability)) {
                        availability = "T";
                    } else {
                        availability = "F";
                    }
                } else {
                    trainerID = " ";
                    availability = " ";
                }
                // call editCustomer to update the database record
                UserInfo ui = new UserInfo(userID, type, fname, lname, gender, email, phone, age, password, availability, trainerID);
                boolean result = udb.editUserRecord(ui);
                // redirect the result to “list” action again
                response.sendRedirect("handleManager?action=list");
            } else if ("Edit Status".equalsIgnoreCase(action)) {
                // obtain the parameters from request
                String type = request.getParameter("type");
                int bookingID = Integer.parseInt(request.getParameter("bookingID"));
                String date = request.getParameter("date");
                String time = request.getParameter("time");
                String trainerID = request.getParameter("trainerID");
                String centerID = request.getParameter("centerID");
                int userID = Integer.parseInt(request.getParameter("userID"));
                int currentUserID = Integer.parseInt(request.getParameter("currentUserID"));
                String status = request.getParameter("status");
                // call editCustomer to update the database record
                Booking bk = new Booking(bookingID, date, time, trainerID, centerID, userID, status);
                boolean result = bdb.editBookingStatus(bk);
                // redirect the result to “list” action again
                if ("S".equals(type)) {
                    response.sendRedirect("handleStaff?action=book&userID=" + currentUserID);
                } else {
                    response.sendRedirect("handleCustomer?action=check&userID=" + currentUserID);
                }
            } else {
                out.println("No such action!!!");
            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private UserDB udb;
    private CenterDB cdb;
    private BookingDB bdb;

    public void init() {
        //1.  obtain the context-param, dbUser, dbPassword and dbUrl which defined in web.xml
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        //2.  create a new db object  with the parameter
        udb = new UserDB(dbUrl, dbUser, dbPassword);
        cdb = new CenterDB(dbUrl, dbUser, dbPassword);
        bdb = new BookingDB(dbUrl, dbUser, dbPassword);
    }
}
