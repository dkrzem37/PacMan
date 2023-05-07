/*
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PoleGry extends JPanel implements Runnable, KeyListener {
    Thread thread;

    //KeyHandler keyHandler = new KeyHandler();
    PacMan pacMan = new PacMan( 100, 100);


    public PoleGry() {
        */
/*try {
            BufferedImage tile = ImageIO.read(new File("src/Sprites/Tile.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*//*

        JTable jTable = new GameBoard(15);
        jTable.setBackground(Color.blue);
        jTable.setGridColor(Color.cyan);
        this.add(jTable);

        this.setPreferredSize(new Dimension(750, 750));
        this.setBackground(Color.black);
        this.addKeyListener(this);
        this.setFocusable(false);
        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(thread != null){
            //System.out.println("test");
            movement();
            repaint();
            try{
                Thread.sleep(10);
            }catch(Exception e){

            }
        }
    }

    public void movement(){
        */
/*pacMan.setX(pacMan.getX() + pacMan.getVelocityX());
        pacMan.setY(pacMan.getY() + pacMan.getVelocityY());*//*


        pacMan.movement();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        pacMan.draw(g2);

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W){
            //System.out.println("here");
            pacMan.up = true;
            pacMan.left = false;
            pacMan.down = false;
            pacMan.right = false;
        }
        if(key == KeyEvent.VK_A){
            pacMan.up = false;
            pacMan.left = true;
            pacMan.down = false;
            pacMan.right = false;
        }
        if(key == KeyEvent.VK_S){
            pacMan.up = false;
            pacMan.left = false;
            pacMan.down = true;
            pacMan.right = false;
        }
        if(key == KeyEvent.VK_D){
            pacMan.up = false;
            pacMan.left = false;
            pacMan.down = false;
            pacMan.right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

 */
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

    }*//*

}
*/
