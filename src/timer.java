import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//from ww  w  . j  a  va  2 s .  c o m
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class timer {

    public static void main(String[] args) {
        new timer();
    }

    public timer() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(500,500));
        frame.add(new TestPane());
        frame.pack();
        frame.setVisible(true);
    }
}

class TestPane extends JPanel {
    JLabel label;
    Timer timer;
    int count;
    public TestPane() {
        label = new JLabel();
        setLayout(new GridBagLayout());
        add(label);
        timer = new Timer(900, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                if (count < 100000) {
                    label.setText(Integer.toString(count));
                } else {
                    ((Timer) (e.getSource())).stop();
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }
}