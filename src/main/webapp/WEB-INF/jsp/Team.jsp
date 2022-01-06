<%@ page import="it.unisa.agency_formation.team.domain.Team" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.agency_formation.autenticazione.domain.Dipendente" %>
<%@ page import="it.unisa.agency_formation.autenticazione.domain.RuoliUtenti" %>
<%@ page import="it.unisa.agency_formation.autenticazione.domain.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    ArrayList<Team> listTeam = (ArrayList<Team>) request.getAttribute("listTeam");
    ArrayList<Dipendente> listDip = (ArrayList<Dipendente>) request.getAttribute("listDip");
    ArrayList<Utente> listUser = (ArrayList<Utente>) request.getAttribute("listUser");
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
        <c:when test="${user.getRole()==RuoliUtenti.TM}">
            <c:forEach var="team" items="${listTeam}">
                <div class="team">
                    <div class="team-inf">
                        <div id="flex-team"><h2>${team.getNomeTeam()}</h2></div>
                        <div id="flex-team"><h3>${team.getNomeProgetto()}</h3></div>
                        <div id="flex-team"><h4>Numero dipendenti:${team.getNumeroDipendenti()}</h4></div>
                    </div>
                    <div class="team-descr">
                        <h4>Descrizione</h4>
                        <div id="flex-team">${team.getDescrizione()}</div>
                    </div>
                    <div class="team-dip">
                        <h4>Dipendenti</h4>
                        <div id="flex-team-dip">
                            <c:forEach var="dip" items="${listDip}">
                                    <c:forEach var="user" items="${listUser}">
                                        <c:if test="${user.getId() == dip.getIdDipendente()}">
                                            <div>${user.getName()} ${user.getSurname()}</div>
                                        </c:if>
                                    </c:forEach>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="team-button">
                        <div id="flex-team-button">
                            <button><a href="/static/Error.html">Specifica Competenze</a></button>
                            <br>
                            <c:if test="${team.getNumeroDipendenti() <= 8}">
                                <button><a href="DipendenteControl">Aggiungi Dipendenti</a></button>
                                <br>
                            </c:if>
                            <!-- Implementare questa funzione con js e aggiungere una Servlet -->
                            <button><a href="ScioglimentoTeamControl?idTeam=${team.getIdTeam()}">Scioglimento Team</a>
                            </button>
                            <button><a href="/static/Error.html">Visualizza Materiale</a></button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:when>

        <c:when test="${user.getRole()==RuoliUtenti.HR}">
            <c:forEach var="team" items="${listTeam}">
                <div class="team">
                    <div class="team-inf">
                        <div id="flex-team"><h2>${team.getNomeTeam()}</h2></div>
                        <div id="flex-team"><h3>${team.getNomeProgetto()}</h3></div>
                        <div id="flex-team"><h4>Numero dipendenti:${team.getNumeroDipendenti()}</h4></div>
                    </div>
                    <div class="team-descr">
                        <h4>Descrizione</h4>
                        <div id="flex-team">${team.getDescrizione()}</div>
                    </div>
                    <!-- Da rivedere perchè l'IdTeam è sempre uguale a 0 -->
                    <div class="team-dip">
                        <h4>Dipendenti</h4>
                        <div id="flex-team-dip">
                            <c:forEach var="dip" items="${listDip}">
                                <c:if test="${dip.getIdTeam() == team.getIdTeam()}">
                                    <c:forEach var="user" items="${listUser}">
                                        <c:if test="${user.getId() == dip.getIdDipendente()}">
                                            <div>${user.getName()} ${user.getSurname()}</div>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="team-button">
                        <div id="flex-team-button">
                            <button><a href="/static/Error.html">Visualizza Competenze</a></button>
                            <br><br>

                            <!-- Si dvee implementare questa fuznionalità  e aggiungere la Servlet -->
                            <form action="UploadMaterialeControl" id="materiale" method="post"
                                  enctype="multipart/form-data">
                                <p class="par">Materiale di Formazione</p><br>
                                <input type="file" id="fileMateriale" name="materiale" size="50"><br>
                                <input type="hidden" id="idTeam" name="idTeam" value="${team.getIdTeam()}">
                                <input type="submit" value="Carica" id="uploadMateriale">
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:when>
    </c:choose>

</div>
</body>
</html>
