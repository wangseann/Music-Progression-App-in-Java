package Persistance;

import model.TimeSignatures;
import persisitance.JsonReader;
import persisitance.JsonWriter;
import model.Playlist;
import model.Progression;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import org.json.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Playlist playlist = new Playlist();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyPlaylist() {
        try {
            Playlist playlist = new Playlist();
            playlist.setName("playlist1");
            playlist.setDate("April 1st 2022");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPlaylist.json");
            writer.open();
            writer.write(playlist);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPlaylist.json");
            playlist = reader.read();
            assertEquals("playlist1", playlist.getName());
            assertEquals(0, playlist.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPlaylist() {
        Playlist playlist = new Playlist();
        playlist.setName("playlist1");
        playlist.setDate("April 1st 2022");
        Progression p1 = new Progression("Blackbird", "G maj", 80, TimeSignatures.FOUR_FOUR);
        Progression p2 = new Progression("SUPERPOSITION", "Eb maj", 65, TimeSignatures.FOUR_FOUR);
        try {
            playlist.addProgression(p1);
            playlist.addProgression(p2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPlaylist.json");
            writer.open();
            writer.write(playlist);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPlaylist.json");
            playlist = reader.read();
            assertEquals("playlist1",playlist.getName());
            List<Progression> progressionList = playlist.listOfProgs();
            assertEquals(2,progressionList.size());
            checkProgression("Blackbird", "G maj", 80, TimeSignatures.FOUR_FOUR,"",p1);
            checkProgression("SUPERPOSITION", "Eb maj", 65, TimeSignatures.FOUR_FOUR,"", p2);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (NullPointerException e) {
            //pass
        }
    }
}
