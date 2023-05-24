import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class RozmiaryPlanszyPopup extends JDialog {

    public RozmiaryPlanszyPopup(){

        this.setTitle("Wybierz rozmiar planszy.");
        //JDialog d = new JDialog(this, "New Score!");
        this.setLayout(new GridBagLayout());
        JTextField jTextField = new JTextField("13");
        JButton b = new JButton ("OK");
        MainMenu.addCtrlShiftQShortcut(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = 0;
                try {
                    size = Integer.parseInt(jTextField.getText());
                }catch(NumberFormatException ex){
                    System.out.println(size);
                }
                if(size != 0){
                    int finalSize = size;
                    SwingUtilities.invokeLater(()-> new OknoGry(finalSize));
                    System.out.println("proba");
                    close();
                }
            }
        });
        JLabel name = new JLabel("Rozmiar: ");

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
