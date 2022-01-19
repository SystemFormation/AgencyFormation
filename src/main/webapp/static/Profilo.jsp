<%@ page import="it.unisa.agency_formation.autenticazione.domain.StatiDipendenti" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/Common.css">
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="js/Formazione.js"></script>
    <title>Profilo</title>
</head>
<body>
<c:import url="Header.jsp"/>
<h1> Profilo Personale</h1>
<div class="flex">
    <div class="content-profile">
        <div class="identity">
            <div class="nome">
                <b>${user.getName()} ${user.getSurname()}</b>
            </div>
            Residenza: ${dip.getResidenza()} &nbsp; &nbsp;
            Telefono: ${dip.getTelefono()} &nbsp; &nbsp;
            Anno di Nascita: ${dip.getAnnoNascita()}
        </div>
        <div class="form-button">
            <div class="dropdown">
                <a href="#formLogin"><button id="gestioneSkill" onclick="viewAddSkill()" >Aggiungi Skill</button></a>
            </div>
            <br>
            <a href="/AgencyFormation/static/Error.jsp">
                <button> Modifica Dati Personali</button>
            </a>
        </div>
        <div class="stato-dip">
            <c:if test="${dip.getStato() == StatiDipendenti.DISPONIBILE}">
                <h2>Disponibile</h2>
                <div class="disponibile">.</div>
            </c:if>
            <c:if test="${dip.getStato() == StatiDipendenti.OCCUPATO}">
                <h2>Occupato</h2>
                <div class="occupato">.</div>
            </c:if>
        </div>
    </div>
    <div class="listSkill">
        <h2>Lista Skills</h2>
        <c:set var="index" value="0"/>
        <c:choose>
        <c:when test="${dip.getSkills() == null}">
            <p> Non hai aggiunto nessuna skill</p>
        </c:when>
        <c:otherwise>
        <c:forEach var="skill" items="${dip.getSkills()}">
        <p>${dip.getSkills().get(index).getNomeSkill()}<p>
            <c:set var="index" value="${index + 1}"/>
        </c:forEach>
        </c:otherwise>
        </c:choose>
    </div>
</div>
<div class="addSkill" id="addSkill" style="display: none">
    <br> <br>
    <h2> Aggiungi Skill </h2>
    <form action="SkillControl" method="post" id="formLogin">
        <input type="text" id="skillName" name="skillName" placeholder="Inserisci nome skill" required><br>
        <textarea id="skillDescr" name="skillDescr" rows="15" cols="40"
                  placeholder="Inserisci la descrizione della skill"
                  required></textarea><br>
        <input type="number" id="quantity" name="quantity" placeholder="Livello da 1 a 5" min="1" max="5" required><br>
        <input type="submit" value="Aggiungi" id="Aggiungi">
    </form>
</div>
</body>
</html>
