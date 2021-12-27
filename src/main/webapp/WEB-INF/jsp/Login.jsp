<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <link rel="stylesheet" href="css/Login.css">
    <meta charset="ISO-8859-1">
    <title>Login</title>
</head>

<body style="background: url(./img/AF_Login.jpg) no-repeat center center fixed;">
<div class="content">
    <div class="block">
        <h1>Login</h1>
        <div class="components">
            <input type="email" id="email" name="email" placeholder="Email" autocomplete="off" required>
            <input type="password" id="password" name="password" placeholder="Password" required>
        </div>
        <div class="components-button" style="margin-top: 50px;">
            <input type="submit" value="Accedi" id="Accedi">
        </div>
        <div class="components">
            <a href="./Recupero">Recupero Password</a>
        </div>
        <img src="img/Logo Team 4-5.png">
    </div>
</div>
</body>
</html>