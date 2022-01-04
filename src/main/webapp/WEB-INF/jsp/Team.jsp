<%@ page import="it.unisa.agency_formation.team.domain.Team" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.agency_formation.autenticazione.domain.Dipendente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    ArrayList<Team> listTeam = (ArrayList<Team>) request.getAttribute("listTeam");
    ArrayList<Dipendente> listDip = (ArrayList<Dipendente>) request.getAttribute("listDip");
%>
<html>
<head>
    <link rel="stylesheet" href="css/Common.css">
    <link rel="icon" type="image/png" href="img/Logo%20Team%204-5.png"/>
    <title>Team</title>
</head>
<body>
<c:import url="/static/Header.jsp"/>
<h1>Team</h1>
<div class="flex">
    <c:choose>
        <c:when test="${user.getRole()==3}">
            <c:forEach var="team" items="${listTeam}">
                <div class="team">
                    <div class="team-inf">
                        <div><h2>${team.getNomeTeam()}</h2></div>
                        <div><h3>${team.getNomeProgetto()}</h3></div>
                        <div><h4>Numero dipendenti:${team.getNumeroDipendenti()}</h4></div>
                    </div>
                    <div class="team-descr">
                        <h4>Descrizione</h4>
                        <div>${team.getDescrizione()}</div>
                    </div>

                    <div class="team-dip">
                        <h4>Dipendenti</h4>
                        <div id="flex-team-dip">
                            <c:forEach var="dip" items="listDip">

                            </c:forEach>
                        </div>
                    </div>
                    <div class="team-button">
                        <div id="flex-team-button">
                            <button><a href="/static/Error.html">Specifica Competenze</a></button>
                            <br>
                            <c:if test="${team.getNumeroDipendenti() < 10}">
                                <button><a href="DipendenteControl">Aggiungi Dipendenti</a></button>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:when>

        <c:when test="${user.getRole()==4}">
            <c:forEach var="team" items="${listTeam}">

            </c:forEach>
        </c:when>
    </c:choose>

</div>
</body>
</html>
