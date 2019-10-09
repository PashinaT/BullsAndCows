


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>
<%@ page import="app.entities.User" %>
<%@ page import="app.entities.Rating" %>
<%@ page import="java.sql.SQLException" %>


<html>
<head>
    <title>Добро пожаловать!</title>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>

</head>
<body>

<script type="text/javascript">
    <%@include file="/resources/js/main.js"%>
</script>

<% Object errorLogin = session.getAttribute("errorLogin"); %>
<% Object errorPassword = session.getAttribute("errorPassword"); %>
<% Object userLogin = session.getAttribute("userLogin"); %>
<%  Object confirmPassword = session.getAttribute("confirmError"); %>
<% Object errorNewlogin = session.getAttribute("exist");%>


<div class="main">
    <div class="heading">
        <h2>Добро пожаловать в игру "Быки и Коровы"!</h2>
    </div>
    <hr class="line">
    <div class="rules">
        <div class="rule_heading">
            Правила
        </div>
        <div class="rule_text">
            Компьютер загадывает 4-х значное число. Цифры загаданного числа не повторяются.
            Задача пользователя угадать загаданное число.
            У пользователя неограниченное число попыток.
            В каждую попытку пользователь дает компьютеру свой вариант числа.
            Компьютер сообщает сколько цифр точно угадано (бык) и сколько цифр угадано без учета позиции (корова).
            По ответу компьютера пользователь должен за несколько ходов угадать число.
        </div>
    </div>
    <hr class="line">
    <% if (userLogin==null){%>
    <div class="start_game">
        <div class="start_game__text">
            Чтобы начать игру войдите в свой личный кабинет или пройдите <a href="#registration" id="hrefReg" onclick="executeSomething()">регистрацию</a>.
        </div>
        <div class="autorization">
            <form action="${pageContext.request.contextPath}/autorization" method="post">

                <div class="form-group">
                    <div class="form_item">
                        <label for="login"> Логин:  </label>
                        <input class="form-control" type="text" name="login" id="login" value="${login}" required
                               placeholder="Введите логин">
                    </div>
                    <%if (errorLogin != null) {%>
                    <span class="errortext"> Неверный логин </span>
                    <%}%>
                    <div class="form_item">
                        <label for="password">Пароль: </label>
                        <input class="form-control" type="password" name="password" id="password" value="${password}" required
                               placeholder="Введите пароль">
                    </div>
                    <%if (errorPassword != null) {%>
                    <span class="errortext"> Неверный пароль </span>
                    <%}%>
                    <div class="form_button">
                        <input type="submit" name="signup" value="Войти" class="btn">
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="reg">

        <div class="reg_heading">
            <h4>Регистрация</h4>
        </div>
        <div class="reg_form">
            <form action="${pageContext.request.contextPath}/userRegistration" method="post">

                <div class="form-group">
                    <div class="form_item">
                        <label for="newLogin"> Логин:  </label>
                        <input class="form-control" type="text" name="newLogin" id="newLogin" onkeypress="return (event.charCode >= 65 && event.charCode <= 90) || (event.charCode >= 97 && event.charCode <= 122) || (event.charCode >= 48 && event.charCode <= 57)" value="${newLogin}" required
                               placeholder="Введите логин(ENG)">
                    </div>
                    <%if (errorNewlogin != null) {%>
                        <script type="text/javascript">
                            executeSomething();
                        </script>
                        <span class="errortext"> Такой логин уже существует </span>
                    <%}%>
                    <div class="form_item">
                        <label for="newPassword">Пароль: </label>
                        <input class="form-control" type="password" name="newPassword" id="newPassword" value="${newPassword}" required
                               placeholder="Введите пароль">
                    </div>
                    <div class="form_item">
                        <label for="confirmPassword">Подтвердите пароль: </label>
                        <input class="form-control" type="password" name="confirmPassword" id="confirmPassword" value="${confirmPassword}" required
                               placeholder="Введите пароль повторно">
                    </div>
                        <%if(confirmPassword !=null){%>
                        <script type="text/javascript">
                            executeSomething();
                        </script>
                        <span  class="errortext"> Пароль не совпадает </span>
                    <%}%>
                    <div class="form_button">
                        <input type="submit" name="signup" value="Зарегистрироваться" class="btn">
                    </div>
                </div>
            </form>
        </div>
    </div>

<%} else{%>
    Вы вошли как ${userLogin}

    <form action="${pageContext.request.contextPath}/logout" method="post">

        <div class="form_button">
            <input type="submit" name="signup" value="Выйти" class="btn">
        </div>

    </form>

    <form action="${pageContext.request.contextPath}/startGame" method="post">

        <div class="form_button">
            <input type="submit" name="signup" value="Начать игру" class="btn">
        </div>

    </form>

<%}%>

</div>

</body>
</html>
