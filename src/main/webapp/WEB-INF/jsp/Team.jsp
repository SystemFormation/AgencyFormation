<%@ page import="it.unisa.agency_formation.team.domain.Team" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%
    ArrayList<Team> listteam = (ArrayList<Team>) request.getAttribute("listTeam");
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
<div class="content flex">
    <div class="team">
    <c:choose>
        <c:when test="${user.getRole()==3}">
            <c:forEach var="team" items="${listTeam.doRetrieveTMTeam(user.getId)}">
                <h2>${team.getNomeTeam()}<h2>
                <h3>${team.getNomeProgetto()}</h3>
                ${team.getNumeroDipendenti()}
                ${team.getDescrizione()}
                    <button><a href="/static/Error.html">Specifica Competenze</a></button>
                    <c:if test="${team.getNumeroDipendenti()}">
                    <button><a href="DipendenteControl">Cerca Dipendenti</a></button>
                    </c:if>
            </c:forEach>
        </c:when>

        <c:when test="${user.getRole()==4}">
            <c:forEach var="team" items="${listTeam.doRetrieveAllTeam()}">

            </c:forEach>
        </c:when>
    </c:choose>
    </div>
</div>
</body>
</html>
