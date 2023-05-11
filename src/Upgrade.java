import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Upgrade extends Thing{
    //public static Upgrade[] upgrades = new Upgrade[5];
    public static ArrayList<Upgrade> upgrades = new ArrayList<>();
    public enum Type{
        SLOW,
        FAST
    }
    PacMan pacMan;
    Type type;
    BufferedImage upgradeSprite;
    Enemy enemy;
    public Upgrade(Type type, PacMan pacMan, Enemy enemy) {
        this.type = type;
        this.pacMan = pacMan;
        this.enemy = enemy;
        this.setHeight(enemy.getHeight() / 3);
        this.setWidth(enemy.getWidth()/ 3);
        this.setX(enemy.getX() + enemy.getWidth() / 2 - this.getWidth() / 2);
        this.setY(enemy.getY() + enemy.getHeight() / 2 - this.getHeight() / 2);
    }


    public void draw(Graphics2D g2){
        g2.drawImage(upgradeSprite, x, y, width, height, null);
    }
    public void effect(){

    }


}
