<%@ page import="it.unisa.agency_formation.autenticazione.domain.Dipendente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    ArrayList<Dipendente> listDipendenti = (ArrayList<Dipendente>) request.getAttribute("dipendenti");
    ArrayList<Utente> listaUtenti = (ArrayList<Utente>) request.getAttribute("utenti");
%>
<html>
<head>
    <link rel="stylesheet" href="css/Dipendenti.css">
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <title>Dipendenti Disponibili</title>
</head>
<body>
<%@include file="Header.jsp" %>
<h1>LISTA DIPENDENTI DISPONIBILI</h1>
<div class="information">
    <table>
        <%for(int i = 0; i<listaUtenti.size(); i++){%>
        <tr>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Stato</th>
        </tr>

        <tr>
            <td><%=listaUtenti.get(i).getName()%></td>
            <td><%=listaUtenti.get(i).getSurname()%></td>
            <td><%=listDipendenti.get(i).isStato()%></td>

            <%if(user.getRole() == 3 && listDipendenti.get(i).isStato() == false){%>
            <td><form action="TeamControl" method="post" id="formTeam">
                <input type="hidden" name="action" value="aggiungi">
                <input type="submit" name="aggiungi" value="aggiungi" id="Aggiungi">
            </form>
            </td>
            <%}%>
        </tr>
        <%}%>


        <!-- <tr>
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
        -->
    </table>

</div>
</div>
</body>
</html>


</body>
</html>
