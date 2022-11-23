package model;

import org.json.JSONObject;
import persisitance.Writable;

import javax.swing.*;

public class Progression implements Writable {
    String name = "";
    String notes = "";
    String key = "";
    int tempo = 0;
    TimeSignatures timeSignature;

    //REQUIRES: int tempo > 0
    //EFFECT: construct progression with given values
    public Progression(String name, String key, int tempo, TimeSignatures timeSignature) {
        setName(name);
        setKey(key);
        setTempo(tempo);
        setTimeSignature(timeSignature);
    }

    //Setters:
    public void setName(String name) {
        this.name = name;
        EventLog.getInstance().logEvent(new Event("Progression " + this.getName() + " has name set to" + name));
    }

    public void setKey(String key) {
        this.key = key;
        EventLog.getInstance().logEvent(new Event("Progression " + this.getName() + " has key set to" + key));
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
        EventLog.getInstance().logEvent(new Event("Progression " + this.getName() + " has tempo set to" + tempo));
    }

    public void setNotes(String notes) {
        this.notes = notes;
        EventLog.getInstance().logEvent(
                new Event("Progression " + this.getName() + " has been given the notes " + notes));
    }

    public void setTimeSignature(TimeSignatures t) {
        this.timeSignature = t;
        EventLog.getInstance().logEvent(new Event("Progression " + this.getName() + " has set time signature to" + t));
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

    //EFFECTS: returns progression as json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("progression name", name);
        json.put("key", key);
        json.put("tempo", tempo);
        json.put("time signature", String.valueOf(timeSignature));
        json.put("notes", notes);
        return json;
    }
}

