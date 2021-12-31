<%@ page import="it.unisa.agency_formation.autenticazione.domain.Utente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%
    ArrayList<Utente> list = (ArrayList<Utente>) request.getAttribute("candidati");
%>
<html>
<head>
    <link rel="stylesheet" href="css/Candidati.css">
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <title>Candidati</title>
</head>
<body>
<%@include file="Header.jsp"%>
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
            <div id="flex"><button>Mostra file</button></div>
        <%}%>
    </div>
</div>
</body>
</html>
