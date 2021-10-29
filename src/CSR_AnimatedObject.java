import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

abstract class CSR_AnimatedObject extends A_GameObject {

    protected BufferedImage[] idleSprites;
    private double idleAnimationTime = 100;
    private double animationTimer = 0;

    protected double speed;

    public CSR_AnimatedObject(double x, double y, double width, double height)
    {
        super(x, y, width, height, null);
        icon = null;
    }

    abstract void catchImages();

    abstract void move(double diffSec);

    void animate(double diffSec){
        animationTimer += diffSec;
        int l = idleSprites.length;
        for(int i = l-1; i > 0; i -= 1){
            if(animationTimer >= idleAnimationTime*i)
            icon = idleSprites[i];
        }
        if(animationTimer >= idleAnimationTime*l){
            icon = idleSprites[0];
            animationTimer = 0;
        }
    }

    protected void draw(Graphics2D g2D) {
        g2D.drawImage(icon, (int) x, (int) y, (int) width, (int) height, null);
        //g2D.drawImage(this.image, 0, 0, null);
    }

    @Override
    int object_type() {
        return A_Const.TYPE_AVATAR;
    }
}
