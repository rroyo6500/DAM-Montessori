import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class BolaRebotante extends JPanel implements ActionListener {

    private enum DirX {
        L, R;
        public DirX getOposite() {
            return switch (this) {
                case L -> DirX.R;
                case R -> DirX.L;
            };
        }
    }
    private enum DirY {
        U, D;
        public DirY getOposite() {
            return switch (this) {
                case U -> DirY.D;
                case D -> DirY.U;
            };
        }
    }

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 600;

    private static int actualColor = 0;

    private final Rectangle ball = new Rectangle(0, 0, 25, 25);
    private DirX dirX = DirX.L;
    private DirY dirY = DirY.U;
    private int velocity = 1;

    Timer t;

    public BolaRebotante() {
        JFrame frame = new JFrame("BolaRebotante");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setContentPane(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        t = new Timer(30, this);
        t.start();

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ball.x <= 0 || (ball.x+ball.width) >= BolaRebotante.WIDTH) {
            dirX = dirX.getOposite();
            ball.setSize(ball.width + 5, ball.height + 5);
            if (ball.x <= 0) {
                ball.setLocation(0, ball.y);
            } else if ((ball.x+ball.width) >= BolaRebotante.WIDTH) {
                ball.setLocation(BolaRebotante.WIDTH-ball.width, ball.y);
            }
            actualColor = (actualColor + 1) % 7;
            velocity++;
        }
        if (ball.y <= 0 || (ball.y+ball.height) >= BolaRebotante.HEIGHT) {
            dirY = dirY.getOposite();
            ball.setSize(ball.width + 5, ball.height + 5);
            if (ball.y <= 0) {
                ball.setLocation(ball.x, 0);
            } else if ((ball.y+ball.height) >= BolaRebotante.HEIGHT) {
                ball.setLocation(ball.x, BolaRebotante.HEIGHT-ball.height);
            }
            actualColor = (actualColor + 1) % 7;
            velocity++;
        }

        ball.setLocation(
                switch (dirX) {
                    case L -> ball.x - velocity;
                    case R -> ball.x + velocity;
                },
                switch (dirY) {
                    case U -> ball.y - velocity;
                    case D -> ball.y + velocity;
                }
        );

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // BG
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, BolaRebotante.WIDTH, BolaRebotante.HEIGHT);

        // Dibujado
        g.setColor(switch (actualColor) {
            case 0 -> Color.RED;
            case 1 -> Color.BLUE;
            case 2 -> Color.MAGENTA;
            case 3 -> Color.ORANGE;
            case 4 -> Color.CYAN;
            case 5 -> Color.YELLOW;
            case 6 -> Color.BLACK;
            default -> Color.WHITE;
        });
        g.fillOval(ball.x, ball.y, ball.width, ball.height);
    }

}
