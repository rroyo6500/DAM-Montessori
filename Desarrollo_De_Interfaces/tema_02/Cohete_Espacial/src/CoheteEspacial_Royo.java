import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class CoheteEspacial_Royo extends JPanel {

    private final Random r = new Random();

    public static void main(String[] args) {
        new CoheteEspacial_Royo();
    }

    public CoheteEspacial_Royo() {

        JFrame frame = new JFrame("Cohete Espacial - Roberto Royo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(800, 800));
        frame.setContentPane(this);
        frame.pack();

        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        g.setColor(new Color(0, 0, 75));
        g.fillRect(0, 0, 1400, 800);

        // Estrellas
        g.setColor(Color.YELLOW);
        setStars(g, 50);

        // Planeta
        g.setColor(new Color(125, 80, 0));
        g.fillOval(600, 600, 100, 100);
        g.setColor(Color.ORANGE);
        g.drawOval(575, 575, 150, 150);

        // Cohete
        g.setColor(Color.ORANGE);
        g.fillPolygon(getFire(300, 600, 200, 200));
        g.setColor(Color.YELLOW);
        g.fillPolygon(getFire(350, 600, 100, 100));

        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(300, 200, 200, 400, 25, 25);
        g.setColor(Color.CYAN);
        g.fillOval(350, 300, 100, 100);
        g.setColor(Color.RED);
        g.fillPolygon(
                new int[] {
                        400, 315, 485
                },
                new int[] {
                        50, 200, 200
                },
                3
        );
        g.fillPolygon(
                new int[] {
                        250, 300, 300
                },
                new int[] {
                        585, 585, 450
                },
                3
        );
        g.fillPolygon(
                new int[] {
                        550, 500, 500
                },
                new int[] {
                        585, 585, 450
                },
                3
        );



        // texto
        g.setColor(Color.WHITE);
        g.drawString("Mision Royo", 370, 15);
    }

    public Polygon getFire(int x, int y, int width, int height) {
        int[] xPoints = new int[] {
                x + (width / 8),
                x,
                x + (width / 8),
                x + (width / 16),
                x + ((width / 16) * 3),
                x + (width / 2),
                x + (width - ((width / 16) * 3)),
                x + (width - (width / 16)),
                x + (width - (width / 8)),
                x + width,
                x + (width - (width / 8))
        };
        int[] yPoints = new int[] {
                y,
                y + ((height / 8) * 2),
                y + (height / 8),
                y + ((height / 8) * 3),
                y + ((height / 8) * 2),
                y + height,
                y + ((height / 8) * 2),
                y + ((height / 8) * 3),
                y + (height / 8),
                y + ((height / 8) * 2),
                y
        };
        return new Polygon(xPoints, yPoints, xPoints.length);
    }

    public void setStars(Graphics g, int nStars) {
        for (int i = 0; i < nStars; i++) {
            int size = r.nextInt(25)+1;
            int x = r.nextInt(801 - size);
            int y = r.nextInt(801 - size);
            g.fillPolygon(getStar(x, y, size, size));
        }
    }

    public Polygon getStar(int x, int y, int width, int height) {
        int[] xPoints = new int[] {
                x + (width / 2),
                x + ((width / 3)),
                x,
                x + (((width / 3) / 3) * 2),
                x,
                x + (width / 2),
                x + (width),
                x + (((width / 3) * 2) + ((width / 3) / 3)),
                x + width,
                x + ((width / 3) * 2)
        };
        int[] yPoints = new int[] {
                y,
                y + (height / 3),
                y + (height / 3),
                y + (((height / 3) + (((height / 3) / 3) * 2))),
                y + height,
                y + (((height / 3) * 2) + ((width / 3) / 3)),
                y + height,
                y + (((height / 3) + (((height / 3) / 3) * 2))),
                y + (height / 3),
                y + (height / 3)
        };
        return new Polygon(xPoints, yPoints, 10);
    }
}
