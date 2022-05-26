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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hei
 */
@WebServlet(name = "HandleCustomer", urlPatterns = {"/handleCustomer"})
public class HandleCustomer extends HttpServlet {

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
            ArrayList<UserInfo> trainer = udb.queryTrainer();
            ArrayList<CenterInfo> center = cdb.queryCenter();
            // set the result into the attribute	 
            request.setAttribute("trainer", trainer);
            request.setAttribute("center", center);
            // redirect the result to the listCustomers.jsp
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listTNC.jsp");
            rd.forward(request, response);
        } else if ("book".equalsIgnoreCase(action)) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            // call the query db to get retrieve for all customer 
            ArrayList<UserInfo> trainer = udb.queryTrainer();
            UserInfo user = udb.queryUserByID(userID);
            ArrayList<CenterInfo> center = cdb.queryCenter();
            // set the result into the attribute	 
            request.setAttribute("trainer", trainer);
            request.setAttribute("center", center);
            request.setAttribute("user", user);
            // redirect the result to the listCustomers.jsp
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/calendar.jsp");
            rd.forward(request, response);
        } else if ("check".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            int userID = Integer.parseInt(request.getParameter("userID"));
            ArrayList<Booking> booking = bdb.queryBookingListForCust(userID);
            UserInfo user = udb.queryUserByID(userID);
            // set the result into the attribute	 
            request.setAttribute("booking", booking);
            request.setAttribute("user", user);
            // redirect the result to the listCustomers.jsp
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listBooking.jsp");
            rd.forward(request, response);
        }/*else if ("getEditCustomer".equalsIgnoreCase(action)) {
            // obtain the parameter id;
            String id = request.getParameter("id");
            if (id != null) {
                // call query db to get retrieve for a customer with the given id
                UserInfo customer = db.queryCustByID(id);
                // set the customer as attribute in request scope
                request.setAttribute("c", customer);
                // forward the result to the editCustomer.jsp            
                RequestDispatcher rd = this.getServletContext()
                        .getRequestDispatcher("/editCustomer.jsp");
                rd.forward(request, response);
            }
        } /*else if ("search".equalsIgnoreCase(action)) {

            // obtain the parameter name;
            String name = request.getParameter("name");
            if (name != null) {
                ArrayList<UserInfo> customers;
                // call queryByName from db 
                ArrayList<UserInfo> customer = db.queryCustByName(name);
                // to retrieve for customers with the given name
                request.setAttribute("customers", customer);
                // set the result into the attribute in request
                // forward the result to the listCustoemrs.jsp
                RequestDispatcher rd = getServletContext()
                        .getRequestDispatcher("/listCustomers.jsp");
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
