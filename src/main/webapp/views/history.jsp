<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 08.10.2019
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Object userLogin = session.getAttribute("userLogin"); %>

<html>
<head>
    <title>История</title>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
</head>
<body>
<div class="main">
    <% if (userLogin!=null){%>
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

        <form action="${pageContext.request.contextPath}/startGame" method="post">
            <div class="form_button">
                <input type="submit" name="signup" value="Играть" class="btn">
            </div>
        </form>

    </div>

    <hr class="line">

    <div class="heading">
        <h2>История</h2>
    </div>

    <hr class="line">

    <div class="history">
        <table>
            <tr>
                <td>№ игры :</td>
                <td>Попытки</td>
            </tr>

            <c:forEach items="${history}" var="tr">
                <tr>
                    <td><c:out value = "${tr.gameNumber}"/></td>
                    <td><c:out value = "${tr.textHistory}"/></td>
                </tr>

             </c:forEach>

        </table>
    </div>
    <%} else {
        String redirectURL = "views/index.jsp";
        response.sendRedirect(redirectURL);
    }%>

</div>

</body>
</html>