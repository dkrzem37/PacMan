import javax.swing.*;
import java.awt.*;

public class OknoGry extends JFrame {
    private int width, height;
    public OknoGry(){
        generateOknoGry();
    }
    public void generateOknoGry(){
        this.width = 714;
        this.height = 787;
        JLabel scoreLabel = new JLabel("Score:");
        JLabel timeLabel = new JLabel("Time:");
        JLabel lifeLabel = new JLabel("Lives:");

        JPanel jPanel = new JPanel();
        jPanel.add(scoreLabel);
        jPanel.add(timeLabel);
        jPanel.add(lifeLabel);
        jPanel.setPreferredSize(new Dimension(width, 50));
        jPanel.setLayout(new GridLayout());

        //JPanel jPanel1 = new PoleGry();
        JTable jTable = new GameBoard(13);

        this.getContentPane().add(jTable, BorderLayout.CENTER);
        this.getContentPane().add(jPanel, BorderLayout.PAGE_END);

        setSize(width, height);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
