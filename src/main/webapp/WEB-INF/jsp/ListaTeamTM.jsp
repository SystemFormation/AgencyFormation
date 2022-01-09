<%@ page import="it.unisa.agency_formation.team.domain.Team" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.agency_formation.autenticazione.domain.Dipendente" %>
<%@ page import="it.unisa.agency_formation.autenticazione.domain.RuoliUtenti" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    ArrayList<Team> listTeam = (ArrayList<Team>) request.getAttribute("listTeam");
    ArrayList<Dipendente> listDip = (ArrayList<Dipendente>) request.getAttribute("listDip");
%>

<html>
<head>
    <link rel="stylesheet" href="css/Common.css">
    <link rel="icon" type="image/png" href="img/Logo%20Team%204-5.png"/>
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="js/Formazione.js"></script>
    <title>Lista Team</title>
</head>
<body>
<c:import url="/static/Header.jsp"/>
<h1>Team</h1>
<div class="flex">
    <c:set var="indexSkill" value="0" scope="page"/>
    <c:forEach var="team" items="${listTeam}">
        <div class="team">
            <div class="team-inf">
                <div id="flex-team"><h2>${team.getNomeTeam()}</h2></div>
                <div id="flex-team"><h3>${team.getNomeProgetto()}</h3></div>
                <div id="flex-team"><h4>Numero massimo dipendenti: ${team.getNumeroDipendenti()}</h4></div>
            </div>
            <div class="team-descr">
                <h4>Descrizione</h4>
                <div id="flex-team">${team.getDescrizione()}</div>
            </div>
            <div class="team-dip">
                <h4>Dipendenti</h4>
                <div id="flex-team-dip">
                    <c:forEach var="dip" items="${listDip}">
                        <c:if test="${dip.getTeam().getIdTeam() == team.getIdTeam()}">
                            <div>${dip.getName()} ${dip.getSurname()}
                                <a href="RemoveTeamControl?idDip=${dip.getIdDipendente()}"><img
                                        src="img/Delete.png"></a>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <div class="team-button">
                <div id="flex-team-button">
                    <c:if test="${team.getCompetenza()==null}">
                        <button onclick="viewSpecifySkills(${indexSkill})" class="dropdown">Specifica Competenze
                        </button>
                    </c:if>
                    <div name="drop" style="display:none">
                        <form action="SpecificaCompetenzeControl" method="post"
                              id="specificaCompetenze">
                            <input type="hidden" name="action" value="competenze">
                            <textarea id="specCompetenze" name="specCompetenze" rows="6" cols="25"
                                      placeholder="Specifica le competenze"></textarea><br>
                            <input type="hidden" name="idTeam" value="${team.getIdTeam()}">
                            <input type="submit" name="specifica" value="Invia" id="specifica"><br>
                        </form>
                    </div>
                    <br>
                    <c:set var="index" value="0" scope="page"/>
                    <c:forEach var="dip" items="${listDip}">
                        <c:if test="${dip.getTeam().getIdTeam() == team.getIdTeam()}">
                            <c:set var="index" value="${index + 1}" scope="page"/>
                        </c:if>
                    </c:forEach>
                    <div name="drop-aggiungi" style="display: inline">
                        <c:if test="${index < team.getNumeroDipendenti()}">
                            <button><a
                                    href="AggiuntaDipendente?idTeam=${team.getIdTeam()}">Aggiungi
                                Dipendenti</a>
                            </button>
                            <br>
                        </c:if>
                    </div>
                    <button name="drop-sciogli" style="display: inline"><a
                            href="ScioglimentoTeamControl?idTeam=${team.getIdTeam()}">Sciogli Team</a>
                    </button>
                </div>
            </div>
        </div>
        <c:set var="indexSkill" value="${indexSkill + 1}" scope="page"/>
    </c:forEach>
</div>
</body>
</html>
