
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <link rel="stylesheet" href="css/Registrazione.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Registrazione</title>
</head>
<body style="background: url(../../img/AF_Registrazione.jpg) no-repeat;">
<div class="block">
    <img src="./img/Cartella.png">
    <h1>Registrazione</h1>
    <form action="Registrazione" method="post" id="Registrazione">
        <div class="form">
            <input type="email" id="email" name="email" placeholder="Email" autocomplete="off" required>
            <input type="password" id="password" name="password" placeholder="Password" required>
            <input type="text" id="name" name="name" placeholder="Nome" autocomplete="off" required>
            <input type="text" id="surname" name="surname" placeholder="Cognome" autocomplete="off" required>
        </div>
        <div class="form-button">
            <input type="submit" value="Registrati" id="Registrati">
        </div>
    </form>
    <img src="img/Logo Team 4-5.png" id="logo">
</div>
</body>
</html>