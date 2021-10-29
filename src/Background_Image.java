import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

class Backgound_Image {
    private BufferedImage bgImage;
    private BufferedImage waterImage;
    private int mode = 0;
    private TexturePaint paint;
    private TexturePaint stonePaint;
    private int highestTileWidth = 256;
    private float xOffset = 0;     //for animation

    public Backgound_Image() {
        GraphicsConfiguration graphicsConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        bgImage = graphicsConf.createCompatibleImage(A_Const.WIDTH+highestTileWidth, A_Const.HEIGHT);
        BufferedImage tempImage = null;
        try{
            this.waterImage = ImageIO.read(this.getClass().getResource("/Images/Water_Background.png"));
            tempImage = ImageIO.read(this.getClass().getResource("/Images/Stones v2.png"));
        }catch (IOException e) { e.printStackTrace(); }

        paint = new TexturePaint(this.waterImage, new Rectangle(0, 0, 64, 64));
        stonePaint = new TexturePaint(tempImage, new Rectangle(0, 0, 256, 256));
    }

    void setMode(){
        mode++;
        if(mode > 1) mode = 0;
        switch (mode) {
            case 0 -> {
                try {
                    this.waterImage = ImageIO.read(this.getClass().getResource("/Images/Water_Background.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                paint = new TexturePaint(this.waterImage, new Rectangle(0, 0, 64, 64));
            }
            case 1 -> {
                try {
                    this.waterImage = ImageIO.read(this.getClass().getResource("/Images/Water_Background v2.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                paint = new TexturePaint(this.waterImage, new Rectangle(0, 0, 64, 64));
            }
        }
    }


    protected void animate(double diffSec){
        //    256 : 4000
        //xOffset : diffSec
        xOffset += diffSec / 7.5;
        while(xOffset >= highestTileWidth) xOffset -= highestTileWidth;
    }
    // draw the images
    protected void draw(Graphics2D g2D) {
        Graphics2D graphics = (Graphics2D) bgImage.getGraphics();
        graphics.setPaint(paint);
        graphics.fillRect(0, 0, A_Const.WIDTH+highestTileWidth, A_Const.HEIGHT);
        graphics.setPaint(stonePaint);
        graphics.translate(0, 30);
        graphics.fillRect(0, A_Const.HEIGHT-256, A_Const.WIDTH+highestTileWidth, A_Const.HEIGHT);
        graphics.translate(0, -30);
        g2D.drawImage(bgImage, 0, 0, A_Const.WIDTH, A_Const.HEIGHT, (int)xOffset, 0, A_Const.WIDTH+(int)xOffset, A_Const.HEIGHT, null);
        //g2D.drawImage(this.image, 0, 0, null);
    }
}