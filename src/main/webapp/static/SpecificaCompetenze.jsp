<%@ page import="it.unisa.agency_formation.autenticazione.domain.RuoliUtenti" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    int idTeam = (int) request.getAttribute("idTeam");
%>
<html>
<head>
    <link rel="stylesheet" href="css/Common.css">
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <title>Competenze</title>
</head>
<body>
<c:import url="Header.jsp"/>
<h1>Competenze</h1>
<div class="content-competenze">
    <div class="content">
        <div class="form">
            <form action="SpecificaCompetenzeControl" method="post" id="specificaCompetenze">
                <input type="hidden" name="action" value="competenze">
                <textarea id="specCompetenze" name="specCompetenze" rows="6" cols="50"
                          placeholder="Specifica le competenze"></textarea><br>
                <input type="hidden" name="idTeam" value="${idTeam}">
                <input type="submit" name="specifica" value="Invia" id="specifica"><br>
            </form>
        </div>
    </div>
</div>
</body>
</html>
