<%@ page import="it.unisa.agency_formation.autenticazione.domain.Utente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%
    ArrayList<Utente> list = (ArrayList<Utente>) request.getAttribute("candidati");
%>
<html>
<head>
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="js/Candidature.js"></script>
    <link rel="stylesheet" href="css/Common.css">
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <title>Candidati</title>
</head>
<body>
<c:import url="/static/Header.jsp"/>
<h1>Lista Candidati</h1>
<div class="content-candidati">
    <div class="content">
            <div id="flex-head">ID</div>
            <div id="flex-head">Nome</div>
            <div id="flex-head">Cognome</div>
            <div id="flex-head">Email</div>
            <div id="flex-head">Azione</div>
<c:set var="index" value="0" scope="page"/>
    <c:forEach var="cand" items="${candidati}">
            <div id="flex">${cand.getId()}</div>
            <div id="flex">${cand.getName()}</div>
            <div id="flex">${cand.getSurname()}</div>
            <div id="flex">${cand.getEmail()}</div>
            <div id="flex"><button onclick="view(${index});viewLink(${cand.getId()},${index})">Mostra file</button></div>
        <div name="drop" class="dropdown-content">
            <a href="DownloadControl?toDownload=curriculum&idCandidato=${cand.getId()}" name="hrefCurriculum">
                <img src="img/Curriculum.png"><p>Curriculum</p>
            </a>
            <a href="DownloadControl?toDownload=documenti&idCandidato=${cand.getId()}" name="hrefDocumenti">
                <img src="img/Documenti.png"><p>Documenti</p>
            </a>
        </div>
        <c:set var="index" value="${index + 1}" scope="page"/>
    </c:forEach>
    </div>
</div>
</body>
</html>