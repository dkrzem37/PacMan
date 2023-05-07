import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        /*for(int i = 0; i< 20; i++){
            System.out.println((int)(Math.random()*4));
        }*/
        SwingUtilities.invokeLater(()-> new MainMenu());
        //SwingUtilities.invokeLater(()-> new OknoGry());
        //SwingUtilities.invokeLater(()-> new TestPole());
    }
}