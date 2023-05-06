/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class APoleGry extends JPanel  implements KeyListener {
    PacMan pacMan;
    public APoleGry() {
        generatePoleGry();
        new Thread(new RuchPacMana()).start();

    }

    public void generatePoleGry(){
        Object[][] aa = {
                {1, 2, 3, 1, 1, 1, 1, 1, 1, 1},
                {4, 5, 6, 2, 2, 2, 2, 2, 2, 2},
                {4, 5, 6, 2, 2, 2, 2, 2, 2, 2},
                {4, 5, 6, 2, 2, 2, 2, 2, 2, 2},
                {4, 5, 6, 2, 2, 2, 2, 2, 2, 2},
                {4, 5, 6, 2, 2, 2, 2, 2, 2, 2},
                {4, 5, 6, 2, 2, 2, 2, 2, 2, 2},
                {4, 5, 6, 2, 2, 2, 2, 2, 2, 2},
                {4, 5, 6, 2, 2, 2, 2, 2, 2, 2},
                {7, 8, 9, 9, 9, 9, 9, 9, 9, 9}

        };
        //String[] kolumny= {"A", "X", "D"};

        //JTable jTable = new JTable();
        //jTable.setModel(new PlanszaGry(aa));
        //this.add(jTable, BorderLayout.CENTER);
        setBackground(Color.RED);
        this.addKeyListener(this);
        pacMan  = new PacMan(100 ,100);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(pacMan.getPacManSprite(), pacMan.getX(), pacMan.getY(), null);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'a': System.out.println("key a");
                pacMan.setVelocityX(-2);
                pacMan.setVelocityY(0);
                break;
            case 'w': pacMan.setVelocityY(2);
                pacMan.setVelocityX(0);
                break;
            case 's': pacMan.setVelocityY(-2);
                pacMan.setVelocityX(0);
                break;
            case 'd': pacMan.setVelocityX(2);
                pacMan.setVelocityY(0);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    protected class RuchPacMana implements Runnable{
        @Override
        public void run() {
            while (true) {
                pacMan.movement();
                repaint();
                try {
                    Thread.sleep(10);
                } catch (Exception ex) {
                }
            }
        }
    }

}
*/
