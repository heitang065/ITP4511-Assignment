/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.Booking;
import ict.bean.CenterInfo;
import ict.bean.UserInfo;
import ict.db.BookingDB;
import ict.db.CenterDB;
import ict.db.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hei
 */
@WebServlet(name = "HandleStaff", urlPatterns = {"/handleStaff"})
public class HandleStaff extends HttpServlet {

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

        if ("list".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            ArrayList<UserInfo> trainer = udb.queryTrainerForStaff();
            ArrayList<CenterInfo> center = cdb.queryCenterForStaff();
            // set the result into the attribute	 
            request.setAttribute("trainer", trainer);
            request.setAttribute("center", center);
            // redirect the result to the listCustomers.jsp
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listTNCstaff.jsp");
            rd.forward(request, response);
        } else if ("deleteTrainer".equalsIgnoreCase(action)) {
            // get parameter, id, from the request
            String trainerID = request.getParameter("trainerID");
            if (trainerID != null) {
                // call delete record method in the database
                bdb.updateBookingByDelTrainer(trainerID);
                udb.delRecord(trainerID);
                // redirect the result to list action 
                response.sendRedirect("handleStaff?action=list");
            }
        } else if ("getEditTrainer".equalsIgnoreCase(action)) {
            // obtain the parameter id;
            String trainerID = request.getParameter("trainerID");
            if (trainerID != null) {
                // call query db to get retrieve for a customer with the given id
                UserInfo trainer = udb.queryTrainerByID(trainerID);
                // set the customer as attribute in request scope
                request.setAttribute("trainer", trainer);
                // forward the result to the editCustomer.jsp            
                RequestDispatcher rd = this.getServletContext()
                        .getRequestDispatcher("/editTrainer.jsp");
                rd.forward(request, response);
            }
        } else if ("deleteCenter".equalsIgnoreCase(action)) {
            // get parameter, id, from the request
            String centerID = request.getParameter("centerID");
            if (centerID != null) {
                // call delete record method in the database
                bdb.updateBookingByDelCenter(centerID);
                cdb.delRecord(centerID);
                // redirect the result to list action 
                response.sendRedirect("handleStaff?action=list");
            }
        } else if ("getEditCenter".equalsIgnoreCase(action)) {
            // obtain the parameter id;
            String centerID = request.getParameter("centerID");
            if (centerID != null) {
                // call query db to get retrieve for a customer with the given id
                CenterInfo center = cdb.queryCenterByID(centerID);
                // set the customer as attribute in request scope
                request.setAttribute("center", center);
                // forward the result to the editCustomer.jsp            
                RequestDispatcher rd = this.getServletContext()
                        .getRequestDispatcher("/editCenter.jsp");
                rd.forward(request, response);
            }
        } else if ("book".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer
            int userID = Integer.parseInt(request.getParameter("userID"));
            ArrayList<Booking> booking = bdb.queryBookingListForStaff();
            UserInfo user = udb.queryUserByID(userID);
            // set the result into the attribute	
            request.setAttribute("booking", booking);
            request.setAttribute("user", user);
            // redirect the result to the listCustomers.jsp
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listBookingRecordstaff.jsp");
            rd.forward(request, response);
        } else if ("getEditStatus".equalsIgnoreCase(action)) {
            // obtain the parameter id;
            int bookingID = Integer.parseInt(request.getParameter("bookingID"));
            int userID = Integer.parseInt(request.getParameter("userID"));
            if (bookingID != 0) {
                // call query db to get retrieve for a customer with the given id
                UserInfo user = udb.queryUserByID(userID);
                Booking booking = bdb.queryBookingByID(bookingID);
                // set the customer as attribute in request scope
                request.setAttribute("user", user);
                request.setAttribute("booking", booking);
                // forward the result to the editCustomer.jsp            
                RequestDispatcher rd = this.getServletContext()
                        .getRequestDispatcher("/editStatus.jsp");
                rd.forward(request, response);
            }
        } else {
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
