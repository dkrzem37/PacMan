import javax.imageio.ImageIO;
import java.io.File;


public class UpgradeGainLife extends Upgrade {
    public UpgradeGainLife(PacMan pacMan, Enemy enemy) {
        super( pacMan, enemy);
        try {
            upgradeSprite = ImageIO.read(new File("src/Sprites/HealthUpgrade.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void effect() {
        if(OknoGry.lives < 3) {
            OknoGry.lives++;
            OknoGry.lifeLabel.setText("Lives:" + OknoGry.lives);
        }
    }
}
