<%-- 
    Document   : listTrainerCenter
    Created on : Apr 27, 2022, 8:55:16 PM
    Author     : Hei
--%>

<link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
<%@page import="ict.bean.CenterInfo"%>
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
                            ArrayList<Booking> trainer = (ArrayList<Booking>) request.getAttribute("trainer");
                            ArrayList<Booking> center = (ArrayList<Booking>) request.getAttribute("center");
                            ArrayList<Booking> booking = (ArrayList<Booking>) request.getAttribute("booking");
                            ArrayList<CenterInfo> centerInfo = (ArrayList<CenterInfo>) request.getAttribute("centerInfo");
                            double total = 1, rateCnt = 1, income = 0, rate = 0, hourlyRate = 0;
                            String centerID = null;
                            for (int i = 0; i < booking.size(); i++) {
                                total++;
                            }
                            if (!trainer.isEmpty()) {
                                for (int i = 0; i < trainer.size(); i++) {
                                    Booking bkt = trainer.get(i);
                                    centerID = bkt.getCenterID();
                                    for (int j = 0; j < centerInfo.size(); j++) {
                                        CenterInfo ci = centerInfo.get(j);
                                        if (centerID.equals(ci.getCenterID())) {
                                            hourlyRate = ci.getHourlyRate();
                                            break;
                                        }
                                    }
                                    rateCnt++;
                                    income += hourlyRate;
                                }
                                rate = rateCnt / total * 100;
                                out.println("<h1>All Booking Record by Personal Trainer</h1>");
                                out.println("<h2>TrainerID: " + trainer.get(0).getTrainerID() + "</h2>");
                                out.println("<h2>Summarize: " + "</h2>");
                                out.println("<h3>Booking Rate: " + String.format("%.2f", rate) + "%" + " /Month , "
                                        + String.format("%.2f", rate) + "%" + " /Year " + "</h3>");
                                out.println("<h3>Income Generated: $" + String.format("%.2f", income) + " /Month , $"
                                        + String.format("%.2f", income) + " /Year " + "</h3>");
                                out.println("<table border='1' id='usertable'>");
                                out.println("<tr>");
                                out.println("<th>BookingID</th><th>Date</th><th>Time</th><th>TrainerID</th>"
                                        + "<th>CenterID</th><th>UserID</th><th>Status</th>");
                                out.println("</tr>");
                                // loop through the customer array to display each customer record
                                for (int i = 0; i < trainer.size(); i++) {
                                    Booking bkt = trainer.get(i);
                                    out.println("<tr>");
                                    out.println("<td>" + bkt.getBookingID() + "</td>");
                                    out.println("<td>" + bkt.getDate() + "</td>");
                                    out.println("<td>" + bkt.getTime() + "</td>");
                                    out.println("<td>" + bkt.getTrainerID() + "</td>");
                                    out.println("<td>" + bkt.getCenterID() + "</td>");
                                    out.println("<td>" + bkt.getUserID() + "</td>");
                                    if ("W".equals(bkt.getStatus())) {
                                        out.println("<td>Pending</td>");
                                    } else if ("C".equals(bkt.getStatus())) {
                                        out.println("<td>Canceled</td>");
                                    } else if ("D".equals(bkt.getStatus())) {
                                        out.println("<td>Declined</td>");
                                    } else if ("A".equals(bkt.getStatus())) {
                                        out.println("<td>Approved</td>");
                                    } else if ("R".equals(bkt.getStatus())) {
                                        out.println("<td>Removed</td>");
                                    } else if ("T".equals(bkt.getStatus())) {
                                        out.println("<td>Timeouted</td>");
                                    }
                                    out.println("</tr>");
                                }
                            } else if (!center.isEmpty()) {
                                for (int i = 0; i < center.size(); i++) {
                                    Booking bkt = center.get(i);
                                    centerID = bkt.getCenterID();
                                    for (int j = 0; j < centerInfo.size(); j++) {
                                        CenterInfo ci = centerInfo.get(j);
                                        if (centerID.equals(ci.getCenterID())) {
                                            hourlyRate = ci.getHourlyRate();
                                            break;
                                        }
                                    }
                                    rateCnt++;
                                    income += hourlyRate;
                                }
                                rate = rateCnt / total * 100;
                                income = rateCnt * hourlyRate;
                                out.println("<h1>All Booking Record by Gym Center</h1>");
                                out.println("<h2>CenterID: " + center.get(0).getCenterID() + "</h2>");
                                out.println("<h2>Summarize: " + "</h2>");
                                out.println("<h3>Booking Rate: " + String.format("%.2f", rate) + "%" + " /Month , "
                                        + String.format("%.2f", rate) + "%" + " /Year " + "</h3>");
                                out.println("<h3>Income Generated: $" + String.format("%.2f", income) + " /Month , $"
                                        + String.format("%.2f", income) + " /Year " + "</h3>");
                                out.println("<table border='1' id='usertable'>");
                                out.println("<tr>");
                                out.println("<th>BookingID</th><th>Date</th><th>Time</th><th>TrainerID</th>"
                                        + "<th>CenterID</th><th>UserID</th><th>Status</th>");
                                out.println("</tr>");
                                for (int i = 0; i < center.size(); i++) {
                                    Booking bkc = center.get(i);
                                    out.println("<tr>");
                                    out.println("<td>" + bkc.getBookingID() + "</td>");
                                    out.println("<td>" + bkc.getDate() + "</td>");
                                    out.println("<td>" + bkc.getTime() + "</td>");
                                    out.println("<td>" + bkc.getTrainerID() + "</td>");
                                    out.println("<td>" + bkc.getCenterID() + "</td>");
                                    out.println("<td>" + bkc.getUserID() + "</td>");
                                    if ("W".equals(bkc.getStatus())) {
                                        out.println("<td>Pending</td>");
                                    } else if ("C".equals(bkc.getStatus())) {
                                        out.println("<td>Canceled</td>");
                                    } else if ("D".equals(bkc.getStatus())) {
                                        out.println("<td>Declined</td>");
                                    } else if ("A".equals(bkc.getStatus())) {
                                        out.println("<td>Approved</td>");
                                    } else if ("R".equals(bkc.getStatus())) {
                                        out.println("<td>Removed</td>");
                                    } else if ("T".equals(bkc.getStatus())) {
                                        out.println("<td>Timeouted</td>");
                                    }
                                    out.println("</tr>");
                                }
                            } else {
                                out.println("<h2>There is no record for this Personal Trainer or Gym Center.</h2>");
                            }
                            out.println("</table>");
                        %>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>