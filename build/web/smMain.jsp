<%-- 
    Document   : welcome
    Created on : Apr 26, 2022, 11:24:40 AM
    Author     : Hei
--%>

<link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
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
                        <jsp:useBean id="userInfo" class="ict.bean.UserInfo" scope="session"/>
                        <h2>Welcome back, <jsp:getProperty name="userInfo" property="lname"/></h2><br>
                        <p>Welcome to Dream Gym Online Booking System</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
