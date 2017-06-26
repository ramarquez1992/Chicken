<!DOCTYPE html>
<html>
<head>
    <title>#Chicken</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">

    <link rel="icon" href="static/img/chicken.ico">
    <link rel="stylesheet" type="text/css" href="static/lib/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="static/lib/Poppins.font">
    <link rel="stylesheet" type="text/css" href="static/lib/Montserrat.font">
    <link rel="stylesheet" type="text/css" href="static/css/main.css">

    <script src="static/lib/jquery-3.2.1.min.js"></script>
    <script src="static/lib/bootstrap.min.js"></script>
    <script src="static/js/main.js"></script>
    <script src="static/js/landing.js"></script>
</head>
<body>

<!-- Navigation bar/menu-->
<nav>
    <a href="landing">landing</a>
    <a href="home">home</a>
    <a href="leaderboard">leaderboard</a>
    <a href="profile">profile</a>
</nav>

<!-- "What is This?" container-->
<div id="about">
    <p>Hello, World!</p>
</div>

<!-- "Leaderboard" container-->
<div id="leaderboard">
</div>

<!-- "Registration" container-->
<div id="registration">
    <form name="register" action="createUser" method="post">
        <input name="firstName" type="text" placeholder="Enter your first name.">
        <input name="lastName" type="text" placeholder="Enter your last name.">
        <input name="email" type="text" placeholder="Enter your email.">
        <input name="password" type="text" placeholder="Enter your password.">
        <input name="passwordConfirm" type="text" placeholder="Enter your password again.">

        <input type="submit" value="register">
    </form>
</div>

<div id="login">
    <form name="login" action="loginUser" method="post">
        <input autofocus type="email" name="email" placeholder="email">
        <input type="password" name="password" placeholder="password">

        <input type="submit" value="login">
    </form>
</div>

<!-- "Footer" container-->
<footer>
</footer>

</body>
</html>
