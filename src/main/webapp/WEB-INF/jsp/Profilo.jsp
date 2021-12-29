<%@ page import="it.unisa.agency_formation.autenticazione.domain.Dipendente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%Dipendente dip = (Dipendente) request.getAttribute("dip");%>
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
            <li> <%=user.getName()%> <%=user.getSurname()%> </li>
            <li> Residenza: <%=dip.getResidenza()%> </li>
            <li> Telefono: <%=dip.getTelefono()%> </li>
            <li> Anno di Nascita: <%=dip.getAnnoNascita()%> </li>
        </ul>
    </div>

    <div class="form-button">
        <a href="jsp/GestioneSkill.jsp"><button> Gestione Skill </button></a>
        <br>
        <a href="jsp/Profilo.jsp"><button> Modifica Dati Personali </button></a>
    </div>
</div>
</body>
</html>
