<%@ include file="header.jspf" %>

<!--  Update User Profile  -->
<div>
    <%-- how do we get the info of the clicked user into the form? --%>
    <%-- how do if check ????? --%>
    <% if((user.getEmail()).equals(email)){ %>
    <form name="update" action="updateProfile" method="post">
    <%-- Eventually replace placeholder with current info --%>
        <input name="firstName" type="text" placeholder="First name">
        <input name="lastName" type="text" placeholder="Last name">
        <input name="password" type="password" placeholder="Password">
        <input name="passwordCheck" type="password" placeholder="Confirm password">
        
        <!-- Avatar and Update Avatar -->
        <div>
            <input type="submit" value="Upload image">
        </div>
        
        <!-- Maturity Filter -->
        <div>
        	<input name="isBaby" type="checkbox">
        </div>
    <% } %>    
        
        <!-- User Status (only visible to Admin) -->
        <% if((user.getStatus.getName()).equals("admin")){ %>   
        <div>
            <input name="status" type="submit" value="normal">
            <input name="status" type="submit" value="shadow ban">
            <input name="status" type="submit" value="permanent ban">
            <input name="status" type="submit" value="admin">
            <input name="status" type="submit" value="Chicken">
        </div>
        <% } %>
        
        <input type="submit" value="Update Profile">
    </form> 
</div>
    
<script src="static/js/profile.js"></script>
    
<%@ include file="footer.jspf" %>
