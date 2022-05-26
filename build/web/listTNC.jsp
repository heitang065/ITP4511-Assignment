<%-- 
    Document   : listTrainerCenter
    Created on : Apr 27, 2022, 8:55:16 PM
    Author     : Hei
--%>

<link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
<%@page import="ict.bean.CenterInfo"%>
<%@page import="ict.bean.UserInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dream Gym Online Booking</title>
    </head>
    <body>
        <div id="maincontainer">
            <div id="menucolumn">
                <jsp:include page="custMenu.jsp" />
                <jsp:include page="footer.jsp" />
            </div>
            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">
                        <%
                            ArrayList<UserInfo> trainer = (ArrayList<UserInfo>) request.getAttribute("trainer");
                            out.println("<h1>Personal Trainer</h1>");
                            out.println("<table border='1' id='usertable'>");
                            out.println("<tr>");
                            out.println("<th>TrainderID</th><th>Name</th><th>Gender</th><th>Email</th><th>Phone</th><th>Age</th><th>Availability</th>");
                            out.println("</tr>");
                            // loop through the customer array to display each customer record
                            for (int i = 0; i < trainer.size(); i++) {
                                UserInfo ui = trainer.get(i);
                                out.println("<tr>");
                                out.println("<td>" + ui.getTrainerID() + "</td>");
                                out.println("<td>" + ui.getFname() + " " + ui.getLname() + "</td>");
                                out.println("<td>" + ui.getGender() + "</td>");
                                out.println("<td>" + ui.getEmail() + "</td>");
                                out.println("<td>" + ui.getPhone() + "</td>");
                                out.println("<td>" + ui.getAge() + "</td>");
                                if ("T".equals(ui.getAvailability())) {
                                    out.println("<td><input type=\"checkbox\" disabled checked /></td>");
                                } else {
                                    out.println("<td><input type=\"checkbox\" disabled /></td>");
                                }
                                out.println("</tr>");
                            }
                            out.println("</table>");
                        %>
                        <%
                            ArrayList<CenterInfo> center = (ArrayList<CenterInfo>) request.getAttribute("center");
                            out.println("<h1>Gym Center</h1>");
                            out.println("<table border='1' id='usertable'>");
                            out.println("<tr>");
                            out.println("<th>CenterID</th><th>Location</th><th>Hourly Rate</th><th>Capacity</th><th>Open Hour</th><th>Availability</th><th>Description</th>");
                            out.println("</tr>");
                            // loop through the customer array to display each customer record
                            for (int i = 0; i < center.size(); i++) {
                                CenterInfo ci = center.get(i);
                                out.println("<tr>");
                                out.println("<td>" + ci.getCenterID() + "</td>");
                                out.println("<td>" + ci.getLocation() + "</td>");
                                out.println("<td>" + ci.getHourlyRate() + "</td>");
                                out.println("<td>" + ci.getCapacity() + "</td>");
                                out.println("<td>" + ci.getOpenHour() + "</td>");
                                if ("T".equals(ci.getAvailability())) {
                                    out.println("<td><input type=\"checkbox\" disabled checked /></td>");
                                } else {
                                    out.println("<td><input type=\"checkbox\" disabled /></td>");
                                }
                                out.println("<td>" + ci.getDescription() + "</td>");
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