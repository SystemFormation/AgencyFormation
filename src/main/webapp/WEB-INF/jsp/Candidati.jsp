<%@ page import="it.unisa.agency_formation.autenticazione.domain.Utente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%
    ArrayList<Utente> list = (ArrayList<Utente>) request.getAttribute("candidati");
%>
<html>
<head>
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="css/Candidati.css">
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <title>Candidati</title>
</head>
<body>
<h1>Lista candidati</h1>
<div class="content">
<table>
    <tr>
        <th>IdCandidato</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Email</th>
        <th>Operazione</th>
    </tr>
    <%for(Utente cand:list){%>
        <tr>
            <td><%=cand.getId()%></td>
            <td><%=cand.getName()%></td>
            <td><%=cand.getSurname()%></td>
            <td><%=cand.getEmail()%></td>
            <td><button onclick="test(<%=cand.getId()%>);view()">Mostra file</button></td>
        </tr>
    <div id="drop" class="dropdown-content" style="display: none;">
        <a id="hrefCurriculum" href="DownloadControl?toDownload=curriculum&idCandidato=<%=cand.getId()%>"style="display: none;">Curriculum</a>
        <a  href="DownloadControl?toDownload=documenti&idCandidato=<%=cand.getId()%>" id="hrefDocumenti" style="display: none;">Documenti</a>

    </div>
    <%}%>
</table>

    <script>
        function test(id){
            var id = id;
            $.ajax({
                type: 'GET',
                data:{"idCandidato":id},
                url: 'ViewCandidaturaControl',
                success: function (data1){
                   var cv =data1.substr(0,data1.indexOf('.'));
                   var doc = data1.substr(data1.indexOf('.')+1,data1.length);
                   if(doc.length>0){
                       var x = document.getElementById("hrefDocumenti");
                       x.style.display = "block";
                   }else{
                       var x = document.getElementById("hrefDocumenti");
                       x.style.display = "none";
                   }
                    if(cv.length>0){
                        var x = document.getElementById("hrefCurriculum");
                        x.style.display = "block";
                    }else{
                        var x = document.getElementById("hrefCurriculum");
                        x.style.display = "none";
                    }

                }
            })
        }
    </script>
    <script>
        function view(){
            var x = document.getElementById("drop");
            if(window.getComputedStyle(x).display==="none"){
                x.style.display = "block";
            }
            else{
                x.style.display = "none";
            }

        }
    </script>
</div>
</body>
</html>
