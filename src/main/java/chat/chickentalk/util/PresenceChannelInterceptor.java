package chat.chickentalk.util;

import chat.chickentalk.controllers.SpotlightController;
import chat.chickentalk.dao.Dao;
import chat.chickentalk.service.SpotlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

public class PresenceChannelInterceptor extends ChannelInterceptorAdapter {

    @Autowired
    private SpotlightController ctrl;

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {

        StompHeaderAccessor sha = StompHeaderAccessor.wrap(message);

        // ignore non-STOMP messages like heartbeat messages
        if(sha.getCommand() == null) {
            return;
        }

        String sessionId = sha.getSessionId();

        switch(sha.getCommand()) {
            case CONNECT:
                String email = sha.getNativeHeader("email").get(0);

                ctrl.addActiveUser(sessionId, email);

                System.out.println("STOMP Connect [sessionId: " + sessionId + "]");
                break;
            case CONNECTED:
                System.out.println("STOMP Connected [sessionId: " + sessionId + "]");
                break;
            case DISCONNECT:
                ctrl.removeActiveUser(sessionId);

                System.out.println("STOMP Disconnect [sessionId: " + sessionId + "]");
                break;
            default:
                break;

        }
    }
}
