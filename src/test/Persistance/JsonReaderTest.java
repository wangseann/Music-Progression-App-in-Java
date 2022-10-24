package Persistance;

import Persisitance.JsonReader;
import model.Playlist;
import model.Progression;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistantFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Playlist playlist = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderEmptyPlaylist() {
        JsonReader reader = new JsonReader();
        try {
            Playlist playlist = reader.read();
            assertEquals(,playlist.getName());
            assertEquals(,playlist.size());
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }

    @Test
    void testReaderGeneralPlaylist() {
        JsonReader reader = new JsonReader();
        try {
            Playlist playlist = reader.read();
            assertEquals(,playlist.getName());
            assertEquals(,playlist.getDate());
            List<Progression> progressionList = playlist.listOfProgs();
            checkProgression();
            checkProgression();
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }
}
