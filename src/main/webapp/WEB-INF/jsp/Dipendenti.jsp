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
                <div/>
          <c:forEach var="dip"
                    items="${dipendenti}">
              <tr>
                  <td>${dip.getIdDipendente()}</td>
                  <td>${dip.getIdTeam()}</td>
                  <td>${dip.getAnnoNascita()}</td>
                  <td>${dip.getResidenza()}</td>
                  <td>${dip.getTelefono()}</td>
                  <td>

                      <form action="DipendenteControl" method="post" id="formDip">
                          <select name="subject" id="subject">

                          <option value="occupati" selected="selected">Occupati</option>
                          <option value="disponibili" selected="selected">Disponibili</option>
                      </select>
                          <div class="searchButton">
                          <input type="submit" value="Cerca" id="Cerca">
                          </div>
                      </form>
                  </td>
              </tr>
          </c:forEach>
        </table>

        <div/>

</body>
</html>
