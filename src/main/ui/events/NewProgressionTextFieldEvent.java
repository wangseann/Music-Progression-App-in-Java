package ui.events;

import javax.swing.*;
import java.awt.event.*;

public class NewProgressionTextFieldEvent {


    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
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
    public NewProgressionTextFieldEvent() {
        panel.add(nameLabel);
        panel.add(textFieldName);
        panel.add(keyLabel);
        panel.add(textFieldKey);
        panel.add(tempoLabel);
        panel.add(textFieldTempo);
        panel.add(timeSignatureLabel);
        panel.add(textFieldTimeSignature);
        panel.add(button);


        //EFFECTS: close textfield frame on "ok" button press
        button.addActionListener(e -> {
            frame.setVisible(false); //you can't see me!
            frame.dispose(); //Destroy the JFrame object
        });

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        textFieldName.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                textField.setText(textField.getText());
            }
        });
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

    //EFFECTS: set progression values to values in TextField
    public void setProgValues() {

    }
}

//    textFieldName.addFocusListener(new FocusListener) {
//        public void focusGained(FocusEvent arg0) {
//            textFieldName.setText("");
//        }
//
//        public void focusLost(FocusEvent arg1) {
//            textFieldName.setText("Please Enter Some Text");
//        }
//
//    }

