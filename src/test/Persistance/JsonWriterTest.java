package Persistance;

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
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPlaylist.json");
            writer.open();
            writer.write(playlist);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPlaylist.json");
            playlist = reader.read();
            assertEquals(, playlist.getName());
            assertEquals(, playlist.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPlaylist() {
        try {
            Playlist playlist = new Playlist();
            playlist.addProgression(new Progression());
            playlist.addProgression(new Progression());
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPlaylist.json");
            writer.open();
            writer.write(playlist);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPlaylist.json");
            playlist = reader.read();
            assertEquals(,playlist.getName());
            List<Progression> progressionList = playlist.listOfProgs();
            assertEquals(,progressionList.size());
            checkProgression();
            checkProgression();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
