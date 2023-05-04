import javax.swing.*;
import java.awt.*;

public class OknoGry extends JFrame {
    public OknoGry(){
        generateOknoGry();
    }
    public void generateOknoGry(){
        JLabel scoreLabel = new JLabel("Score:");
        JLabel timeLabel = new JLabel("Time:");
        JLabel lifeLabel = new JLabel("Lives:");

        JPanel jPanel = new JPanel();
        jPanel.add(scoreLabel);
        jPanel.add(timeLabel);
        jPanel.add(lifeLabel);
        jPanel.setPreferredSize(new Dimension(750, 50));
        jPanel.setLayout(new GridLayout());

        JPanel jPanel1 = new PoleGry();

        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
        this.getContentPane().add(jPanel, BorderLayout.PAGE_END);

        setSize(750, 800);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
