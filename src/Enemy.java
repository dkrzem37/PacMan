import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Enemy extends Thing{
    private boolean isEdible = false;
    public static Enemy[] enemies= new Enemy[4];
    private BufferedImage enemySprite;
    public Enemy(GameBoard gB){
        this.gB = gB;
        this.speed = 3;
        up = true;
        this.x = 595;
        this.y = 585;

        this.height = 700 / gB.getSize1() - 5;
        this.width = 700/ gB.getSize1() - 5;
        try {
            enemySprite = ImageIO.read(new File("src/Sprites/EnemySprite.png"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void movement(){
        if(Math.random()< 0.05)
             setDirection();
        if(up){
            int temp = y;
            y -= speed;
            if(checkColision()) {
                y = temp;
            }
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
    }
    private void setDirection() {
        Rectangle enemyRectDown = new Rectangle(this.x, this.y + this.height/2, this.width, this.height);
        Rectangle enemyRectUp = new Rectangle(this.x, this.y - this.height/2, this.width, this.height);
        Rectangle enemyRectLeft = new Rectangle(this.x - this.width/2, this.y, this.width, this.height);
        Rectangle enemyRectRight = new Rectangle(this.x + this.width/2, this.y, this.width, this.height);
        int rand = (int)(Math.random()*4);
        boolean potentialUp, potentialDown,potentialLeft, potentialRight;
        switch(rand){
            case 0: potentialUp = true;
                for (int column = 0; column < gB.getSize1(); column++) {
                    for (int row = 0; row < gB.getSize1(); row++) {
                        if (gB.getSize1() % 2 == 1) {
                            if (!((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && (column % 2 == 1 || row % 2 == 1))) {
                                Rectangle r = gB.getCellRect(row, column, false);
                                if (enemyRectUp.intersects(r)) {
                                    potentialUp = false;
                                }
                            }
                        } else {
                            if (!((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && ((column % 2 == 1 && column < gB.getSize1() / 2) || (row % 2 == 1 && row < gB.getSize1() / 2) || (column % 2 == 0 && column > (gB.getSize1() / 2)) || (row % 2 == 0 && row > (gB.getSize1() / 2))))) {
                                Rectangle r = gB.getCellRect(row, column, false);
                                if (enemyRectUp.intersects(r)) {
                                    potentialUp = false;
                                }
                            }
                        }
                    }
                }
                if(potentialUp) {
                    up = potentialUp;
                    left = false;
                    down = false;
                    right = false;
                }
                break;
            case 1: potentialDown = true;
                for (int column = 0; column < gB.getSize1(); column++) {
                    for (int row = 0; row < gB.getSize1(); row++) {
                        if (gB.getSize1() % 2 == 1) {
                            if (!((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && (column % 2 == 1 || row % 2 == 1))) {
                                Rectangle r = gB.getCellRect(row, column, false);
                                if (enemyRectDown.intersects(r)) {
                                    potentialDown = false;
                                }
                            }
                        } else {
                            if (!((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && ((column % 2 == 1 && column < gB.getSize1() / 2) || (row % 2 == 1 && row < gB.getSize1() / 2) || (column % 2 == 0 && column > (gB.getSize1() / 2)) || (row % 2 == 0 && row > (gB.getSize1() / 2))))) {
                                Rectangle r = gB.getCellRect(row, column, false);
                                if (enemyRectDown.intersects(r)) {
                                    potentialDown = false;
                                }
                            }
                        }
                    }
                }
                if(potentialDown) {
                    down = potentialDown;
                    left = false;
                    up = false;
                    right = false;
                }
                break;
            case 2: potentialLeft = true;
                for (int column = 0; column < gB.getSize1(); column++) {
                    for (int row = 0; row < gB.getSize1(); row++) {
                        if (gB.getSize1() % 2 == 1) {
                            if (!((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && (column % 2 == 1 || row % 2 == 1))) {
                                Rectangle r = gB.getCellRect(row, column, false);
                                if (enemyRectLeft.intersects(r)) {
                                    potentialLeft = false;
                                }
                            }
                        } else {
                            if (!((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && ((column % 2 == 1 && column < gB.getSize1() / 2) || (row % 2 == 1 && row < gB.getSize1() / 2) || (column % 2 == 0 && column > (gB.getSize1() / 2)) || (row % 2 == 0 && row > (gB.getSize1() / 2))))) {
                                Rectangle r = gB.getCellRect(row, column, false);
                                if (enemyRectLeft.intersects(r)) {
                                    potentialLeft = false;
                                }
                            }
                        }
                    }
                }
                if(potentialLeft) {
                    left = potentialLeft;
                    up = false;
                    down = false;
                    right = false;
                }
                break;
            case 3: potentialRight = true;
                for (int column = 0; column < gB.getSize1(); column++) {
                    for (int row = 0; row < gB.getSize1(); row++) {
                        if (gB.getSize1() % 2 == 1) {
                            if (!((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && (column % 2 == 1 || row % 2 == 1))) {
                                Rectangle r = gB.getCellRect(row, column, false);
                                if (enemyRectRight.intersects(r)) {
                                    potentialRight = false;
                                }
                            }
                        } else {
                            if (!((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && ((column % 2 == 1 && column < gB.getSize1() / 2) || (row % 2 == 1 && row < gB.getSize1() / 2) || (column % 2 == 0 && column > (gB.getSize1() / 2)) || (row % 2 == 0 && row > (gB.getSize1() / 2))))) {
                                Rectangle r = gB.getCellRect(row, column, false);
                                if (enemyRectRight.intersects(r)) {
                                    potentialRight = false;
                                }
                            }
                        }
                    }
                }
                if(potentialRight) {
                    right = potentialRight;
                    left = false;
                    down = false;
                    up = false;
                }
                break;
        }
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

    public void draw(Graphics2D g2){
        g2.drawImage(enemySprite, x, y, width, height, null);
    }

    public boolean isEdible() {
        return isEdible;
    }

    public void setEdible(boolean edible) {
        isEdible = edible;
    }
}
