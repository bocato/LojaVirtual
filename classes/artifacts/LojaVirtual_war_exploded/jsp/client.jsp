<%@ page import="Loja.LProd" %>
<%@ page import="Loja.Produto" %>
<%@ page import="java.util.List" %>
<%@ page import="Loja.LLogin" %>
<%--
  Created by IntelliJ IDEA.
  User: gap
  Date: 02/06/15
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
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
<br>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Loja Virtual</title>
  <style>
    h1 {
      color: black;
      text-align: center;
    }
  </style>
</head>
<body style="background-color: #4c759a">
<h1>Loja Virtual -> Client</h1>
<table  align="right">
  <tr>
    <td><form action='/LojaVirtual/cart' method='POST'><input type="submit" name="carrinho" value="Carrinho"></form></td>
      <td><form action='/LojaVirtual/logout' method='POST'><input type="submit" name="logout" value="Logout"></form></td>
  </tr>
</table>
<table  align="center" border="1">
  <tr>
    <td><b><center>Id</center></b></td>
    <td><b><center>Produto</center></b></td>
    <td><b><center>Preço</center></b></td>
    <td><b><center>Comprar</center></b></td>
  </tr>

  <%
    LProd prod;
    prod = (LProd) sessao.getAttribute("prod");
    if (prod == null) {
      prod = new LProd();
      sessao.setAttribute("prod", prod);
    }
    List<Produto> produtos = prod.listarProdutos();

    for (Produto produto : produtos) {
      out.println("<tr><td>" + produto.getId() + "</td>");
      out.println("<td>" + produto.getNome() + "</td>");
      out.println("<td>" + produto.getPreco() + "</td>");
      out.println("<td><form action='/LojaVirtual/buyProd' method='POST'>");
      out.println("<input type='submit' name='op' value='Adicionar ao carrinho'></td>");
      out.println("<input type='hidden' name='id' value='" + produto.getId() + "'/></form></td></tr>");
    }

  %>

</table>

</body>
</html>
