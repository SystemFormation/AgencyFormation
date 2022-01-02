<%@ page import="it.unisa.agency_formation.autenticazione.domain.Dipendente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    ArrayList<Dipendente> list = (ArrayList<Dipendente>) request.getAttribute("dipendenti");
%>
<html>
<head>
    <link rel="stylesheet" href="css/Dipendenti.css">
    <link rel="stylesheet" href="css/Common.css">
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <title>Dipendenti</title>
</head>
<body>
<%@include file="Header.jsp" %>
<h1>Lista Dipendenti</h1>

<div class="content">
    <div class="found">
        <form action="DipendenteControl" method="post" id="formDip">
            <select name="subject" id="subject">
                <option value="null" selected="selected" id=0>---</option>
                <option value="occupati" id=1>Occupati</option>
                <option value="disponibili" id=2>Disponibili</option>
            </select>
        </form>
    </div>
    <div class="information">
        <div id="flex-head">ID Dipendente</div>
        <div id="flex-head">ID Team</div>
        <div id="flex-head">Competenze</div>
        <div id="flex-head">Azione</div>
        <div id="flex-head">Stato</div>
    <c:choose>
    <c:when test="${value == null}">
    <c:forEach var="dip" items="${dipendenti}">
        <div id="flex">${dip.getIdDipendente()}</div>
        <div id="flex">${dip.getIdTeam()}</div>
        <div id="flex"><button onclick="view(${index});viewLink(${cand.getId()},${index})">Mostra file</button></div>
        <div name="drop" class="dropdown-content">
            <a href="DownloadControl?toDownload=curriculum&idCandidato=${cand.getId()}" name="hrefCurriculum">
                <img src="img/Curriculum.png"><p>Curriculum</p>
            </a>
            <a href="DownloadControl?toDownload=documenti&idCandidato=${cand.getId()}" name="hrefDocumenti">
                <img src="img/Documenti.png"><p>Documenti</p>
            </a>
        </div>
        <div id="flex"> <c:if test="(user.getRole() == 3 && dip.isStato() == true)">
            <a href="TeamControl?action=aggiungi&id=${dip.getIdDipendente()}">Aggiungi</a>
            </c:if> </div>

        <div id="flex"> <c:if test="(user.getRole() == 3 && dip.isStato() == false)">
            <p>Richiedi Disponibilità</p> <!-- Da implementare mandando una notifica al TM -->
        </c:if> </div>

        <c:if test="${dip.isStato() == false}">
            <div id="flex">Occupato</div>
        </c:if>
        <c:if test="${dip.isStato() == true}">
            <div id="flex">Disponibile</div>
        </c:if>
    </c:forEach>
    </c:when>

    <c:when test="${value == disponibili}">
    <c:forEach var="dip" items="${dipendenti}">
        <c:if test="${dip.isStato() == true}">
            <c:forEach var="dip" items="${dipendenti}">
                <div id="flex">${dip.getIdDipendente()}</div>
                <div id="flex">${dip.getIdTeam()}</div>
                <div id="flex">${dip.getAnnoNascita()}</div>
                <div id="flex">${dip.getResidenza()}</div>
                <div id="flex">${dip.getTelefono()}</div>
                <div id="flex">Disponibile</div>
            </c:forEach>
        </c:if>
    </c:forEach>
    </c:when>

    <c:when test="${value == occupati}">
    <c:forEach var="dip" items="${dipendenti}">
        <c:if test="${dip.isStato() == false}">
            <c:forEach var="dip" items="${dipendenti}">
                <div id="flex">${dip.getIdDipendente()}</div>
                <div id="flex">${dip.getIdTeam()}</div>
                <div id="flex">${dip.getAnnoNascita()}</div>
                <div id="flex">${dip.getResidenza()}</div>
                <div id="flex">${dip.getTelefono()}</div>
                <div id="flex">Occupato</div>
            </c:forEach>
        </c:if>
    </c:forEach>
    </c:when>
    </c:choose>

    </div>
</div>
</body>
</html>
