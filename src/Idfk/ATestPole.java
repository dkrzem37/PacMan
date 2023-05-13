/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ATestPole extends JFrame implements  Runnable, KeyListener {
    //private Image pacManSprite;
    PacMan pacMan = new PacMan(100, 100);
    JLabel jLabel;
    public ATestPole() {
        generatePole();
        */
/*Thread thread = new Thread(this);
        thread.start();*//*


    }


    public void generatePole(){

        //ImageIcon ii = new ImageIcon("src/Sprites/PacManNormal.png");
        //pacManSprite = pacMan.getPacManSprite();

        */
/*try {
            pacManSprite = ImageIO.read(new File("src/Sprites/PacManNormal.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*//*



        jLabel = new JLabel();
        ImageIcon ii = new ImageIcon("src/Sprites/PacManNormal.png");
        jLabel.setIcon(ii);

        addKeyListener(this);

        setBackground(Color.black);
        add(jLabel);
        //this.getContentPane().add(jPanel);


        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

   */
/* @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(pacMan.getPacManSprite(), pacMan.getX(), pacMan.getY(), null);
    }*//*



    @Override
    public void run() {
        System.out.println("hello");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        pacMan.movement();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'a': jLabel.setLocation(jLabel.getX() - 10, jLabel.getY());
                break;
            case 'w': jLabel.setLocation(jLabel.getX(), jLabel.getY() - 10);
                break;
            case 's': jLabel.setLocation(jLabel.getX(), jLabel.getY() + 10);
                break;
            case 'd': jLabel.setLocation(jLabel.getX()+ 10, jLabel.getY());
                break;
        }
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}
*/
