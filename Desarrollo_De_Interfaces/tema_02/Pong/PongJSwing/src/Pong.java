import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Pong {

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

    private final JPanel panel = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // BG
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, Pong.WIDTH, Pong.HEIGHT);

            // Logica

            if (ball.x <= 0 || (ball.x+ball.width) >= Pong.WIDTH) {
                dirX = dirX.getOposite();
                ball.setSize(ball.width + 10, ball.height + 10);
                if (ball.x <= 0) {
                    ball.setLocation(0, ball.y);
                } else if ((ball.x+ball.width) >= Pong.WIDTH) {
                    ball.setLocation(Pong.WIDTH-ball.width, ball.y);
                }
                actualColor = (actualColor + 1) % 7;
            }
            if (ball.y <= 0 || (ball.y+ball.height) >= Pong.HEIGHT) {
                dirY = dirY.getOposite();
                ball.setSize(ball.width + 10, ball.height + 10);
                if (ball.y <= 0) {
                    ball.setLocation(ball.x, 0);
                } else if ((ball.y+ball.height) >= Pong.HEIGHT) {
                    ball.setLocation(ball.x, Pong.HEIGHT-ball.height);
                }
                actualColor = (actualColor + 1) % 7;
            }

            ball.setLocation(
                    switch (dirX) {
                        case L -> ball.x - 10;
                        case R -> ball.x + 10;
                    },
                    switch (dirY) {
                        case U -> ball.y - 10;
                        case D -> ball.y + 10;
                    }
            );

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
    };

    public Pong() {
        JFrame frame = new JFrame("Pong");
        frame.setSize(WIDTH, HEIGHT);
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setContentPane(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        setTimer();

        frame.setVisible(true);
    }

    private void setTimer() {
        Timer t = new java.util.Timer();
        t.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        panel.repaint();
                    }
                },
                0, 1000/30
        );

    }


}
