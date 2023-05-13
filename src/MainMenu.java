import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Vector;

public class MainMenu extends JFrame {
    public MainMenu(){
        generateMainMenu();
    }
    public void generateMainMenu(){
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.BLACK);
        addCtrlShiftQShortcut(jPanel);

        BufferedImage titleScreen = null;
        try {
            titleScreen = ImageIO.read(new File("src/Sprites/titleArt.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        jPanel.add(new JLabel(new ImageIcon(titleScreen.getScaledInstance(500, 500, Image.SCALE_DEFAULT))));


        JPanel jPanel1 = new JPanel();

        jPanel1.setPreferredSize(new Dimension(500, 50));
        JButton startButton = new JButton("START");
        startButton.setForeground(Color.YELLOW);
        startButton.setBackground(Color.BLACK);
        startButton.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()-> new RozmiaryPlanszyPopup());
            }
        });
        JButton scoresButton = new JButton("HIGH SCORES");
        scoresButton.setForeground(Color.YELLOW);
        scoresButton.setBackground(Color.BLACK);
        scoresButton.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        scoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SwingUtilities.invokeLater(()-> new HighScores());
            }
        });
        JButton exitButton = new JButton("EXIT");
        exitButton.setForeground(Color.YELLOW);
        exitButton.setBackground(Color.BLACK);
        exitButton.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
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
                //ZAPISZ VECTOR Z HS DO PLIKU
                zapiszWynikiDoPliku("src/highScores.txt");
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

        setSize(500, 590);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void zapiszWynikiDoPliku(String path){
        //ZAPISZ VECTOR Z HS DO PLIKU
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(path,false);
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

    public static void addCtrlShiftQShortcut(JComponent jComponent) {
        //SHORTCUT
        Action quitAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MainMenu.zapiszWynikiDoPliku("src/highScores.txt");
                System.exit(0);
            }
        };
        String command = "c";
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, command);
        jComponent.getActionMap().put(command, quitAction);
    }

        private void close(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

    }

}
