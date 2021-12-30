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
<h1>Lista candidati</h1>
<div class="content">
<table>
    <tr>
        <th>IdCandidato</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Email</th>
        <th>Operazione</th>
    </tr>
    <%for(Utente cand:list){%>
        <tr>
            <td><%=cand.getId()%></td>
            <td><%=cand.getName()%></td>
            <td><%=cand.getSurname()%></td>
            <td><%=cand.getEmail()%></td>
            <td><button>Mostra file</button></td>
        </tr>
    <%}%>
</table>
</div>
</body>
</html>
