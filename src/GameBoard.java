import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.HashSet;

public class GameBoard extends JTable implements Runnable, KeyListener {
    private Thread thread, timeCounter, spawningUpgrades;
    private PacMan pacMan;
    private int height, width,size;
    private int numberOfEdibles = 0;
    OknoGry oG;
    public boolean checkForGameEnd;


    public GameBoard(int size, OknoGry oG){
        super(new PlanszaGry(size));
        checkForGameEnd = false;
        this.oG = oG;

        /*Action quitAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        String command = "c";
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(keyStroke, command);
        this.getActionMap().put(command, quitAction);*/

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
        this.timeCounter = new Thread(new TimeCounter());
        timeCounter.start();


    }

    @Override
    public void run() {
        //THIS WORKS
        /*pacMan.setX(700 / this.getSize1() + 10);
        pacMan.setY(700 / this.getSize1() + 10);

        for(int i = 0; i< Enemy.enemies.length; i++){
            Enemy.enemies[i].setX(595);
            Enemy.enemies[i].setY(585);
        }*/

        /*Rectangle r = this.getCellRect(1, 1, true);
        System.out.println("x: " + (int)r.getX());*/

        try{
            for(int i = 3; i>0; i--) {
                if(i == 2){
                    Rectangle r1 = this.getCellRect(1, 1, true);
                    pacMan.setX((int)r1.getX());
                    pacMan.setY((int)r1.getY());
                    System.out.println((int)r1.getX());
                    System.out.println((int)r1.getY());
                    ///////
                    pacMan.setHeight((int)r1.getHeight()/3);
                    pacMan.setWidth((int)r1.getWidth()/3);
                    /*pacMan.setHeight((int)r1.getHeight() - 2);
                    pacMan.setWidth((int)r1.getWidth() - 2);*/
                    Rectangle r2 = this.getCellRect(this.getSize1() -2, this.getSize1() -2, true);

                    for(int j = 0; j< Enemy.enemies.length; j++){
                        Enemy.enemies[j].setX((int)r2.getX());
                        //System.out.println("x" + (int)r2.getX());
                        Enemy.enemies[j].setY((int)r2.getY());
                        Enemy.enemies[j].setHeight((int)r2.getHeight() -2);
                        Enemy.enemies[j].setWidth((int)r2.getWidth() -2);
                /*Enemy.enemies[j].setX((int)r2.getX());
                Enemy.enemies[j].setY((int)r2.getY() + 1);
                Enemy.enemies[j].setHeight((int)r2.getHeight() - 2);
                Enemy.enemies[j].setWidth((int)r2.getWidth() - 2);*/
                    }
                    repaint();
                }

                System.out.println(i);
                Thread.sleep(1000);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        spawningUpgrades = new Thread(Enemy.enemies[0]);
        spawningUpgrades.start();
        while(thread != null){ //thread != null

            if(Thread.interrupted()){
                if(checkForGameEnd){
                    gameEnd();
                    break;
                }
                break;
            }

            //System.out.println(this.getWidth());
            //System.out.println("test");
            movement();
            repaint();
            try{
                Thread.sleep(10);
            }catch(InterruptedException e){
                if(checkForGameEnd){
                    gameEnd();
                    break;
                }
                break;
            }
            setRowHeight(this.getHeight()/size);
            /*for (int x = 0; x < this.getColumnCount(); ++x) {
                TableColumn col = this.getColumnModel().getColumn(x);
                //col.setPreferredWidth(1000);
                col.setWidth(700/size);
            }*/
            checkForGameEnd = checkForGameEnd();
            if(checkForGameEnd){
                gameEnd();
                break;
            }
        }
    }


    public void movement(){
        /*pacMan.setX(pacMan.getX() + pacMan.getVelocityX());
        pacMan.setY(pacMan.getY() + pacMan.getVelocityY());*/
        for(int i = 0; i< pacMan.getSpeed(); i++) {
            pacMan.movement();
        }
        for(int i = 0; i< Enemy.enemies.length; i++){
            for(int j = 0; j< Enemy.enemies[i].getSpeed(); j++) {
                Enemy.enemies[i].movement();
            }

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        drawEdibles(g2);
        for(int i = 0; i< Upgrade.upgrades.size(); i++){
            if(Upgrade.upgrades.get(i) != null) {
                Upgrade.upgrades.get(i).draw(g2);
                System.out.println("drawn " + i);
            }
        }
        pacMan.draw(g2);
        /*pacMan.setHeight(this.getHeight()/(2*size));
        pacMan.setWidth(this.getWidth()/(2*size));*/
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
        timeCounter.interrupt();
        oG.saveScore();
    }
    public void resetBoard(){
        spawningUpgrades.interrupt();
        thread.interrupt();
        Rectangle r1 = this.getCellRect(1, 1, true);
        pacMan.setX((int)r1.getX());
        pacMan.setY((int)r1.getY());
        Rectangle r2 = this.getCellRect(this.getSize1() -2, this.getSize1() -2, true);
        for(int j = 0; j< Enemy.enemies.length; j++){
            Enemy.enemies[j].setX((int)r2.getX());
            Enemy.enemies[j].setY((int)r2.getY());
            Enemy.enemies[j].setHeight((int)r2.getHeight() -2);
            Enemy.enemies[j].setWidth((int)r2.getWidth() -2);
        }

        this.thread = new Thread(this);
        thread.start();
    }

    public void drawEdibles(Graphics2D g2){
        for(int column = 0; column< this.getSize1(); column++){
            for(int row = 0; row< this.getSize1(); row++){
                Rectangle r = this.getCellRect(row, column, true);
                if(this.getSize1() % 2 == 1) {
                    if ((row != 0 && row != this.getSize1() - 1 && column != 0 && column != this.getSize1() - 1) && (column % 2 == 1 || row % 2 == 1)) {
                        //Rectangle r = this.getCellRect(row, column, true);
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
                        //Rectangle r = this.getCellRect(row, column, true);
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

    public PacMan getPacMan() {
        return pacMan;
    }
}
