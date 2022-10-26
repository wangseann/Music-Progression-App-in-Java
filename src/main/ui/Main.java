package ui;

import model.Playlist;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            new Playlist();
            new MusicApp();
        } catch (IOException e) {
            System.out.println("Exception Found");
        }

    }
}
