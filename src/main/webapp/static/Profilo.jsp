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
            <ul>
                <li><b>${user.getName()} ${user.getSurname()}</b></li>
                <li> Residenza: ${dip.getResidenza()}</li>
                <li> Telefono: ${dip.getTelefono()} </li>
                <li> Anno di Nascita: ${dip.getAnnoNascita()} </li>
            </ul>
        </div>
        <div class="form-button">
            <h2>Stato</h2>
            <c:if test="${dip.getStato() == StatiDipendenti.DISPONIBILE}">
                <div class="disponibile">.</div>
            </c:if>
            <c:if test="${dip.getStato() == StatiDipendenti.OCCUPATO}">
                <div class="occupato">.</div>
            </c:if>
            <br>
            <div class="dropdown">
                <button id="gestioneSkill" onclick="viewSkill()">Gestione Skill</button>
            </div>
            <br>
            <a href="/AgencyFormation/static/Error.html">
                <button> Modifica Dati Personali</button>
            </a>
        </div>
    </div>
    <div class="listSkill">
        <h2>Lista Skill</h2>
        <c:set var="index" value="0"/>
        <c:forEach var="skill" items="${dip.getSkills()}">
        <p>${dip.getSkills().get(index).getNomeSkill()}<p>
            <c:set var="index" value="${index + 1}"/>
        </c:forEach>
    </div>
</div>
<div class="addSkill" id="addSkill" style="display: none">
    <h2> Aggiungi Skill </h2>
    <form action="SkillControl" method="post" id="formLogin">
        <input type="text" id="skillName" name="skillName" placeholder="Nome Skill" required><br>
        <textarea id="skillDescr" name="skillDescr" rows="15" cols="40" placeholder="Descrizione Skill"
                  required></textarea><br>
        <input type="number" id="quantity" name="quantity" placeholder="Livello Skill" min="1" max="5" required><br>
        <input type="submit" value="Aggiungi" id="Aggiungi">
    </form>
</div>
</body>
</html>
