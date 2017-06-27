<%@ include file="header.jspf" %>

${user.getEmail()}

<div id="spotlight">
    <div id="chick1StreamContainer"></div>
    <div id="chick2StreamContainer"></div>

    <button id="chick1StreamBtn">chick1-stream</button>
    <button id="chick2StreamBtn">chick2-stream</button>

    <button onclick="attachSpotlight();">View</button>

</div>

<div id="global">
    <p>global chat</p>
</div>

<div id="misc">
    <p>misc info</p>
</div>

<script src="https://cdn.pubnub.com/pubnub-3.7.14.min.js"></script>
<script src="https://cdn.pubnub.com/webrtc/webrtc.js"></script>
<script src="https://cdn.pubnub.com/webrtc/rtc-controller.js"></script>
<script src="static/js/webrtcKeys.js"></script>
<script src="static/js/home.js"></script>


<%@ include file="footer.jspf" %>



