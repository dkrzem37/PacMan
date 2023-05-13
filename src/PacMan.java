import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PacMan extends Thing{
    //KeyHandler keyHandler = new KeyHandler();
    private BufferedImage pacManSprite;
    private boolean repeatPrevention = false;
    boolean potentialUp, potentialLeft, potentialRight, potentialDown;

    public PacMan( int x, int y, GameBoard gB){
        this.gB = gB;
        try {
            pacManSprite = ImageIO.read(new File("src/Sprites/PacManNormal.png"));
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(gB.getSize1()>30){
            this.speed = 2;
        }else{
            this.speed = 3;
        }

    }

    public void movement(){
        Rectangle pacRectDown = new Rectangle(this.x, this.y + this.height/2, this.width, this.height);
        Rectangle pacRectUp = new Rectangle(this.x, this.y - this.height/2, this.width, this.height);
        Rectangle pacRectLeft = new Rectangle(this.x - this.width/2, this.y, this.width, this.height);
        Rectangle pacRectRight = new Rectangle(this.x + this.width/2, this.y, this.width, this.height);

        if(potentialDown && checkDir(pacRectDown)){
            down = potentialDown;
            up = false;
            left = false;
            right = false;
        }
        if(potentialUp && checkDir(pacRectUp)){
            down = false;
            up = potentialUp;
            left = false;
            right = false;
        }
        if(potentialLeft && checkDir(pacRectLeft)){
            down = false;
            up = false;
            left = potentialLeft;
            right = false;
        }
        if(potentialRight && checkDir(pacRectRight)){
            down = false;
            up = false;
            left = false;
            right = potentialRight;
        }
        if(up){
            int temp = y;
            y -= 1;
            if(checkColision())
                y = temp;
        }else if(down){
            int temp = y;
            y += 1;
            if(checkColision())
                 y = temp;
        }else if(left){
            int temp = x;
            x -= 1;
            if(checkColision())
                 x = temp;
        }else if(right){
            int temp = x;
            x += 1;
            if(checkColision())
                x = temp;
        }

        checkEdible();

        //bad, but works
        if(!repeatPrevention && gB.checkForGameEnd()){
            gB.gameEnd();
        }
        checkEnemies();
        checkUpgrade();
    }
    private boolean checkColision(){
        for(int column = 0; column< gB.getSize1(); column++){
            for(int row = 0; row< gB.getSize1(); row++){
                Rectangle r = gB.getCellRect(row, column, true);
                if(gB.getSize1() % 2 == 1) {
                    if (!((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && (column % 2 == 1 || row % 2 == 1))) {
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

    private boolean checkDir(Rectangle pacRect){
        for (int column = 0; column < gB.getSize1(); column++) {
            for (int row = 0; row < gB.getSize1(); row++) {
                if (gB.getSize1() % 2 == 1) {
                    if (!((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && (column % 2 == 1 || row % 2 == 1))) {
                        Rectangle r = gB.getCellRect(row, column, false);
                        if (pacRect.intersects(r)) {
                            return false;
                        }
                    }
                } else {
                    if (!((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && ((column % 2 == 1 && column < gB.getSize1() / 2) || (row % 2 == 1 && row < gB.getSize1() / 2) || (column % 2 == 0 && column > (gB.getSize1() / 2)) || (row % 2 == 0 && row > (gB.getSize1() / 2))))) {
                        Rectangle r = gB.getCellRect(row, column, false);
                        if (pacRect.intersects(r)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
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
                        OknoGry.scoreLabel.setText("Score: " + OknoGry.score);
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
                    gB.checkForGameEnd = gB.checkForGameEnd();
                    if((gB.checkForGameEnd && !repeatPrevention) || gB.checkForGameEnd()){
                        repeatPrevention = true;
                        gB.gameEnd();
                    }else if(!repeatPrevention){
                        gB.resetBoard();
                    }
                }
            }

        }
    }
    public void checkUpgrade(){
        Rectangle pacRect = new Rectangle(this.x, this.y, this.width, this.height);
        for(int i = 0; i< Upgrade.upgrades.size(); i++){
            if (Upgrade.upgrades.get(i) != null) {
                Rectangle rec1 = new Rectangle(Upgrade.upgrades.get(i).getX(), Upgrade.upgrades.get(i).getY(), Upgrade.upgrades.get(i).getWidth(), Upgrade.upgrades.get(i).getHeight());
                if (pacRect.intersects(rec1)) {
                    Upgrade.upgrades.get(i).effect();
                    Upgrade.upgrades.remove(i);
                }
            }
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(pacManSprite, x, y, width, height, null);
    }



    public Image getPacManSprite() {
        return pacManSprite;
    }

    public void setPacManSprite(BufferedImage pacManSprite) {
        this.pacManSprite = pacManSprite;
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
