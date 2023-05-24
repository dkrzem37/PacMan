import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class OknoGry extends JFrame {
    private int width, height;
    public static int score = 0;
    public static int lives = 3;
    public static int time = 0;
    public static JLabel scoreLabel;
    public static JLabel timeLabel;
    public static JLabel lifeLabel;
    int size;
    public OknoGry(int size){
        generateOknoGry(size);
    }
    public void generateOknoGry(int size){
        this.width = 714;
        this.height = 787;

        OknoGry.lives = 3;
        OknoGry.score = 0;
        OknoGry.time = 0;
        scoreLabel = new JLabel("Score:" + score);
        timeLabel = new JLabel("Time:" + time);
        lifeLabel = new JLabel("Lives: " + lives);
        scoreLabel.setForeground(Color.YELLOW);
        scoreLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        timeLabel.setForeground(Color.YELLOW);
        timeLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        lifeLabel.setForeground(Color.YELLOW);
        lifeLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));

        setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        jPanel.add(scoreLabel);
        jPanel.add(timeLabel);
        jPanel.add(lifeLabel);
        jPanel.setPreferredSize(new Dimension(width, 50));
        jPanel.setLayout(new GridLayout());
        jPanel.setBackground(Color.BLACK);



        //solution1
        JPanel jPanel1 = new JPanel();

        //po odkomentowaniu nastepnej instrukcji dostajemy resizibility ale mamy bugi w grze
        jPanel1.setLayout(new BorderLayout());

        GameBoard jTable = new GameBoard(size,this);
        jTable.setBackground(Color.BLACK);
        MainMenu.addCtrlShiftQShortcut(jTable);
        jPanel1.add(jTable, BorderLayout.CENTER);

        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
        this.getContentPane().add(jPanel, BorderLayout.PAGE_END);

        //solution2
        /*GameBoard jTable = new GameBoard(size,this);
        MainMenu.addCtrlShiftQShortcut(jTable);
        jTable.setBackground(Color.BLACK);

        this.getContentPane().add(jTable, BorderLayout.CENTER);
        this.getContentPane().add(jPanel, BorderLayout.PAGE_END);*/

        this.addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                if(!jTable.checkForGameEnd)
                    jTable.gameEnd();
            }
        });
        setSize(width, height);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void close(){

        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    public void saveScore(){
        SwingUtilities.invokeLater(()-> new SaveScorePopup(this));
    }
}
