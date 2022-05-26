<%-- 
    Document   : editCustomer
    Created on : Apr 9, 2022, 1:52:48 PM
    Author     : Hei
--%>

<link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="booking" scope="request" class="ict.bean.Booking" />
<jsp:useBean id="user" scope="request" class="ict.bean.UserInfo" />
<%
    int bookingID = booking.getBookingID();
    String date = booking.getDate();
    String time = booking.getTime();
    String trainerID = booking.getTrainerID();
    String centerID = booking.getCenterID();
    int userID = booking.getUserID();
    String status = booking.getStatus();
    int currentUserID = user.getUserID();
    String type = user.getType();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Status</title>
    </head>
    <body>
        <div id="maincontainer">
            <div id="menucolumn">
                <%
                    if ("S".equals(type)) {
                %>
                <jsp:include page="staffMenu.jsp" />
                <%
                } else {
                %>
                <jsp:include page="custMenu.jsp" />
                <%
                    }
                %>
                <jsp:include page="footer.jsp" />
            </div>
            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">
                        <h1>Edit Status</h1>
                        <form  method="get" action="handleEdit">
                            <input type="hidden" name="action" value="Edit Status" />
                            <input type="hidden" name="type" value="<%=type%>" />
                            <input type="hidden" name="currentUserID" value="<%=currentUserID%>" />
                            BookingID: <input type="text" name="bookingID" value="<%=bookingID%>" readonly /><br>
                            Date: <input type="text" name="date" value="<%=date%>" readonly /><br>
                            Time: <input type="text" name="time" value="<%=time%>" readonly /><br>
                            TrainerID: <input type="text" name="trainerID" value="<%=trainerID%>" readonly /><br>
                            CenterID: <input type="text" name="centerID" value="<%=centerID%>" readonly /><br>
                            UserID: <input type="text" name="userID" value="<%=userID%>" readonly /><br>
                            Status: 
                            <select name="status">
                                <option disabled selected hidden>
                                    <%
                                        if ("S".equals(type)) {
                                            out.print("<option value = \"A\">Accept");
                                            out.print("<option value = \"D\">Decline");
                                        } else {
                                            out.print("<option value = \"C\">Cancel");
                                        }
                                    %>
                            </select><br>
                            <td ><input type="submit" value="submit"/> <br>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
