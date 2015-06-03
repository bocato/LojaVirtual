<%@ page import="Loja.LLogin" %>
<%--
  Created by IntelliJ IDEA.
  User: gap
  Date: 02/06/15
  Time: 00:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  HttpSession sessao = request.getSession();
  LLogin login;
  login = (LLogin) sessao.getAttribute("login");
  if ( login == null || !login.getPermission().equals("admin") ) {
    response.sendRedirect("/LojaVirtual/ops");
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
  <script src="js/admin.js"></script>
</head>
<body style="background-color: #4c759a">
<h1>Loja Virtual -> Admin</h1>
<table  align="right">
  <tr>
    <td><form action='/LojaVirtual/logout' method='POST'><input type="submit" name="logout" value="Logout"></form></td>
  </tr>
</table>
<table  align="center">
<tr>
<td>
<form name="cadprod" method="POST" action="/LojaVirtual/cadProd" onSubmit="return ProdCadastreValidate()">
  <table align="left">
    <tr>
      <td colspan="2">
        <p style="text-align: center"><b>Cadastrar produto</b></p>
      </td>
    <tr>
    <tr>
      <td>Produto ID:</td>
      <td><input type="text" name="prodi"></td>
    </tr>
    <tr>
      <td>Produto Nome:</td>
      <td><input type="text" name="prodn"></td>
    </tr>
    <tr>
      <td>Produto Preço:</td>
      <td><input type="text" name="prodp"></td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" value="Cadastrar" style="float: right"/>
      </td>
    <tr>
  </table>
</form>
</td>
<td>
  <form name="cadcli" method="POST" action="/LojaVirtual/cadCli" onSubmit="return CliCadastreValidate()">
    <table align="right">
      <tr>
        <td colspan="2">
          <p style="text-align: center"><b>Cadastrar cliente</b></p>
        </td>
      <tr>
      <tr>
        <td>Usuário:</td>
        <td><input type="text" name="user"></td>
      </tr>
      <tr>
        <td>Senha:</td>
        <td><input type="password" name="pass1"></td>
      </tr>
      <tr>
        <td>Confirmar senha:</td>
        <td><input type="password" name="pass2"></td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="submit" value="Cadastrar" style="float: right"/>
        </td>
      <tr>
    </table>
  </form>
</td>
</tr>
</table>
</body>
</html>