package ui;

import java.util.Locale;
import java.util.Scanner;

//Music progression app
public class MusicApp {
    private Scanner input;

    //EFFECTS: runs the music application
    public MusicApp() {
        runMusicApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runMusicApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {

    }

    //MODIFIES: this
    //EFFECTS: initializes application
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");

    }
}
