<%@ page import="it.unisa.agency_formation.autenticazione.domain.Utente" %><%--
  Created by IntelliJ IDEA.
  User: Vincenzo
  Date: 22/12/2021
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Utente user = (Utente)request.getSession().getAttribute("user");%>
<% if(user!=null){int ruolo = user.getRole();}%>
<html>
  <head>
    <title>HomePage</title>
  </head>
  <body>
  <%if(user == null){ %>
    <a href="html/Login.html"> Login</a>
    <a href="html/Registrazione.html"> Registrazione</a>
  <%}else if(user != null){  %>
  <a href="LogoutControl">Logout</a>
  <%}%>
  </body>
</html>
