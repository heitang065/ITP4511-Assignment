<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <title>Dream Gym Online Booking</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style type="text/css">
            fieldset {
                width: 45%;
                padding: 20px 0 20px 0;
                margin-top: 25px;
            }
            input {
                margin: 10px;
                width: 200px;
            }
            input[type=submit] {
                margin: 10px;
                width: 100px;
            }
        </style>
    </head>
    <body>
        <div id="maincontainer">
            <div id="menucolumn">
                <jsp:include page="menu.jsp"/>
            </div>
            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">
                        <jsp:include page="login.jsp"/>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
