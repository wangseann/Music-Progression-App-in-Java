package model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    ArrayList<Progression> playlist;
    private String date;

    public Playlist() {
        this.playlist = new ArrayList<Progression>();
    }

    //MODIFIES: this
    //EFFECT: add progression to playlist
    public void addProgression(Progression p) {
        this.playlist.add(p);
    }

    //MODIFIES: this
    //EFFECT: remove progression to playlist
    public void removeProgression(String name) {
        for (int i = 0; i < playlist.size(); i++) {
            if (this.playlist.get(i).getName().equals(name)) {
                this.playlist.remove(i);
            }
        }
    }

    //EFFECT: get named progression from playlist
    public Progression getProgression(String name) {
        Progression defaultProg = new Progression("","",0,TimeSignatures.FOUR_FOUR);

        for (Progression p : playlist) {
            if (p.getName().equals(name)) {
                defaultProg = p;
            }
        }

        if (defaultProg.getName().equals("")) {
            return null;
        }

        return defaultProg;
    }


    //EFFECTS: returns number of progressions in playlist
    public int size() {
        return playlist.size();
    }


    //EFFECTS: checks if playlist contains progression
    public boolean contains(Progression p1) {
        return this.playlist.contains(p1);
    }

    //EFFECTS: constructs and returns list of progressions in playlist
    public List<Progression> listOfProgs() {
        return new ArrayList<>(playlist);
    }

    //EFFECTS: sets playlist date as given date
    public void setDate(String date) {
        this.date = date;
    }

    //EFFECTS: returns playlist date as string
    public String getDate() {
        return this.date;
    }
}
