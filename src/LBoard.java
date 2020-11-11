import javafx.scene.input.DataFormat;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.*;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class LBoard extends JPanel {
    private final JTable table;


    public LBoard() {
        super(new BorderLayout(3, 3));
        this.table = new JTable(new MyModel());
        this.table.setPreferredScrollableViewportSize(new Dimension(700, 300));
        this.table.setFillsViewportHeight(true);
        JPanel ButtonOpen = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(ButtonOpen, BorderLayout.SOUTH);
        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        // Add the scroll pane to this panel.
        add(scrollPane, BorderLayout.CENTER);
        // add a nice border
        setBorder(new EmptyBorder(5, 5, 5, 5));
        CSVFile Rd = new CSVFile();
        MyModel NewModel = new MyModel();
        this.table.setModel(NewModel);
        File DataFile = new File("C:/Users/USER/Desktop/2-2/2048/LeaderBoard1.csv");
        ArrayList<String[]> Rs2 = Rd.ReadCSVfile(DataFile);
        NewModel.AddCSVData(Rs2);
        System.out.println("Rows: " + NewModel.getRowCount());
        System.out.println("Cols: " + NewModel.getColumnCount());
        try {
            //create the font to use. Specify the size!
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts2/Portico Stencil Rough.otf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            table.setFont(customFont);
            table.getTableHeader().setFont(customFont);

        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        table.getTableHeader().setBackground(Color.BLACK);
        table.getTableHeader().setForeground(Color.white);
        table.setForeground(Color.WHITE);
        table.setBackground(Color.BLACK);

    }

    // Method for reading CSV file
    public class CSVFile {
        private final ArrayList<String[]> Rs = new ArrayList<String[]>();
        private String[] OneRow;

        public ArrayList<String[]> ReadCSVfile(File DataFile)
        {
            String csvFile = "C:/Users/USER/Desktop/2-2/2048/LeaderBoard1.csv";
            String line = "";
            String cvsSplitBy = ",";
            List<List<String>> llp = new ArrayList<>();
            try
            {

                BufferedReader brd = new BufferedReader(new FileReader(DataFile));
                while ((line = brd.readLine()) != null) {
                    llp.add(Arrays.asList(line.split(cvsSplitBy)));
                }
                llp.sort(new Comparator<List<String>>() {
                    @Override
                    public int compare(List<String> o1, List<String> o2)
                    {
                        int a =Integer.parseInt(o2.get(1));
                        int b =Integer.parseInt(o1.get(1));
                        return Integer.compare(a, b);
                    }
                });
                System.out.println(llp);
                for (int k = 0; k < llp.size(); k++)
                {
                    String str = llp.get(k).toString()
                            .replace("[", "") //remove the right bracket
                            .replace("]", "") //remove the left bracket
                            .trim();
                    OneRow = str.split(",");
                    Rs.add(OneRow);
                    table.setRowHeight(35);

                }

            }
            catch (Exception e)
            {
                String errmsg = e.getMessage();
                System.out.println("File not found:" + errmsg);
            }
            return Rs;
        }
    }



    private static void createAndShowGUI() {

        // Create and set up the window.
        JFrame frame = new JFrame("Leader Board");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create and set up the content pane.
        LBoard newContentPane = new LBoard(){
            Color c = new Color(129, 0, 174);
            protected void paintComponent(Graphics g) {
                Paint p = new GradientPaint(getWidth(), getHeight(), c,
                        0.0f, 0.0f,Color.BLACK, true);
                Graphics2D g2d = (Graphics2D)g;
                g2d.setPaint(p);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        newContentPane.setBorder(new EmptyBorder(50,30,50,30));
        frame.setContentPane(newContentPane);
        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    class MyModel extends AbstractTableModel {
        private final String[] columnNames = {"Name","Score"};
        private ArrayList<String[]> Data = new ArrayList<String[]>();


        public void AddCSVData(ArrayList<String[]> DataIn) {
            this.Data = DataIn;
            this.fireTableDataChanged();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;// length;
        }

        @Override
        public int getRowCount() {
            return Data.size();
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }


        @Override
        public Object getValueAt(int row, int col) {
            return Data.get(row)[col];
        }
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
