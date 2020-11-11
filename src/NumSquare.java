import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NumSquare extends JComponent {
    static final int Scale =100;
    static final int Border =Scale/20;
    static final int Font_size =(int)(Scale*0.4);
    //static final Font FONT =new Font("Consolas",Font.PLAIN,28);
    static final Font FONT =new Font("OpenSans",Font.PLAIN,28);
    public int value;
    int h,w;
    public NumSquare(int val){
        this.value=val;
        this.setFont(FONT);
        this.setPreferredSize(new Dimension(Scale,Scale));
    }
    public void setValue(int val){
        this.value=val;
    }
    int getValue(){
        return this.value;
    }
    Random rand = new Random();
    int max=160;
    int min=47;
    int r = rand.nextInt(max - min) + min;
    Color randomColor = new Color(r,7,40);
    public void paint(Graphics g){

        h=getHeight();
        w=getWidth();
        g.setColor(Color.black);
        g.fillRect(0,0,w,h);
        g.setColor(new Color(156,0,0));


        Color color = null;
        if (this.value == 0) {
            //color =new Color(100, 250, 200);
            color=Color.black;
            //color=new Color(242, 173, 93);
            //System.out.println("this value: "+this.value);

        } else if(this.value>0){
            //System.out.println("else");
            int len = Integer.numberOfTrailingZeros(value);
            //color = Color.getHSBColor(len / 8.0f, 0.0f, 0.5f);
            //color=new Color(76, 245, 217);
            //color=new Color(60, 7, 130);
            //color= Color.getHSBColor((float)Math.random(), 0.9f, 0.8f );
            color=randomColor;
        }
        g.setColor(color);
        g.fillRoundRect(5,5,w-10,h-10,Scale/4,Scale/4);
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

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.add(new NumSquare(16));
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }





}

