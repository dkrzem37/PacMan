import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class PacMan {
    KeyHandler keyHandler = new KeyHandler();
    PoleGry pg;
    private int x, y, width, height;
    private int speed;
    private BufferedImage pacManSprite;

    public PacMan(PoleGry pg, KeyHandler k, int x, int y){
        loadImage();
        keyHandler = k;
        this.pg = pg;
        this.x = x;
        this.y = y;
        this.speed = 4;
    }

    private void loadImage(){
        try {
            pacManSprite = ImageIO.read(new File("src/Sprites/PacMan.png"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*public void movement(){
        x += velocityX;
        y += velocityY;
    }*/

    public void movement(){
        /*pacMan.setX(pacMan.getX() + pacMan.getVelocityX());
        pacMan.setY(pacMan.getY() + pacMan.getVelocityY());*/

        if(keyHandler.up){
            y -= speed;
        }else if(keyHandler.down){
            y += speed;
        }else if(keyHandler.left){
            x -= speed;
        }else if(keyHandler.right){
            x += speed;
        }
    }

    public void draw(Graphics2D g2){
        /*g2.setColor(Color.BLUE);
        g2.fillRect(x, y, 30, 30);*/
        g2.drawImage(pacManSprite, x, y, 32, 32, null);
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
