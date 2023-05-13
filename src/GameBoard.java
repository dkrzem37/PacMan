import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class GameBoard extends JTable implements Runnable, KeyListener {
    private Thread thread, timeCounter, spawningUpgrades, pacManAnimation;
    private PacMan pacMan;
    private int height, width,size;
    private int numberOfEdibles = 0;
    OknoGry oG;
    public boolean checkForGameEnd;
    private BufferedImage countdown;
    public GameBoard(int size, OknoGry oG){

        checkForGameEnd = false;
        this.oG = oG;
         Upgrade.upgrades = new ArrayList<>();

        setModel(new PlanszaGry(size));

        this.size = size;
        Edible.edibleArray = new Edible[size][size];
        addEdiblesToArray();

        for(int column = 0; column < size; column++) {
            this.getColumnModel().getColumn(column).setMinWidth(1);
            this.getColumnModel().getColumn(column).setPreferredWidth(700/size);
        }
        setRowHeight(700/size);
        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));
        setRowMargin(0);

        setDefaultRenderer(Object.class, new GameBoardCellRenderer(this));


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

        try {
            for (int i = 3; i > 0; i--) {
                switch (i) {
                    case 3:
                        try {
                            countdown = ImageIO.read(new File("src/Sprites/3.png"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            countdown = ImageIO.read(new File("src/Sprites/2.png"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            countdown = ImageIO.read(new File("src/Sprites/1.png"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
                if (i == 2) {
                    Rectangle r1 = this.getCellRect(1, 1, true);
                    pacMan.setX((int) r1.getX());
                    pacMan.setY((int) r1.getY());
                    //System.out.println((int)r1.getX());
                    //System.out.println((int)r1.getY());
                    ///////
                    /*pacMan.setHeight((int)r1.getHeight()/3);
                    pacMan.setWidth((int)r1.getWidth()/3);*/

                    //here good
                    pacMan.setHeight((int) r1.getHeight() - 1);
                    pacMan.setWidth((int) r1.getWidth() - 1);
                    Rectangle r2 = this.getCellRect(this.getSize1() - 2, this.getSize1() - 2, true);


                    for (int j = 0; j < Enemy.enemies.length; j++) {
                        Enemy.enemies[j].setX((int) r2.getX());
                        //System.out.println("x" + (int)r2.getX());
                        Enemy.enemies[j].setY((int) r2.getY());
                        Enemy.enemies[j].setHeight((int) r2.getHeight() - 1);
                        Enemy.enemies[j].setWidth((int) r2.getWidth() - 1);
                /*Enemy.enemies[j].setX((int)r2.getX());
                Enemy.enemies[j].setY((int)r2.getY() + 1);
                Enemy.enemies[j].setHeight((int)r2.getHeight() - 2);
                Enemy.enemies[j].setWidth((int)r2.getWidth() - 2);*/
                    }
                    //repaint();
                }
                if(i==1){
                    for (int j = 0; j < Enemy.enemies.length; j++) {
                        Enemy.enemies[j].setEnemyAnimation(new EnemyAnimation(Enemy.enemies[j]));
                        Enemy.enemies[j].getEnemyAnimation().start();
                    }
                    pacManAnimation = new PacManAnimation(pacMan);
                    pacManAnimation.start();

                    spawningUpgrades = new Thread(Enemy.enemies[0]);
                    spawningUpgrades.start();
                }
                repaint();
                System.out.println("Countdown: " + i);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("flag 1");
            System.out.println(e.getMessage());
            thread.interrupt();
        }
        //System.out.println("Column: " + this.getColumnModel().getColumn(1).getWidth());
        countdown = null;
        //if(!Thread.interrupted()) {}
            /*for (int i = 0; i < Enemy.enemies.length; i++) {
                Enemy.enemies[i].setEnemyAnimation(new EnemyAnimation(Enemy.enemies[i]));
                Enemy.enemies[i].getEnemyAnimation().start();
            }
            pacManAnimation = new PacManAnimation(pacMan);
            pacManAnimation.start();

            spawningUpgrades = new Thread(Enemy.enemies[0]);
            spawningUpgrades.start();*/




        //System.out.println(this.getWidth());
        //System.out.println(" height: " + this.getHeight());

        while(thread != null){ //thread != null

            /*Rectangle r2 = this.getCellRect(this.getSize1() -2, this.getSize1() -2, true);
            System.out.println(r2.getWidth());
            System.out.println("Height of cell" + r2.getHeight());*/

            if(Thread.interrupted()){
                /*checkForGameEnd = checkForGameEnd();
                if(checkForGameEnd){
                    gameEnd();
                    //break;
                }*/
                break;
            }

            //System.out.println(this.getWidth());
            //System.out.println("test");
            movement();
            repaint();

            try{
                Thread.sleep(10);
            }catch(InterruptedException e){
                /*checkForGameEnd = checkForGameEnd();
                if(checkForGameEnd){
                    gameEnd();
                    //break;
                }*/
                System.out.println("flag 2");
                break;
            }
            //todo:solution1
            /*System.out.println("panel width:" + jPanel.getWidth());
            System.out.println("panel height:" + jPanel.getHeight());
            setRowHeight(jPanel.getHeight()/size);
            for(int column = 0; column < size; column++) {
                columnModel.getColumn(column).setPreferredWidth(jPanel.getWidth()/size);
            }*/


            /*for (int x = 0; x < this.getColumnCount(); ++x) {
                TableColumn col = this.getColumnModel().getColumn(x);
                //col.setPreferredWidth(1000);
                col.setWidth(700/size);
            }*/
            /*checkForGameEnd = checkForGameEnd();
            if(checkForGameEnd){
                gameEnd();
                break;
            }*/
        }
    }


    public void movement(){
        /*pacMan.setX(pacMan.getX() + pacMan.getVelocityX());
        pacMan.setY(pacMan.getY() + pacMan.getVelocityY());*/
        for(int i = 0; i< pacMan.getSpeed(); i++) {

            /*if(pacMan.getX()/previousWindowWidth !=  pacMan.getX()/this.getWidth()) {
                pacMan.setX((pacMan.getX() * this.getWidth()/ previousWindowWidth));
            }
            previousWindowWidth = this.getWidth();*/
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

        /*for(int column = 0; column < size; column++) {
            this.getColumnModel().getColumn(column).setMinWidth(1);
            this.getColumnModel().getColumn(column).setPreferredWidth(this.getWidth()/size);
            //this.getColumnModel().getColumn(column).setWidth(7);
        }*/

        setRowHeight(this.getHeight()/size);
        /*Rectangle r1 = this.getCellRect(1, 1, false);
        pacMan.setWidth(this.getColumnModel().getColumn(0).getWidth() - 1);
        pacMan.setHeight((int)r1.getHeight() - 1);*/
        //pacMan.setX( (int)r1.getX());
        //pacMan.setY((int)r1.getY());

        drawEdibles(g2);
        for(int i = 0; i< Upgrade.upgrades.size(); i++){
            if(Upgrade.upgrades.get(i) != null) {
                Upgrade.upgrades.get(i).draw(g2);
                //System.out.println("drawn " + i);
            }
        }
        pacMan.draw(g2);
        /*pacMan.setHeight(this.getHeight()/(2*size));
        pacMan.setWidth(this.getWidth()/(2*size));*/
        for(int i = 0; i< Enemy.enemies.length; i++){
            Enemy.enemies[i].draw(g2);
        }
        if(countdown != null){
            g2.drawImage(countdown, this.getHeight()/2 - 25, this.getWidth()/ 2 - 25, 50, 50, null);
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
            pacMan.potentialUp = true;
            pacMan.potentialLeft = false;
            pacMan.potentialDown = false;
            pacMan.potentialRight = false;
        }
        if(key == KeyEvent.VK_A){
            pacMan.potentialUp = false;
            pacMan.potentialLeft = true;
            pacMan.potentialDown = false;
            pacMan.potentialRight = false;
        }
        if(key == KeyEvent.VK_S){
            pacMan.potentialUp = false;
            pacMan.potentialLeft = false;
            pacMan.potentialDown = true;
            pacMan.potentialRight = false;
        }
        if(key == KeyEvent.VK_D){
            pacMan.potentialUp = false;
            pacMan.potentialLeft = false;
            pacMan.potentialDown = false;
            pacMan.potentialRight = true;
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
        //Rectangle r1 = this.getCellRect(1, 1, true);
        pacMan.setX(-200);
        pacMan.setY(-200);

        //Rectangle r2 = this.getCellRect(this.getSize1() -2, this.getSize1() -2, true);
        for(int j = 0; j< Enemy.enemies.length; j++){
            Enemy.enemies[j].setX(-100);
            Enemy.enemies[j].setY(-100);
            /*Enemy.enemies[j].setHeight((int)r2.getHeight() -2);
            Enemy.enemies[j].setWidth((int)r2.getWidth() -2);*/
        }

        System.out.println("GAME ENDED");
        if(Enemy.enemies[0].getEnemyAnimation() != null) {
            for (int i = 0; i < Enemy.enemies.length; i++) {
                Enemy.enemies[i].getEnemyAnimation().interrupt();
            }
        }
        if(pacManAnimation != null)
            pacManAnimation.interrupt();
        if(spawningUpgrades != null)
            spawningUpgrades.interrupt();
        timeCounter.interrupt();
        thread.interrupt();
        if(checkForGameEnd)
            oG.saveScore();
    }
    public void resetBoard(){
        for(int i = 0; i< Enemy.enemies.length; i++){
            Enemy.enemies[i].getEnemyAnimation().interrupt();
        }
        if(pacManAnimation != null)
            pacManAnimation.interrupt();

        if(spawningUpgrades != null)
            spawningUpgrades.interrupt();

        thread.interrupt();

        /*Rectangle r1 = this.getCellRect(1, 1, true);
        pacMan.setX((int)r1.getX());
        pacMan.setY((int)r1.getY());*/
        pacMan.setX(-200);
        pacMan.setY(-200);
        if(this.getSize1()>30){
            pacMan.speed = 2;
        }else{
            pacMan.speed = 3;
        }
        //Rectangle r2 = this.getCellRect(this.getSize1() -2, this.getSize1() -2, true);
        for(int j = 0; j< Enemy.enemies.length; j++){
            /*Enemy.enemies[j].setX((int)r2.getX());
            Enemy.enemies[j].setY((int)r2.getY());*/
            Enemy.enemies[j].setX(-100);
            Enemy.enemies[j].setY(-100);
            if(this.getSize1()>30){
                Enemy.enemies[j].setSpeed(2);
            }else{
                Enemy.enemies[j].setSpeed(3);
            }
            /*Enemy.enemies[j].setHeight((int)r2.getHeight() -2);
            Enemy.enemies[j].setWidth((int)r2.getWidth() -2);*/
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
