<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <link rel="stylesheet" href="css/Upload.css">
    <meta charset="ISO-8859-1">
    <title>Caricamento</title>
</head>
<body>
<%@include file="Header.jsp"%>
<h1>CARICAMENTO DOCUMENTI</h1>
<div class="content">
    <div class="text-block">
        Carica qui il tuo Curriculum, attestati o certificazioni per farli revisionare da un HR.
        Successivamente, in base all'esito, verrà inviata una notifica.
    </div>
    <form action="UploadCandidatureControl" id="form" method="post" enctype = "multipart/form-data">
        <input type="file" id="file" name="file" size="50" multiple><br><br>
        <input type="submit" value="Carica" id="upload">
    </form>
</div>
</body>
</html>