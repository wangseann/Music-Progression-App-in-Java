package ui;


import model.Playlist;
import model.Progression;
import model.TimeSignatures;
import persisitance.JsonReader;
import persisitance.JsonWriter;
import ui.TextFieldEvent.NewProgressionTextFieldEvent;
import ui.TextFieldEvent.SavePlaylistTextFieldEvent;
import ui.buttons.*;
import ui.buttons.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.TimeSignatures.*;


//Music progression app
public class MusicApp extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private List<Button> buttons;
    private Button activeButton;
    private Progression currentProgression;

    private Scanner input;
    private Playlist playlist = new Playlist();

//    //EFFECTS: runs the music application
//    public MusicApp() {
//        try {
//            runMusicApp();
//        } catch (IOException e) {
//            System.out.println("Exception Found");
//        }
//    }
//
    //EFFECTS: runs the music application
    public MusicApp() {
        super("Music Application");
        intializeFields();
        initializeGraphics();
        initializeInteraction();
    }

    //MODIFIES: this
    //EFFECTS: initializes a MusicMouseListener to be used in JFrame
    private void initializeInteraction() {
        MusicMouseListener musicMouseListener = new MusicMouseListener();
        addMouseListener(musicMouseListener);
        addMouseMotionListener(musicMouseListener);
    }

    //MODIFIES: this
    //EFFECTS: draws JFrame window where MusicMouseListener will function to manipulate music app frame
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH, HEIGHT));
        createButtons();
        initializeKeyboard();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds keyboard image to initial frame
    private void initializeKeyboard() {
        try {
            ImageIcon image = new ImageIcon("src/images/pianokeyboard.jpeg");
            JLabel displayField = new JLabel(image);
            add(displayField);
        } catch (Exception e) {
            System.out.println("image cannot be found");
        }
    }

    //MODIFIES: this
    //EFFECTS: sets activeButton, currentPlaylist = null, currentProgression = null,
    // and populates buttons with buttons being used.
    private void intializeFields() {
        activeButton = null;
        currentProgression = null;
        buttons = new ArrayList<Button>();
    }


    //MODIFIES: this
    //EFFECTS: processes user input
    private void runMusicApp() throws IOException {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            //displayMenu();

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                promptToSave();
                keepGoing = false;
            } else if (command.equals("n")) {
                openNewProg();
            } else if (command.equals("o")) {
                if (playlist.size() == 0) {
                    //return to main menu
                } else {
                    openOldProg();
                }
            } else if (command.equals("s")) {
                openSavedPlaylists();
            } else {
                processCommand(command);
            }
        }
    }

    //MODIFIES: playlist
    //EFFECTS:prompt user to save progessions in playlist
    public void promptToSave() {
        SavePlaylistTextFieldEvent savePlaylistTextFieldEvent = new SavePlaylistTextFieldEvent();
        playlist.setName(savePlaylistTextFieldEvent.getTextFieldName());
        playlist.setDate(savePlaylistTextFieldEvent.getTextFieldDate());

        saveProgInPlaylist(playlist);
    }

    //EFFECTS: returns this music app's current playlist
    public Playlist getPlaylist() {
        return playlist;
    }

    //EFFECTS: save progressions in given playlist to file
    public void saveProgInPlaylist(Playlist playlist) {
        try {
            JsonWriter writer = new JsonWriter("./data/savedPlaylists.json");
            writer.open();
            writer.write(playlist);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found");
        }
    }

    //MODIFIES: playlist
    //EFFECTS: read playlist from file and set as current playlist
    public void openSavedPlaylists() {
        System.out.println("\nSaved Playlists...");
        File file = new File("./data/savedPlaylists.json");
        if (file.length() == 0) {
            //return to main menu
        } else {
            try {
                JsonReader reader = new JsonReader("./data/savedPlaylists.json");
                playlist = reader.read();
                System.out.println(playlist.getName());
                System.out.println(playlist.getDate());
                printListOfProgs(playlist);
            } catch (IOException e) {
                System.out.println("\nIO Exception");
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    //used later for midi keyboard
    private void processCommand(String command) {
        System.out.println("invalid input try again");
    }

    //MODIFIES: this
    //EFFECTS: initializes application
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

//    //EFFECTS: produces menu of options
//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\tn -> new progression");
//        System.out.println("\to -> open progression");
//        System.out.println("\ts -> open saved playlist");
//        System.out.println("\tq -> quit");
//    }

    //MODIFIES: playlist
    //EFFECTS: return new display for creating new progressions
    public void openNewProg() {
        Progression prog = new Progression("","",0,FOUR_FOUR);
        handleNewProgSetup(prog);
        //addMusicToProg(prog);
        printProgReceipt(prog);
        playlist.addProgression(prog);

        System.out.println("\nReturning to main Menu");
    }

    //MODIFIES: this
    //EFFECTS: set up of new progression to user specifications
    private void handleNewProgSetup(Progression prog) {
        NewProgressionTextFieldEvent newProgressionTextFieldEvent = new NewProgressionTextFieldEvent();
        String name = newProgressionTextFieldEvent.getTextFieldName();
        String key = newProgressionTextFieldEvent.getTextFieldKey();
        int tempo = 0;
        int timeSignatureInt = 0;
        String tempoString = newProgressionTextFieldEvent.getTextFieldTempo();
        if (tempoString.equals("")) {
            tempoString = "1";
        }

        String timeSignatureString = newProgressionTextFieldEvent.getTextFieldTimeSignature();
        if (timeSignatureString.equals("")) {
            timeSignatureString = "1";
        }

        prog.setName(name);
        prog.setKey(key);

        try {
            tempo = Integer.parseInt(tempoString);
        } catch (NumberFormatException e) {
            tempoString = "1";
        } finally {
            prog.setTempo(tempo);
        }



        try {
            timeSignatureInt = Integer.parseInt(timeSignatureString);
        } catch (NumberFormatException e) {
            timeSignatureString = "1";
        } finally {
            TimeSignatures ts = handleTimeSignature(timeSignatureInt);
            prog.setTimeSignature(ts);
        }
        printProgReceipt(prog);
    }

    //MODIFIES: playlist
    //EFFECTS: handle old progressions page
    public void openOldProg() {
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
        } else if (input.nextLine().equals("R")) {
            playlist.removeProgression(selectedProg.getName());
            System.out.println(selectedProg.getName() + " deleted.");
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

    //REQUIRES: int ts given is either 1,2, or 3
    //EFFECTS: returns time signature specified by user
    private TimeSignatures handleTimeSignature(int ts) {
        if (ts == 1) {
            return FOUR_FOUR;
        } else if (ts == 2) {
            return THREE_FOUR;
        } else if (ts == 3) {
            return SEVEN_FOUR;
        } else {
            System.out.println("\nPlease choose one by entering the digit: 4/4(1) 3/4(2) 7/4(3)");
            handleTimeSignature(Integer.parseInt(input.next()));
            return null;
        }
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

    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all buttons
    private void createButtons() {
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(0,1));
        buttonArea.setSize(new Dimension(0, 0));
        add(buttonArea, BorderLayout.SOUTH);

        Button newProgressionButton = new NewProgressionButton(this, buttonArea);
        buttons.add(newProgressionButton);

        Button openProgressionButton = new OpenProgressionButton(this, buttonArea);
        buttons.add(openProgressionButton);

        Button quitButton = new QuitButton(this, buttonArea);
        buttons.add(quitButton);

        Button savedProgressionsButton = new SavedProgressionsButton(this, buttonArea);
        buttons.add(savedProgressionsButton);

        Button savePlaylistButton = new SavePlaylistButton(this, buttonArea);
        buttons.add(savePlaylistButton);

        setActiveButton(newProgressionButton);  //? do i need active button?
    }

    //MODIFIES: this
    //EFFECTS: sets the given button and the activeButton
    public void setActiveButton(Button button) {
        if (activeButton != null) {
            activeButton.deactivate();
        }
        button.activate();
        activeButton = button;
    }

    private class MusicMouseListener extends MouseAdapter {

        // EFFECTS: Forward mouse pressed event to the active tool
        public void mousePressed(MouseEvent e) {
            //handleMousePressed(translateEvent(e));
        }

        // EFFECTS: Forward mouse released event to the active tool
        public void mouseReleased(MouseEvent e) {
            //handleMouseReleased(translateEvent(e));
        }

        // EFFECTS:Forward mouse clicked event to the active tool
        public void mouseClicked(MouseEvent e) {
            //handleMouseClicked(translateEvent(e));
        }

        // EFFECTS:Forward mouse dragged event to the active tool
        public void mouseDragged(MouseEvent e) {
            //handleMouseDragged(translateEvent(e));
        }

        // EFFECTS: translates the mouse event to current drawing's coordinate system
        private void translateEvent(MouseEvent e) {
//            return SwingUtilities.convertMouseEvent(e.getComponent(), e, currentProgression);
        }
    }

    // EFFECTS: if activeTool != null, then mouseReleasedInDrawingArea is invoked on activeTool, depends on the
    // type of the tool which is currently activeTool
    private void handleMousePressed(MouseEvent e) {
        if (activeButton != null) {
            activeButton.mouseReleasedInDrawingArea(e);
        }
        repaint();
    }

    // EFFECTS: if activeTool != null, then mouseReleasedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
    private void handleMouseReleased(MouseEvent e) {
        if (activeButton != null) {
            activeButton.mouseReleasedInDrawingArea(e);
        }
        repaint();
    }

    // EFFECTS: if activeTool != null, then mouseClickedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
    private void handleMouseClicked(MouseEvent e) {
        if (activeButton != null) {
            activeButton.mouseClickedInDrawingArea(e);
        }
        repaint();
    }

    // EFFECTS: if activeTool != null, then mouseDraggedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
    private void handleMouseDragged(MouseEvent e) {
        if (activeButton != null) {
            activeButton.mouseDraggedInDrawingArea(e);
        }
        repaint();
    }
}
