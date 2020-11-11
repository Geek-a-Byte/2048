import com.opencsv.CSVWriter;
import javafx.scene.media.MediaPlayer;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class StartGame implements ActionListener{

    JFrame frame;
    JButton scoreBoard=new JButton();



    public static void main(String args[]) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        StartGame w = new StartGame();
        w.go();

    }

    public void go() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File("Marshmello_-_Summer_Official_Music_Video_with_Lele_Pons (online-audio-converter.com).wav")));

        frame = new JFrame("Game 2048");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyDrawing drawPanel = new MyDrawing();
        JButton start_gameButton = new JButton("Start Game");
        JButton start_music_on = new JButton();
        JButton start_music_off = new JButton();
        start_music_on.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    clip.start();
                }
                catch (Exception exc)
                {
                    exc.printStackTrace(System.out);
                }
            }
        });
        start_music_off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    clip.stop();
                }
                catch (Exception exc)
                {
                    exc.printStackTrace(System.out);
                }
            }
        });
        JPanel rowPanel = new JPanel();
        JPanel rowPanel2 = new JPanel();
        rowPanel.setLayout(new FlowLayout());
        rowPanel2.setLayout(new GridLayout(2,1,0,20));
        JLabel name=new JLabel("Enter Your Name: ");
        name.setForeground(Color.WHITE);
        name.setPreferredSize(new Dimension(200,50));
        JTextField textField=new JTextField();
        textField.setPreferredSize(new Dimension(400,30));
        rowPanel.add( name);
        rowPanel.add( textField );
        String n;
//        try {
//            n=textField.getText();
//            checkForMissingFields( n);
//
//        }
//        catch ( MissingFieldException e) {
//            // handle exception
//
//            textField.setText(e.getMessage());
//        }

        rowPanel.setBackground(Color.black);
        rowPanel2.add( start_music_on );
        rowPanel2.add(start_music_off );
        rowPanel2.setBackground(Color.black);

        //rowPanel.setSize(1000,100);
        start_gameButton.setBackground(Color.black);
        start_gameButton.setForeground(Color.WHITE);
        start_gameButton.setFocusPainted(false);
        start_gameButton.setPreferredSize(new Dimension(200,50));
        start_gameButton.setBorder(new LineBorder(Color.CYAN));
        rowPanel.add(start_gameButton);
        try {
            //create the font to use. Specify the size!
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts2/Portico Stencil Rough.otf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            start_gameButton.setFont(customFont);
            name.setFont(customFont);

        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        frame.getContentPane().add(BorderLayout.PAGE_START,rowPanel2);
        try {
            //Image img = ImageIO.read(getClass().getResource("C:/Users/USER/Desktop/2-2/2048/42226.jpg"));
            Image img =ImageIO.read(new File("C:/Users/USER/Desktop/2-2/2048/42226.jpg"));
            Image img2 =ImageIO.read(new File("C:/Users/USER/Desktop/2-2/2048/music on.jpg"));
            Image img3 =ImageIO.read(new File("C:/Users/USER/Desktop/2-2/2048/pause.jpg"));
            Image newimg  = img.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
            Image newimg2  = img2.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
            Image newimg3  = img3.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
            scoreBoard.setIcon(new ImageIcon(newimg));
            start_music_on.setIcon(new ImageIcon(newimg2));
            start_music_off.setIcon(new ImageIcon(newimg3));

        } catch (Exception ex) {
            System.out.println(ex);
        }
        rowPanel2.setBackground(Color.BLACK);
        scoreBoard.setBackground(Color.BLACK);
        start_music_off.setBackground(Color.BLACK);
        start_music_on.setBackground(Color.BLACK);
        frame.getContentPane().add(BorderLayout.EAST,scoreBoard);
        frame.getContentPane().add(BorderLayout.WEST,rowPanel2);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.getContentPane().add(BorderLayout.PAGE_END,rowPanel);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        start_gameButton.addActionListener(this);
        start_gameButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {

                String arr = textField.getText();
                File file = new File("C:/Users/USER/Desktop/2-2/2048/LeaderBoard1.csv");

                try
                {
                    FileWriter fw = new FileWriter(file,true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    //bw.newLine();
                    if(arr.equals(""))
                    {
                        //textField.setText("Please Enter Your Name First");
                        throw new nameNotPresentException("Name Not Present");
                    }
                    bw.write(arr);
                    bw.close();
                    frame.setVisible(false);
                    new GameGridSelector().main(new String[1]);
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
                catch(nameNotPresentException e1)
                {
                    System.out.println("error "+ e1.getMessage());
                    JOptionPane.showMessageDialog(null,"Enter Your Name First","Warning",JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        scoreBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new LBoard().main(new String[1]);
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            }
        });
    }

    public void actionPerformed(ActionEvent event) {
        frame.repaint();
    }


}

class MyDrawing extends JPanel
{
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
        g.drawString("Welcome to ", x,y);
        g.drawImage(img, x, y, this);


    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(img.getWidth(null)+400, img.getHeight(null)+400);
    }
}