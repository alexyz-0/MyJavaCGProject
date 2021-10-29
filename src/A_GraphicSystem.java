import java.awt.*;
import java.awt.image.BufferedImage;

interface A_GraphicSystem {
    // prepare to draw a new Screen
    void clear();

    // draw ONE GameObject on the Screen
    void draw(A_GameObject dot);

    // display the completed Screen
    void redraw();

    //Return graphics to draw on
    Graphics2D getGraphicsToDraw();

    //Return graphics to draw on
    BufferedImage getBufferedImage();

    // set world
    void setWorld(A_World world);
}