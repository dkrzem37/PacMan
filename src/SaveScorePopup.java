import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class SaveScorePopup extends JDialog {
    OknoGry oG;
    public SaveScorePopup(OknoGry oG){
        this.oG = oG;

        this.setTitle("New Score!");
        this.setLayout(new GridBagLayout());
        JTextField jTextField = new JTextField("Your name");
        JButton b = new JButton ("OK");
        MainMenu.addCtrlShiftQShortcut(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HighScores.wyniki.add(new HighScoreNode(jTextField.getText(), OknoGry.score));
                System.out.println("dodano");
                oG.close();
                close();
            }
        });
        JLabel name = new JLabel("Your name: ");

        jTextField.setPreferredSize(new Dimension(100, 30));
        this.add(name);
        this.add(jTextField);
        this.add(b);

        this.setSize(300,150);
        this.setVisible(true);


        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void close(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
