<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" type="image/png" href="img/Logo Team 4-5.png"/>
    <link rel="stylesheet" href="css/Login.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Login</title>
</head>

<body>

    <div class="content">
        <div class="block">
            <h1>Login</h1>
        <form action="LoginControl" method="post" id="formLogin">
            <div class="components">
                <input type="email" id="email" name="email" placeholder="Email" autocomplete="off" required>
                <input type="password" id="password" name="password" placeholder="Password" required>
            </div>
            <div class="components-button" style="margin-top: 50px;">
                <input type="submit" value="Accedi" id="Accedi">
            </div>
        </form>
            <div class="components">
                <a href="./Recupero">Recupero Password</a>
            </div>
            <img src="img/Logo Team 4-5.png">
        </div>
    </div>
</body>
</html>