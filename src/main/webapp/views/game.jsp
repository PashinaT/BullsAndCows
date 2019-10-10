<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ page import="java.util.*, java.text.*" %>
<%@ page import="app.entities.User" %>
<%@ page import="app.entities.Rating" %>
<%@ page import="java.sql.SQLException" %>
<% Object userLogin = session.getAttribute("userLogin"); %>
<html>
<head>
    <title>Игра</title>
    <script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
</head>
<body>


<script type="text/javascript">
    <%@include file="/resources/js/main.js"%>
</script>


<div class="game">
    <% if (userLogin!=null){%>
        <div class="leftPart">
        <div class="headingPart">
            Вы вошли как: ${userLogin}
            <form action="${pageContext.request.contextPath}/logout" method="post">

                <div class="form_button">
                    <input type="submit" name="signup" value="Выйти" class="btn">
                </div>

            </form>
            <form action="${pageContext.request.contextPath}/rating" method="post">

                <div class="form_button">
                    <input type="submit" name="signup" value="Показать рейтинг" class="btn" >
                </div>

            </form>

            <form action="${pageContext.request.contextPath}/history" method="post">

                <div class="form_button">
                    <input type="submit" name="signup" value="Показать историю"  class="btn" >
                </div>

            </form>

        </div>
        <div class="gameArea">
            <div class="textArea">
                    <textarea id="text_area" cols="100" rows="20" readonly></textarea>
            </div>


            <form id="myForm">
                <input class="input" type="text" id="inputNumber"  pattern=".{4,4}"
                       placeholder="Введите 4-х значное число" required onkeyup="return check(this);"
                       onchange="return check(this); " />
                <button  class="button" type="submit"  id="toSend">Отправить</button>

            </form>

            <form id="myForm1"  >

                <input type="submit" name="btn1" id="1b" value="1234">
            </form>
            <form id="myForm2"  >
                <input type="submit" name="btn1"  id="2b" value="5678">
            </form>

            <form id="myForm3"  >
                <input type="submit" name="btn1"  id="3b" value="0912">
            </form>
            <form id="myForm4"  >
                <input type="submit" name="btn1" id="4b" value="3478">
            </form>







        </div>


    </div>
    <hr noshade class="vertic" >
    <div class="rightPart">
    </div>
    <%} else {
        String redirectURL = "views/index.jsp";
        response.sendRedirect(redirectURL);
    }%>


</div>


</body>
</html>
