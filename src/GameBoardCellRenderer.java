import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameBoardCellRenderer extends DefaultTableCellRenderer {
    private GameBoard gB;
    private BufferedImage badTile;
    private JLabel jLabel;

    public GameBoardCellRenderer(GameBoard gB) {
        this.gB = gB;
        try {
            badTile = ImageIO.read(new File("src/Sprites/BadTile.png"));
        }catch (Exception e) {
            e.printStackTrace();
        }
        jLabel = new JLabel(new ImageIcon(badTile.getScaledInstance(700/gB.getSize1(), 700/gB.getSize1(),Image.SCALE_DEFAULT)));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(gB.getSize1() % 2 == 1) {
            if ((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && (column % 2 == 1 || row % 2 == 1)) {
                l.setBackground(Color.CYAN);
            } else {
                //l.setBackground(Color.BLACK);
                return jLabel;
            }
        }else{
            if ((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1 ) && ((column % 2 == 1 && column < gB.getSize1()/2) || (row % 2 == 1 && row < gB.getSize1()/2) || (column % 2 == 0 && column > (gB.getSize1()/2)) || (row % 2 == 0 && row > (gB.getSize1()/2)))) {
                l.setBackground(Color.CYAN);
            } else {
                //l.setBackground(Color.BLACK);
                return jLabel;
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
