<%@ page import="it.unisa.agency_formation.autenticazione.domain.Dipendente" %>
<%@ page import="it.unisa.agency_formation.autenticazione.domain.RuoliUtenti" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.agency_formation.autenticazione.domain.StatiDipendenti" %>
<%@ page import="it.unisa.agency_formation.team.domain.Team" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="js/Formazione.js"></script>
    <title>Dipendenti</title>
</head>
<body>
<c:import url="/static/Header.jsp"/>
<h1>Lista Dipendenti</h1>

<div class="content">
    <div class="information">
        <div id="flex-head">Dipendente</div>
        <div id="flex-head">Competenze</div>
        <div id="flex-head">Azione</div>
        <div id="flex-head">Stato</div>
        <c:set var="index" value="0"/>
        <c:forEach var="dip" items="${dipendenti}">
            <c:if test="${dip.getStato() == StatiDipendenti.DISPONIBILE}">
                <div id="flex">${dip.getName()} ${dip.getSurname()}</div>

                <div id="flex">
                    <c:choose>
                        <c:when test="${dip.getSkills() != null}">
                            <button onclick="viewSkill(${index})" class="dropdown">
                                Mostra skill
                            </button>
                            <div name="drop" class="skills" style="display: none">
                                <c:set var="indexSkill" value="0"/>
                                <c:forEach var="skill" items="${dip.getSkills()}">
                                    ${dip.getSkills().get(indexSKill).getNomeSkill()}
                                    <c:set var="indexSKill" value="${indexSkill + 1}"/>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise> Non sono presenti Skills </c:otherwise>
                    </c:choose>
                </div>

                <div id="flex">
                    <a href="AddTeamControl?action=aggiungi&id=${dip.getIdDipendente()}&idTeam=${idTeam}">Aggiungi</a>
                </div>
                <div id="flex">
                    <div class="disponibile">.</div>
                </div>
            </c:if>
            <c:set var="index" value="${index + 1}" scope="page"/>
        </c:forEach>
    </div>
</div>
</body>
</html>
