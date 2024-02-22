package test;

import javax.swing.*;
import java.awt.*;

public class FrameServeur extends JFrame {
    public FrameServeur() {
        setTitle("Frame Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
}