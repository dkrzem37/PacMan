import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

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



        JPanel jPanel = new JPanel();
        jPanel.add(scoreLabel);
        jPanel.add(timeLabel);
        jPanel.add(lifeLabel);
        jPanel.setPreferredSize(new Dimension(width, 50));
        jPanel.setLayout(new GridLayout());

        //JPanel jPanel1 = new PoleGry();
        JTable jTable = new GameBoard(size,this);
        MainMenu.addCtrlShiftQShortcut(jTable);

        this.getContentPane().add(jTable, BorderLayout.CENTER);
        this.getContentPane().add(jPanel, BorderLayout.PAGE_END);

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
        //SwingUtilities.invokeLater(()-> new OknoGry());
    }
}
