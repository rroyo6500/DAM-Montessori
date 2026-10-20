import javax.swing.*;
import java.awt.*;

public class Robot extends JPanel {

    public static void main(String[] args) {
        new Robot();
    }

    public Robot() {
        JFrame frame = new JFrame("Robor");
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 800));
        frame.setContentPane(this);
        frame.pack();

        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //antena
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(395, 150, 10, 75);
        g.setColor(Color.RED);
        g.fillOval(390, 145, 20, 20);
        g.setColor(Color.GRAY);
        g.fillPolygon(
                new int[] {
                        375, 425, 405, 395
                },
                new int[] {
                        250, 250, 225, 225
                },
                4
        );
        //cabeza
        g.fillRect(250, 250, 300, 200);

        //ojos
        g.setColor(Color.BLACK);
        g.fillRect(300, 300, 75, 25);
        g.fillRect(425, 300, 75, 25);

        //boca
        g.fillRect(300, 375, 200, 25);
        g.setColor(Color.WHITE);
        g.drawRect(300, 375, 25, 25);
        g.drawRect(325, 375, 25, 25);
        g.drawRect(350, 375, 25, 25);
        g.drawRect(375, 375, 25, 25);
        g.drawRect(400, 375, 25, 25);
        g.drawRect(425, 375, 25, 25);
        g.drawRect(450, 375, 25, 25);
        g.drawRect(475, 375, 25, 25);

    }
}