import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class PlanszaGry extends AbstractTableModel {

    /*private Object[][] pola ={
        {3, 2, 3, 1, 1, 1, 1, 1, 1, 1},
        {4, 5, 6, 2, 2, 2, 3, 2, 2, 2},
        {4, 5, 6, 2, 2, 2, 2, 2, 2, 2},
        {4, 5, 6, 2, 2, 2, 2, 2, 2, 2},
        {4, 5, 6, 2, 2, 2, 2, 2, 2, 2},
        {4, 5, 6, 2, 2, 2, 2, 2, 2, 2},
        {4, 5, 6, 2, 2, 2, 2, 2, 2, 2},
        {4, 5, 6, 2, 2, 2, 2, 2, 2, 2},
        {4, 5, 6, 2, 2, 2, 2, 2, 2, 2},
        {7, 8, 9, 9, 9, 9, 9, 9, 9, 9}

    };*/
    private Object[][] pola;
    //private String[] kolumny;

    public PlanszaGry(int size){
        this.pola  = new Object[size][size];
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
