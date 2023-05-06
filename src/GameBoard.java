import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class GameBoard extends JTable implements Runnable, KeyListener {
    private Thread thread;
    PacMan pacMan;
    private int height, width,size;
    HashSet<Rectangle> obstacles;


    public GameBoard(int size){
        super(new PlanszaGry(size));


        this.width = width;
        this.height = height;
        this.size = size;
        //setOpaque(true);
        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));
        setRowMargin(0);


        setDefaultRenderer(Object.class, new GameBoardCellRenderer(this));
        //setDefaultEditor(Object.class, new GameBoardCellEditor());
        //this.setPreferredSize(new Dimension(width, height));
        setRowHeight(700/size);

        this.addKeyListener(this);
        pacMan = new PacMan( 100, 100, this);

        this.setFocusable(true);
        this.thread = new Thread(this);
        thread.start();


    }

    @Override
    public void run() {
        while(thread != null){

            System.out.println(this.getWidth());
            //System.out.println("test");
            movement();
            repaint();
            try{
                Thread.sleep(10);
            }catch(Exception e){

            }
            setRowHeight(this.getHeight()/size);
            /*for (int x = 0; x < this.getColumnCount(); ++x) {
                TableColumn col = this.getColumnModel().getColumn(x);
                //col.setPreferredWidth(1000);
                col.setWidth(700/size);
            }*/

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
        pacMan.setHeight(this.getHeight()/(2*size));
        pacMan.setWidth(this.getWidth()/(2*size));
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

    public HashSet<Rectangle> getObstacles() {
        return obstacles;
    }

    public int getSize1() {
        return size;
    }
}
