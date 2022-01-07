<%@ page import="it.unisa.agency_formation.autenticazione.domain.Dipendente" %>
<%@ page import="it.unisa.agency_formation.autenticazione.domain.RuoliUtenti" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.agency_formation.autenticazione.domain.StatiDipendenti" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    ArrayList<Dipendente> dip = (ArrayList<Dipendente>) request.getAttribute("dipendenti");
%>

<html>
<head>
    <link rel="stylesheet" href="css/Dipendenti.css">
    <link rel="stylesheet" href="css/Common.css">
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <title>Dipendenti</title>
</head>
<body>
<c:import url="/static/Header.jsp"/>
<h1>Lista Dipendenti</h1>

<div class="content">
    <!-- <div class="found">
        <form action="DipendenteControl" method="post" id="formDip">
            <select name="subject" id="subject">
                <option value="null" selected="selected" id=0>---</option>
                <option value="occupati" id=1>Occupati</option>
                <option value="disponibili" id=2>Disponibili</option>
            </select>
        </form>
    </div> -->

    <div class="information">
        <div id="flex-head">ID Dipendente</div>
        <div id="flex-head">ID Team</div>
        <div id="flex-head">Competenze</div>
        <div id="flex-head">Stato</div>

        <c:forEach var="dip" items="${dipendenti}">
            <div id="flex">${dip.getIdDipendente()}</div>
            <div id="flex">${dip.getIdTeam()}</div>

            <!-- DA RIVEDERE -->
            <div id="flex">
                <button onclick="view(${index});viewLink(${cand.getId()},${index})">Mostra skill</button>
            </div>

            <c:if test="${dip.getStato() == StatiDipendenti.OCCUPATO}">
                <div id="flex-dip">
                    <div class="occupato">.</div>
                </div>
            </c:if>
            <c:if test="${dip.getStato() == StatiDipendenti.DISPONIBILE}">
                <div id="flex-dip">
                    <div class="disponibile">.</div>
                </div>

            </c:if>

        </c:forEach>


    </div>
</div>
</body>
</html>
