
import javax.swing.*;
import java.awt.*;


class Drive {
    public static void main(String[] args){
        GameFrame frame = new GameFrame();
        System.out.println("h1");
        frame.add(frame.panelMain);
        System.out.println("h2");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        System.out.println("h3");
        frame.setMinimumSize(new Dimension(650,800));
        System.out.println("h4");
        frame.pack();
        frame.setVisible(true);
        System.out.println("h5");

    }

}
