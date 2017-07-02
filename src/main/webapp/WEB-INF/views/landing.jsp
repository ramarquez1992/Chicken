<%@ include file="header.jspf" %>

<main>
    <section id="main-desc">
        <video muted loop autoplay poster="static/img/chicken-bg.jpeg">
            <source src="static/img/pupper.mp4"/>
        </video>



        <div id="desc" class="container-fluid">
            <h1>Talk like a #Chicken.</h1>

            <div class="row">

                <div class="col-xs-4">
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

                <div id="login" class="col-xs-4 col-xs-offset-1">
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


    <%--<section id="about" class="container-fluid">--%>
        <%--<h2>How do you #Chicken?</h2>--%>

        <%--<p>--%>
            <%--#Chicken is an online community space where users compete for time in the Spotlight.--%>
        <%--</p>--%>
        <%--<p>--%>
            <%--The Spotlight holds a 1-on-1 conversation in front of an audience that may chat among themselves. After a set period of time the audience votes on who they think should remain on the stage. One user gets voted off the stage and another is pulled at random from the audience.--%>
        <%--</p>--%>

    <%--</section>--%>

</main>



<script src="static/js/landing.js"></script>


<%@ include file="footer.jspf" %>
