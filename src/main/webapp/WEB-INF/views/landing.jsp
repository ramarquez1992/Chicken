<%@ include file="header.jspf" %>

<main>
    <section id="main-desc">


        <div id="desc" class="container-fluid">
            <div class="row">
                <div class="col-xs-12">
                    <h1>Talk like a <a href="#" onClick="aboutChicken()">#Chicken</a>.</h1>
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
    
    <div class="modal fade" id="About">
            <div class="modal-dialog">
                <div class="modal-content" style="padding-bottom:2em; padding-top:2em;">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h3 class="modal-title" style = "text-align: center;">About <em>#Chicken</em></h3>
                    </div>
                    <div class="modal-body" style="font-size:0.9em;">
                    	</br>
						<p style="padding-left:3em; padding-right:3em;"><em>#Chicken</em> is the first online battle chat community where
							users compete to stay in the spotlight!
						</p>
						<p style="padding-left:3em; padding-right:3em;">The spotlight stages a 1-on-1 conversation in front of an
							audience that may chat amongst themselves. After a set period of
							time the audience votes on who they think should remain on the
							stage. Only one can remain in the spotlight. <em>How Chicken are you?</em>
						</p>
						<div class="modal-header" style="margin-top: -0.7em;">
						</div>
						<h4 style = "text-align: center; margin-top: 1.5em;">Chicken Defined</h4>
						<p style="padding-left:3em; padding-right:3em; margin-top: 1em">This table is not Chicken. This lamp is very Chicken; Chicken
							can be used as a measurement of how Chicken a thing is. Anyone or
							anything may or may not be Chicken. If a person is Chicken, that
							does not necessary mean that their clothes or other personal items
							are also Chicken. Being anti-social is extremely not Chicken. The
							plural of as well as the past and future tense of Chicken is
							Chicken.
						</p>
						<ul style="margin-left: 4em; margin-right: 4em;">
							<li style="margin-top:1em;">Not competing in the spotlight is very not Chicken.</li>
							<li style="margin-top:0.5em;">Joining the #Chicken community is extremely Chicken.</li>
							<li style="margin-top:0.5em;">The spotlight chat itself is Chicken but the global chat
								is only moderately Chicken.</li>
						</ul>
					</div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
    </div>


</main>



<script src="static/js/landing.js"></script>
<link rel="stylesheet" type="text/css" href="static/css/landing.css">

<%@ include file="footer.jspf" %>
