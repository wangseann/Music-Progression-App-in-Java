package Persistance;

import model.Progression;
import model.TimeSignatures;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkProgression(String name, String key, int tempo, TimeSignatures timeSignature, String notes, Progression progression) {
        assertEquals(name, progression.getName());
        assertEquals(key, progression.getKey());
        assertEquals(tempo, progression.getTempo());
        assertEquals(timeSignature, progression.getTimeSignature());
        assertEquals(notes, progression.getNotes());
    }
}
