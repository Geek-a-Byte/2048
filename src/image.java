//import javax.swing.*;
//
//
//class BackgroundImageJFrame extends JFrame
//{
//
//    public BackgroundImageJFrame()
//    {
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setVisible(true);
//        setContentPane(new JLabel(new ImageIcon("C:/Users/USER/Desktop/2-2/2048/2048 gif2.gif")));
//        setSize(800,800);
//    }
//
//    public static void main(String s[])
//    {
//        new BackgroundImageJFrame();
//    }
//
//}
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class image extends JPanel{

    private Image img = new ImageIcon("C:/Users/USER/Desktop/2-2/2048/2048 gif2.gif").getImage();


    public void paintComponent(Graphics g){

        //next two lines are for centering image
        int x = (this.getWidth() - img.getWidth(null)) / 2;
        int y = (this.getHeight() - img.getHeight(null)) / 2;
        try {
            //create the font to use. Specify the size!
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts2/Portico Stencil Rough.otf")).deriveFont(70f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            g.setFont(customFont);

        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        Graphics2D g2 = (Graphics2D) g;
        Color c = new Color(129, 0, 174);
        GradientPaint blueToBlack = new GradientPaint(0, 0, c, x+500, y+500, Color.BLACK);
        g2.setPaint(blueToBlack);
        g2.fillRect(0,0,2000,1000);
        g.setColor(Color.white);
        g.drawString("Welcome to ", x, y);
        g.drawImage(img, x, y, this);


    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(img.getWidth(null)+400, img.getHeight(null)+400);
    }

    public static void  main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        image it = new image();
        frame.add(it);
        frame.pack();
        frame.setVisible(true);
    }
}
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//class image {
//
//    public Dimension getPreferredSize(){
//        return new Dimension(image.getWidth(null)+200, image.getHeight(null)+200);
//    }
//    BufferedImage image = ImageIO.read(new File("C:/Users/USER/Desktop/2-2/2048/2048 gif2.gif"));
//    Image i2 = image.getScaledInstance(getPreferredSize().width, getPreferredSize().height, Image.SCALE_SMOOTH);
//    JLabel l1;
//    JPanel p1;
//
//    public image() throws IOException {
//        System.out.println("IOexception");
//        l1 = new JLabel(new ImageIcon(i2));
//    }
//
//    public static void main(String[] args) throws IOException {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setBackground(Color.BLACK);
//        //frame.setSize(500,500);
//        frame.add(new image().l1);
//        frame.pack();
//        frame.setVisible(true);
//    }
//}
//
