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
<div class="home">
    <div class="content flex">
        <div id="home"><a href="ListaCandidati">
            <h2> Lista Candidati </h2></a>
            <span id="noCandidati"></span>
            <p>Ottieni la lista candidati per poter controllare: i file caricati da un candidato,
                il loro rispettivo nome e cognome</p>
        </div>
        <div id="home"><a href="ListaTeam">
            <h2> Lista Teams </h2></a>
            <p>Ottieni la lista di tutti i teams con i relativi dati e la gestione del
                caricamento del materiale di formazione</p>
        </div>
        <div id="home"><a href="ListaColloqui">
            <h2> Lista Colloqui </h2></a>
            <p>Ottieni la lista dei colloqui per poter assumere o rifiutare un candidato</p>
        </div>
    </div>
</div>
</body>
</html>
