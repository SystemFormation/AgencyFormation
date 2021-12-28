<%@ page import="it.unisa.agency_formation.autenticazione.domain.Utente" %><%--
  Created by IntelliJ IDEA.
  User: Vincenzo
  Date: 22/12/2021
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Utente User = (Utente)request.getSession().getAttribute("User");%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <%if(User == null){ %>
  <a href="LoginControl"> Login</a>
  <a href="RegistrazioneControl"> Registrazione</a>
      <%} %>
    <%if(User != null){ %>
  <a href="LogoutControl">Logout</a>
    <%}%>
</html>
