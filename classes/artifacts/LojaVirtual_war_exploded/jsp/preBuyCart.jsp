<%@ page import="Loja.LLogin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  HttpSession sessao = request.getSession();
  LLogin login;
  login = (LLogin) sessao.getAttribute("login");
  if ( login == null || login.getPermission() == null ) {
    response.sendRedirect("/LojaVirtual/ops");
  }
%>
<!DOCTYPE html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Loja Virtual</title>
  <style>
    h1 {
      color: black;
      text-align: center;
    }
  </style>
  <script src="js/buy.js"></script>
</head>
<body style="background-color: #4c759a">
<h1>Loja Virtual -> Finalizar compra</h1>
<table  align="right">
  <tr>
    <td><form action='/LojaVirtual/cart' method='POST'><input type="submit" name="Voltar" value="Voltar"></form></td>
    <td><form action='/LojaVirtual/logout' method='POST'><input type="submit" name="logout" value="Logout"></form></td>
  </tr>
</table>

<form name="buyCart" method="POST" action="/LojaVirtual/buyCart" onSubmit="return dataBuyValidate()">
<table  align="center">
  <tr>
    <td>Nome:</td>
    <td><input type="text" name="name"></td>
  </tr>
  <tr>
    <td>Endereço:</td>
    <td><input type="text" name="end"></td>
  </tr>
  <tr>
    <td>CPF:</td>
    <td><input type="text" name="cpf"></td>
  </tr>
  <tr>
    <td colspan="2">
      <input type="submit" value="Comprar" style="float: right"/>
    </td>
  <tr>
</table>
  </form>

</body>
