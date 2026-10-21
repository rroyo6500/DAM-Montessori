import javax.swing.*;
import java.awt.*;

public class Marcador {

    private static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 24);

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setTitle("Marcador");
        frame.setSize(600,550);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JLabel puntuacionLocal = new JLabel("0");
        puntuacionLocal.setBounds(0, 70, 300, 100);
        puntuacionLocal.setFont(DEFAULT_FONT);
        puntuacionLocal.setHorizontalAlignment(JLabel.CENTER);
        puntuacionLocal.setVerticalAlignment(JLabel.CENTER);
        frame.add(puntuacionLocal);

        JLabel equipoLocal = new JLabel("Equipo Local");
        equipoLocal.setBounds(0, 0, 300, 100);
        equipoLocal.setFont(DEFAULT_FONT);
        equipoLocal.setHorizontalAlignment(JLabel.CENTER);
        equipoLocal.setVerticalAlignment(JLabel.CENTER);
        equipoLocal.setOpaque(true);
        frame.add(equipoLocal);

        JLabel puntuacionExtrangero = new JLabel("0");
        puntuacionExtrangero.setBounds(300, 70, 300, 100);
        puntuacionExtrangero.setFont(DEFAULT_FONT);
        puntuacionExtrangero.setHorizontalAlignment(JLabel.CENTER);
        puntuacionExtrangero.setVerticalAlignment(JLabel.CENTER);
        frame.add(puntuacionExtrangero);

        JLabel equipoExtrangero = new JLabel("Equipo Extrangero");
        equipoExtrangero.setBounds(300, 0, 300, 100);
        equipoExtrangero.setFont(DEFAULT_FONT);
        equipoExtrangero.setHorizontalAlignment(JLabel.CENTER);
        equipoExtrangero.setVerticalAlignment(JLabel.CENTER);
        equipoExtrangero.setOpaque(true);
        frame.add(equipoExtrangero);

        createScoreButtons(
                frame,
                new Point(10, 140), new Dimension(280, 75),
                puntuacionLocal);
        createScoreButtons(
                frame,
                new Point(310, 140), new Dimension(280, 75),
                puntuacionExtrangero);

        JButton bReiniciar = new JButton("Reiniciar");
        bReiniciar.setBounds(200, 395, 200, 50);
        bReiniciar.addActionListener(e -> {
            equipoLocal.setBackground(Color.decode("#EEEEEE"));
            equipoExtrangero.setBackground(Color.decode("#EEEEEE"));
            puntuacionLocal.setText("0");
            puntuacionExtrangero.setText("0");
        });
        frame.add(bReiniciar);

        JButton bFinalizar = new JButton("Finalizar");
        bFinalizar.setBounds(200, 450, 200, 50);
        bFinalizar.addActionListener(e -> {
            int pLocal = Integer.parseInt(puntuacionLocal.getText());
            int pExtrangero = Integer.parseInt(puntuacionExtrangero.getText());

            if (pLocal > pExtrangero) {
                equipoLocal.setBackground(Color.GREEN);
                equipoExtrangero.setBackground(Color.RED);
            } else if (pLocal < pExtrangero) {
                equipoLocal.setBackground(Color.RED);
                equipoExtrangero.setBackground(Color.GREEN);
            } else {
                equipoLocal.setBackground(Color.LIGHT_GRAY);
                equipoExtrangero.setBackground(Color.LIGHT_GRAY);
            }

        });
        frame.add(bFinalizar);

        frame.setVisible(true);
    }

    public static void createScoreButtons(
            JFrame frame, Point startPoint, Dimension bounds,
            JLabel scoreLabel
    ) {
        scoreLabel.setText("0");
        for (int i = 1; i <= 3; i++) {
            JButton button = new JButton("+" + i);
            button.setBounds(startPoint.x, startPoint.y, bounds.width, bounds.height);
            int fi = i;
            button.addActionListener(e ->
                    scoreLabel.setText(String.valueOf(Integer.parseInt(scoreLabel.getText())+fi))
            );
            frame.add(button);

            startPoint.setLocation(startPoint.x,
                    startPoint.y + (bounds.height + 10));
        }

    }

}
