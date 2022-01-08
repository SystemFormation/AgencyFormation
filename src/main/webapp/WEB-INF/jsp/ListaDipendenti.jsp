<%@ page import="it.unisa.agency_formation.autenticazione.domain.Dipendente" %>
<%@ page import="it.unisa.agency_formation.autenticazione.domain.RuoliUtenti" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.agency_formation.autenticazione.domain.StatiDipendenti" %>
<%@ page import="it.unisa.agency_formation.team.domain.Team" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    ArrayList<Dipendente> dip = (ArrayList<Dipendente>) request.getAttribute("dipendenti");
    ArrayList<Team> teams = (ArrayList<Team>) request.getAttribute("teams");
    int idTeam = (int) request.getAttribute("idTeam");
%>
<html>
<head>
    <link rel="stylesheet" href="css/Common.css">
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <title>Dipendenti</title>
</head>
<body>
<c:import url="/static/Header.jsp"/>
<h1>Lista Dipendenti</h1>

<div class="content">
    <div class="information">
        <div id="flex-head">ID Dipendente</div>
        <div id="flex-head">Competenze</div>
        <div id="flex-head">Azione</div>
        <div id="flex-head">Stato</div>

        <c:forEach var="dip" items="${dipendenti}">
            <c:if test="${dip.getStato() == StatiDipendenti.DISPONIBILE}">
            <div id="flex">${dip.getIdDipendente()}</div>
            <!-- DA VEDERE-->
            <div id="flex">
                <button onclick="view(${index});viewLink(${cand.getId()},${index})">Mostra skill</button>
            </div>

            <div id="flex">
                    <a href="AddTeamControl?action=aggiungi&id=${dip.getIdDipendente()}&idTeam=${idTeam}">Aggiungi</a>
            </div>
                    <div id="flex">
                        <div class="disponibile">.</div>
                    </div>
            </c:if>
        </c:forEach>
    </div>
</div>
</body>
</html>
