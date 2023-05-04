import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PoleGry extends JPanel implements Runnable {

    Thread thread;

    int testX, testY;
    int speed = 4;
    KeyHandler keyHandler = new KeyHandler();
    PacMan pacMan = new PacMan(this, keyHandler, 100, 100);;
    public PoleGry() {

        this.setPreferredSize(new Dimension(750, 750));
        this.setBackground(Color.black);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.thread = new Thread(this);
        thread.start();
        testX = 100;
        testY = 100;
    }

    @Override
    public void run() {
        while(thread != null){
            System.out.println("test");
            movement();
            repaint();
            try{
                Thread.sleep(10);
            }catch(Exception e){

            }
        }
    }

    public void movement(){
        /*pacMan.setX(pacMan.getX() + pacMan.getVelocityX());
        pacMan.setY(pacMan.getY() + pacMan.getVelocityY());*/

        pacMan.movement();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        pacMan.draw(g2);
    }

 /*   @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Aaa");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyChar();
        switch(key){
            case 'a': System.out.println("key a");
                //testX += 2;
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
    public void keyReleased(KeyEvent e) {

    }*/
}
