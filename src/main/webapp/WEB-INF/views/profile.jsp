<%@ include file="header.jspf" %>

<c:if test="${empty user}">
    <% response.sendRedirect("403"); %>
</c:if>



<!--  Update User Profile  -->
<div id="profile-container" class="white-container">
    <h1 id="profilePageHeader"><em>Update</em> Information</h1>

    <div class="container-fluid row">
        <div class="col-xs-3 col-xs-offset-1">
            <!-- avatar and Update avatar, uses bootstrap-fileinput plugin -->
            <div id="currentAvatar"> <!-- current avatar display -->
                <img src="${user.getAvatar()}" alt="current avatar">
            </div>
            <!-- Form for uploading/submitting new avatar -->
            <div onsubmit="findImageAvatar('imageForm','img')"> <!-- calls JS method -->
                <form id="imageForm" class="form-horizontal" method="post" action="uploadAvatar"> <!-- call uploadAvatar in Controller -->
                    <div class="form-group">
                        <label for="receipt_input" class="control-label" style="visibility: hidden">Avatar</label>
                        <input name="receipt" id="receipt_input" type="file" class="file" accept="image/*" data-show-upload="false" data-allowed-file-extensions='["jpg", "png"]' required>
                    </div>
                    <input id="avatar" name="avatar" hidden=hidden/> <!-- shows image preview after selection -->
                    <div class="form-group"> <!-- submit avatar button -->
                        <button class="form-control btn btn-primary" type="submit" method="post" action="uploadAvatar">Upload</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="col-xs-6 col-xs-offset-1">
            <form name="update" action="updateProfile" method="post">
                <!-- User Account Info -->
                <table class="table">
                    <tr>
                        <td>First name</td>
                        <td>
                            <input class="form-control" name="firstName" type="text" placeholder="${user.getFirstName()}">
                        </td>
                    </tr>
                    <tr>
                        <td>Last name</td>
                        <td>
                            <input class="form-control" name="lastName" type="text" placeholder="${user.getLastName()}">
                        </td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td>
                            <input class="form-control" name="email" type="text" placeholder="${user.getEmail()}">
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td>
                            <input class="form-control" name="password" type="password" placeholder="New password">
                        </td>
                    </tr>
                    <tr>
                        <td>Confirm password</td>
                        <td>
                            <input class="form-control" name="passwordCheck" type="password" placeholder="Confirm password">
                        </td>
                    </tr>

                    <tr>
                        <td>Maturity filter</td>
                        <td>
                            <input data-toggle="toggle" data-on="On" data-off="Off" class="form-control" name="isBaby" type="checkbox" <c:if test="${user.isBaby()}">checked </c:if>>
                        </td>
                    </tr>

                </table>
                <input class="form-control btn btn-primary" type="submit" value="Update Profile" action="updateProfile" method="post">
            </form>
        </div>
    </div>
</div>


<script src="static/js/profile.js"></script>

<%@ include file="footer.jspf" %>
