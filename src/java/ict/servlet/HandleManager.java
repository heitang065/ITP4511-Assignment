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
@WebServlet(name = "HandleManager", urlPatterns = {"/handleManager"})
public class HandleManager extends HttpServlet {

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
        
        if ("record".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            ArrayList<UserInfo> trainer = udb.queryTrainer();
            ArrayList<CenterInfo> center = cdb.queryCenter();
            // set the result into the attribute	 
            request.setAttribute("trainer", trainer);
            request.setAttribute("center", center);
            // redirect the result to the listCustomers.jsp
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/bookingRecord.jsp");
            rd.forward(request, response);
        } else if ("displayRecord".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer
            String trainerID = request.getParameter("trainerID");
            String centerID = request.getParameter("centerID");
            ArrayList<Booking> trainer = bdb.queryBookingByTrainerID(trainerID);
            ArrayList<Booking> center = bdb.queryBookingByCenterID(centerID);
            ArrayList<Booking> booking = bdb.queryBookingList();
            ArrayList<CenterInfo> centerInfo = cdb.queryCenterInfo();
            // set the result into the attribute	
            request.setAttribute("trainer", trainer);
            request.setAttribute("center", center);
            request.setAttribute("booking", booking);
            request.setAttribute("centerInfo", centerInfo);
            // redirect the result to the listCustomers.jsp
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listBookingForManager.jsp");
            rd.forward(request, response);
        } else if ("list".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            ArrayList<UserInfo> user = udb.queryUser();
            // set the result into the attribute	 
            request.setAttribute("user", user);
            // redirect the result to the listCustomers.jsp
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listUser.jsp");
            rd.forward(request, response);
        } else if ("deleteUser".equalsIgnoreCase(action)) {
            // get parameter, id, from the request
            int userID = Integer.parseInt(request.getParameter("userID"));
            if (userID != 0) {
                // call delete record method in the database
                bdb.updateBookingByDelUser(userID);
                udb.delUserRecord(userID);
                // redirect the result to list action 
                response.sendRedirect("handleManager?action=list");
            }
        } else if ("getEditUser".equalsIgnoreCase(action)) {
            // obtain the parameter id;
            int userID = Integer.parseInt(request.getParameter("userID"));
            if (userID != 0) {
                // call query db to get retrieve for a customer with the given id
                UserInfo user = udb.queryUserByID(userID);
                // set the customer as attribute in request scope
                request.setAttribute("user", user);
                // forward the result to the editCustomer.jsp            
                RequestDispatcher rd = this.getServletContext()
                        .getRequestDispatcher("/editUser.jsp");
                rd.forward(request, response);
            }
        }/* else if ("deleteCenter".equalsIgnoreCase(action)) {
            // get parameter, id, from the request
            String centerID = request.getParameter("centerID");
            if (centerID != null) {
                // call delete record method in the database
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
