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
    <title>Home</title>
</head>
<body>
<jsp:include page="/static/Header.jsp">
    <jsp:param value="false" name="sameLocation"/>
</jsp:include>

<h1>Bentornato ${user.getName()}</h1>
</body>
<div class="home">
    <div class="content flex">
         <c:choose>
             <c:when test="${user.getRole() == RuoliUtenti.CANDIDATO}">
                 <div id="home"><a href="UploadCandidatureControl">
                     <h2> Caricamento Documenti </h2></a>
                     <p>Carica il tuo curriculum o anche i documenti per avviare il tuo processo di candidatura
                     </p>
                 </div>
                 <script type="text/javascript">
                     $(document).ready(function() {

                         $.ajax({
                             type: 'GET',
                             url: 'ViewCandidaturaControl',
                             success: function (data1){
                                 var cv =data1.substr(0,data1.indexOf("."));
                                 var doc = data1.substr(data1.indexOf(".")+1,data1.length);
                                 if(cv.length>0 && doc.length>0){
                                     var x = document.getElementById("home");
                                     x.style.display = "none";
                                     var z = document.getElementById("stato");
                                     z.style.display="block";
                                 }else if(cv.length>0 && (doc.length<1)){
                                     var z = document.getElementById("stato");
                                     z.style.display="block";
                                 }
                             }
                         })
                     });
                 </script>
                 <div id="stato" style="display: none">
                     <h1>Stato candidatura</h1>
                 </div>
                 <!-- <div class ="disponibile">.</div> -->
                 <!-- <div class="occupato">.</div> -->
             </c:when>
             <c:when test="${user.getRole() == RuoliUtenti.DIPENDENTE}">
                 <div id="home"><a href="ProfiloControl">
                     <h2> Profilo </h2></a>
                     <p>Accedi al tuo profilo personale per gestire le tue skill e i tuoi dati personali
                         e visualizzare il proprio stato</p>
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
</html>