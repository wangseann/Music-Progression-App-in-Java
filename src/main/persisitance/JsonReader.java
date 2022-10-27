package persisitance;



import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Playlist;
import model.Progression;
import model.TimeSignatures;
import org.json.*;


public class JsonReader {
    private final String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads playlist from file and returns it;
    // throws IOException if an error occurs reading data form file
    public Playlist read() throws IOException {
        Playlist playlist = new Playlist();

        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        playlist = parsePlaylist(jsonObject);

        return playlist;
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        Stream<String> stream = Files.lines(Paths.get(source),StandardCharsets.UTF_8);
        stream.forEach(contentBuilder::append);

        return contentBuilder.toString();
    }

    //EFFECTS: parses playlist from JSON object and returns it
    private Playlist parsePlaylist(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String date = jsonObject.getString("date");
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setDate(date);
        addInfo(playlist, jsonObject);
        return playlist;
    }

    // MODIFIES: playlist
    // EFFECTS: parses info from JSON object and adds them to playlist
    private void addInfo(Playlist playlist, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("progressions");
        for (Object json : jsonArray) {
            JSONObject nextProgression = (JSONObject) json;
            addProgression(playlist, nextProgression);
        }
    }

    //MODIFIES: playlist
    //EFFECTS: parses progression from JSON object and adds it to playlist
    private void addProgression(Playlist playlist, JSONObject jsonObject) {
        String name = jsonObject.getString("progression name");
        String key = jsonObject.getString("key");
        int tempo = jsonObject.getInt("tempo");
        String timeSignatureString = jsonObject.getString("time signature");
        String notes = jsonObject.getString("notes");
        TimeSignatures timeSignature = null;

        switch (timeSignatureString) {
            case "FOUR_FOUR":
                timeSignature = TimeSignatures.FOUR_FOUR;
                break;

            case "THREE_FOUR":
                timeSignature = TimeSignatures.THREE_FOUR;
                break;

            case "SEVEN_FOUR":
                timeSignature = TimeSignatures.SEVEN_FOUR;
                break;
        }


        Progression progression = new Progression(name, key, tempo, timeSignature);
        progression.setNotes(notes);

        playlist.addProgression(progression);
    }
}
