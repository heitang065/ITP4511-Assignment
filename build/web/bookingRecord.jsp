<%-- 
    Document   : bookingRecord
    Created on : Apr 29, 2022, 4:20:57 AM
    Author     : Hei
--%>

<link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
<%@page import="ict.bean.CenterInfo"%>
<%@page import="ict.bean.UserInfo"%>
<%@page import="ict.bean.Booking"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dream Gym Online System</title>
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
                            ArrayList<UserInfo> trainer = (ArrayList<UserInfo>) request.getAttribute("trainer");
                            out.println("<h1>Personal Trainer</h1>");
                            out.println("<table border='1' id='usertable'><tr>");
                            // loop through the customer array to display each customer record
                            for (int i = 0; i < trainer.size(); i++) {
                                UserInfo ui = trainer.get(i);
                                out.println("<th><a href=\"handleManager?action=displayRecord&trainerID=" + ui.getTrainerID() + "\" >" + ui.getTrainerID() + "</a></th>");
                            }
                            out.println("</tr></table>");
                        %>
                        <%
                            ArrayList<CenterInfo> center = (ArrayList<CenterInfo>) request.getAttribute("center");
                            out.println("<h1>Gym Center</h1>");
                            out.println("<table border='1' id='usertable'><tr>");
                            // loop through the customer array to display each customer record
                            for (int i = 0; i < center.size(); i++) {
                                CenterInfo ci = center.get(i);
                                out.println("<th><a href=\"handleManager?action=displayRecord&centerID=" + ci.getCenterID() + "\" >" + ci.getCenterID() + "</a></th>");
                            }
                            out.println("</tr></table>");
                        %>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>