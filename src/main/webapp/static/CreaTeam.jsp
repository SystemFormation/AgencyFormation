<%@ page import="it.unisa.agency_formation.autenticazione.domain.Dipendente" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="../css/Common.css">
    <link rel="icon" type="image/png" href="../img/Logo Team 4-5.png"/>
    <title>Creazione Team</title>
</head>
<body>
<jsp:include page="/static/Header.jsp">
    <jsp:param value="true" name="sameLocation"/>
</jsp:include>
<h1>Creazione Team</h1>
<div class="content-createTeam">
    <div class="content">
        <div class="form">
            <form action="../CreateTeamControl" method="post" id="formTeam">
                <input type="hidden" name="action" value="crea">
                <label for="formTeam">Nome del Team</label><br>
                <input type="text" id="fname" name="fname" placeholder="Team" required><br>
                <label for="formTeam">Nome del Progetto</label><br>
                <input type="text" id="lname" name="lname" placeholder="Progetto" required><br>
                <label for="quantity">Numero Dipendenti</label><br>
                <input type="number" id="quantity" name="quantity" min="1" max="8" required><br>
                <label for="quantity">Descrizione</label><br>
                <textarea id="teamDescr" name="teamDescr" placeholder="Descrizione del Team"
                          required></textarea><br>
                <input type="submit" name="crea" value="Crea" id="crea"><br>
            </form>
        </div>
    </div>
</div>
</body>
</html>