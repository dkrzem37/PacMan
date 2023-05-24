import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Edible extends Thing{
    private BufferedImage ediblesSprite;
    public static Edible[][] edibleArray;

    public Edible(GameBoard gB){
        this.gB = gB;

        this.height = gB.getRowHeight();
        this.width = gB.getRowHeight();
        try {
            ediblesSprite = ImageIO.read(new File("src/Sprites/Edible.png"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getEdiblesSprite() {
        return ediblesSprite;
    }
}
