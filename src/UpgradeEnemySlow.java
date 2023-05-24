import javax.imageio.ImageIO;
import java.io.File;


public class UpgradeEnemySlow extends Upgrade {
    public UpgradeEnemySlow(PacMan pacMan, Enemy enemy) {
        super( pacMan, enemy);
        try {
            upgradeSprite = ImageIO.read(new File("src/Sprites/UpgradeEnemySlow.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void effect() {
        if (Enemy.enemies[0].getSpeed() > 1) {
            for(Enemy e: Enemy.enemies){
                e.setSpeed(e.getSpeed() - 1);
            }

        }
    }
}
