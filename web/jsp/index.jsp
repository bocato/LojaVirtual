<%@ page import="Loja.LLogin" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sessao = request.getSession();
    LLogin login;
    login = (LLogin) sessao.getAttribute("login");
    if (login != null && login.getPermission() != null) {
        if (login.getPermission().equals("admin")) {
            response.sendRedirect("/LojaVirtual/admin");
        } else if (login.getPermission().equals("client")) {
            response.sendRedirect("/LojaVirtual/client");
        } else {
            response.sendRedirect("/LojaVirtual/logout");
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Loja Virtual</title>
    <style>
        h1 {
            color: black;
            text-align: center;
        }
    </style>
    <script src="js/login.js"></script>
</head>
<body style="background-color: #4c759a">
<h1>Loja Virtual -> Login</h1>
<form name="login" method="POST" action="/LojaVirtual/login" onSubmit="return LoginValidate()">
    <table  align="center">
        <tr>
            <td>Usuário:</td>
            <td><input type="text" name="user"></td>
        </tr>
        <tr>
            <td>Senha:</td>
            <td><input type="password" name="pass"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Logar" style="float: right"/>
            </td>
        <tr>
    </table>
</form>
</body>
</html>