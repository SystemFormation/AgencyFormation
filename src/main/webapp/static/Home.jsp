<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/Common.css">
    <link rel="icon" type="image/png" href="../img/Logo Team 4-5.png"/>
    <title>Home</title>
</head>
<body>
<c:import url="Header.jsp"/>

<h1>Bentornato ${user.getName()}</h1>
</body>
<div class="home">
    <div class="content flex">
         <c:choose>
             <c:when test="${user.getRole() == 1}">
                 <div id="home"><a href="static/Upload.jsp"><h2> Caricamento Documenti </h2></a><br></div>
             </c:when>
             <c:when test="${user.getRole() == 2}">
                 <div id="home"><a href="ProfiloControl"><h2> Profilo </h2></a><br></div>
             </c:when>
             <c:when test="${user.getRole() == 3}">
                 <div id="home"><a href="CreateTeamControl">
                     <h2> Creazione Team </h2></a>
                     <p>Crea un team specificando: nome del team, nome del progetto,
                         numero dipendenti e la relativa descrizione</p>
                 </div>
                 <div id="home"><a href="DipendenteControl">
                     <h2> Lista Dipendenti </h2></a>
                     <p>Ottieni la lista dipendenti per scegliere quale dipendente disponibile aggiungere
                     al proprio team oppure a quale dipendente occupato per un altro progetto effettuare
                     la richiesta di disponbilità</p>
                 </div>
             </c:when>
             <c:when test="${user.getRole() == 4}">
                 <div id="home"><a href="DipendenteControl">
                     <h2> Lista Dipendenti </h2></a>
                     <p>Ottieni la lista dipendenti per scegliere quale dipendente disponibile aggiungere
                         al proprio team oppure a quale dipendente occupato per un altro progetto effettuare
                         la richiesta di disponbilità</p>
                 </div>
                 <div id="home"><a href="ViewCandidatiControl"><h2> Candidati </h2></a><br></div>
             </c:when>
         </c:choose>
    </div>
</div>
</html>