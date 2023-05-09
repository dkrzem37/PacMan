import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

public class MainMenu extends JFrame {
    public MainMenu(){
        generateMainMenu();
    }
    public void generateMainMenu(){

        JPanel jPanel = new JPanel();

        JPanel jPanel1 = new JPanel();
        JButton startButton = new JButton("START");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SwingUtilities.invokeLater(()-> new OknoGry());
            }
        });
        JButton scoresButton = new JButton("HIGH SCORES");
        scoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SwingUtilities.invokeLater(()-> new HighScores());
            }
        });
        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
                //System.exit(0);
            }
        });
        jPanel1.add(startButton);
        jPanel1.add(scoresButton);
        jPanel1.add(exitButton);
        jPanel1.setBackground(Color.black);

        this.getContentPane().add(jPanel, BorderLayout.CENTER);
        this.getContentPane().add(jPanel1, BorderLayout.PAGE_END);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Hello");
                //ZAPISZ VECTOR Z HS DO PLIKU
                FileOutputStream fileOutputStream = null;
                ObjectOutputStream objectOutputStream = null;

                try {
                    fileOutputStream = new FileOutputStream("src/highScores.txt",false);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(HighScores.wyniki);
                    objectOutputStream.flush();
                    objectOutputStream.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("src/highScores.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            objectInputStream = new ObjectInputStream(fileInputStream);
            try {
                HighScores.wyniki = (Vector<HighScoreNode>) objectInputStream.readObject();

            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            objectInputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void close(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

    }

}
