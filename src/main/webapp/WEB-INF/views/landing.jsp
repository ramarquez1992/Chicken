<%@ include file="header.jspf" %>


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
        <input name="firstName" type="text" placeholder="First name">
        <input name="lastName" type="text" placeholder="Last name">
        <input name="email" type="text" placeholder="Email">
        <input name="password" type="password" placeholder="Password">
        <input name="passwordConfirm" type="password" placeholder="Confirm password">

        <input type="submit" value="Register">
    </form>
</div>

<div id="login">
    <form name="login" action="loginUser" method="post">
        <input autofocus type="email" name="email" placeholder="Email">
        <input type="password" name="password" placeholder="Password">

        <input type="submit" value="Login">
    </form>
</div>


<script src="static/js/landing.js"></script>


<%@ include file="footer.jspf" %>
