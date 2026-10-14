import java.awt.*;
import java.awt.geom.Ellipse2D;


/**
 * This class makes a good moon
 */


public class Moon {
    int x;
    int y;
    int w;
    int h;

    /**
     *
     * @param x0 - The x0 value gives the top left coordinate going side to side
     * @param y0 - The y0 value gives the top left coordinate going up and down
     * @param w0 - The w0 value gives the width of the box
     * @param h0 - The h0 value gives the width of the box
     */

    public Moon(int x0, int y0, int w0, int h0){
        x = x0;
        y = y0;
        w = w0;
        h = h0;
    }

    /**
     * This Parameter draws the moon, using the graphics 2D method through g2.
      * @param g2 , Graphics g2 is graphics2d which draws,colors,and makes the shape of the moon
     */

    public void draw(Graphics2D g2) {

        //moon
        Ellipse2D.Double moon = new Ellipse2D.Double(x-100, y-100, w-10, h-10);
        Color OffWhite = new Color(200, 200, 200);
        g2.setColor(OffWhite);
        g2.fill(moon);

        //moon craters
        Ellipse2D.Double crater = new Ellipse2D.Double(x-85, y-83, w-75, h-75);
        Color Grey = new Color(150, 150, 150);
        g2.setColor(Grey);
        g2.fill(crater);

        Ellipse2D.Double crater2 = new Ellipse2D.Double(x-44, y-44, w-83, h-83);
        Color Gray = new Color(143, 143, 143);
        g2.setColor(Gray);
        g2.fill(crater2);

        Ellipse2D.Double crater3 = new Ellipse2D.Double(x-82, y-77, w-86, h-86);
        Color Grey2 = new Color(125, 125, 125);
        g2.setColor(Grey2);
        g2.fill(crater3);

        Ellipse2D.Double crater4 = new Ellipse2D.Double(x-82, y-42, w-79, h-79);
        Color Grey3 = new Color(120, 120, 120);
        g2.setColor(Grey3);
        g2.fill(crater4);


        //Arc
        Color Ring = new Color(255, 255, 0);
        g2.setColor(Ring);
        g2.setStroke(new BasicStroke(15));
        g2.drawArc(x-225, y-225, w+150, h+150, 270, 90);

        Color Ring2 = new Color(200, 200, 0);
        g2.setColor(Ring2);
        g2.setStroke(new BasicStroke(10));
        g2.drawArc(x-225, y-225, w+150, h+150, 270, 90);

        Color Ring3 = new Color(150, 150, 0);
        g2.setColor(Ring3);
        g2.setStroke(new BasicStroke(6));
        g2.drawArc(x-225, y-225, w+150, h+150, 270, 90);

        Color Ring4 = new Color(100, 100, 0);
        g2.setColor(Ring4);
        g2.setStroke(new BasicStroke(3));
        g2.drawArc(x-225, y-225, w+150, h+150, 270, 90);

        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(1));
    }
}
