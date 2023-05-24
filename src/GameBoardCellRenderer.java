import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class GameBoardCellRenderer extends DefaultTableCellRenderer {
    private GameBoard gB;

    public GameBoardCellRenderer(GameBoard gB) {
        this.gB = gB;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if(gB.getSize1() % 2 == 1) {
            if ((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && (column % 2 == 1 || row % 2 == 1)) {
                l.setBackground(Color.CYAN);
            } else {
                l.setBackground(Color.BLACK);
            }
        }else{
            if ((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1 ) && ((column % 2 == 1 && column < gB.getSize1()/2) || (row % 2 == 1 && row < gB.getSize1()/2) || (column % 2 == 0 && column > (gB.getSize1()/2)) || (row % 2 == 0 && row > (gB.getSize1()/2)))) {
                l.setBackground(Color.CYAN);
            } else {
                l.setBackground(Color.BLACK);
            }
        }

        /*if(row == 0 || row == gB.getSize1() - 1 || column == 0 || column == gB.getSize1() - 1){
            l.setBackground(Color.BLACK);
        }else{
            l.setBackground(Color.CYAN);
        }*/

        return l;
    }
}
