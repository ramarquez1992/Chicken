<%@ include file="header.jspf" %>

<%-- For accessing current user w/o a getSelf call--%>
<%--${user.getEmail()}--%>
<div style="display: none;">
    <span id="firstName" style="visibility: hidden;">${user.getFirstName()}</span>
    <span id="lastName" style="visibility: hidden;">${user.getLastName()}</span>
    <span id="idNum" style="visibility: hidden;">${user.getId()}</span>
    <span id="isBaby" style="visibility: hidden;">${user.isBaby()}</span>
    <span id="status" style="visibility: hidden;">${user.getStatus().getName()}</span>
</div>


<div id="home-container" class="container-fluid row">

    <div id="spotlight-col" class="col-xs-9">

        <div ng-controller="SpotlightController" class="container-fluid">

            <input id="qAble" data-toggle="toggle" data-on="Yes" data-off="No" type="checkbox">

            <div id="spotlightContainer" class="row">
                <div id="chick1" class="col-xs-6">
                    <h3 class="chickName">{{currentRound.chick1.email}}</h3>
                    <div id="chick1StreamContainer"></div>
                    <button id="voteChick1">vote ({{currentRound.chick1Votes}})</button>
                </div>

                <div id="chick2" class="col-xs-6">
                    <h3 class="chickName">{{currentRound.chick2.email}}</h3>
                    <div id="chick2StreamContainer"></div>
                    <button id="voteChick2">vote ({{currentRound.chick2Votes}})</button>
                </div>

                <div id="queueContainer" class="col-xs-4">
                    <h3>Queue</h3>
                    <table class="table table-hover">
                        <tr ng-repeat="user in currentRound.queue">
                            <td>{{user.email}}</td>
                        </tr>
                    </table>
                </div>

            </div>
        </div>
    </div>


    <div class="col-xs-3" id="globalChatContainer">

        <%-- Global chat--%>
        <div id="container">
            <div id="chatboxContainer">
                <div id="chatbox"></div>
            </div>
            <div id="userList"><p>User List</p></div>
        </div>


        <div id="messageContainer">
            <input class="form-control" name="keywords" type="text" id="message" size="50" placeholder="Say something!" onkeypress="handleKeyPress(event)">
        </div>

        <div class="modal fade" id="UserProfile">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">User Profile</h4>
                    </div>
                    <div class="modal-body">
                        <h3>User Info</h3>
                        <img id="avatar" src=""/>
                        <p id="fullName"></p>
                        <div id="userStatus">
                            <select id="selectStatus">
                                <option id="stat1" value="normal">normal</option>
                                <option id="stat2" value="shadow ban">shadow ban</option>
                                <option id="stat3" value="permanent ban">permanent ban</option>
                                <option id="stat4" value="admin" selected>admin</option>
                            </select>
                            <button id="statusButton">Submit Changes</button>
                        </div>
                        <p id="games"></p>
                        <p id="wins"></p>
                        <p id="spotlight"></p>
                        <p id="votes"></p>
                        <p id="votesCast"></p>
                        <span id="statusChangeId" style="visibility: hidden;"></span>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
    </div>


</div>


<script src="https://cdn.pubnub.com/pubnub-3.7.14.min.js"></script>
<script src="https://cdn.pubnub.com/webrtc/webrtc.js"></script>
<script src="https://cdn.pubnub.com/webrtc/rtc-controller.js"></script>
<script src="static/js/webrtcKeys.js"></script>

<script src="//cdn.temasys.io/skylink/skylinkjs/0.6.x/skylink.complete.min.js"></script>
<script src="static/js/globalChat.js"></script>

<script src="static/lib/stomp.min.js"></script>
<script src="static/js/home.js"></script>


<%@ include file="footer.jspf" %>



