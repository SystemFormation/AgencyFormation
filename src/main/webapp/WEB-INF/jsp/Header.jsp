<%@ page import="it.unisa.agency_formation.autenticazione.domain.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Utente user = (Utente) request.getSession().getAttribute("user");
%>
<html>
<head>
    <link rel="stylesheet" href="css/Header.css">
    <title>Header</title>
</head>
<body>
<div class="header">
    <div class="logo">
        <img src="img/Logo Team 4-5.png">
    </div>
    <ul>
        <li><a href="../AgencyFormation">Home</a></li>
        <% if(user!=null && user.getRole()>1 && user.getRole()<=4) {%>
        <li>Team</li>
        <%}%>
        <li><a href="WEB-INF/jsp/Upload.jsp">Upload</a></li>
    </ul>
    <div class="header-right">
        <ul>
            <% if(user==null) {%>
            <li><a href="html/Login.html">Accedi</a></li>
            <li><a href="html/Registrazione.html">Registrati</a></li>
                <% } else if (user!=null) {%>
            <li><a href="LogoutControl">Logout</a></li>
                <% } %>
        </ul>
    </div>
</div>
</body>
</html>