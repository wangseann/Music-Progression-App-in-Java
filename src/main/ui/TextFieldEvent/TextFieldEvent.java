package ui.TextFieldEvent;

import javax.swing.*;
import java.awt.event.*;

public class TextFieldEvent {


    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JTextField textField = new JTextField();
    JButton button = new JButton("Ok");


    //EFFECTS: constructs a TextField
    public TextFieldEvent() {
        panel.add(textField);
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
        textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                textField.setText(text.toUpperCase());
            }
        });
    }
}

//    textField.addFocusListener(new FocusListener) {
//        public void focusGained(FocusEvent arg0) {
//            textField.setText("");
//        }
//
//        public void focusLost(FocusEvent arg1) {
//            textField.setText("Please Enter Some Text");
//        }
//
//    }

