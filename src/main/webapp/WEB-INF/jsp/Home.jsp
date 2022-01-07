<%@ page import="it.unisa.agency_formation.autenticazione.domain.RuoliUtenti" %>
<%@ page import="it.unisa.agency_formation.reclutamento.domain.Candidatura" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%
    Candidatura candidatura = (Candidatura) request.getAttribute("candidatura");
%>
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
         <c:choose>
             <c:when test="${user.getRole() == RuoliUtenti.CANDIDATO}">
                 <div id="home"><a href="UploadCandidatureControl">
                     <h2> Caricamento Documenti </h2></a>
                     <p>Carica il tuo curriculum o anche i documenti per avviare il tuo processo di candidatura
                     </p>
                 </div>

                 <div id="stato" style="display: none">
                     <h2>Stato della tua candidatura:</h2>
                     <c:if test="${candidatura.getStato() == 'Accettata'}">
                         <h3>Accettata</h3>
                     </c:if>
                     <c:if test="${candidatura.getStato() == 'Rifiutata'}">
                         <h3>Rifiutata</h3>
                     </c:if>
                 </div>
             </c:when>
             <c:when test="${user.getRole() == RuoliUtenti.DIPENDENTE}">
                 <div id="home"><a href="ProfiloControl">
                     <h2> Profilo </h2></a>
                     <p>Accedi al tuo profilo personale per gestire le tue skill e i tuoi dati personali
                         e visualizzare il proprio stato</p>
                 </div>
                 <div id="home">
                     <h2 onclick="view(), viewLink()"> Materiale di formazione </h2>
                     <div id="drop" class="dropdown-content" style="display:none">
                         <a href="DownloadMaterialeControl" id="hrefDocumenti">
                             <img src="img/Materiale.png">
                             <p>Materiale</p>
                         </a>
                     </div>
                 </div>
             </c:when>
             <c:when test="${user.getRole() == RuoliUtenti.TM}">
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
                 <div id="home"><a href="TeamControl">
                     <h2> Lista Teams </h2></a>
                     <p>Ottieni la lista dei tuoi teams con i relativi dati e la gestione per
                     speficare le competenze richieste</p>
                 </div>
             </c:when>
             <c:when test="${user.getRole() == RuoliUtenti.HR}">
                 <div id="home"><a href="DipendenteControl">
                     <h2> Lista Dipendenti </h2></a>
                     <p>Ottieni la lista dipendenti per scegliere quale dipendente disponibile aggiungere
                         al proprio team oppure a quale dipendente occupato per un altro progetto effettuare
                         la richiesta di disponbilità</p>
                 </div>
                 <div id="home"><a id="viewCandidates" href="ViewCandidatiControl" onmouseover="controlCandidates()" onmouseleave="deleteSpan()">
                     <h2> Lista Candidati </h2></a>
                     <span id="noCandidati"></span>
                     <p>Ottieni la lista candidati per poter controllare: i file caricati da un candidato,
                     il loro rispettivo nome e cognome e la loro email</p>
                 </div>
                 <div id="home"><a href="TeamControl">
                     <h2> Lista Teams </h2></a>
                     <p>Ottieni la lista di tutti i teams con i relativi dati e la gestione del
                     caricamento del materiale di formazione</p>
                 </div>
             </c:when>
         </c:choose>
    </div>
</div>
</body>


</html>