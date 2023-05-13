import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class EnemyAnimation extends Thread{
        private Enemy enemy;
        private BufferedImage enemyNormal, enemyLeft, enemyRight;
        public EnemyAnimation(Enemy enemy) {
            this.enemy = enemy;
            try {
                enemyNormal = ImageIO.read(new File("src/Sprites/EnemySprite.png"));
                enemyLeft = ImageIO.read(new File("src/Sprites/EnemySpriteLeft.png"));
                enemyRight = ImageIO.read(new File("src/Sprites/EnemySpriteRight.png"));

            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            int i = 0;
            while(!Thread.interrupted()){
                if(i == 0){
                    enemy.setEnemySprite(enemyNormal);
                    i++;
                }else if(i == 1){
                    enemy.setEnemySprite(enemyLeft);
                    i++;
                }else if(i ==2){
                    enemy.setEnemySprite(enemyRight);
                    i = 0;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("interrupted Enemy animation");
                    break;
                }
            }
        }


}
