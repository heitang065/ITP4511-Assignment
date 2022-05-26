<%-- 
    Document   : booking
    Created on : Apr 26, 2022, 10:39:40 PM
    Author     : Hei
--%>

<link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
<%@page import="ict.bean.CenterInfo"%>
<%@page import="ict.bean.UserInfo"%>
<%@page import="ict.bean.Booking"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="request" class="ict.bean.UserInfo" />
<!DOCTYPE html>
<%
    int userID = user.getUserID();
%>
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
                        <h2>Please choose a day you want to book.</h2>
                        <form method="get" action="bookingController">
                            <input type="hidden" name="action" value="Request Booking" />
                            <input type="hidden" name="userID" value="<%=userID%>" />
                            Date:
                            <select name="date" >
                                <option disabled selected hidden>
                                <option value="22/4/2022">22/4/2022
                                <option value="23/4/2022">23/4/2022
                                <option value="24/4/2022">24/4/2022
                                <option value="25/4/2022">25/4/2022
                                <option value="26/4/2022">26/4/2022
                                <option value="27/4/2022">27/4/2022
                                <option value="28/4/2022">28/4/2022
                            </select>
                            <br><br>
                            Time: 
                            <select name="time">
                                <option disabled selected hidden>
                                <option value="11:00">11:00
                                <option value="12:00">12:00
                                <option value="13:00">13:00
                                <option value="14:00">14:00
                                <option value="15:00">15:00
                                <option value="16:00">16:00
                                <option value="17:00">17:00
                                <option value="18:00">18:00
                                <option value="19:00">19:00
                                <option value="20:00">20:00
                                <option value="21:00">21:00
                                <option value="22:00">22:00
                            </select>
                            <br><br>
                            Personal Trainer: 
                            <select name="trainerName">
                                <option disabled selected hidden>
                                    <%
                                        ArrayList<UserInfo> trainer = (ArrayList<UserInfo>) request.getAttribute("trainer");
                                        // loop through the customer array to display each customer record
                                        for (int i = 0; i < trainer.size(); i++) {
                                            UserInfo ui = trainer.get(i);
                                            out.println("<option value=\"" + ui.getFname() + " " + ui.getLname()
                                                    + "\">" + ui.getFname() + " " + ui.getLname());
                                        }
                                    %>
                            </select>
                            <br><br>
                            Center: 
                            <select name="centerID">
                                <option disabled selected hidden>
                                    <%
                                        ArrayList<CenterInfo> center = (ArrayList<CenterInfo>) request.getAttribute("center");
                                        // loop through the customer array to display each customer record
                                        for (int i = 0; i < center.size(); i++) {
                                            CenterInfo ci = center.get(i);
                                            out.println("<option value=\"" + ci.getCenterID() + "\">" + ci.getCenterID());
                                        }
                                    %>
                            </select>
                            <br><br>
                            <input type="submit" value="Request Booking"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
