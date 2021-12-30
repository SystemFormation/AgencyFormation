<%@ page import="it.unisa.agency_formation.autenticazione.domain.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
        <c:if test="${(user!=null && user.getRole()>1 && user.getRole()<=4)}">
        <li>Team</li>
        </c:if>
        <li><a href="WEB-INF/jsp/Upload.jsp">Upload</a></li>
    </ul>
    <div class="header-right">
        <ul>
            <c:if test="${user == null}">
            <li><a href="html/Login.html">Accedi</a></li>
            <li><a href="html/Registrazione.html">Registrati</a></li>
            </c:if>
                <c:choose>
                    <c:when test="${user.getRole() == 1}">
                        <li>Candidato</li>
                    </c:when>
                    <c:when test="${user.getRole() == 2}">
                        <li>Dipendente</li>
                    </c:when>
                    <c:when test="${user.getRole() == 3}">
                        <li>TM</li>
                    </c:when>
                    <c:when test="${user.getRole() == 4}">
                        <li>HR</li>
                    </c:when>
                </c:choose>

                <c:if test="${user != null}">
                <li><a href="LogoutControl">Logout</a></li>
                </c:if>
            </ul>
    </div>
</div>
</body>
</html>