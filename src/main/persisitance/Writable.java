package persisitance;

import org.json.JSONObject;

public interface Writable {
    //EFFECTS: returns this a JSON object
    JSONObject toJson();
}
