import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame{
    private JPanel panelMain;
    private JLabel two;
    private JLabel zero;
    private JLabel four;
    private JLabel eight;
    private JPanel panelButton;
    private JButton rightClick;
    private GamePanel panelGame;
    private JButton upClick;
    private JButton downClick;
    private JButton leftClick;


    GameFrame(){



    }

    JPanel getMainPanel()
    {
        return panelMain;
    }




    private void createUIComponents() {
        // TODO: place custom component creation code here
        panelGame = new GamePanel(6,6);
//        panelGame.setLayout(new GridLayout(6, 6));
//        for (int i = 0; i < 36; i++) {
//            panelGame.add(new JLabel("hello"));
//        }
    }
}
