import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class PlanszaGry extends AbstractTableModel {
    private Object[][] pola;
    //private String[] kolumny;

    public PlanszaGry(Object[][] pola){
        this.pola = pola;
        //this.kolumny = kolumny;
    }
    @Override
    public int getRowCount() {
        return pola.length;
    }

    @Override
    public int getColumnCount() {
        return pola[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return pola[rowIndex][columnIndex];
    }
}
