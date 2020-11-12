import javax.swing.*;
import java.awt.*;

   class GamePanel extends JPanel {
       void init(int xSize, int ySize) {
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
       int COLUMNS, ROWS;
       NumSquare numbers[][];
       public GamePanel(int xSize, int ySize) {
           init(xSize, ySize);
       }


       public void setValue(int i, int j, NumSquare cellValue) {
           numbers[i][j].setValue(cellValue.value);

       }
   }


