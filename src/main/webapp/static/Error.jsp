<%@ page import="it.unisa.agency_formation.autenticazione.domain.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String descrizione = request.getParameter("descrizione");
    Utente user = (Utente) request.getSession().getAttribute("user");
%>

<c:set var="error" value="<%=descrizione%>"/>
<html>
<head>
    <link rel="stylesheet" href="../css/Common.css">
    <title>Error</title>
</head>
<body>
<div class="error">
    <c:choose>
        <c:when test="${descrizione.length()==0 || descrizione==null}">
            Funzionalità in sviluppo
        </c:when>
        <c:otherwise>
            ${descrizione}
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${user!=null}">
            <div class="back">
                <button><a href="../LoginControl">Torna alla Home</a></button>
            </div>
        </c:when>
        <c:otherwise>
            <div class="back">
                <button><a href="http://localhost:8080/AgencyFormation/">Torna alla Home</a></button>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>