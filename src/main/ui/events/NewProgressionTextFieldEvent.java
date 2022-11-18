package ui.events;

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
    public NewProgressionTextFieldEvent(Progression prog) {
        JPanel panel = new JPanel();
        addComponenetsToPanel(panel);

        //add components to current frame
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        //close textfield frame and update prog values on "ok" button press
        button.addActionListener(e -> {
            setProgValues(prog);
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
    public void setProgValues(Progression prog) {
        //read local variable values from TextFieldEvent
        String name = this.getTextFieldName();
        String key = this.getTextFieldKey();

        //Set progression name and key
        prog.setName(name);
        prog.setKey(key);

        //set default values for tempo and time signature
        handleProgTimeSig(prog);
        handleProgTempo(prog);
    }

    //MODIFIES: this
    //EFFECTS: sets progression time signature
    private void handleProgTimeSig(Progression prog) {
        int timeSignatureInt = 0;

        //set TimeSignature string as string in textfield or "1" as default
        String timeSignatureString = this.getTextFieldTimeSignature();
        if (timeSignatureString.equals("")) {
            timeSignatureString = "1";
        }

        //set prog time sig
        try {
            timeSignatureInt = Integer.parseInt(timeSignatureString);
        } catch (NumberFormatException e) {
            timeSignatureString = "1";
        } finally {
            TimeSignatures ts = handleTimeSignature(timeSignatureInt);
            prog.setTimeSignature(ts);
        }
    }

    //MODIFIES: this
    //EFFECTS: sets progressions tempo
    private void handleProgTempo(Progression prog) {
        int tempo = 0;

        //set tempo string as string in textfield or "1" as default
        String tempoString = this.getTextFieldTempo();
        if (tempoString.equals("")) {
            tempoString = "1";
        }
        //Set prog tempo
        try {
            tempo = Integer.parseInt(tempoString);
        } catch (NumberFormatException e) {
            tempoString = "1";
        } finally {
            prog.setTempo(tempo);
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


