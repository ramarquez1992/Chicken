<%@ include file="header.jspf" %>

<!--  Update User Profile  -->
<div>
    <!-- Avatar and Update Avatar -->
    <div onsubmit="findImageAvatar('imageForm','img')">
        <form id="imageForm" class="form-horizontal" method="post" action="uploadAvatar">
            <div class="form-group">
                <label for="receipt_input" class="control-label">Avatar</label>
                <input name="receipt" id="receipt_input" type="file" class="file" accept="image/*" data-show-upload="false" data-allowed-file-extensions='["jpg", "png"]' required>
            </div>
            <input id="avatar" name="avatar" hidden=hidden/>
            <div class="form-group">
                <button type="submit" method="post" action="uploadAvatar">Submit Avatar</button>
            </div>
            <c:if test="${ avatar != null }">   <!-- for showing all past images, iirc -->
                <div class="form-group">
                    <label class="control-label">Avatar</label>
                    <input name="avatar_input" id="avatar_input" type="file" class="file" data-show-upload="false">
                    <input id="imageAvatar" value="${avatar}" hidden="hidden"/>
                </div>
            </c:if>
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
            <input name="isBaby" type="checkbox"> Turn on maturity filter 
        </div>

        <!-- Submit all the changes -->
        <input type="submit" value="Update Profile" action="updateProfile" method="post">
    </form>
</div>
    
<script src="static/js/profile.js"></script>

<%@ include file="footer.jspf" %>
