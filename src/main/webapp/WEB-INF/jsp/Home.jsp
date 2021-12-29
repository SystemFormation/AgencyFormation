<%@ page import="it.unisa.agency_formation.autenticazione.domain.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Utente user = (Utente)request.getSession().getAttribute("user");
%>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Bentornato <%user.getName();%></h1>
</body>
</html>
