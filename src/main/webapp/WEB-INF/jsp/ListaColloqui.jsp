<%@ page import="it.unisa.agency_formation.autenticazione.domain.Utente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.agency_formation.reclutamento.domain.Candidatura" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Candidatura candidatura = (Candidatura) request.getAttribute("candidatura");
    ArrayList<Utente> candidati = (ArrayList<Utente>) request.getAttribute("candidati");
%>
<html>
<head>
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="css/Common.css">
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <script type="text/javascript" src="js/Candidature.js"></script>
    <title>Colloqui</title>
</head>
<body>
<c:import url="/static/Header.jsp"/>
<h1>Lista Colloqui</h1>
<div class="content-colloqui">
    <div id="flex-head">ID</div>
    <div id="flex-head">Nome</div>
    <div id="flex-head">Cognome</div>
    <div id="flex-head">Azione</div>
    <c:set var="index" value="0" scope="page"/>
    <c:if test="${candidati==null}"><h1>Non ci sono colloqui</h1>
    </c:if>
    <c:forEach var="cand" items="${candidati}">
        <c:if test="${candidatura.getStato() == StatiCandidatura.Accettata}">
        <div id="flex">${cand.getId()}</div>
        <div id="flex">${cand.getName()}</div>
        <div id="flex">${cand.getSurname()}</div>
        <div id="flex">
            <button onclick="assumi(${cand.getId()})">Assumi</button>
            <button onclick="rejectCandidature(${cand.getId()})">Rifiuta</button>
        </div>
        <c:set var="index" value="${index + 1}" scope="page"/>
        </c:if>
    </c:forEach>
</div>
</div>
</body>
</html>
