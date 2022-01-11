<%@ page import="it.unisa.agency_formation.autenticazione.domain.RuoliUtenti" %>
<%@ page import="it.unisa.agency_formation.reclutamento.domain.Candidatura" %>
<%@ page import="it.unisa.agency_formation.reclutamento.domain.StatiCandidatura" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Candidatura candidatura = (Candidatura) request.getAttribute("candidatura");
%>
<html>
<head>
    <link rel="stylesheet" href="css/Common.css">
    <link rel="icon" type="image/png" href="img/Logo%20Team%204-5.png"/>
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="js/Reclutamento.js"></script>
    <script type="text/javascript" src="js/Formazione.js"></script>
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
            <c:when test="${candidatura.getStato() != StatiCandidatura.Accettata}">
                <c:if test="${candidatura==null||candidatura.getDocumentiAggiuntivi()==null}">
                    <div id="home"><a href="UploadCandidatureControl">
                        <h2> Caricamento Documenti </h2></a>
                        <p>Carica il tuo curriculum o anche i documenti per avviare il tuo processo di candidatura
                        </p>
                    </div>
                </c:if>
            </c:when>
            <c:otherwise>
                <div id="home">
                    <h2> Data e ora colloquio:</h2></p>
                    <p> ${candidatura.getDataOraColloquio()} </p>

                </div>
            </c:otherwise>
        </c:choose>
        <c:if test="${candidatura!=null && candidatura.getCurriculum()!=null}">
            <div id="stato">
                <h2>Stato della tua candidatura:</h2>

                <c:choose>
                    <c:when test="${candidatura.getStato() == StatiCandidatura.Accettata}">
                        <p>Accettata</p>
                    </c:when>
                    <c:when test="${candidatura.getStato() == StatiCandidatura.Rifiutata}">
                        <p>Rifiutata</p>
                    </c:when>
                    <c:when test="${candidatura.getStato() == StatiCandidatura.NonRevisionato}">
                        <p>Non Revisionato</p>
                    </c:when>
                    <c:when test="${candidatura.getStato() == StatiCandidatura.Assunzione}">
                        <p>Assunzione</p>
                        <h3>Compila questi campi per completare la tua assunzione</h3>
                        <form method="post" id="formDipendente" action="CandidatoAssuntoControl">
                            <input type="hidden" name="action" value="crea">
                            <label for="formDipendente">Anno di nascita:</label>
                            <input type="number" min="1940" max="2014" id="annoDipendente" name="annoDipendente" placeholder="Anno" required pattern="[0-9]{4}"><br>
                            <label for="formDipendente">Anno di nascita:</label>
                            <input type="text" id="residenzaDipendente" name="residenzaDipendente" placeholder="Residenza" required pattern=""><br>
                            <label for="formDipendente">Anno di nascita:</label>
                            <input type="number" id="telefonoDipendente" name="telefonoDipendente" placeholder="Telefono" required pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}}"><br>
                            <input type="submit" name="crea" value="Crea" id="crea">
                        </form>
                    </c:when>
                </c:choose>
            </div>
        </c:if>
    </div>
</div>
</body>

</html>
