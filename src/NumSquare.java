import javax.swing.*;
import java.awt.*;

public class NumSquare extends JComponent {
   static final int Scale =100;
   static final int Border =Scale/20;
   static final int Font_size =(int)(Scale*0.4);
    static final Font FONT =new Font("Consolas",Font.PLAIN,28);
    private int value;

    public void setValue(int val){
        this.value=val;
    }
    int getValue(){
        return this.value;
    }

    public NumSquare(int val){
        this.value=val;
        this.setFont(FONT);
        this.setPreferredSize(new Dimension(Scale,Scale));
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.add(new NumSquare(16));
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g){
        int h=getHeight();
        int w=getWidth();
        g.setColor(Color.white);
        g.fillRect(0,0,w,h);
        g.setColor(Color.PINK);
//        g.fillRoundRect(0, 0, 80, 30, 15, 15);
        g.fillRoundRect(5,5,w-10,h-10,Scale/3,Scale/3);
        Color color;
        if (value == 0) {
            color = Color.CYAN;
        } else {
            int len = Integer.numberOfTrailingZeros(value);
            color = Color.getHSBColor(len / 11.0f, 0.8f, 0.5f);
        }
        g.setColor(color);

        FontMetrics metrics=getFontMetrics(FONT);
        metrics.getAscent();
        String txt = Integer.toString(value);
//      color of the number
        g.setColor(Color.white);
        g.drawString(txt,
                (getWidth() - metrics.stringWidth(txt)) / 2,
                getHeight() / 2 + metrics.getAscent() / 3);

//        g.drawString(String.valueOf(this.value),w/2,h/2);

        ((Graphics2D)g).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
    }


}

