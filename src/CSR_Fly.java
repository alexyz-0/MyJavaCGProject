import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class CSR_Fly extends CSR_AnimatedObject {

    CSR_Fly(){
        super(A_Const.WIDTH, 0, 64, 64);

        y = ThreadLocalRandom.current().nextInt(50, A_Const.HEIGHT - 128 + 1);

        this.idleSprites = new BufferedImage[4];
        catchImages();

        icon = idleSprites[0];

        speed = 0.5;
    }

    @Override
    void catchImages() {
        try{
            this.idleSprites[0] = ImageIO.read(this.getClass().getResource("/Images/Fly_Enemy1.png"));
            this.idleSprites[1] = ImageIO.read(this.getClass().getResource("/Images/Fly_Enemy2.png"));
            this.idleSprites[2] = ImageIO.read(this.getClass().getResource("/Images/Fly_Enemy3.png"));
            this.idleSprites[3] = ImageIO.read(this.getClass().getResource("/Images/Fly_Enemy4.png"));
        }catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    void move(double diffSec) {
        x -= speed * diffSec;
    }

    boolean checkOnScreen(){
        return (this.x < 0);
    }
}
