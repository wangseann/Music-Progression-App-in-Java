package ui.events;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SavePlaylistTextFieldEvent {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel nameLabel = new JLabel("Type Playlist Name: eg. Playlist 1");
    JLabel dateLabel = new JLabel("Type Playlist Date: eg. March 1, 2022");
    JTextField textFieldName = new JTextField("untitled playlist");
    JTextField textFieldDate = new JTextField("March 1, 2022");
    JButton button = new JButton("Ok");


    //EFFECTS: constructs a TextField
    public SavePlaylistTextFieldEvent() {
        panel.add(nameLabel);
        panel.add(textFieldName);
        panel.add(dateLabel);
        panel.add(textFieldDate);
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
                String text = textField.getText();
                textField.setText(text.toUpperCase());
            }
        });
    }

    //Getters
    public String getTextFieldName() {
        return textFieldName.getText();
    }

    public String getTextFieldDate() {
        return textFieldDate.getText();
    }
}
