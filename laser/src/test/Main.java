package test;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        setTitle("Dessins superposés avec JLayeredPane");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 400));
        layeredPane.setLayout(new OverlayLayout(layeredPane));

        // Création du premier JPanel (rectangle rouge)
        JPanel firstPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.fillRect(0, 0, 400, 400);
            }
        };
        firstPanel.setBounds(0, 0, 400, 400);
        layeredPane.add(firstPanel, 0);

        // Création du deuxième JPanel (cercle bleu)
        JPanel secondPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(255,255,0,50));
                g.fillOval(50, 50, 300, 300);
            }
        };
        secondPanel.setBounds(0, 0, 400, 400);
        layeredPane.add(secondPanel, 1, 0);

        add(layeredPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }
}