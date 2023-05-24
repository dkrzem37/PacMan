import javax.imageio.ImageIO;
import java.io.File;

public class UpgradeFast extends Upgrade{
    public UpgradeFast(PacMan pacMan, Enemy enemy) {
        super(pacMan, enemy);
        try {
            upgradeSprite = ImageIO.read(new File("src/Sprites/FastUpgrade.png"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void effect() {
        if(pacMan.getSpeed() < 6)
            pacMan.setSpeed(pacMan.getSpeed() + 1);
    }
}
