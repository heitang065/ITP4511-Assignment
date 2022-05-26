<%-- 
    Document   : editCustomer
    Created on : Apr 9, 2022, 1:52:48 PM
    Author     : Hei
--%>

<link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="trainer" scope="request" class="ict.bean.UserInfo" />
<%
    String fname = trainer.getFname();
    String lname = trainer.getLname();
    String gender = trainer.getGender();
    String email = trainer.getEmail();
    String phone = trainer.getPhone();
    int age = trainer.getAge();
    String availability = trainer.getAvailability();
    String trainerID = trainer.getTrainerID();
    String password = trainer.getPassword();

    String type = trainer.getTrainerID() != null ? "Edit Trainer" : "Add Trainer";
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
                                if ("Add Trainer".equals(type)) {
                                    out.print("Password: <input type=\"text\" name=\"password\" value=\"" + password + "\" /><br>");
                                    out.print("TrainerID: <input type=\"text\" name=\"trainerID\" value=\"" + trainerID + "\" /><br>");
                                } else {
                                    out.print("<input type=\"hidden\" name=\"trainerID\"  value=\"" + trainerID + "\" />");
                                }
                            %>
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
