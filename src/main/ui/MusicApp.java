package ui;


import model.Playlist;
import model.Progression;
import model.TimeSignatures;
import persisitance.JsonReader;
import persisitance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static model.TimeSignatures.*;


//Music progression app
public class MusicApp {
    private Scanner input;

    private Playlist playlist = new Playlist();

    //EFFECTS: runs the music application
    public MusicApp() throws IOException {
        try {
            runMusicApp();
        } catch (IOException e) {
            System.out.println("Exception Found");
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runMusicApp() throws IOException {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                promptToSave();
                keepGoing = false;
            } else if (command.equals("n")) {
                openNewProg();
            } else if (command.equals("o")) {
                openOldProg();
            } else if (command.equals("s")) {
                openSavedPlaylists();
            } else {
                processCommand(command);
            }
        }
    }

    //EFFECTS:prompt to save
    private void promptToSave() {
        System.out.println("\nDo you wish to save progressions in current playlist to file? Y/N");

        if (input.nextLine().equals("Y")) {
            saveProgInPlaylist(playlist);
        } else if (input.nextLine().equals("N")) {
            System.out.println("Are you sure? Y/N");
            if (input.nextLine().equals("Y")) {
                saveProgInPlaylist(playlist);
            }
        }
    }

    //EFFECTS: save progressions in given playlist to file
    private void saveProgInPlaylist(Playlist playlist) {
        try {
            JsonWriter writer = new JsonWriter("./data/savedPlaylists.json");
            writer.open();
            writer.write(playlist);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found");
        }
    }

    //EFFECTS: return new display for opening a saved playlist
    private void openSavedPlaylists() throws IOException {
        System.out.println("\nSaved Playlists...");

        try {
            JsonReader reader = new JsonReader("./data/savedPlaylists.json");
            playlist = reader.read();
            printListOfProgs(playlist);
        } catch (IOException e) {
            System.out.println("\nIO Exception");
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        System.out.println("invalid input try again");
    }

    //MODIFIES: this
    //EFFECTS: initializes application
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");

    }

    //EFFECTS: produces menu of options
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> new progression");
        System.out.println("\to -> open progression");
        System.out.println("\ts -> saved playlists");
        System.out.println("\tq -> quit");
    }


    //EFFECTS: return new display for creating new progressions
    private void openNewProg() {
        System.out.println("\nCreating New Progression...");
        Progression prog = new Progression("","",0,FOUR_FOUR);
        handleNewProgSetup(prog);
        addMusicToProg(prog);
        printProgReceipt(prog);
        playlist.addProgression(prog);

        System.out.println("\nReturning to main Menu");
    }

    //MODIFIES: this
    //EFFECTS: set up of new progression to user specifications
    private void handleNewProgSetup(Progression prog) {
        String name;
        String key;
        int tempo;
        TimeSignatures ts;
        String notes;

        System.out.println("\nType Progression Name: eg. Song");
        name = input.next();
        prog.setName(name);

        System.out.println("\nType Progression Key: eg. C");
        key = input.next();
        prog.setKey(key);

        System.out.println("\nType Tempo: eg. 200");
        tempo = Integer.parseInt(input.next());
        prog.setTempo(tempo);

        System.out.println("\nSelect One Time Signature: 4/4(1) 3/4(2) 7/4(3)");
        int i = Integer.parseInt(input.next());
        ts = handleTimeSignature(i);
        prog.setTimeSignature(ts);

    }


    //EFFECTS: handle old progressions page
    private void openOldProg() {
        Progression selectedProg;
        System.out.println("Old Progressions:");
        printListOfProgs(playlist);


        System.out.println("Select Progression by typing its name:");
        String nameOfProg = input.next();
        selectedProg = playlist.getProgression(nameOfProg);
        System.out.println("Progression " + selectedProg.getName() + " selected");

        System.out.println("Select Modify(M) Remove(R) or View(V):");

        if (input.next().equals("M")) {
            handleNewProgSetup(selectedProg);
        } else if (input.nextLine().equals("V")) {
            printProgReceipt(selectedProg);
            return;
        } else if (input.nextLine().equals("R")) {
            playlist.removeProgression(selectedProg.getName());
            System.out.println(selectedProg.getName() + " deleted.");
            return;
        } else {
            System.out.println("invalid input try again");
        }

    }


    //MODIFIES: this
    //EFFECTS: adds notes to progression
    private void addMusicToProg(Progression prog) {
        System.out.println("\nAdd Notes or Chords to " + prog.getName() + ".");
        System.out.println("\nType Notes as Individual Letters each separated by a space(eg. C B D)");
        System.out.println("\nType Chords as Roman Numerals each separated by a space(eg. I IV V)");

        String notesAndChords = input.next();
        prog.setNotes(notesAndChords);
        System.out.println("\nNotes or Chords added to " + prog.getName() + ".");
    }



    //EFFECTS: print info about progression
    public void printProgReceipt(Progression p) {
        System.out.println("\tName:" + p.getName());
        System.out.println("\tKey:" + p.getKey());
        System.out.println("\tTempo:" + p.getTempo());
        System.out.println("\tTime Signature:" + p.getTimeSignature());
        System.out.println("\tNotes/Chords:" + p.getNotes());
    }


    //EFFECTS: returns time signature specified by user
    private TimeSignatures handleTimeSignature(int next) {
        if (next == 1) {
            return FOUR_FOUR;
        } else if (next == 2) {
            return THREE_FOUR;
        } else if (next == 3) {
            return SEVEN_FOUR;
        } else {
            System.out.println("\nPlease choose one by entering the digit: 4/4(1) 3/4(2) 7/4(3)");
            handleTimeSignature(Integer.parseInt(input.next()));
            return null;
        }
    }

    //EFFECTS: save progression to list of progressions
    private void addProgressionToList(Progression p) {
        playlist.addProgression(p);
        System.out.println(p.getName() + " added to Progressions List");
    }

    //EFFECTS: prints list of progessions in playlist
    public void printListOfProgs(Playlist playlist) {
        for (Progression p : playlist.listOfProgs()) {
            System.out.println("\tName:" + p.getName());
            System.out.println("\tKey:" + p.getKey());
            System.out.println("\tTempo:" + p.getTempo());
            System.out.println("\tTime Signature:" + p.getTimeSignature());
        }
    }

}
