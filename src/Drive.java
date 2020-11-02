
import javax.swing.*;
import java.awt.*;


class Drive {
    public static void main(String[] args){
        GameFrame frame = new GameFrame();
        frame.add(frame.panelMain);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(650,800));
        frame.pack();
        frame.setVisible(true);

    }

}
