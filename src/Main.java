import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {
        /*for(int i = 0; i< 20; i++){
            System.out.println((int)(Math.random()*4));
        }*/


        SwingUtilities.invokeLater(()-> new MainMenu());


        //SwingUtilities.invokeLater(()-> new SaveScorePopup());

        //SwingUtilities.invokeLater(()-> new OknoGry());
        //SwingUtilities.invokeLater(()-> new TestPole());
    }
}