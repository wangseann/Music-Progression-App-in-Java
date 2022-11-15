package ui.buttons;

import model.Progression;
import ui.MusicApp;

import javax.swing.*;

public class OpenProgressionButton extends Button {
    protected Progression progression;

    public OpenProgressionButton(MusicApp musicApp, JComponent parent) {
        super(musicApp, parent);
        progression = null;
    }

    @Override
    protected void createButton(JComponent parent) {

    }

    @Override
    protected void addListener() {

    }
}
