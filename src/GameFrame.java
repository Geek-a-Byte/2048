import com.opencsv.CSVWriter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


class GameFrame extends JFrame{
    public JPanel panelMain;
    public JLabel two;
    public JLabel zero;
    public JLabel four;
    public JLabel eight;
    public JLabel score;
    public JPanel panelButton;
    public GamePanel panelGame;
    public JButton NewGame;
    public JButton leftClick;
    public JButton downClick;
    public JButton upClick;
    public JButton rightClick;
    public GameCode game;
    public JPanel scorePanel;
    int score_int;

      void gameOver(){
          String[] options={"New Game","Exit"};
          int result = JOptionPane.showOptionDialog(this, "Game over.\nYour score was " + game.getScore(), "Game Over!",
                  JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
          if(result== JOptionPane.YES_OPTION)
          {
              newGame();
          }
          else
          {
              System.exit(0);
          }

      }
      void newGame(){
          String[] options={ "3x3", "4x4", "5x5","6x6","10x10"};
          String choice = (String)JOptionPane.showInputDialog(this, "What size game field?", "New Game",
                  JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
          if(choice==null)
              return;
          else {
              if(choice.equals("3x3"))
              {
                  System.out.println("called");
                  game=new GameCode(3,3);
                  panelMain.add(NewGame);
              }
              if(choice.equals("4x4"))
              {
                  System.out.println("called");
                  game=new GameCode(4,4);


              }
              if(choice.equals("5x5"))
              {
                  System.out.println("called");
                  game=new GameCode(5,5);

              }
              if(choice.equals("6x6"))
              {
                  System.out.println("called");
                  game=new GameCode(6,6);

              }
              if(choice.equals("10x10"))
              {
                  System.out.println("called");
                  game=new GameCode(10,10);

              }
          }
          panelGame.removeAll();
          panelButton.removeAll();
          panelGame = new GamePanel(game.ROWS, game.COLUMNS);
          panelMain.add(panelGame);
          game.addNew2();
          game.addNew2();
          updateNumSquares();
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
          panelButton.setSize(200,100);
          Color color=new Color(81, 0, 67);
          panelButton.setBackground(color);
          panelMain.add(panelButton);
          pack();
       }

    void updateNumSquares () {

        for (int i = 0; i < game.ROWS; i++) {
            for (int j = 0; j < game.COLUMNS; j++) {
                System.out.printf(game.getCellValue(j,i).value+" ");
                panelGame.setValue(i, j, game.getCellValue(j,i));
            }
            System.out.println();
        }
//        if(game.getScore() != 0)
//        {
//
//        }
//        else
//        {
//            score_int = 0;
//        }
        score_int=game.getScore();
        System.out.println(score_int);
        score.setText("Score:  "+ score_int);
        panelGame.repaint();//this line is for repainting the whole grid when a button is clicked.
        if(game.canPlay()==false)
        {
            String a = Integer.toString(score_int);
            File file = new File("D:/2048-Nawreen/Leaderboard1.csv");
            try
            {
                FileWriter fw = new FileWriter(file,true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(","+a);
                bw.newLine();
                bw.close();
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            gameOver();
        }

    }

    public GameFrame() {
        super("Game 2048");
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

                String[] options={"New Game","Exit"};
                int result = JOptionPane.showOptionDialog(null, "Game over.\nYour score was " + game.getScore(), "Game Over!",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                String a = Integer.toString(score_int);
                File file = new File("D:/2048-Nawreen/Leaderboard1.csv");
                try
                {
                    FileWriter fw = new FileWriter(file,true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(","+a);
                    bw.newLine();
                    bw.close();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
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
        panelMain=new JPanel();
        score=new JLabel();
        score.setPreferredSize(new Dimension(200,40));
        //panelMain.setBackground(Color.black);
        game= new GameCode(4,4);
        panelGame = new GamePanel(game.ROWS, game.COLUMNS);
        score_int=game.getScore();
        System.out.println(score_int);
        score.setText("Score:  " + score_int);
        NewGame=new JButton("New Game");
        try {
            //create the font to use. Specify the size!
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts2/Portico Stencil Rough.otf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            score.setFont(customFont);
            score.setForeground(Color.WHITE);
            NewGame.setFont(customFont);
            NewGame.setForeground(Color.WHITE);

        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        scorePanel=new JPanel();
        scorePanel.setPreferredSize(new Dimension(800,50));
        scorePanel.setBackground(Color.black);
        scorePanel.setBorder(new LineBorder(Color.CYAN));
        //NewGame

        NewGame.setBackground(Color.black);
        NewGame.setForeground(Color.WHITE);
        NewGame.setFocusPainted(false);
        NewGame.setPreferredSize(new Dimension(200,40));
        //scorePanel.add(NewGame);
        scorePanel.add(score);
//        scorePanel.add(NewGame);
        panelMain.add(scorePanel);

        //panelMain.add(score);
        //panelGame.setSize(400,400);
        panelMain.add(panelGame);
        panelMain.setLayout(new BoxLayout(panelMain,BoxLayout.Y_AXIS));
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
        panelButton.setSize(200,100);
        Color color=new Color(81, 0, 67);
        panelButton.setBackground(color);
        panelMain.add(panelButton);
        game.addNew2();
        game.addNew2();
        updateNumSquares();

        //left=up
        //right=>down
        //down=>right
        //up=>left;
        leftClick.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
//                System.out.println("Clicked");
                game.slideUp();
                game.addNew2();
                updateNumSquares();

                //panelGame.repaint();

            }
        });
        rightClick.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
//                System.out.println("Clicked");
                game.slideDown();
                game.addNew2();
                updateNumSquares();


                //panelGame.repaint();

            }
        });
        upClick.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
//                System.out.println("Clicked");
                game.slideLeft();
                game.addNew2();
                updateNumSquares();

                //panelGame.repaint();

            }
        });
        downClick.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
//                System.out.println("Clicked");
                game.slideRight();
                game.addNew2();
                updateNumSquares();


                //panelGame.repaint();
            }
        });


        KeyListener anonymous= new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    game.slideLeft();
                    game.addNew2();
                    updateNumSquares();

                    //System.out.println("call");
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    game.slideRight();
                    game.addNew2();
                    updateNumSquares();
                }
                else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    game.slideUp();
                    game.addNew2();
                    updateNumSquares();

                }
                else
                {
                    game.slideDown();
                    game.addNew2();
                    updateNumSquares();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        upClick.addKeyListener(anonymous);
        downClick.addKeyListener(anonymous);
        leftClick.addKeyListener(anonymous);
        downClick.addKeyListener(anonymous);
        panelMain.setBackground(Color.black);
        panelMain.add(NewGame);
        NewGame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){

            GameFrame frame2 = new GameFrame();
            frame2.add(frame2.panelMain);
            frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame2.setMinimumSize(new Dimension(700,850));
            frame2.pack();
            frame2.setVisible(true);

        }
        });

    }



}
