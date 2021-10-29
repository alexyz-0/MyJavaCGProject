import java.awt.*;
import java.awt.image.*;
import java.io.Serial;
import javax.swing.*;

class B_Panel extends JPanel implements A_GraphicSystem {
    // constants
    @Serial
    private static final long serialVersionUID = 1L;
    private static final Font font = new Font("Arial",Font.PLAIN,24);


    // InputSystem is an external instance
    private B_InputSystem inputSystem = new B_InputSystem();
    private A_World       world       = null;


    // GraphicsSystem variables
    //
    private GraphicsConfiguration graphicsConf =
            GraphicsEnvironment.getLocalGraphicsEnvironment().
                    getDefaultScreenDevice().getDefaultConfiguration();
    private BufferedImage imageBuffer1;
    private BufferedImage imageBuffer2;
    private boolean whichBufferedImage = false;
    private Graphics2D      graphics;


    public B_Panel()
    {
        this.setSize(A_Const.WIDTH,A_Const.HEIGHT);
        imageBuffer1 = graphicsConf.createCompatibleImage(
                this.getWidth(), this.getHeight());
        imageBuffer2 = graphicsConf.createCompatibleImage(
                this.getWidth(), this.getHeight());
        graphics = (Graphics2D) imageBuffer1.getGraphics();

        // initialize Listeners
        this.addMouseListener(inputSystem);
        this.addMouseMotionListener(inputSystem);
        this.addKeyListener(inputSystem);
    }

    public void clear()
    { graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, A_Const.WIDTH, A_Const.HEIGHT);
    }


    public final void draw(A_GameObject object)
    {
        /*B_Shape shape = (B_Shape)object.shape;

        if(object.type()==A_Const.TYPE_AVATAR)
        {
            int x = (int)(object.x-shape.radius());
            int y = (int)(object.y-shape.radius());
            int d = (int)(shape.radius()*2);

            graphics.setColor(shape.color);
            graphics.fillOval(x, y, d, d);
            graphics.setColor(Color.DARK_GRAY);
            graphics.drawOval(x,y,d,d);

            int mx = (int)object.x;
            int my = (int)object.y;
            int ex = mx + (int)(shape.radius()*Math.cos(object.alfa));
            int ey = my + (int)(shape.radius()*Math.sin(object.alfa));

            graphics.setColor(Color.BLACK);
            graphics.drawLine(mx, my, ex, ey);
        }
        else if (object.type()==A_Const.TYPE_BRICK)
        {
            Gam11_Brick brick = (Gam11_Brick) object;
            graphics.setColor(shape.color);
            int x      = (int)object.x;
            int y      = (int)object.y;
            int width  = (int)(brick.x2-x);
            int height = (int)(brick.y2-y);
            graphics.fillRect(x, y, width, height);
        }*/

    }

    public Graphics2D getGraphicsToDraw() {
        return graphics;
    }

    public BufferedImage getBufferedImage() {
        return (whichBufferedImage) ? imageBuffer2 : imageBuffer1;
    }


    public void redraw() {
        if(whichBufferedImage){
            this.getGraphics().drawImage(imageBuffer2, 0, 0, this);
            graphics = (Graphics2D) imageBuffer1.getGraphics();
            whichBufferedImage = false;
        }else{
            this.getGraphics().drawImage(imageBuffer1, 0, 0, this);
            graphics = (Graphics2D) imageBuffer2.getGraphics();
            whichBufferedImage = true;
        }
    }

    public final A_InputSystem getInputSystem() { return inputSystem; }
    public final void setWorld(A_World world_)  {this.world = world_;}
}

