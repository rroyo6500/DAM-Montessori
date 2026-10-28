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

    private static final Font SCORE_FONT = new Font("Arial", Font.BOLD, 24);

    private final Timer timer;

    private final Rectangle bola = new Rectangle(WIDTH/2, HEIGHT/2, 25, 25);
    private boolean bolaLeft = false;
    private boolean bolaUp = false;

    private final Rectangle player1 = new Rectangle(100, (HEIGHT/2)-50, 10, 100);
    private final Rectangle player2 = new Rectangle(900, (HEIGHT/2)-50, 10, 100);
    private PlayerDirection player1Direction = PlayerDirection.NULL;
    private PlayerDirection player2Direction = PlayerDirection.NULL;
    private int p1Score = 0;
    private int p2Score = 0;

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

        if (p1Score >= 5 || p2Score >= 5) {
            return;
        }

        //Bola
        if (bola.intersects(player1) || bola.intersects(player2) ||
                bola.y <= 0 || (bola.y+bola.height) >= HEIGHT
        ) {
            if (bola.intersects(player1)) {
                bolaLeft = false;
            }
            else if (bola.intersects(player2)) {
                bolaLeft = true;
                bola.x = player2.x-bola.width;
            }

            if (bola.y <= 0) bolaUp = false;
            else if ((bola.y+bola.height) >= HEIGHT) {
                bolaUp = true;
                bola.y = HEIGHT-bola.height;
            }
        }

        if (bola.x < 0) {
            p2Score++;
            bola.x = WIDTH/2;
            bola.y = HEIGHT/2;
        }

        if (bola.x+bola.width >= WIDTH) {
            p1Score++;
            bola.x = WIDTH/2;
            bola.y = HEIGHT/2;
        }

        if (bolaLeft) bola.x -= 5;
        else bola.x += 5;

        if (bolaUp) bola.y -= 5;
        else bola.y += 5;

        // player1

        PlayerControls(player1, 1);

        // player2

        PlayerControls(player2, 2);

        repaint();
    }

    private void PlayerControls(Rectangle player, int playerID) {
        if (player.y <= 0) {
            if (playerID == 1) player1Direction = PlayerDirection.NULL;
            if (playerID == 2) player2Direction = PlayerDirection.NULL;
            player.y = 5;
        } else if ((player.y + player.height) >= HEIGHT) {
            if (playerID == 1) player1Direction = PlayerDirection.NULL;
            if (playerID == 2) player2Direction = PlayerDirection.NULL;
            player.y = (HEIGHT-(player.height))-5;
        }

        player.y += switch (playerID == 1? player1Direction : player2Direction) {
            case NULL -> 0;
            case UP -> -5;
            case DOWN -> 5;
        };
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(getWidth()/2, 0, 1, getHeight());

        g.setFont(SCORE_FONT);
        g.drawString(String.valueOf(p1Score), getWidth()/2-50, 50);
        g.drawString(String.valueOf(p2Score), getWidth()/2+50, 50);

        g.setColor(Color.WHITE);
        g.fillOval(bola.x, bola.y, bola.width, bola.height);

        // player1
        g.fillRect(player1.x, player1.y, player1.width, player1.height);

        // player2
        g.fillRect(player2.x, player2.y, player2.width, player2.height);

        if (p1Score >= 5 || p2Score >= 5) {
            g.setColor(Color.GREEN);
            g.drawString((p1Score > p2Score? "Player 1" : "Player2") + " WIN!", (WIDTH/2)-75, HEIGHT/2);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // player1
        if (e.getKeyCode() == KeyEvent.VK_W)
            player1Direction = PlayerDirection.UP;
        else if (e.getKeyCode() == KeyEvent.VK_S)
            player1Direction = PlayerDirection.DOWN;

        // player2
        if (e.getKeyCode() == KeyEvent.VK_UP)
            player2Direction = PlayerDirection.UP;
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            player2Direction = PlayerDirection.DOWN;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
