<%@ page import="it.unisa.agency_formation.autenticazione.domain.RuoliUtenti" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/Common.css">
    <link rel="icon" type="image/png" href="img/Logo%20Team%204-5.png"/>
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="js/Reclutamento.js"></script>
    <script type="text/javascript" src="js/Formazione.js"></script>
    <title>Home</title>
</head>
<body>
<jsp:include page="/static/Header.jsp">
    <jsp:param value="false" name="sameLocation"/>
</jsp:include>
<h1>Bentornato ${user.getName()}</h1>
<div class="content">
    <div class="flex">
        <div id="home"><a href="ListaCandidati">
            <h2> Lista Candidati </h2></a>
            <span id="noCandidati"></span>
            <p>Visualizza tutti i candidati che hanno proposto una candidatura.</p>
        </div>
        <div id="home"><a href="ListaTeam">
            <h2> Lista Teams </h2></a>
            <p>Visualizza tutti i team presenti nell'azienda.</p>
        </div>
        <div id="home"><a href="ListaColloqui">
            <h2> Lista Colloqui </h2></a>
            <p>Visualizza tutti i candidati che hanno svolto il colloquio.</p>
        </div>
    </div>
</div>
</body>
</html>
