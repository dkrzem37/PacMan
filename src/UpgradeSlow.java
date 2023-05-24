import javax.imageio.ImageIO;
import java.io.File;

public class UpgradeSlow extends Upgrade{
    public UpgradeSlow(PacMan pacMan, Enemy enemy) {
        super(pacMan, enemy);
        try {
            upgradeSprite = ImageIO.read(new File("src/Sprites/SlowUpgrade.png"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void effect() {
        if(pacMan.getSpeed() >1)
            pacMan.setSpeed(pacMan.getSpeed() - 1);
    }
}
