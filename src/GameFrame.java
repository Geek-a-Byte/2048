import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
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
          System.out.println();
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
          //Color color=new Color(81, 0, 67);
          panelMain.add(panelButton);
          panelMain.add(NewGame);
          pack();
       }

    void updateNumSquares () {

        for (int i = 0; i < game.ROWS; i++) {
            for (int j = 0; j < game.COLUMNS; j++) {
                //System.out.printf(game.getCellValue(j,i).value+" ");
                panelGame.setValue(i, j, game.getCellValue(j,i));
            }
            //System.out.println();
        }
        score_int=game.getScore();
        //System.out.println(score_int);
        score.setText("Score:  "+ score_int);
        panelGame.repaint();//this line is for repainting the whole grid when a button is clicked.
        if(game.canPlay()==false)
            gameOver();
    }

    public GameFrame(int r,int c) {
        super("Game 2048");
        panelMain=new JPanel();
        score=new JLabel();
        score.setPreferredSize(new Dimension(200,40));
        //panelMain.setBackground(Color.black);
        game= new GameCode(c,r);
        panelGame = new GamePanel(game.ROWS, game.COLUMNS);
        score_int=game.getScore();
        //System.out.println(score_int);
        score.setText("Score:  " + score_int);
        NewGame=new JButton("New Game");
        try {
            //create the font to use. Specify the size!
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts2/Portico Stencil Rough.otf")).deriveFont(20f);
            Font customFont1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts2/Portico Stencil Rough.otf")).deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
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
        //scorePanel.add(NewGame);
        scorePanel.add(score);
//        scorePanel.add(NewGame);
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
        //up=>left;
        leftClick.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
//                System.out.println("Clicked");
                game.slideUp();
                game.addNew2();
                System.out.println();
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
                System.out.println();
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
                System.out.println();
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
                System.out.println();
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
                    System.out.println();
                    updateNumSquares();
                    //System.out.println("call");
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    game.slideRight();
                    game.addNew2();
                    System.out.println();
                    updateNumSquares();
                }
                else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    game.slideUp();
                    game.addNew2();
                    System.out.println();
                    updateNumSquares();

                }
                else
                {
                    game.slideDown();
                    game.addNew2();
                    System.out.println();
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
        panelMain.setBackground(Color.black);
        panelMain.add(NewGame);
        NewGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new GameGridSelector().main(new String[0]);

            }
        });

    }



}
