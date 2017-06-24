package chat.chickentalk.service;

import java.util.ArrayList;
import java.util.List;

public class SpotlightService {
    private static SpotlightService INSTANCE = new SpotlightService();

    private SpotlightService() {}

    public static SpotlightService getInstance() {
        return INSTANCE;
    }

    public List<String> getSpotlightQueue() {
        List<String> result = new ArrayList<String>();

        result.add("richie");
        result.add("cody");
        result.add("darrin");
        result.add("theresa");

        return result;
    }
}
