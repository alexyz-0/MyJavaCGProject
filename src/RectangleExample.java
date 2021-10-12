import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Iterator;
import javax.swing.JFrame;


public class RectangleExample extends JFrame {


    public static void main(String[] args) {
        new RectangleExample();
    }
    public RectangleExample(){
        super("Meine 2D-Grafik");
        setSize(500,500);
        setVisible(true);
    }
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        int[] x = {80, 160, 160};
        int[] y = {100, 50, 150};
        int[] x2 = new int[3];
        int[] y2 = new int[3];

        AffineTransform at = new AffineTransform(0.707, 0.707, -0.707, 0.707, 200, 0);
        Iterator<Integer> it1 = Arrays.stream(x).iterator();
        Iterator<Integer> it2 = Arrays.stream(y).iterator();
        int count = 0;
        while(it1.hasNext() && it2.hasNext()){
            Point p = new Point(it1.next(), it2.next());
            at.transform(p, p);
            x2[count] = p.x;
            y2[count] = p.y;
            count++;
        }

        Polygon pol = new Polygon(x, y, 3);
        Polygon polTransformed = new Polygon(x2, y2, 3);
        g2d.setColor(Color.blue);
        g2d.fillPolygon(pol);
        g2d.setColor(Color.black);
        g2d.fillPolygon(polTransformed);

    }

}
