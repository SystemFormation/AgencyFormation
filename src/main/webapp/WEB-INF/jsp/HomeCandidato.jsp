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
<div class="content-home">
    <div class="flex">
        <c:choose>
            <c:when test="${candidatura.getStato() != StatiCandidatura.Accettata && candidatura.getStato() != StatiCandidatura.Assunzione}">
                <c:if test="${candidatura==null||candidatura.getDocumentiAggiuntivi()==null}">
                    <div id="home">
                        <h2> Caricamento Documenti </h2>
                        <p> Carica il tuo curriculum e in seguito anche i documenti, per proporre la tua
                            candidatura </p>
                        <button><a href="UploadCandidatureControl">Accedi all'area</a></button>
                    </div>
                </c:if>
            </c:when>
            <c:when test="${candidatura.getStato() == StatiCandidatura.Accettata}">
                <div id="home">
                    <h2> Data e ora colloquio:</h2>
                    <p> ${candidatura.getDataOraColloquio()} </p>
                </div>
            </c:when>
        </c:choose>
        <c:if test="${candidatura!=null && candidatura.getCurriculum()!=null}">
            <div id="stato">
                <c:choose>
                    <c:when test="${candidatura.getStato() == StatiCandidatura.Accettata}">
                        <h2>Stato della tua candidatura:</h2>
                        <p>Accettata</p>
                    </c:when>
                    <c:when test="${candidatura.getStato() == StatiCandidatura.Rifiutata}">
                        <h2>Stato della tua candidatura:</h2>
                        <p>Rifiutata</p>
                        <div id="home">
                            <h2> Ricandidatura </h2>
                            <p>Ricandidati caricando il tuo curriculum e in seguito anche i documenti</p>
                            <button><a href="RicandidaturaControl">Accedi all'area</a></button>
                        </div>
                    </c:when>
                    <c:when test="${candidatura.getStato() == StatiCandidatura.NonRevisionato}">
                        <div id="home">
                            <h2>Stato della tua candidatura:</h2>
                            <p>Non Revisionato</p>
                        </div>
                    </c:when>
                    <c:when test="${candidatura.getStato() == StatiCandidatura.Assunzione}">
                        <h1>Compila questi campi per completare la tua assunzione</h1>
                        <div class="form">
                            <form method="post" id="formDipendente" action="CandidatoAssuntoControl">
                                <input type="hidden" name="action" value="crea">
                                <label for="formDipendente">Anno di nascita:</label><br>
                                <input type="number" min="1940" max="2004" id="annoDipendente" name="annoDipendente"
                                       placeholder="Anno" required><br>
                                <label for="formDipendente">Residenza:</label><br>
                                <input type="text" id="residenzaDipendente" name="residenzaDipendente"
                                       placeholder="Residenza" required><br>
                                <label for="formDipendente">Recapito Telefonico:</label><br>
                                <input type="number" id="telefonoDipendente" name="telefonoDipendente"
                                       placeholder="Telefono" required pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}}"><br><br>
                                <input type="submit" name="invia" value="Invia" id="invia">
                            </form>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </c:if>
    </div>
</div>
</body>

</html>
