<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/Profilo.css">
    <title>Home</title>
</head>
<body>
<%@include file="Header.jsp"%>

<h1>Bentornato ${user.getName()}</h1>
</body>
<a href="ProfiloControl"> Profilo </a>
<a href="UploadDispatch"> Upload </a>
<a href="DipendenteControl">Ciao Peter</a>


<table>
    <tr>
        <th> Nome </th>
        <td> Pasquale </td>
        <td> Emanuele </td>
        <td> Luigi </td>
    </tr>
    <tr>
        <th> Cognome </th>
        <td> Severino </td>
        <td> Scarpa </td>
        <td> Giacchetti </td>
    </tr>
</table>
</html>