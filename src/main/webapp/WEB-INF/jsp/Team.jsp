<%@ page import="it.unisa.agency_formation.autenticazione.domain.Dipendente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%
    Dipendente dip = (Dipendente) request.getAttribute("dip");
%>
<html>
<head>
    <link rel="stylesheet" href="css/Team.css">
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <title>Creazione Team</title>
</head>
<body>
<%@include file="Header.jsp"%>
<h1>CREAZIONE TEAM</h1>
<div class="container">
    <div class="project">
        <form action="TeamControl" method="post" id="formTeam">
            <input type="hidden" name="action" value="crea">
            <label for="formTeam">Nome del Team</label>
            <input type="text" id="fname" name="fname" value="John"><br>
            <label for="formTeam">Nome del Progetto</label><br>
            <input type="text" id="lname" name="lname" value="Doe">

            <label for="quantity">Numero Dipendenti:</label>
            <input type="number" id="quantity" name="quantity" min="1" max="">
            <input type="submit">


            <textarea id="teamDescr" name="teamDescr" rows="15" cols="70" placeholder="Descrizione "></textarea>
            <input type="submit" name="Crea" value="Crea" id="Cerca">



        </form>








    </div>





</div>

</body>
</html>