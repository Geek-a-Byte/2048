import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class GameFrame extends JFrame  {
    public JPanel panelMain;
    public JLabel two;
    public JLabel zero;
    public JLabel four;
    public JLabel eight;
    public JPanel panelButton;
    public GamePanel panelGame;
    public JButton leftClick;
    public JButton downClick;
    public JButton upClick;
    public JButton rightClick;
    public GameCode game;

      void updateNumSquares () {
        for (int i = 0; i < game.ROWS; i++) {
            for (int j = 0; j < game.COLUMNS; j++) {
                panelGame.setValue(i, j, game.getCellValue(j,i));
            }

        }

        panelGame.repaint();//this line is for repainting the whole grid when a button is clicked.
      }




    public GameFrame(){
        super("Game 2048");
        panelMain=new JPanel();
        //panelMain.setBackground(Color.black);
        game= new GameCode(6,6);
        panelGame = new GamePanel(game.ROWS, game.COLUMNS);
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
                System.out.println("Clicked");
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
                System.out.println("Clicked");
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
                System.out.println("Clicked");
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
                System.out.println("Clicked");
                game.slideRight();
                game.addNew2();
                updateNumSquares();
                //panelGame.repaint();

            }
        });



    }



}
