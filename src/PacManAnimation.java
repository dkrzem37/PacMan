import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PacManAnimation extends Thread{
    private PacMan pacMan;
    private BufferedImage pacManNormal, pacManDownOpen, pacManLeftOpen, pacManRightOpen, pacManUpOpen;
    public PacManAnimation(PacMan pacMan) {
        this.pacMan = pacMan;
        try {
            pacManNormal = ImageIO.read(new File("src/Sprites/PacManNormal.png"));
            pacManDownOpen = ImageIO.read(new File("src/Sprites/PacManDownOpen.png"));
            pacManLeftOpen = ImageIO.read(new File("src/Sprites/PacManLeftOpen.png"));
            pacManRightOpen = ImageIO.read(new File("src/Sprites/PacManRightOpen.png"));
            pacManUpOpen = ImageIO.read(new File("src/Sprites/PacManUpOpen.png"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int i = 0;
        while(this != null){
            if(Thread.interrupted()){
                try {
                    pacMan.setPacManSprite(ImageIO.read(new File("src/Sprites/PacManNormal.png")));
                } catch (IOException e) {
                    System.out.println("interrupted PacMan animation");
                    break;
                }
            }
            if(i == 0){
                if(pacMan.down)
                    pacMan.setPacManSprite(pacManDownOpen);
                else if(pacMan.left)
                pacMan.setPacManSprite(pacManLeftOpen);
                else if(pacMan.right)
                    pacMan.setPacManSprite(pacManRightOpen);
                else if(pacMan.up)
                    pacMan.setPacManSprite(pacManUpOpen);
                i++;
            }else{
                pacMan.setPacManSprite(pacManNormal);
                i=0;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("interrupted PacMan animation");
                try {
                    pacMan.setPacManSprite(ImageIO.read(new File("src/Sprites/PacManNormal.png")));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            }
        }
    }
}
