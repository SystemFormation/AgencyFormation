<%--
  Created by IntelliJ IDEA.
  User: Luigi
  Date: 06/01/2022
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="it.unisa.agency_formation.autenticazione.domain.RuoliUtenti" %>
<%@ page import="it.unisa.agency_formation.reclutamento.domain.Candidatura" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%
    Candidatura candidatura = (Candidatura) request.getAttribute("candidatura");
%>
<html>
<head>
    <title>Home</title>
</head>
<body>
<jsp:include page="/static/Header.jsp">
    <jsp:param value="false" name="sameLocation"/>
</jsp:include>

<h1>Bentornato ${user.getName()}</h1>
<div class="home">
    <div class="content flex">
        <c:choose>
            <c:when test="${user.getRole() == RuoliUtenti.CANDIDATO}">
                <div id="home"><a href="UploadCandidatureControl">
                    <h2> Caricamento Documenti </h2></a>
                    <p>Carica il tuo curriculum o anche i documenti per avviare il tuo processo di candidatura
                    </p>
                </div>

                <div id="stato" style="display: none">
                    <h1>Stato della tua candidatura:</h1>
                    <c:if test="${candidatura.getStato() == 'Accettata'}">
                        <h2>Accettata</h2>
                    </c:if>
                    <c:if test="${candidatura.getStato() == 'Rifiutata'}">
                        <h2>Rifiutata</h2>
                    </c:if>
                </div>
            </c:when>
        </c:choose>
    </div>
</div>
</body>
</html>
