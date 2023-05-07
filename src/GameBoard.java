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
    private int numberOfEdibles = 0;


    public GameBoard(int size){
        super(new PlanszaGry(size));


        this.width = width;
        this.height = height;
        this.size = size;
        Edible.edibleArray = new Edible[size][size];
        addEdiblesToArray();
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

        for(int i = 0; i< Enemy.enemies.length; i++){
            Enemy.enemies[i] = new Enemy(this);
        }

        this.setFocusable(true);
        this.thread = new Thread(this);
        thread.start();


    }

    @Override
    public void run() {
        try{
            for(int i = 3; i>0; i--) {
                System.out.println(i);
                Thread.sleep(1000);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        /*for(int i = 0; i< Enemy.enemies.length; i++){
            Enemy.enemies[i].setX(Edible.edibleArray[this.getSize1() - 3][this.getSize1() - 3].getX());
            Enemy.enemies[i].setY(Edible.edibleArray[this.getSize1() - 3][this.getSize1() - 3].getY());
        }*/
        while(thread != null){

            //System.out.println(this.getWidth());
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
            if(checkForGameEnd()){
                gameEnd();
                break;
            }
        }
    }


    public void movement(){
        /*pacMan.setX(pacMan.getX() + pacMan.getVelocityX());
        pacMan.setY(pacMan.getY() + pacMan.getVelocityY());*/

        pacMan.movement();
        for(int i = 0; i< Enemy.enemies.length; i++){
            Enemy.enemies[i].movement();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawEdibles(g2);
        pacMan.draw(g2);
        pacMan.setHeight(this.getHeight()/(2*size));
        pacMan.setWidth(this.getWidth()/(2*size));

        for(int i = 0; i< Enemy.enemies.length; i++){
            Enemy.enemies[i].draw(g2);
        }

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
    public boolean checkForGameEnd(){
        if(numberOfEdibles == 0 || OknoGry.lives <= 0){
            return true;
        }

        return false;
    }
    public void gameEnd(){
        System.out.println("GAME ENDED");
    }

    public void drawEdibles(Graphics2D g2){
        for(int column = 0; column< this.getSize1(); column++){
            for(int row = 0; row< this.getSize1(); row++){
                if(this.getSize1() % 2 == 1) {
                    if ((row != 0 && row != this.getSize1() - 1 && column != 0 && column != this.getSize1() - 1) && (column % 2 == 1 || row % 2 == 1)) {
                        Rectangle r = this.getCellRect(row, column, true);
                        if(Edible.edibleArray[column][row] != null){
                            Edible.edibleArray[column][row].setHeight((int)(r.getHeight()/5));
                            Edible.edibleArray[column][row].setWidth((int) (r.getWidth()/5));
                            Edible.edibleArray[column][row].setX((int)(r.getX() + r.getWidth()/2 - Edible.edibleArray[column][row].getWidth()/2));
                            Edible.edibleArray[column][row].setY((int)(r.getY() + r.getHeight()/2 - Edible.edibleArray[column][row].getHeight()/2));

                            g2.drawImage(Edible.edibleArray[column][row].getEdiblesSprite(), Edible.edibleArray[column][row].getX(), Edible.edibleArray[column][row].getY(), Edible.edibleArray[column][row].getWidth(), Edible.edibleArray[column][row].getHeight(), null);
                        }
                    }
                }else{
                    if ((row != 0 && row != this.getSize1() - 1 && column != 0 && column != this.getSize1() - 1 ) && ((column % 2 == 1 && column < this.getSize1()/2) || (row % 2 == 1 && row < this.getSize1()/2) || (column % 2 == 0 && column > (this.getSize1()/2)) || (row % 2 == 0 && row > (this.getSize1()/2)))) {
                        Rectangle r = this.getCellRect(row, column, true);
                        if(Edible.edibleArray[column][row] != null){
                            Edible.edibleArray[column][row].setHeight((int)(r.getHeight()/5));
                            Edible.edibleArray[column][row].setWidth((int) (r.getWidth()/5));
                            Edible.edibleArray[column][row].setX((int)(r.getX() + r.getWidth()/2 - Edible.edibleArray[column][row].getWidth()/2));
                            Edible.edibleArray[column][row].setY((int)(r.getY() + r.getHeight()/2 - Edible.edibleArray[column][row].getHeight()/2));

                            g2.drawImage(Edible.edibleArray[column][row].getEdiblesSprite(), Edible.edibleArray[column][row].getX(), Edible.edibleArray[column][row].getY(), Edible.edibleArray[column][row].getWidth(), Edible.edibleArray[column][row].getHeight(), null);
                        }
                    }
                }
            }
        }
    }
    public void addEdiblesToArray(){
        for(int column = 0; column< this.getSize1(); column++){
            for(int row = 0; row< this.getSize1(); row++){
                if(this.getSize1() % 2 == 1) {
                    if ((row != 0 && row != this.getSize1() - 1 && column != 0 && column != this.getSize1() - 1) && (column % 2 == 1 || row % 2 == 1)) {
                        Edible.edibleArray[column][row] = new Edible(this);
                        this.numberOfEdibles++;
                    }
                }else{
                    if ((row != 0 && row != this.getSize1() - 1 && column != 0 && column != this.getSize1() - 1 ) && ((column % 2 == 1 && column < this.getSize1()/2) || (row % 2 == 1 && row < this.getSize1()/2) || (column % 2 == 0 && column > (this.getSize1()/2)) || (row % 2 == 0 && row > (this.getSize1()/2)))) {
                        Edible.edibleArray[column][row] = new Edible(this);
                        this.numberOfEdibles++;
                    }
                }
            }
        }
    }


    public int getSize1() {
        return size;
    }

    public int getNumberOfEdibles() {
        return numberOfEdibles;
    }

    public void setNumberOfEdibles(int numberOfEdibles) {
        this.numberOfEdibles = numberOfEdibles;
    }

}
