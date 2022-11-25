package ui;


import model.Event;
import model.EventLog;
import model.Playlist;
import model.Progression;
import persisitance.JsonReader;
import persisitance.JsonWriter;
import ui.events.NewProgressionTextFieldEvent;
import ui.events.RemoveProgressionTextFieldEvent;
import ui.events.SavePlaylistTextFieldEvent;
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
        displayProgressions();
        createButtons();
        initializeKeyboard();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: displays progression details in GUI
    private void displayProgressions() {
        JPanel displayField = new JPanel();
        displayField.setLayout(new BoxLayout(displayField, BoxLayout.PAGE_AXIS));
        JLabel displayFieldName = new JLabel("Current Progressions:");
        displayField.add(displayFieldName);

        if (playlist == null) {
            // display nothing
        } else {
            for (Progression p : playlist.listOfProgs()) {
                JLabel progression = new JLabel(p.getName() + " " + p.getKey() + " " + p.getTempo());
                displayField.add(progression);
            }
        }

        displayField.setVisible(true);
        add(displayField);
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
        File file = new File("./data/savedPlaylists.json");
        if (file.length() == 0) {
            //return to main menu
        } else {
            try {
                JsonReader reader = new JsonReader("./data/savedPlaylists.json");
                playlist = reader.read();
                displayProgressions();
            } catch (IOException e) {
                System.out.println("\nIO Exception");
            }
        }
    }


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
        //open popup
        NewProgressionTextFieldEvent newProgressionTextFieldEvent = new NewProgressionTextFieldEvent(prog);
        displayProgressions();
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

        Button removeProgressionButton = new RemoveProgressionButton(this, buttonArea);
        buttons.add(removeProgressionButton);

        Button savedProgressionsButton = new SavedProgressionsButton(this, buttonArea);
        buttons.add(savedProgressionsButton);

        Button savePlaylistButton = new SavePlaylistButton(this, buttonArea);
        buttons.add(savePlaylistButton);

        Button quitButton = new QuitButton(this, buttonArea);
        buttons.add(quitButton);

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

    //MODIFIES: this
    //EFFECTS: handles the removing of a progression from the current playlist
    public void handleRemoveProgression() {
        RemoveProgressionTextFieldEvent removeProgressionTextFieldEvent = new RemoveProgressionTextFieldEvent(playlist);
        displayProgressions();
    }

    //EFFECTS: prints events in event log to console
    public void printEventLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n\n");
        }
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
