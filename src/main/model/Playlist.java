package model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    ArrayList<Progression> playlist;

    public Playlist() {
        this.playlist = new ArrayList<>();
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

    //EFFECTS: prints list of progessions in playlist
    public void printListOfProgs() {
        for (Progression p : playlist) {
            System.out.println("\tName:" + p.getName());
            System.out.println("\tKey:" + p.getKey());
            System.out.println("\tTempo:" + p.getTempo());
            System.out.println("\tTime Signature:" + p.getTimeSignature());
        }
    }

    //EFFECTS: checks if playlist contains progression
    public boolean contains(Progression p1) {
        return this.playlist.contains(p1);
    }
}
