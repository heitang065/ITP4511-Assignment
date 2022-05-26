<%-- 
    Document   : loginError
    Created on : Apr 26, 2022, 11:23:08 AM
    Author     : Hei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Error</title>
    </head>
    <body>
        <p>Incorrect Password</p>
        <p><% out.println("<a href=\"" + request.getContextPath() + "/main\">Login again</a>");%></p>
    </body>
</html>
