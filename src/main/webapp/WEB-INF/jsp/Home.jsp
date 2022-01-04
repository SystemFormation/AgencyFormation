<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/Common.css">
    <link rel="icon" type="image/png" href="img/Logo%20Team%204-5.png"/>
    <title>Home</title>
</head>
<body>
<c:import url="/static/Header.jsp"/>

<h1>Bentornato ${user.getName()}</h1>
</body>
<div class="home">
    <div class="content flex">
         <c:choose>
             <c:when test="${user.getRole() == 1}">
                 <div id="home"><a href="UploadDispatch">
                     <h2> Caricamento Documenti </h2></a>
                     <p>Carica il tuo curriculum o anche i documenti per avviare il tuo processo di candidatura
                     </p>
                 </div>
             </c:when>
             <c:when test="${user.getRole() == 2}">
                 <div id="home"><a href="ProfiloControl">
                     <h2> Profilo </h2></a>
                     <p>Accedi al tuo profilo personale per gestire le tue skill e i tuoi dati personali
                         e visualizzare il proprio stato</p>
                 </div>
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
                 <div id="home"><a href="TeamControl">
                     <h2> Lista Teams </h2></a>
                     <p>Ottieni la lista dei tuoi teams con i relativi dati e la gestione per
                     speficare le competenze richieste</p>
                 </div>
             </c:when>
             <c:when test="${user.getRole() == 4}">
                 <div id="home"><a href="DipendenteControl">
                     <h2> Lista Dipendenti </h2></a>
                     <p>Ottieni la lista dipendenti per scegliere quale dipendente disponibile aggiungere
                         al proprio team oppure a quale dipendente occupato per un altro progetto effettuare
                         la richiesta di disponbilità</p>
                 </div>
                 <div id="home"><a href="ViewCandidatiControl">
                     <h2> Lista Candidati </h2></a>
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