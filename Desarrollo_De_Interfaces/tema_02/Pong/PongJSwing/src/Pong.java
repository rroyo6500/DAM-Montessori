import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends JPanel implements ActionListener, KeyListener {

    private enum PlayerDirection {
        UP, DOWN, NULL
    }

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    private final Timer timer;

    private final Rectangle bola = new Rectangle(WIDTH/2, HEIGHT/2, 25, 25);
    private Color color = Color.WHITE;
    private boolean bolaLeft = false;
    private boolean bolaUp = false;

    private final Rectangle player = new Rectangle(100, (HEIGHT/2)-50, 10, 100);
    private PlayerDirection playerDirection = PlayerDirection.NULL;

    public Pong () {
        JFrame frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setContentPane(this);
        frame.pack();
        frame.addKeyListener(this);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Bola
        if (bola.x <= 0 || (bola.x+bola.width) >= WIDTH || bola.intersects(player) ||
                bola.y <= 0 || (bola.y+bola.height) >= HEIGHT
        ) {
            if (bola.x <= 0 || bola.intersects(player)) bolaLeft = false;
            else if ((bola.x+bola.width) >= WIDTH) {
                bolaLeft = true;
                bola.x = WIDTH-bola.width;
            }

            if (bola.y <= 0) bolaUp = false;
            else if ((bola.y+bola.height) >= HEIGHT) {
                bolaUp = true;
                bola.y = HEIGHT-bola.height;
            }

            int r = (int) (Math.random() * 256);
            int g = (int) (Math.random() * 256);
            int b = (int) (Math.random() * 256);
            color = new Color(r, g, b);
        }

        if (bolaLeft) bola.x -= 5;
        else bola.x += 5;

        if (bolaUp) bola.y -= 5;
        else bola.y += 5;

        // player

        if (player.y <= 0) {
            playerDirection = PlayerDirection.NULL;
            player.y = 5;
        } else if ((player.y+player.height) >= HEIGHT) {
            playerDirection = PlayerDirection.NULL;
            player.y = (HEIGHT-(player.height))-5;
        }

        player.y += switch (playerDirection) {
            case NULL -> 0;
            case UP -> -5;
            case DOWN -> 5;
        };

        repaint();
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(color);
        g.fillOval(bola.x, bola.y, bola.width, bola.height);

        g.setColor(Color.WHITE);
        g.fillRect(player.x, player.y, player.width, player.height);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W)
            playerDirection = PlayerDirection.UP;
        else if (e.getKeyCode() == KeyEvent.VK_S)
            playerDirection = PlayerDirection.DOWN;
        else playerDirection = PlayerDirection.NULL;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
