import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Semaforo {

    private static final Color[] COLORES_SEMAFORO = {Color.RED, Color.GREEN, Color.YELLOW};
    private static int color = 0;

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setTitle("Semaforo");
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setResizable(false);

        JLabel lSemaforo = new JLabel(getColorName());
        lSemaforo.setForeground(getColor());
        lSemaforo.setFont(new Font("Arial", Font.BOLD, 40));
        lSemaforo.setBounds(0, 0, 400, 200);
        lSemaforo.setHorizontalAlignment(JLabel.CENTER);
        lSemaforo.setVerticalAlignment(JLabel.CENTER);
        frame.add(lSemaforo);

        JButton bSemaforo = new JButton("Cambiar luz");
        bSemaforo.setBounds(100, 200, 200, 50);
        bSemaforo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color++;
                if (color == COLORES_SEMAFORO.length) color = 0;
                lSemaforo.setText(getColorName());
                lSemaforo.setForeground(getColor());
            }
        });
        frame.add(bSemaforo);


        frame.setVisible(true);
    }

    public static Color getColor() {
        return COLORES_SEMAFORO[color];
    }

    public static String getColorName() {
        return switch (color) {
            case 0 -> "Rojo";
            case 1 -> "Verde";
            case 2 -> "Amarillo";
            default -> throw  new IllegalStateException("Numero de color fuera de rango.");
        };
    }

}
