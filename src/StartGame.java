import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class StartGame implements ActionListener{

    JFrame frame;

    public static void main(String args[]) {
        StartGame w = new StartGame();
        w.go();
    }

    public void go() {


        frame = new JFrame("Game 2048");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyDrawing drawPanel = new MyDrawing();
        JButton start_gameButton = new JButton("Start Game");
        JButton start_music_on = new JButton("Sound On");
        JButton start_music_off = new JButton("Sound Off");
        JPanel rowPanel = new JPanel();
        JPanel rowPanel2 = new JPanel();
        rowPanel.setLayout(new FlowLayout());
        rowPanel2.setLayout(new FlowLayout());
        JLabel name=new JLabel("Enter Your Name: ");
        name.setForeground(Color.WHITE);
        name.setPreferredSize(new Dimension(200,50));
        JTextField textField=new JTextField();
        textField.setPreferredSize(new Dimension(400,30));
        rowPanel.add( name);
        rowPanel.add( textField );
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
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.getContentPane().add(BorderLayout.PAGE_END,rowPanel);
        frame.setSize(1200, 800);
        frame.setVisible(true);


        start_gameButton.addActionListener(this);
        start_gameButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                 frame.setVisible(false);
                 new GameGridSelector().main(new String[1]);

//                GameFrame frame2 = new GameFrame();
//                frame2.add(frame2.panelMain);
//                frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//                frame2.setPreferredSize(new Dimension(600,850));
//                frame2.pack();
//                frame2.setVisible(true);

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