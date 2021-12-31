<%@ page import="it.unisa.agency_formation.autenticazione.domain.Utente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%
    ArrayList<Utente> list = (ArrayList<Utente>) request.getAttribute("candidati");
%>
<html>
<head>
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="js/Candidature.js"></script>
    <link rel="stylesheet" href="css/Candidati.css">
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <title>Candidati</title>
</head>
<body>
<h1>Lista candidati</h1>
<div class="content-wrap">
    <div class="content">
            <div id="flex-head">ID</div>
            <div id="flex-head">Nome</div>
            <div id="flex-head">Cognome</div>
            <div id="flex-head">Email</div>
            <div id="flex-head">Azione</div>
        <%for(Utente cand:list){%>
            <div id="flex"><%=cand.getId()%></div>
            <div id="flex"><%=cand.getName()%></div>
            <div id="flex"><%=cand.getSurname()%></div>
            <div id="flex"><%=cand.getEmail()%></div>
            <div id="flex"><button onclick="test(<%=cand.getId()%>);view()">Mostra file</button></div>
        <div id="drop" class="dropdown-content" style="display: none;">
            <a id="hrefCurriculum" href="DownloadControl?toDownload=curriculum&idCandidato=<%=cand.getId()%>"style="display: none;">Curriculum</a>
            <a  href="DownloadControl?toDownload=documenti&idCandidato=<%=cand.getId()%>" id="hrefDocumenti" style="display: none;">Documenti</a>
        </div>
    </div>
</div>
</body>
</html>
