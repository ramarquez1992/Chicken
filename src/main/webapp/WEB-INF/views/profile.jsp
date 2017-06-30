<%@ include file="header.jspf" %>

<!-- !!! lets a User update their own account details, doesn't work to let a User view other people's accounts -->

<!--  Update User Profile  -->
<div>
    <!-- avatar and Update avatar, uses bootstrap-fileinput plugin -->
    <div> <!-- current avatar display -->
        <img src="${user.getAvatar()}" alt="current avatar">
    </div>
    <!-- Form for uploading/submitting new avatar --> 
    <div onsubmit="findImageAvatar('imageForm','img')"> <!-- calls JS method -->
        <form id="imageForm" class="form-horizontal" method="post" action="uploadAvatar"> <!-- call uploadAvatar in Controller -->
            <div class="form-group">
                <label for="receipt_input" class="control-label">Avatar</label>
                <input name="receipt" id="receipt_input" type="file" class="file" accept="image/*" data-show-upload="false" data-allowed-file-extensions='["jpg", "png"]' required>
            </div>
            <input id="avatar" name="avatar" hidden=hidden/> <!-- shows image preview after selection --> 
            <div class="form-group"> <!-- submit avatar button --> 
                <button type="submit" method="post" action="uploadAvatar">Submit Avatar</button>
            </div>
        </form>
    </div>    
   
    <form name="update" action="updateProfile" method="post">
        <!-- User Account Info -->
        <input name="firstName" type="text" placeholder="${user.getFirstName()}">
        <input name="lastName" type="text" placeholder="${user.getLastName()}">
        <input name="email" type="text" placeholder="${user.getEmail()}">
        <input name="password" type="password" placeholder="New password">
        <input name="passwordCheck" type="password" placeholder="Confirm new password">

        <!-- Maturity Filter -->
        <div>
            <!-- shows as checked if Baby, not if Adult -->
            <input name="isBaby" type="checkbox" <c:if test="${user.isBaby()}">checked </c:if>> Toggle maturity filter 
        </div>

        <!-- Submit all the changes -->
        <input type="submit" value="Update Profile" action="updateProfile" method="post">
    </form>
</div>
    
<script src="static/js/profile.js"></script>

<%@ include file="footer.jspf" %>
