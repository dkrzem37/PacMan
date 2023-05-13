import javax.imageio.ImageIO;
import java.io.File;


public class UpgradeEnemyFast extends Upgrade {
    public UpgradeEnemyFast(PacMan pacMan, Enemy enemy) {
        super( pacMan, enemy);
        try {
            upgradeSprite = ImageIO.read(new File("src/Sprites/UpgradeEnemyFast.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void effect() {
        if (Enemy.enemies[0].getSpeed() < 6) {
            for(Enemy e: Enemy.enemies){
                e.setSpeed(e.getSpeed() + 1);
            }

        }
    }
}
