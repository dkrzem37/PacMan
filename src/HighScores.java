import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Vector;

public class HighScores extends JFrame {

    public static Vector<HighScoreNode> wyniki = new Vector<>();
    public HighScores(){
        //posortowac wyniki
        Collections.sort(wyniki);
        //zamien wyniki na vector stringow
        Vector<String> wynikiString = new Vector<>();
        int i = 1;
        for(HighScoreNode h: wyniki){
            wynikiString.add(i + ". " + h.toString());
            i++;
        }

        ListHighScores highScores = new ListHighScores(wynikiString);
        JList list = new JList();
        MainMenu.addCtrlShiftQShortcut(list);
        list.setModel(highScores);
        list.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBackground(Color.BLACK);
                setForeground(Color.YELLOW);
                setFont(new Font(Font.MONOSPACED, Font.PLAIN, 28));
                return c;
            }

        });

        JScrollPane jScrollPane = new JScrollPane(list);

        this.getContentPane().add(jScrollPane);



        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
