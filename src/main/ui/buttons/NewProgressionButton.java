package ui.buttons;

import model.Progression;
import ui.MusicApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class NewProgressionButton extends Button {
    protected Progression progression;

    public NewProgressionButton(MusicApp musicApp, JComponent parent) {
        super(musicApp, parent);
        progression = null;
    }

    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(getLabel());
        button = customizeButton(button);
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new NewProgressionClickHandler());
    }

    // MODIFIES: this
    // EFFECTS:  a shape is instantiate MouseEvent occurs, and played and
    //           added to the editor's drawing
    @Override
    public void mousePressedInDrawingArea(MouseEvent e) {
        musicApp.openNewProg();
    }


    // MODIFIES: this
    // EFFECTS:  unselects this shape, and sets it to null
    @Override
    public void mouseReleasedInDrawingArea(MouseEvent e) {
        //do nothing
    }

    // MODIFIES: this
    // EFFECTS:  sets the bounds of thes shape to where the mouse is dragged to
    @Override
    public void mouseDraggedInDrawingArea(MouseEvent e) {
        //do nothing
    }

    //EFFECTS: returns Label for new progression button as string
    public String getLabel() {
        return "New Progression";
    }

    private class NewProgressionClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the new progression tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            musicApp.setActiveButton(NewProgressionButton.this);
        }
    }

}