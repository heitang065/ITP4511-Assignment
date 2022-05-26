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
                <jsp:include page="smMenu.jsp" />
                <jsp:include page="footer.jsp" />
            </div>
            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">
                        <%
                            ArrayList<UserInfo> user = (ArrayList<UserInfo>) request.getAttribute("user");
                            out.println("<h1>List All User</h1>");
                            out.println("<table border='1' id='usertable'>");
                            out.println("<tr>");
                            out.println("<th>UserID</th><th>Type</th><th>Name</th><th>Gender</th><th>Email</th>"
                                    + "<th>Phone</th><th>Age</th><th>Availability</th><th>TrainerID</th>"
                                    + "<th colspan='2'><a href='editUser.jsp' >Add User</a></th>");
                            out.println("</tr>");
                            // loop through the customer array to display each customer record
                            for (int i = 0; i < user.size(); i++) {
                                UserInfo ui = user.get(i);
                                out.println("<tr>");
                                out.println("<td>" + ui.getUserID() + "</td>");
                                if ("M".equals(ui.getType()))  {
                                    out.println("<td>Senior Manager</td>");
                                } else if ("S".equals(ui.getType()))  {
                                    out.println("<td>Staff</td>");
                                } else if ("P".equals(ui.getType()))  {
                                    out.println("<td>Personal Trainer</td>");
                                } else if ("C".equals(ui.getType()))  {
                                    out.println("<td>Customer</td>");
                                }
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
                                out.println("<td>" + ui.getTrainerID() + "</td>");
                                out.println("<td><a href=\"handleManager?action=deleteUser&userID="
                                        + ui.getUserID() + "\">Delete</a></td>");
                                out.println("<td><a href=\"handleManager?action=getEditUser&userID="
                                        + ui.getUserID() + "\">Edit</a></td>");
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