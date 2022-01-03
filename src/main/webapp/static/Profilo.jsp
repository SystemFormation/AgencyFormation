<%@ page import="it.unisa.agency_formation.autenticazione.domain.Dipendente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <link rel="stylesheet" href="css/Profilo.css">
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <title>Profilo</title>
</head>
<body>
<c:import url="Header.jsp"/>
<h1> Profilo Personale</h1>
<div class="content-all">
    <div class="identity">
        <ul>
            <li> <b>${user.getName()} ${user.getSurname()}</b> </li>
            <li> Residenza: ${dip.getResidenza()} </li>
            <li> Telefono: ${dip.getTelefono()} </li>
            <li> Anno di Nascita: ${dip.getAnnoNascita()} </li>
        </ul>

    </div>
    <div class="form-button">
        <c:if test="${dip.isStato() == true}">
            <div class ="disponibile">.</div>
        </c:if>
        <c:if test="${dip.isStato() == false}">
            <div class="occupato">.</div>
        </c:if>
        <a href="/AgencyFormation/static/Error.html"><button> Gestione Skill </button></a>
        <br>
        <a href="/AgencyFormation/static/Error.html"><button> Modifica Dati Personali </button></a>
    </div>

</div>

<div class = "addSkill">
    <h2> Aggiungi Skill </h2>
    <form action="SkillControl" method="post" id="formLogin">
        <input type="text" id="skillName" name="skillName" placeholder="Nome Skill" required><br>
        <textarea id="skillDescr" name="skillDescr" rows="15" cols="40" placeholder="Descrizione Skill" required></textarea><br>
        <input type="submit" value="Aggiungi" id="Aggiungi">
    </form>
</div>
</body>
</html>
