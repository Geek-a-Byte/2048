import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Drive {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.add(new NumSquare(16));
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,800);
        frame.add(new GameFrame().getMainPanel());
        frame.setVisible(true);
    }
}
