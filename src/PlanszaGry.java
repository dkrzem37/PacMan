import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class PlanszaGry extends AbstractTableModel {
    private Object[][] pola;

    public PlanszaGry(int size){
        this.pola  = new Object[size][size];
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
