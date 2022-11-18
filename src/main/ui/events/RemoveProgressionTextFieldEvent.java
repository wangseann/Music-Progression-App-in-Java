package ui.events;

import model.Playlist;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RemoveProgressionTextFieldEvent {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Type Progression Name to Remove: eg. Blackbird");
    JTextField textField = new JTextField();
    JButton button = new JButton("Ok");


    //EFFECTS: constructs a TextField
    public RemoveProgressionTextFieldEvent(Playlist playlist) {
        panel.add(label);
        panel.add(textField);
        panel.add(button);



        //EFFECTS: close textfield frame on "ok" button press
        button.addActionListener(e -> {
            playlist.removeProgression(this.getTextField());
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
                textField.setText(textField.getText());
            }
        });
    }

    //Getters
    public String getTextField() {
        return textField.getText();
    }
}
