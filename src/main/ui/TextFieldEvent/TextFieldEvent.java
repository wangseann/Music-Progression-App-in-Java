package ui.TextFieldEvent;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextFieldEvent {


    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JTextField textField = new JTextField();
    JButton button = new JButton("Ok");


    //EFFECTS: constructs a TextField
    public TextFieldEvent() {
        panel.add(textField);
        panel.add(button);
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

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
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

