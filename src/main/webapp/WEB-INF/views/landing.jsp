<%@ include file="header.jspf" %>

<main>
    <section id="main-desc">


        <div id="desc" class="container-fluid">
            <div class="row">
                <div class="col-xs-12">
                    <h1>Talk like a <a href="#">#Chicken</a>.</h1>
                </div>
            </div>

            <div class="row">

                <div class="col-xs-4 col-xs-offset-2">
                    <h3>Sign up</h3>
                    <form id="registration" name="register" action="createUser" method="post">
                        <input class="form-control" name="firstName" type="text" placeholder="First name">
                        <input class="form-control" name="lastName" type="text" placeholder="Last name">
                        <input class="form-control" name="email" type="text" placeholder="Email">
                        <input class="form-control" name="password" type="password" placeholder="Password">
                        <input class="form-control" name="passwordConfirm" type="password" placeholder="Confirm password">
                        <input class="form-control btn btn-primary" type="submit" value="Register">
                    </form>

                </div>

                <div id="login" class="col-xs-3 col-xs-offset-1">
                    <h3>Login</h3>
                    <form name="login" action="loginUser" method="post">
                        <input class="form-control" autofocus type="email" name="email" placeholder="Email">
                        <input class="form-control" type="password" name="password" placeholder="Password">

                        <input class="form-control btn btn-primary" type="submit" value="Login">
                    </form>
                </div>


            </div>
        </div>

    </section>


</main>



<script src="static/js/landing.js"></script>
<link rel="stylesheet" type="text/css" href="static/css/landing.css">

<%@ include file="footer.jspf" %>
