import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CSR_GameOverScreen {

    BufferedImage YouDeadImage;
    private double animationTimer = 0;
    private boolean isInitialized = false;
    private boolean isShown = false;
    private boolean canContinue = false;

    CSR_GameOverScreen(){
        try{
            this.YouDeadImage = ImageIO.read(this.getClass().getResource("/Images/GameOver.png"));
        }catch (IOException e) { e.printStackTrace(); }
    }

    void triggerDeath() {
        isInitialized = true;
    }

    boolean isInitialized(){
        return isInitialized;
    }

    void waitTilShow(double diffSec){
        animationTimer += diffSec;
        if(animationTimer > 3000) isShown = true;
        if(animationTimer > 5000) canContinue = true;
    }

    boolean isShown() {
        return isShown;
    }

    boolean canContinue() {
        return canContinue;
    }

    protected void draw(Graphics2D g2D) {
        g2D.drawImage(YouDeadImage, 0, 0, A_Const.WIDTH, A_Const.HEIGHT, null);
    }

}
