<%@ page import="it.unisa.agency_formation.autenticazione.domain.Dipendente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%
    ArrayList<Dipendente> list = (ArrayList<Dipendente>) request.getAttribute("dipendenti");
%>
<html>
<head>
    <link rel="stylesheet" href="css/Dipendenti.css">
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <title>Dipendenti</title>

</head>
<body>
<%@include file="Header.jsp"%>
<h1>LISTA DIPENDENTI</h1>
<div class="content">
        <div class="found">
            <form action="DipendenteControl" method="post" id="formDip">
                <select name="subject" id="subject">
                    <option value="null" selected="selected">---</option>
                    <option value="occupati">Occupati</option>
                    <option value="disponibili">Disponibili</option>
                </select>
            </form>
        </div>
    <div class="information">
        <table>
            <tr>
                <th>IdDipendente</th>
                <th>IdTeam</th>
                <th>Anno Di Nascita</th>
                <th>Residenza</th>
                <th>Telefono</th>
                <th>Stato</th>
            </tr>
                <!--if (value==null) {-->
              <c:forEach var="dip"
                         items="${dipendenti}">
                  <tr>
                      <td>${dip.getIdDipendente()}</td>
                      <td>${dip.getIdTeam()}</td>
                      <td>${dip.getAnnoNascita()}</td>
                      <td>${dip.getResidenza()}</td>
                      <td>${dip.getTelefono()}</td>
                      <td> Occupato </td>
                  </tr>
              </c:forEach>
                <!-- } else if (value==disponibli)
                APRI IL FOR EACH
                IF (dip.isStato() == true)
                    fa la tabella, altri,enti non entra nella tabella
                CHIUDI IL FOR EACH -->

                <!-- else if (value==occupati)
                APRI IL FOR EACH
                IF (dip.isStato() == true)
                    fa la tabella, altri,enti non entra nella tabella
                CHIUDI IL FOR EACH -->

                    <tr>
                        <td>2</td>
                        <td>4</td>
                        <td>1998</td>
                        <td>Pompei</td>
                        <td>334680</td>
                        <td> Disponibile </td>
                    </tr>
                  <tr>
                      <td>2</td>
                      <td>4</td>
                      <td>1998</td>
                      <td>Pompei</td>
                      <td>334680</td>
                      <td> Disponibile </td>
                  </tr>
                  <tr>
                      <td>2</td>
                      <td>4</td>
                      <td>1998</td>
                      <td>Pompei</td>
                      <td>334680</td>
                      <td> Occupato </td>
                  </tr>
                  <tr>
                      <td>2</td>
                      <td>4</td>
                      <td>1998</td>
                      <td>Pompei</td>
                      <td>334680</td>
                      <td> Occupato </td>
                  </tr>
                  <tr>
                      <td>2</td>
                      <td>4</td>
                      <td>1998</td>
                      <td>Pompei</td>
                      <td>334680</td>
                      <td> Disponibile </td>
                  </tr>
                  <tr>
                      <td>2</td>
                      <td>4</td>
                      <td>1998</td>
                      <td>Pompei</td>
                      <td>334680</td>
                      <td> Disponibile </td>
                  </tr>
                  <tr>
                      <td>2</td>
                      <td>4</td>
                      <td>1998</td>
                      <td>Pompei</td>
                      <td>334680</td>
                      <td> Occupato </td>
                  </tr>
                  <tr>
                      <td>2</td>
                      <td>4</td>
                      <td>1998</td>
                      <td>Pompei</td>
                      <td>334680</td>
                      <td> Occupato </td>
                  </tr>
                  <tr>
                      <td>2</td>
                      <td>4</td>
                      <td>1998</td>
                      <td>Pompei</td>
                      <td>334680</td>
                      <td> Disponibile </td>
                  </tr>
            </table>

    </div>
</div>
</body>
</html>
