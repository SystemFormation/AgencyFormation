<%@ page import="it.unisa.agency_formation.autenticazione.domain.Utente" %>
<%@ page import="it.unisa.agency_formation.reclutamento.domain.Candidatura" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%
    Utente user = (Utente) request.getSession().getAttribute("user");
    Candidatura cand = (Candidatura) request.getSession().getAttribute("cand");
%>
<html>
<head>
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <link rel="stylesheet" href="css/Documents.css">
    <title>Documenti</title>
</head>
<body>
<h1>Lista Documenti</h1>
<div class="content">
    <h2> ${user.getName()} ${user.getSurname()} </h2>
    <c:forEach var="user" items="${categoriesList}">
    <div class="block">

    </div>
    </c:forEach>
    <div class="buttons">
        <button id="accept" name="accept">Accetta Candidatura</button>
        <button id="reject" name="reject">Rifiuta Candidatura</button>
    </div>
</div>
</body>
</html>
