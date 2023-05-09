import javax.swing.*;
import java.util.AbstractList;
import java.util.Vector;

public class ListHighScores extends AbstractListModel {
    Vector<String> wyniki;
    public ListHighScores(Vector<String> a){
        wyniki = a;
    }

    @Override
    public int getSize() {
        return wyniki.size();
    }

    @Override
    public Object getElementAt(int index) {
        return wyniki.get(index);
    }
}
