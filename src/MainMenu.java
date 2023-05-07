import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jPanel1.add(startButton);
        jPanel1.add(scoresButton);
        jPanel1.add(exitButton);
        jPanel1.setBackground(Color.black);

        this.getContentPane().add(jPanel, BorderLayout.CENTER);
        this.getContentPane().add(jPanel1, BorderLayout.PAGE_END);

        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
