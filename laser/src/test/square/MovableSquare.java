package test.square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class MovableSquare extends JFrame implements ActionListener {
    private Timer timer;
    private int x, y;
    private int squareSize = 50;
    private int speed = 5;
    private Set<ClientHandler> clientHandlers;

    public MovableSquare(Set<ClientHandler> clientHandlers) {
        this.clientHandlers = clientHandlers;
        setTitle("Movable Square");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        x = 100;
        y = 100;

        timer = new Timer(1000 / 60, this); // 60 FPS
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_UP) {
                    y -= speed;
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    y += speed;
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    x -= speed;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    x += speed;
                }

                updateClientHandlers();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillRect(x, y, squareSize, squareSize);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void updateClientHandlers() {
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.setX(x);
            clientHandler.setY(y);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            MovableSquare movableSquare = new MovableSquare(new HashSet<>());
            movableSquare.setVisible(true);
        });
    }

    
}