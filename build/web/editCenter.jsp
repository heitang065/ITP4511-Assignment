<%-- 
    Document   : editCustomer
    Created on : Apr 9, 2022, 1:52:48 PM
    Author     : Hei
--%>

<link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="center" scope="request" class="ict.bean.CenterInfo" />
<%
    String centerID = center.getCenterID();
    String location = center.getLocation();
    int hourlyRate = center.getHourlyRate();
    int capacity = center.getCapacity();
    String availability = center.getAvailability();
    String description = center.getDescription();
    String openHour = center.getOpenHour();

    String type = center.getCenterID() != null ? "Edit Center" : "Add Center";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Customer</title>
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
                        <h1><%=type%></h1>
                        <form  method="get" action="handleEdit">
                            <input type="hidden" name="action"  value="<%=type%>" />
                            CenterID: <input type="text" name="centerID"  value="<%=centerID%>" /><br>
                            Location: <input type="text" name="location" value="<%=location%>" /><br>
                            Hourly Rate: <input type="text" name="hourlyRate" value="<%=hourlyRate%>" /><br>
                            Capacity <input type="text" name="capacity" value="<%=capacity%>" /><br>
                            <%
                                if ("T".equals(availability)) {
                                    availability = "checked";
                                } else {
                                    availability = " ";
                                }
                            %>
                            Availability: <input type="checkbox" name="availability" <%=availability%> /><br>
                            Description: <input type="text" name="description" value="<%=description%>" /><br>
                            Opening Hour: <input type="text" name="openHour" value="<%=openHour%>" /><br>
                            <td><input type="submit" value="submit" /><br>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
