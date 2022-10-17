package model;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProgressionTest {
    Progression p1 = new Progression("songA", "C",200, TimeSignatures.FOUR_FOUR);
    Progression p2 = new Progression("songB", "D",201, TimeSignatures.SEVEN_FOUR);
    Progression p3 = new Progression("songC", "E",210, TimeSignatures.THREE_FOUR);
    Progression p4 = new Progression("songD", "F",210, TimeSignatures.THREE_FOUR);

    @Test
    public void setNotesTest() {
        p1.setNotes("A B C D");
        assertEquals("A B C D", p1.getNotes());
        p2.setNotes("");
        assertEquals("", p2.getNotes());
    }

    @Test
    public void getNotesTest() {
        Playlist playlist = new Playlist();
        playlist.addProgression(p1);
        playlist.addProgression(p2);
        playlist.addProgression(p3);
        playlist.addProgression(p4);

        p3.setNotes("A B C D");
        assertEquals("A B C D", playlist.getProgression("songC").getNotes());
        p4.setNotes("");
        assertEquals("", playlist.getProgression("songD").getNotes());
    }
}
