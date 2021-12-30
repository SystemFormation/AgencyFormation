<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
    boolean curriculum = (boolean) request.getAttribute("curriculum");
    boolean document = (boolean) request.getAttribute("document");
%>
<html>
<head>
    <link rel="stylesheet" href="css/Profilo.css">
    <title>Home</title>
</head>
<body>
<%@include file="Header.jsp"%>

<h1>Bentornato ${user.getName()}</h1>
</body>
<c:if test="${(!curriculum || !document)}">
    <a href="ProfiloControl"> Profilo </a>
    <a href="UploadDispatch"> Upload </a>
</c:if>
<c:if test="${(curriculum && document)}">
    <a href="ProfiloControl"> Profilo </a>
</c:if>

<a href="DipendenteControl">Dipendenti</a>



</html>