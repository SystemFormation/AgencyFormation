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

        <div>${team.getCompetenza()}</div>

    </div>
</div>
</body>
</html>
