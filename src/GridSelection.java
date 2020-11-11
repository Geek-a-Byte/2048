import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class GridSelection extends JApplet {
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.setBorder(new EmptyBorder(25, 120, 10, 20));
        panel.setBackground(Color.LIGHT_GRAY);
        frame.setPreferredSize(new Dimension(1200,800));
        frame.setContentPane(panel);
        frame.setVisible(true);
        JLabel label1 = new JLabel();
        panel.add(label1);
        JLabel label2 = new JLabel();
        panel.add(label2);
        JLabel label3 = new JLabel();
        panel.add(label3);
        JLabel label4 = new JLabel();
        panel.add(label4);




        try {
            BufferedImage myPicture = ImageIO.read(new File("D:/2048/fourbyfour.PNG"));
            myPicture=resize(myPicture, 400, 300);

            BufferedImage myPicture1 = ImageIO.read(new File("D:/2048/fivebyfive.PNG"));
            myPicture1=resize(myPicture1, 400, 300);

            BufferedImage myPicture2 = ImageIO.read(new File("D:/2048/sixbysix.PNG"));
            myPicture2=resize(myPicture2, 400, 300);

            BufferedImage myPicture3 = ImageIO.read(new File("D:/2048/eightbyeight.PNG"));
            myPicture3 =resize(myPicture3, 400, 300);

            label1.setIcon(new ImageIcon(myPicture));
            label2.setIcon(new ImageIcon(myPicture1));
            label3.setIcon(new ImageIcon(myPicture2));
            label4.setIcon(new ImageIcon(myPicture3));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

