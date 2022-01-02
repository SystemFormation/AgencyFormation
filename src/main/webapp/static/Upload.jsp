<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" type="image/png" href="../img/Logo Team 4-5.png"/>
    <link rel="stylesheet" href="../css/Upload.css">
    <meta charset="ISO-8859-1">
    <title>Caricamento</title>
</head>
<body>
<c:import url="Header.jsp"/>
<h1>Caricamento Documenti</h1>
<div class="content">
    <div class="text-block">
        Carica qui il tuo Curriculum, attestati o certificazioni per farli revisionare da un HR.
        Successivamente, in base all'esito, verrà inviata una notifica.
    </div>

    <form action="UploadCandidatureControl" id="curriculum" method="post" enctype = "multipart/form-data">
        Curriculum
        <input type="file" id="fileCurriculum" name="curriculum" size="50"><br><br>
        <input type="hidden" id="sceltaCurriculum" name="sceltaUpload" value="1">
        <input type="submit" value="Carica" id="uploadCurriculum">
    </form>

    <form action="UploadCandidatureControl" id="documenti" method="post" enctype = "multipart/form-data">
        Documenti aggiuntivi
        <input type="file" id="fileDocumenti" name="documenti" size="50"><br><br>
        <input type="hidden" id="sceltaDocumenti" name="sceltaUpload" value="2">
        <input type="submit" value="Carica" id="uploadDocumenti">
    </form>
</div>
</body>
</html>