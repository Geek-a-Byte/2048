import javax.swing.*;
import java.awt.*;

   class GamePanel extends JPanel {
       private JPanel GamePanel;
       private NumSquare val;
       int COLUMNS, ROWS;
       NumSquare[][] numbers;

       public GamePanel(int xSize, int ySize) {
           init(xSize, ySize);
       }

       private void init(int xSize, int ySize) {
           removeAll();
           COLUMNS = xSize;
           ROWS = ySize;
           setLayout(new GridLayout(ROWS, COLUMNS));
           numbers = new NumSquare[COLUMNS][ROWS];
           for (int row = 0; row < ROWS; row++) {
               for (int col = 0; col < COLUMNS; col++) {
                   numbers[col][row] = new NumSquare(0);
                   add(numbers[col][row]);
               }
           }
       }

       public NumSquare getNumbers(int col, int row) {
           return numbers[row][col];
       }

       public void setValue(int row, int col, NumSquare val) {
           numbers[row][col] = val;
       }


       public static void main(String args[]) {
           JFrame frame = new JFrame();
           GamePanel panel = new GamePanel(6,6);
           frame.add(panel);
           frame.setSize(400,400);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.pack();
           frame.setVisible(true);
       }
   }


