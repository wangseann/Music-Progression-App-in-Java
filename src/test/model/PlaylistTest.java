package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.MusicApp;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {
    Playlist playlist = new Playlist();
    Progression p1 = new Progression("songA", "C",200, TimeSignatures.FOUR_FOUR);
    Progression p2 = new Progression("songB", "D",201, TimeSignatures.SEVEN_FOUR);
    Progression p3 = new Progression("songC", "E",210, TimeSignatures.THREE_FOUR);
    Progression p4 = new Progression("songD", "F",210, TimeSignatures.THREE_FOUR);

    @BeforeEach
    void testSetUp() {
        this.playlist.addProgression(p1);
        this.playlist.addProgression(p2);
        this.playlist.addProgression(p3);

    }

    @Test
    void addProgressionTest() {

        assertEquals(3, playlist.size());
        assertEquals(p1, playlist.getProgression("songA"));
        assertEquals(201, playlist.getProgression("songB").getTempo());
        assertEquals("E", playlist.getProgression("songC").getKey());
        assertEquals(TimeSignatures.THREE_FOUR, playlist.getProgression("songC").getTimeSignature());
        assertFalse(playlist.playlist.contains(p4));
    }

    @Test
    void removeProgressionTest() {
        playlist.removeProgression("songA");

        assertFalse(playlist.contains(p1));
        assertEquals(2,playlist.size());
        assertEquals(201, playlist.getProgression("songB").getTempo());
        assertEquals("E", playlist.getProgression("songC").getKey());
    }

    @Test
    void getProgressionTest() {
        assertEquals("songB",playlist.getProgression("songB").getName());
        assertNull(playlist.getProgression("songD"));
    }

    @Test
    void sizeTest() {
        assertEquals(3, playlist.size());
    }

    @Test
    void containsTest() {
        assertTrue(this.playlist.contains(p1));
        assertFalse(this.playlist.contains(p4));
    }


}
