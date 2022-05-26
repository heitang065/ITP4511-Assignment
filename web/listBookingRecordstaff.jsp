<%-- 
    Document   : listTrainerCenter
    Created on : Apr 27, 2022, 8:55:16 PM
    Author     : Hei
--%>

<link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
<%@page import="ict.bean.UserInfo"%>
<%@page import="ict.bean.Booking"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="request" class="ict.bean.UserInfo" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dream Gym Online System</title>
    </head>
    <body>
        <div id="maincontainer">
            <div id="menucolumn">
                <jsp:include page="staffMenu.jsp" />
                <jsp:include page="footer.jsp" />
            </div>
            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">
                        <%
                            ArrayList<Booking> booking = (ArrayList<Booking>) request.getAttribute("booking");
                            out.println("<h1>All Booking Record by Personal Trainer</h1>");
                            out.println("<table border='1' id='usertable'>");
                            out.println("<tr>");
                            out.println("<th>BookingID</th><th>Date</th><th>Time</th><th>TrainerID</th>"
                                    + "<th>CenterID</th><th>UserID</th><th>Status</th><th></th>");
                            out.println("</tr>");
                            // loop through the customer array to display each customer record
                            for (int i = 0; i < booking.size(); i++) {
                                Booking bk = booking.get(i);
                                out.println("<tr>");
                                out.println("<td>" + bk.getBookingID() + "</td>");
                                out.println("<td>" + bk.getDate() + "</td>");
                                out.println("<td>" + bk.getTime() + "</td>");
                                out.println("<td>" + bk.getTrainerID() + "</td>");
                                out.println("<td>" + bk.getCenterID() + "</td>");
                                out.println("<td>" + bk.getUserID() + "</td>");
                                if ("W".equals(bk.getStatus()))  {
                                    out.println("<td>Pending</td>");
                                } else if ("C".equals(bk.getStatus())){
                                    out.println("<td>Canceled</td>");
                                } else if ("D".equals(bk.getStatus())){
                                    out.println("<td>Declined</td>");
                                } else if ("A".equals(bk.getStatus())){
                                    out.println("<td>Approved</td>");
                                } else if ("R".equals(bk.getStatus())){
                                    out.println("<td>Removed</td>");
                                } else if ("T".equals(bk.getStatus())){
                                    out.println("<td>Timeouted</td>");
                                }
                                if ("W".equals(bk.getStatus())) {
                                    out.println("<td><a href=\"handleStaff?action=getEditStatus&userID="
                                            + user.getUserID() + "&bookingID="
                                            + bk.getBookingID() + "\">Edit Status</a></td>");
                                } else {
                                    out.println("<td></td>");
                                }
                                out.println("</tr>");
                            }
                            out.println("</table>");
                        %>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>