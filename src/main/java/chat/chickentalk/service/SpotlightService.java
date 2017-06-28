package chat.chickentalk.service;

import chat.chickentalk.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Service
public class SpotlightService {
    private Deque<User> queue = new ArrayDeque<>();

    public List<String> getSpotlightQueue() {
        List<String> result = new ArrayList<>();

        result.add("richie");
        result.add("cody");
        result.add("darrin");
        result.add("theresa");

        return result;
    }

    // TODO: check if not banned, etc.
    public boolean addUserToQueue(User u) {
        queue.add(u);

        return true;
    }

    public boolean removeUserFromQueue(User userToRemove) {
        boolean result = false;

        for (User u : queue) {
            if (u.getEmail().equals(userToRemove.getEmail())) {
                queue.remove(u);
                result = true;
                break;
            }
        }

        return result;
    }

}
