import javax.swing.*;
import java.awt.*;

   class GamePanel extends JPanel {
       void init(int xSize, int ySize) {
           System.out.println("panelGame init called");
           removeAll();
           System.out.println("h11");
           COLUMNS = xSize;
           System.out.println("h12");
           ROWS = ySize;
           System.out.println("h13");
           setLayout(new GridLayout(ROWS, COLUMNS));
           System.out.println("h14");
           numbers = new NumSquare[COLUMNS][ROWS];
           System.out.println("h15");
           for (int row = 0; row < ROWS; row++) {
               for (int col = 0; col < COLUMNS; col++) {
                   System.out.println("h16");
                   numbers[col][row] = new NumSquare(0);
                   System.out.println("h17");
                   add(numbers[col][row]);
                   System.out.println("h18");
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

//       //public Object getValue(int i, int j) {
//           return numbers[i][j];
//       }


//       public static void main(String args[]) {
//           JFrame frame = new JFrame();
//           GamePanel panel = new GamePanel(6,6);
//           frame.add(panel);
//           frame.setSize(400,400);
//           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//           frame.pack();
//           frame.setVisible(true);
//       }



   }


