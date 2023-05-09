import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PacMan extends Thing{
    //KeyHandler keyHandler = new KeyHandler();
    private BufferedImage pacManSprite;

    public PacMan( int x, int y, GameBoard gB){
        this.gB = gB;
        try {
            pacManSprite = ImageIO.read(new File("src/Sprites/PacMan.png"));
        }catch (Exception e) {
            e.printStackTrace();
        }
        //keyHandler = k;

        this.x = 700 / gB.getSize1() + 10;
        this.y = 700/ gB.getSize1() + 10;
        this.width = 350/ gB.getSize1();
        this.height = 350/ gB.getSize1();
        if(gB.getSize1()>30){
            this.speed = 1;
        }else{
            this.speed = 3;
        }

    }
    /*public void movement(){
        x += velocityX;
        y += velocityY;
    }*/

    public void movement(){
        /*pacMan.setX(pacMan.getX() + pacMan.getVelocityX());
        pacMan.setY(pacMan.getY() + pacMan.getVelocityY());*/
        if(up){
            int temp = y;
            y -= speed;
            if(checkColision())
                y = temp;
        }else if(down){
            int temp = y;
            y += speed;
            if(checkColision())
                 y = temp;
        }else if(left){
            int temp = x;
            x -= speed;
            if(checkColision())
                 x = temp;
        }else if(right){
            int temp = x;
            x += speed;
            if(checkColision())
                x = temp;
        }
        checkEdible();
        checkEnemies();
    }
    private boolean checkColision(){
        for(int column = 0; column< gB.getSize1(); column++){
            for(int row = 0; row< gB.getSize1(); row++){
                if(gB.getSize1() % 2 == 1) {
                    if (!((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && (column % 2 == 1 || row % 2 == 1))) {
                        Rectangle r = gB.getCellRect(row, column, true);
                        if(this.x > r.getX() && this.x < r.getX() + r.getWidth() && this.y > r.getY() && this.y < r.getY() + r.getHeight())
                            return true;
                        if(this.x + this.width> r.getX() && this.x + this.width< r.getX() + r.getWidth() && this.y > r.getY() && this.y < r.getY() + r.getHeight())
                            return true;
                        if(this.x > r.getX() && this.x < r.getX() + r.getWidth() && this.y + this.height> r.getY() && this.y + this.height< r.getY() + r.getHeight())
                            return true;
                        if(this.x + this.width> r.getX() && this.x + this.width< r.getX() + r.getWidth() && this.y + this. height> r.getY() && this.y + this.height< r.getY() + r.getHeight())
                            return true;
                    }
                }else{
                    if (!((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1 ) && ((column % 2 == 1 && column < gB.getSize1()/2) || (row % 2 == 1 && row < gB.getSize1()/2) || (column % 2 == 0 && column > (gB.getSize1()/2)) || (row % 2 == 0 && row > (gB.getSize1()/2))))) {
                        Rectangle r = gB.getCellRect(row, column, true);
                        if(this.x > r.getX() && this.x < r.getX() + r.getWidth() && this.y > r.getY() && this.y < r.getY() + r.getHeight())
                            return true;
                        if(this.x + this.width> r.getX() && this.x + this.width< r.getX() + r.getWidth() && this.y > r.getY() && this.y < r.getY() + r.getHeight())
                            return true;
                        if(this.x > r.getX() && this.x < r.getX() + r.getWidth() && this.y + this.height> r.getY() && this.y + this.height< r.getY() + r.getHeight())
                            return true;
                        if(this.x + this.width> r.getX() && this.x + this.width< r.getX() + r.getWidth() && this.y + this. height> r.getY() && this.y + this.height< r.getY() + r.getHeight())
                            return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean checkEdible(){
        Rectangle pacRect = new Rectangle(this.x, this.y, this.width, this.height);
        for(int i = 0; i< Edible.edibleArray.length; i++){
            for(int j = 0; j< Edible.edibleArray[0].length; j++){
                if(Edible.edibleArray[i][j] != null){
                    Rectangle rec1 = new Rectangle(Edible.edibleArray[i][j].getX(),Edible.edibleArray[i][j].getY(), Edible.edibleArray[i][j].getWidth(),Edible.edibleArray[i][j].getHeight());
                    if(pacRect.intersects(rec1)) {
                        Edible.edibleArray[i][j] = null;
                        gB.setNumberOfEdibles(gB.getNumberOfEdibles() - 1);
                        OknoGry.score++;
                        OknoGry.scoreLabel.setText("Score " + OknoGry.score);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void checkEnemies(){
        Rectangle pacRect = new Rectangle(this.x, this.y, this.width, this.height);
        for(int i = 0; i< Enemy.enemies.length; i++){
            Rectangle recEnemy = new Rectangle(Enemy.enemies[i].getX(),Enemy.enemies[i].getY(), Enemy.enemies[i].getWidth(), Enemy.enemies[i].getHeight());
            if (!Enemy.enemies[i].isEdible()) {
                if (pacRect.intersects(recEnemy)) {
                    OknoGry.lives--;
                    OknoGry.lifeLabel.setText("Lives: " + OknoGry.lives);
                    if(OknoGry.lives == 0){
                        gB.checkForGameEnd = true;
                    }else{
                        gB.resetBoard();
                    }
                    //todo zresetowac plansze i dodac tutaj check czy lives == 0
                }
            }

        }
    }

    public void draw(Graphics2D g2){
        /*g2.setColor(Color.BLUE);
        g2.fillRect(x, y, 30, 30);*/
        g2.drawImage(pacManSprite, x, y, width, height, null);
    }



    public Image getPacManSprite() {
        return pacManSprite;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
