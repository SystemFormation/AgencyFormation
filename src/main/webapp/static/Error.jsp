<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String descrizione = request.getParameter("descrizione");
%>
<html>
<head>
    <link rel="stylesheet" href="../css/Common.css">
    <title>Error</title>
</head>
<body>
<div class="error">
    <c:choose>
        <c:when test="${descrizione.length()==0}">
            Funzionalità in sviluppo
        </c:when>
        <c:otherwise>
            <%=descrizione%>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>