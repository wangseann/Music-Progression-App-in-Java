package ui;


import model.Playlist;
import model.Progression;
import model.TimeSignatures;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.TimeSignatures.*;


//Music progression app
public class MusicApp {
    private Scanner input;
    private List<Progression> progressionsList = new ArrayList<>();
    private Playlist playlist = new Playlist();

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
            displayMenu();

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else if (command.equals("n")) {
                openNewProg();
            } else if (command.equals("o")) {
                openOldProg();
            } else {
                processCommand(command);
            }
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
        System.out.println("\tq -> quit");
    }


    //EFFECTS: return new display for creating new progressions
    private void openNewProg() {
        System.out.println("\nCreating New Progression...");
        Progression prog = new Progression("","",0,FOUR_FOUR);
        handleNewProgSetup(prog);
        addMusicToProg(prog);
        printProgReceipt(prog);
        progressionsList.add(prog);
        playlist.addProgression(prog);

        System.out.println("\nReturning to main Menu");
    }

    //MODIFIES: this
    //EFFECTS: set up of new progression to user specifications
    private Progression handleNewProgSetup(Progression prog) {
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

        return prog;
    }


    //EFFECTS: handle old progressions page
    private void openOldProg() {
        Progression selectedProg;
        System.out.println("Old Progressions:");
        playlist.printListOfProgs();


        System.out.println("Select Progression by typing its name:");
        String nameOfProg = input.next();
        selectedProg = playlist.getProgression(nameOfProg);
        System.out.println("Progression " + selectedProg.getName() + " selected");

        System.out.println("Select Modify(M) Remove(R) or View(V):");

        if (input.next().equals("M")) {
            handleNewProgSetup(selectedProg);
        } else if (input.next().equals("V")) {
            printProgReceipt(selectedProg);
            return;
        } else if (input.next().equals("R")) {
            playlist.removeProgression(selectedProg.getName());
            System.out.println(selectedProg.getName() + " deleted.");
            return;
        } else {
            System.out.println("invalid input try again");
        }

    }

    private void printListOfProgs(List<Progression> progressionsList) {
        for (Progression p : progressionsList) {
            System.out.println("\tName:" + p.getName());
            System.out.println("\tKey:" + p.getKey());
            System.out.println("\tTempo:" + p.getTempo());
            System.out.println("\tTime Signature:" + p.getTimeSignature());
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

    //MODIFIES:
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

    //EFFECTS: ask user to save progression to list of progressions
    private void addProgressionToList(Progression p) {
        playlist.addProgression(p);
        progressionsList.add(p);
        System.out.println(p.getName() + " added to Progressions List");
    }


}
