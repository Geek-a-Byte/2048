import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GameGridSelector extends JFrame {

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp,0, 0, null);
        g2d.dispose();
        return dimg;
    }


    public static void main(String[] args){
        JFrame frame = new JFrame("Game 2048");
        frame.setLayout(new BorderLayout());
        JLabel Header=new JLabel("Select Your Grid");
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JPanel HeaderPanel = new JPanel(new FlowLayout());
        HeaderPanel.add(Header);
        HeaderPanel.setBorder(new EmptyBorder(50,40,30,40));
        HeaderPanel.setBackground(Color.BLACK);
        HeaderPanel.setPreferredSize(new Dimension(100,500));
        panel.setBorder(new EmptyBorder(10, 40, 10, 40));
        frame.setPreferredSize(new Dimension(1200,1000));
        frame.setContentPane(HeaderPanel);
        frame.add(panel);
        frame.setVisible(true);

        JPanel inPanel1 = new JPanel(){
            Color c = new Color(129, 0, 174);
            protected void paintComponent(Graphics g) {
                Paint p = new GradientPaint(getWidth(), getHeight(), c,
                        0.0f, 0.0f,Color.BLACK, true);
                Graphics2D g2d = (Graphics2D)g;
                g2d.setPaint(p);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        inPanel1.setBorder(new EmptyBorder(50,50,50,50));// Create new panel
        JLabel label1 = new JLabel();
        label1.setBorder(new EmptyBorder(0,10,10,10));
        JButton fourbtn=new JButton("4x4");// Add components to it
        inPanel1.add(label1);
        inPanel1.add(fourbtn);
        panel.add(inPanel1);


        JPanel inPanel2 = new JPanel(){
            Color c = new Color(129, 0, 174);
            protected void paintComponent(Graphics g) {
                Paint p = new GradientPaint(0.0f, getHeight(), c,
                        getWidth(), 0.0f,Color.BLACK, true);
                Graphics2D g2d = (Graphics2D)g;
                g2d.setPaint(p);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        inPanel2.setBorder(new EmptyBorder(50,10,50,10));// Create new panel
        JLabel label2 = new JLabel();
        label2.setBorder(new EmptyBorder(0,0,10,0));
        JButton fivebtn=new JButton("5x5");// Add components to it
        inPanel2.add(label2);
        inPanel2.add(fivebtn);
        panel.add(inPanel2);


        JPanel inPanel3 = new JPanel()  {
            Color c = new Color(129, 0, 174);
            protected void paintComponent(Graphics g) {
                Paint p = new GradientPaint(getWidth(), 0.0f, c,
                        0.0f, getHeight(),Color.BLACK, true);
                Graphics2D g2d = (Graphics2D)g;
                g2d.setPaint(p);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        inPanel3.setBorder(new EmptyBorder(50,10,50,10));// Create new panel

        JLabel label3 = new JLabel();
        label3.setBorder(new EmptyBorder(0,0,10,0));
        JButton sixbtn=new JButton("6x6");// Add components to it
        inPanel3.add(label3);
        inPanel3.add(sixbtn);
        panel.add(inPanel3);

        JPanel inPanel4 = new JPanel(){
            Color c = new Color(129, 0, 174);
            protected void paintComponent(Graphics g) {
                Paint p = new GradientPaint(0.0f, 0.0f, c,
                        getWidth(), getHeight(),Color.BLACK, true);
                Graphics2D g2d = (Graphics2D)g;
                g2d.setPaint(p);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };


        inPanel4.setBorder(new EmptyBorder(50,10,50,10));// Create new panel
        JLabel label4 = new JLabel();
        label4.setBorder(new EmptyBorder(0,0,10,0));
        JButton eightbtn=new JButton("8x8");// Add components to it
        inPanel4.add(label4);
        inPanel4.add(eightbtn);
        panel.add(inPanel4);

        try {
            //create the font to use. Specify the size!
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts2/Portico Stencil Rough.otf")).deriveFont(35f);
            Font customFont1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts2/Portico Stencil Rough.otf")).deriveFont(25f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            Header.setFont(customFont);
            Header.setForeground(Color.WHITE);

            ge.registerFont(customFont1);
            fourbtn.setFont(customFont1);
            fivebtn.setFont(customFont1);
            sixbtn.setFont(customFont1);
            eightbtn.setFont(customFont1);

            fourbtn.setBackground(Color.black);
            fivebtn.setBackground(Color.black);
            sixbtn.setBackground(Color.black);
            eightbtn.setBackground(Color.black);

            fourbtn.setForeground(Color.WHITE);
            fivebtn.setForeground(Color.WHITE);
            sixbtn.setForeground(Color.WHITE);
            eightbtn.setForeground(Color.WHITE);


        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }


        try {
            BufferedImage four = ImageIO.read(new File("C:/Users/USER/Desktop/2-2/2048/images/four.png"));
            BufferedImage five = ImageIO.read(new File("C:/Users/USER/Desktop/2-2/2048/images/five.png"));
            BufferedImage six = ImageIO.read(new File("C:/Users/USER/Desktop/2-2/2048/images/six.png"));
            BufferedImage eight = ImageIO.read(new File("C:/Users/USER/Desktop/2-2/2048/images/eight.png"));
            four=resize(four,400,270);
            five=resize(five,400,270);
            six=resize(six,400,270);
            eight=resize(eight,400,270);
            label1.setIcon(new ImageIcon(four));
            label2.setIcon(new ImageIcon(five));
            label3.setIcon(new ImageIcon(six));
            label4.setIcon(new ImageIcon(eight));
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());


        fourbtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                GameFrame frame2 = new GameFrame(4,4);
                frame2.add(frame2.panelMain);
                frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame2.setPreferredSize(new Dimension(600,850));
                frame2.pack();
                frame2.setVisible(true);
            }
        });
        fivebtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                GameFrame frame2 = new GameFrame(5,5);
                frame2.add(frame2.panelMain);
                frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame2.setPreferredSize(new Dimension(850,850));
                frame2.pack();
                frame2.setVisible(true);
            }
        });
        sixbtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                GameFrame frame2 = new GameFrame(6,6);
                frame2.add(frame2.panelMain);
                frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame2.setPreferredSize(new Dimension(850,850));
                frame2.pack();
                frame2.setVisible(true);
            }
        });
        eightbtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                GameFrame frame2 = new GameFrame(8,8);
                frame2.add(frame2.panelMain);
                frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame2.setPreferredSize(new Dimension(850,1000));
                frame2.pack();
                frame2.setVisible(true);
            }
        });
    }



}