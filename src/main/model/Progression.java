package model;

public class Progression {
    String name = "";
    String notes = "";
    String key = "";
    int tempo = 0;
    TimeSignatures timeSignature;

    //EFFECT: construct progression
    public Progression(String name, String key, int tempo, TimeSignatures timeSignature) {
        setName(name);
        setKey(key);
        setTempo(tempo);
        setTimeSignature(timeSignature);
    }

    //Setters:
    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setTimeSignature(TimeSignatures t) {
        this.timeSignature = t;
    }

    //Getters:
    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public int getTempo() {
        return tempo;
    }

    public String getNotes() {
        return notes;
    }

    public TimeSignatures getTimeSignature() {
        return timeSignature;
    }
}

