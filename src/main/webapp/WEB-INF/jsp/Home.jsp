<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/Profilo.css">
    <title>Home</title>
</head>
<body>
<%@include file="Header.jsp"%>

<h1>Bentornato <c:out value=user.getName() ></h1>
</body>
<a href="ProfiloControl"> Profilo </a>
<br><br><br><br><br><br>


</html>
