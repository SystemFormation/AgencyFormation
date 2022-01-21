<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String error = request.getParameter("error");
%>

<!DOCTYPE html>
<html>
<head>
  <script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
  <script type="text/javascript" src="../js/ConvalidazioneCampi.js"></script>
  <link rel="icon" type="image/png" href="../img/Logo Team 4-5.png"/>
  <link rel="stylesheet" href="../css/HomePage.css">
  <meta charset="ISO-8859-1">
  <title>Registrazione</title>
</head>
<body class="background-reg">
<div class="logo">
  <img src="../img/Logo Team 4-5.png">
</div>
<div class="reg-block">
  <h1>Registrazione</h1>
  <form action="../RegistrazioneControl" method="post" id="Registrazione">
    <div class="form">
      <input type="text" id="email" name="email" placeholder="Email" autocomplete="off"
             onkeyup="checkEmail()"><br>
      <span id="rsEmail"></span><br>
      <input type="password" id="pwd" name="pwd" placeholder="Password"  onkeyup="checkPassword()"><br>
      <span id="rsPassword"></span><br>
      <input type="text" id="name" name="nome" placeholder="Nome" autocomplete="off"
             onkeyup="checkNome()"><br>
      <span id="rsNome"></span><br>
      <input type="text" id="surname" name="cognome" placeholder="Cognome" autocomplete="off"
             onkeyup="checkCognome()"><br>
      <%if(error!=null){%>
      <p style="color: red"><%=error%></p>
      <%}%>
      <span id="rsCognome"></span><br>
    </div>
    <div class="form-button">
      <input type="button" value="Registrati" id="Registrati" onclick="checkRegistrationSubmit()">
    </div>
    <script>
      document.write('<a href="' + document.referrer + '"><div class="back">Torna alla Home</div></a>');
    </script>
  </form>
</div>
</body>
</html>