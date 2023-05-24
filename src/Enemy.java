import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Enemy extends Thing implements Runnable{
    private boolean isEdible = false;
    public static Enemy[] enemies= new Enemy[4];
    private final String lock = "a";
    private BufferedImage enemySprite;
    private Thread enemyAnimation;
    public Enemy(GameBoard gB){
        this.gB = gB;
        if(gB.getSize1()>30){
            this.speed = 2;
        }else{
            this.speed = 3;
        }
        up = true;
        try {
            enemySprite = ImageIO.read(new File("src/Sprites/EnemySprite.png"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void movement(){
        setDirection();
        if(up){
            int temp = y;
            y -= 1;
            if(checkColision()) {
                y = temp;
            }
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
    }
    private void setDirection() {
        Rectangle enemyRectDown = new Rectangle(this.x, this.y + this.height/2, this.width, this.height);
        Rectangle enemyRectUp = new Rectangle(this.x, this.y - this.height/2, this.width, this.height);
        Rectangle enemyRectLeft = new Rectangle(this.x - this.width/2, this.y, this.width, this.height);
        Rectangle enemyRectRight = new Rectangle(this.x + this.width/2, this.y, this.width, this.height);
        int rand = (int)(Math.random()*4);
        boolean potentialUp, potentialDown,potentialLeft, potentialRight;
        switch(rand){
            case 0:
                if(down)
                    break;
                potentialUp = true;
                potentialUp = changePotentialDir(enemyRectUp);
                if(potentialUp) {
                    up = potentialUp;
                    left = false;
                    down = false;
                    right = false;
                }
                break;
            case 1:
                if(up)
                    break;
                potentialDown = true;
                potentialDown = changePotentialDir(enemyRectDown);

                if(potentialDown) {
                    down = potentialDown;
                    left = false;
                    up = false;
                    right = false;
                }
                break;
            case 2:
                if(right)
                    break;
                potentialLeft = true;
                potentialLeft = changePotentialDir(enemyRectLeft);

                if(potentialLeft) {
                    left = potentialLeft;
                    up = false;
                    down = false;
                    right = false;
                }
                break;
            case 3:
                if(left)
                    break;
                potentialRight = true;
                potentialRight = changePotentialDir(enemyRectRight);

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

    public void draw(Graphics2D g2){
        g2.drawImage(enemySprite, x, y, width, height, null);
    }

    public boolean isEdible() {
        return isEdible;
    }

    public void setEdible(boolean edible) {
        isEdible = edible;
    }

    @Override
    public void run() {
        int counter = 0;

        while(!Thread.interrupted()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                break;
            }
            for (Enemy e : Enemy.enemies) {
                if (Math.random() < 0.25) {
                    switch ((int) (Math.random() * 5)) {
                        case 0:
                            UpgradeSlow slow = new UpgradeSlow(gB.getPacMan(), e);
                            synchronized (lock) {
                                Upgrade.upgrades.add(slow);
                                counter++;
                                System.out.println("spawnowanie slow " + counter);
                            }
                            break;
                        case 1:
                            UpgradeFast fast = new UpgradeFast(gB.getPacMan(), e);
                            synchronized (lock) {
                                Upgrade.upgrades.add(fast);
                            }
                            break;
                        case 2:
                            UpgradeEnemyFast enemyFast = new UpgradeEnemyFast(gB.getPacMan(), e);
                            synchronized (lock) {
                                Upgrade.upgrades.add(enemyFast);
                            }
                            break;
                        case 3:
                            UpgradeEnemySlow enemySlow = new UpgradeEnemySlow(gB.getPacMan(), e);
                            synchronized (lock) {
                                Upgrade.upgrades.add(enemySlow);
                            }
                            break;
                        case 4:
                            UpgradeGainLife gainLife = new UpgradeGainLife(gB.getPacMan(), e);
                            synchronized (lock) {
                                Upgrade.upgrades.add(gainLife);
                            }
                            break;
                    }
                }
            }
        }
    }
    private boolean changePotentialDir(Rectangle enemyRect){
        for (int column = 0; column < gB.getSize1(); column++) {
            for (int row = 0; row < gB.getSize1(); row++) {
                if (gB.getSize1() % 2 == 1) {
                    if (!((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && (column % 2 == 1 || row % 2 == 1))) {
                        Rectangle r = gB.getCellRect(row, column, false);
                        if (enemyRect.intersects(r)) {
                            return false;
                        }
                    }
                } else {
                    if (!((row != 0 && row != gB.getSize1() - 1 && column != 0 && column != gB.getSize1() - 1) && ((column % 2 == 1 && column < gB.getSize1() / 2) || (row % 2 == 1 && row < gB.getSize1() / 2) || (column % 2 == 0 && column > (gB.getSize1() / 2)) || (row % 2 == 0 && row > (gB.getSize1() / 2))))) {
                        Rectangle r = gB.getCellRect(row, column, false);
                        if (enemyRect.intersects(r)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public BufferedImage getEnemySprite() {
        return enemySprite;
    }

    public void setEnemySprite(BufferedImage enemySprite) {
        this.enemySprite = enemySprite;
    }

    public Thread getEnemyAnimation() {
        return enemyAnimation;
    }

    public void setEnemyAnimation(Thread enemyAnimation) {
        this.enemyAnimation = enemyAnimation;
    }
}
