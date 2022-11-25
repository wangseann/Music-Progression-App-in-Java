package ui.events;

import model.Playlist;
import model.Progression;
import model.TimeSignatures;

import javax.swing.*;
import java.awt.event.*;

import static model.TimeSignatures.*;

public class NewProgressionTextFieldEvent {


    JFrame frame = new JFrame();
    JLabel nameLabel = new JLabel("Type Progression Name: eg. Blackbird");
    JLabel keyLabel = new JLabel("Type Progression Key: eg. C");
    JLabel tempoLabel = new JLabel("Type Tempo: eg. 200");
    JLabel timeSignatureLabel = new JLabel("Select One Time Signature: 4/4(1) 3/4(2) 7/4(3)");
    JTextField textFieldName = new JTextField("untitled");
    JTextField textFieldKey = new JTextField("C");
    JTextField textFieldTempo = new JTextField();
    JTextField textFieldTimeSignature = new JTextField();
    JButton button = new JButton("Ok");


    //EFFECTS: constructs a TextField
    public NewProgressionTextFieldEvent(Playlist playlist) {
        JPanel panel = new JPanel();
        addComponenetsToPanel(panel);

        //add components to current frame
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        //close textfield frame and update prog values on "ok" button press
        button.addActionListener(e -> {
            setProgValues(playlist);
            frame.setVisible(false); //you can't see me!
            frame.dispose(); //Destroy the JFrame object
        });

        textFieldName.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                textField.setText(textField.getText());
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: adds components to panel in this frame
    private void addComponenetsToPanel(JPanel panel) {
        panel.add(nameLabel);
        panel.add(textFieldName);
        panel.add(keyLabel);
        panel.add(textFieldKey);
        panel.add(tempoLabel);
        panel.add(textFieldTempo);
        panel.add(timeSignatureLabel);
        panel.add(textFieldTimeSignature);
        panel.add(button);
    }

    //Getters
    public String getTextFieldName() {
        return textFieldName.getText();
    }

    public String getTextFieldKey() {
        return textFieldKey.getText();
    }

    public String getTextFieldTempo() {
        return textFieldTempo.getText();
    }

    public String getTextFieldTimeSignature() {
        return textFieldTimeSignature.getText();
    }

    //MODIFIES: prog
    //EFFECTS: set progression values to values in TextField
    public void setProgValues(Playlist playlist) {
        //read local variable values from TextFieldEvent
        String name = this.getTextFieldName();
        String key = this.getTextFieldKey();


        //set default values for tempo and time signature
        playlist.addProgression(
                new Progression(name, key,
                        handleProgTempo(this.getTextFieldTempo()),
                        handleProgTimeSig(this.getTextFieldTimeSignature())));
    }

    //MODIFIES: this
    //EFFECTS: returns TimeSignature based on given string
    private TimeSignatures handleProgTimeSig(String tsAsString) {
        int timeSignatureInt = 0;

        //set TimeSignature string as string in textfield or "1" as default
        if (tsAsString.equals("")) {
            tsAsString = "1";
        }

        //set prog time sig
        try {
            timeSignatureInt = Integer.parseInt(tsAsString);
        } catch (NumberFormatException e) {
            tsAsString = "1";
        } finally {
            TimeSignatures ts = handleTimeSignature(timeSignatureInt);
            return ts;
        }
    }

    //MODIFIES: this
    //EFFECTS: sets progressions tempo
    private int handleProgTempo(String tempoAsString) {
        int tempo = 0;

        //set tempo string as string in textfield or "1" as default
        if (tempoAsString.equals("")) {
            tempoAsString = "1";
        }
        //Set prog tempo
        try {
            tempo = Integer.parseInt(tempoAsString);
        } catch (NumberFormatException e) {
            tempoAsString = "1";
        } finally {
            return tempo;
        }
    }

    //EFFECTS: returns time signature specified
    private TimeSignatures handleTimeSignature(int ts) {
        if (ts == 1) {
            return FOUR_FOUR;
        } else if (ts == 2) {
            return THREE_FOUR;
        } else if (ts == 3) {
            return SEVEN_FOUR;
        } else {
            return null;
        }
    }
}


