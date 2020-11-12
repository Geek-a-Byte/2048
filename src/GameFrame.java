import javafx.stage.PopupWindow;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
class GameFrame extends JFrame implements KeyListener {
    public JPanel panelMain;
    public JLabel score;
    public JPanel panelButton;
    public GamePanel panelGame;
    public JButton NewGame;
    public JButton leftClick=new JButton("Left");
    public JButton downClick=new JButton("Down");
    public JButton upClick=new JButton("Up");
    public JButton rightClick=new JButton("Right");
    public GameCode game;
    public JPanel scorePanel;
    int score_int;
    JLabel timerlabel;
    final int[] count = {0};
    int flag=1;

    void gameOver(){

        String[] options={"New Game","Exit"};
        int result = JOptionPane.showOptionDialog(this, "Game over.\nYour score was " + game.getScore(), "Game Over!",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if(result== JOptionPane.YES_OPTION)
        {
            String a = Integer.toString(score_int);
            File file2 = new File("C:/Users/USER/Desktop/2-2/2048/LeaderBoard1.csv");
            try
            {
                FileWriter fw = new FileWriter(file2,true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(","+a);
                bw.newLine();
                bw.close();
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            dispose();
            new StartGame().frame.setVisible(true);
        }
        else if(result==JOptionPane.NO_OPTION)
        {
            System.exit(0);
        }
        else {
            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }

    }


    void updateNumSquares () {

        for (int i = 0; i < game.ROWS; i++) {
            for (int j = 0; j < game.COLUMNS; j++) {
                //System.out.printf(game.getCellValue(j,i).value+" ");
                panelGame.setValue(i, j, game.getCellValue(j,i));
                if(game.getCellValue(j,i).value==2048 && flag==1)
                {
                    JOptionPane.showMessageDialog(null,"You've win!","Congratulations!",JOptionPane.INFORMATION_MESSAGE);
                    flag=0;
                }
            }
            //System.out.println();
        }
        score_int=game.getScore();
        //System.out.println(score_int);
        score.setText("Score:  "+ score_int);
        panelGame.repaint();//this line is for repainting the whole grid when a button is clicked.
        if(game.canPlay()==false)
        {
            gameOver();
        }
    }

    @Override

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        {
            if (code == KeyEvent.VK_DOWN) {
                game.slideDown();
                game.addNew2();
                updateNumSquares();

            }
            if (code == KeyEvent.VK_UP) {
                game.slideUp();
                game.addNew2();
                updateNumSquares(); // same goes for here

            }
            if (code == KeyEvent.VK_LEFT) {

                game.slideLeft();
                game.addNew2();
                updateNumSquares();
            }
            if (code == KeyEvent.VK_RIGHT) {

                game.slideRight();
                game.addNew2();
                updateNumSquares();

            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public GameFrame(int r,int c) {

        super("Game 2048");
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

                    String[] options = {"Exit"};

                    int result= JOptionPane.showOptionDialog(null, "Game over.\nYour score was " + game.getScore(), "Do you want to exit the Game?",
                            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                    if (result == JOptionPane.YES_OPTION) {

                        String a = Integer.toString(score_int);
                        File file2 = new File("C:/Users/USER/Desktop/2-2/2048/LeaderBoard1.csv");
                        try
                        {
                            FileWriter fw = new FileWriter(file2,true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write(","+a);
                            bw.newLine();
                            bw.close();
                        }
                        catch (IOException ioException)
                        {
                            ioException.printStackTrace();
                        }
                        System.exit(0);
                    } else {
                        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    }



            }

            @Override
            public void windowClosed(WindowEvent e) {


            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        game= new GameCode(c,r);
        panelGame = new GamePanel(game.ROWS, game.COLUMNS);
        timerlabel=new JLabel();
        timerlabel.setForeground(Color.WHITE);
        Timer timer;
        timer = new Timer(900, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count[0]++;
                if (game.canPlay()==true) {
                    timerlabel.setText("Time taken (seconds)  : "+Integer.toString(count[0]));

                } else {
                    ((Timer) (e.getSource())).stop();
                    count[0]=0;
                    timerlabel.setText("Time taken (seconds)  : "+Integer.toString(count[0]));
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();
        panelMain=new JPanel();
        panelMain.add(timerlabel);
        score=new JLabel();
        score.setPreferredSize(new Dimension(200,40));
        score_int=game.getScore();
        score.setText("Score:  " + score_int);
        NewGame=new JButton("New Game");

        try {
            //create the font to use. Specify the size!
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts2/Portico Stencil Rough.otf")).deriveFont(20f);
            Font customFont1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts2/Portico Stencil Rough.otf")).deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            timerlabel.setFont(customFont1);
            score.setFont(customFont);
            score.setForeground(Color.WHITE);
            NewGame.setFont(customFont);
            NewGame.setForeground(Color.WHITE);
            leftClick.setFont(customFont1);
            leftClick.setForeground(Color.WHITE);
            rightClick.setFont(customFont1);
            rightClick.setForeground(Color.WHITE);
            upClick.setFont(customFont1);
            upClick.setForeground(Color.WHITE);
            downClick.setFont(customFont1);
            downClick.setForeground(Color.WHITE);
            leftClick.setBackground(Color.black);
            rightClick.setBackground(Color.black);
            upClick.setBackground(Color.black);
            downClick.setBackground(Color.black);


        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        scorePanel=new JPanel(){

            Color c = new Color(129, 0, 174);
            protected void paintComponent(Graphics g) {
                Paint p = new GradientPaint(0.0f, getHeight(), c,
                        0.0f, 0.0f, Color.BLACK, true);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(p);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        scorePanel.setPreferredSize(new Dimension(200,50));
        scorePanel.setBackground(Color.black);

        //NewGame

        NewGame.setBackground(Color.black);
        NewGame.setForeground(Color.WHITE);
        NewGame.setFocusPainted(false);
        NewGame.setPreferredSize(new Dimension(200,40));
        scorePanel.add(NewGame);

        NewGame.setFocusable(false);//this line is necessary for the keylisteners to work properly after the game starts

        leftClick.addKeyListener(this);
        rightClick.addKeyListener(this);
        upClick.addKeyListener(this);
        downClick.addKeyListener(this);
        //NewGame.addMouseListener(this);

        NewGame.addActionListener(new ActionListener() {
            private Object String;

            @Override
            public void actionPerformed(ActionEvent e) {

                String[] options = {"Yes"};

                int result= JOptionPane.showOptionDialog(null, "Game over.\nYour score was " + game.getScore(), "Do you want to Start a New Game?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                if (result == JOptionPane.YES_OPTION) {

                    String a = Integer.toString(score_int);
                    File file2 = new File("C:/Users/USER/Desktop/2-2/2048/LeaderBoard1.csv");
                    try
                    {
                        FileWriter fw = new FileWriter(file2,true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(","+a);
                        bw.newLine();
                        bw.close();
                    }
                    catch (IOException ioException)
                    {
                        ioException.printStackTrace();
                    }
                    dispose();
                    new StartGame().frame.setVisible(true);


                } else {
                    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                }



            }
        });

        scorePanel.add(score);
        panelMain.add(scorePanel);

        //panelMain.add(score);
        //panelGame.setSize(400,400);
        panelMain.add(panelGame);
        panelMain.setLayout(new BoxLayout(panelMain,BoxLayout.Y_AXIS));
        panelButton=new JPanel(){
            Color c = new Color(129, 0, 174);
            protected void paintComponent(Graphics g) {
                Paint p = new GradientPaint(0.0f, 0.0f, c,
                        0.0f, getHeight(), Color.BLACK, true);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(p);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panelButton.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0; constraints.gridy = 7;
        panelButton.add(leftClick, constraints);
        constraints.gridx = 10; constraints.gridy = 7;
        panelButton.add(rightClick, constraints);
        constraints.gridx = 5; constraints.gridy = 0;
        panelButton.add(upClick, constraints);
        constraints.gridx = 5; constraints.gridy = 15;
        panelButton.add(downClick, constraints);
        Color color=new Color(49, 28, 74);
        panelButton.setBackground(color);
        panelButton.setBorder(new EmptyBorder(15,15,15,5));
        panelMain.add(panelButton);
        game.addNew2();
        game.addNew2();
        System.out.println();
        updateNumSquares();
        //left=up
        //right=>down
        //down=>right
        //up=>left
        panelMain.setBackground(Color.black);

    }
}