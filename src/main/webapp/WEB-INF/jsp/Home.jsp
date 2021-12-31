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
<a href="TeamControl"> Team </a>
<a href="DipendenteControl">Dipendenti</a>
<a href="ViewCandidatiControl">Candidati</a>


</html>