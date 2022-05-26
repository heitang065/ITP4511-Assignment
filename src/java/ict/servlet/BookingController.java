/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.Booking;
import ict.bean.UserInfo;
import ict.db.BookingDB;
import ict.db.CenterDB;
import ict.db.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hei
 */
@WebServlet(name = "BookingController", urlPatterns = {"/bookingController"})
public class BookingController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private UserDB udb;
    private CenterDB cdb;
    private BookingDB bdb;

    public void init() {
        //1.  obtain the context-param, dbUser, dbPassword and dbUrl which defined in web.xml
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");

        //2.  create a new db object  with the parameter
        udb = new UserDB(dbUrl, dbUser, dbPassword);
        cdb = new CenterDB(dbUrl, dbUser, dbPassword);
        bdb = new BookingDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("Request Booking".equalsIgnoreCase(action)) {
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            String trainerName = request.getParameter("trainerName");
            String[] name = trainerName.split(" ");
            String fname = name[0], lname = name[1];
            UserInfo trainer = udb.queryTrainerByName(fname, lname);
            String trainerID = trainer.getTrainerID();
            String centerID = request.getParameter("centerID");
            int userID = Integer.parseInt(request.getParameter("userID"));
            boolean result = bdb.addBooking(0, date, time, trainerID, centerID, userID, "W");
            // call the query db to get retrieve for all customer 
            ArrayList<Booking> booking = bdb.queryBookingListForCust(userID);
            // set the result into the attribute	
            request.setAttribute("booking", booking);
            // redirect the result to the listCustomers.jsp
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listBooking.jsp");
            rd.forward(request, response);
        }/* else if ("Request Booking".equalsIgnoreCase(action)) {
            String bookingID = request.getParameter("bookingID");
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            String trainerID = request.getParameter("trainerID");
            String centerID = request.getParameter("centerID");
            String userID = request.getParameter("userID");
            String status = request.getParameter("status");

            boolean result = bdb.addBooking(null, date, time, trainerID, centerID, userID, status);
            response.sendRedirect("book");
        }*/ else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
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

}
