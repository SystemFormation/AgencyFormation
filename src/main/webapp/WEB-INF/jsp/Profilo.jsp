<%@ page import="it.unisa.agency_formation.autenticazione.domain.Dipendente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%
    Dipendente dip = (Dipendente) request.getAttribute("dip");
%>
<html>
<head>
    <link rel="stylesheet" href="css/Profilo.css">
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <title>Profilo</title>
</head>
<body>
<%@include file="Header.jsp"%>
<h1> PROFILO PERSONALE</h1>
<div class="content-all">
    <div class="identity">
        <ul>
            <li> ${user.getName()} ${user.getSurname()} </li>
            <li> Residenza: ${dip.getResidenza()} </li>
            <li> Telefono: ${dip.getTelefono()} </li>
            <li> Anno di Nascita: ${dip.getAnnoNascita()} </li>
        </ul>


        <div class="form-button">
            <a href="GestioneSkill.jsp"><button> Gestione Skill </button></a>
            <br>
            <a href="Profilo.jsp"><button> Modifica Dati Personali </button></a>
        </div>
    </div>
</div>

<div class = "addSkill">
    <h2> Aggiunta Skill </h2>
    <form action="SkillControl" method="post" id="formLogin">
        <input type="text" id="skillName" name="skillName" placeholder="Nome Skill">
        <textarea id="skillDescr" name="skillDescr" rows="15" cols="70" placeholder="Descrizione Skill"></textarea>
        <input type="submit" value="Aggiungi" id="Aggiungi">
    </form>
</div>
</body>
</html>
