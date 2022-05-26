<%-- 
    Document   : editCustomer
    Created on : Apr 9, 2022, 1:52:48 PM
    Author     : Hei
--%>

<link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="request" class="ict.bean.UserInfo" />
<%
    int userID = user.getUserID();
    String type = user.getType();
    String fname = user.getFname();
    String lname = user.getLname();
    String gender = user.getGender();
    String email = user.getEmail();
    String phone = user.getPhone();
    int age = user.getAge();
    String availability = user.getAvailability();
    String trainerID = user.getTrainerID();
    String password = user.getPassword();

    String mode = user.getUserID() != 0 ? "Edit User" : "Add User";
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
                <jsp:include page="smMenu.jsp" />
                <jsp:include page="footer.jsp" />
            </div>
            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">
                        <h1><%=mode%></h1>
                        <form  method="get" action="handleEdit">
                            <input type="hidden" name="action"  value="<%=mode%>" />
                            <input type="hidden" name="userID"  value="<%=userID%>" />
                            Type:
                            <select name="type">
                                <option selected hidden value="<%=type%>"><%=type%></option>
                                <option value="P">Personal Trainer</option>
                                <option value="C">Customer</option>
                                <option value="S">Staff</option>
                                <option value="M">Senior Manager</option>
                            </select><br>
                            First Name: <input type="text" name="fname" value="<%=fname%>" /><br>
                            Last Name: <input type="text" name="lname" value="<%=lname%>" /><br>
                            Gender:
                            <select name="gender">
                                <option selected hidden value="<%=gender%>"><%=gender%></option>
                                <option value="M">M</option>
                                <option value="F">F</option>
                            </select><br>
                            Email: <input type="text" name="email" value="<%=email%>" /><br>
                            Phone: <input type="text" name="phone" value="<%=phone%>" /><br>
                            Age: <input type="text" name="age" value="<%=age%>" /><br>
                            <%
                                if ("Add User".equals(mode)) {
                                    out.print("Password: <input type=\"text\" name=\"password\" value=\"" + password + "\" /><br>");
                                } else {
                                    out.print("<input type=\"hidden\" name=\" userID\" value=\"" + userID + "\" />");
                                }
                            %>
                            TrainerID: <input type="text" name="trainerID" value="<%=trainerID%>" /><br>
                            <%
                                if ("T".equals(availability)) {
                                    availability = "checked";
                                } else {
                                    availability = " ";
                                }
                            %>
                            Availability: <input type="checkbox" name="availability" <%=availability%> /><br>
                            <td ><input type="submit" value="submit"/> <br>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
