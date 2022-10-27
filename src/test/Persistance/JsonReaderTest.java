package Persistance;

import model.TimeSignatures;
import org.json.JSONException;
import persisitance.JsonReader;
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
        JsonReader reader = new JsonReader("./data/noSuchFile1.json");
        try {
            Playlist playlist = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderEmptyPlaylist() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPlaylist.json");
        try {
            Playlist playlist = reader.read();
            assertEquals("playlist1",playlist.getName());
            assertEquals(0,playlist.size());
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }

    @Test
    void testReaderGeneralPlaylist() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPlaylist.json");
        Progression p1 = new Progression("song1", "C", 100, TimeSignatures.FOUR_FOUR);
        p1.setNotes("ABC");
        Progression p2 = new Progression("song2", "D", 200, TimeSignatures.SEVEN_FOUR);
        p2.setNotes("DEF");
        Progression p3 = new Progression("song3", "E", 300, TimeSignatures.THREE_FOUR);
        p3.setNotes("ABC");
        Progression p4 = new Progression("song4", "E", 300, TimeSignatures.THREE_FOUR);
        p3.setNotes("ABC");

        try {

            Playlist playlist = reader.read();
            assertEquals("playlist1", playlist.getName());
            assertEquals("April 1 2021", playlist.getDate());
            List<Progression> progressionList = playlist.listOfProgs();
            Progression prog1 = progressionList.get(0);
            Progression prog2 = progressionList.get(1);
            Progression prog3 = progressionList.get(2);

            checkProgression(prog1.getName(), prog1.getKey(), prog1.getTempo(), prog1.getTimeSignature(), prog1.getNotes(), p1);
            checkProgression(prog2.getName(), prog2.getKey(), prog2.getTempo(), prog2.getTimeSignature(), prog2.getNotes(), p2);
            checkProgression(prog3.getName(), prog3.getKey(), prog3.getTempo(), prog3.getTimeSignature(), prog3.getNotes(), p3);
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }
}
