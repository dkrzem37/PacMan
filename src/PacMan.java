import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PacMan {
    //KeyHandler keyHandler = new KeyHandler();

    private int x, y, width, height;
    private int speed;
    private BufferedImage pacManSprite;
    boolean up, left, right, down;
    GameBoard gB;

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
            if(checkCollision())
                y = temp;
        }else if(down){
            int temp = y;
            y += speed;
            if(checkCollision())
                 y = temp;
        }else if(left){
            int temp = x;
            x -= speed;
            if(checkCollision())
                 x = temp;
        }else if(right){
            int temp = x;
            x += speed;
            if(checkCollision())
                x = temp;
        }
    }
    private boolean checkCollision(){
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
