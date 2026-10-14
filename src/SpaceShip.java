import java.awt.*;
import java.awt.Graphics2D;
//import java.awt.Graphics;
import java.awt.Color;
//import javax.swing.JComponent;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
//import java.awt.geom.Arc2D;
//import java.awt.geom.Rectangle2D;
//import java.awt.Font;
import java.awt.Polygon;
//import java.util.concurrent.BlockingDeque;

/**
 * The SpaceShip class will build the spaceship for the game using "public class spaceship" which assigns the x,y,w,h.
 * The "public SpaceShip" which gives a set value to those coordinates.
 * The "public void draw(Graphics g2)" which has all the components to make the spaceship, including the color
 * shape, and size of all the different objects attacthed too it.
 */

public class SpaceShip {
    int x;
    int y;
    int w;
    int h;

    private final int speed = 50;

    /**
     *
     * @param x  the x value gives the top left coordinate of the area going side too side
     * @param y0 the y0 value gives the top left coordinate going up and down
     * @param w0 the w0 value gives the width of the box
     * @param h0 the h0 value gives the width of the box
     */

    public SpaceShip(int x, int y0, int w0, int h0) {
        this.x = x;
        y = y0;
        w = w0;
        h = h0;
    }

    /**
     * This Parameter draws the spaceship, using the graphics 2D method through g2.
     * @param g2 , Graphics g2 is graphics2d which draws,colors,and makes the shape of the spaceship
     * as well as the other aspects like rings and guns.
     */
    public void draw(Graphics2D g2) {


        Rectangle box = new Rectangle(x, y, w, h);
        g2.setColor(Color.BLACK);
        g2.draw(box);

        //THRUSTERS Flames

        Ellipse2D.Double Flame = new Ellipse2D.Double(x + 21, y + 60, w - 76, h - 52);
        Color Flame1 = new Color(225, 60, 60);
        g2.setColor(Flame1);
        g2.fill(Flame);

        Ellipse2D.Double Flame2 = new Ellipse2D.Double(x + 55, y + 60, w - 76, h - 52);
        g2.setColor(Flame1);
        g2.fill(Flame2);

        //THRUSTERS

        int[] triX = new int[3];
        triX[0] = x + 23;
        triX[1] = x + 33;
        triX[2] = x + 43;

        int[] triY = new int[3];
        triY[0] = y + 63;
        triY[1] = y + 50;
        triY[2] = y + 63;

        Polygon tri = new Polygon(triX, triY, 3);
        g2.setStroke(new BasicStroke(2));
        Color GreyThruster = new Color(50, 50, 50);
        g2.setColor(GreyThruster);
        g2.fill(tri);
        g2.draw(tri);


        int[] triX2 = new int[3];
        triX2[0] = x + 57;
        triX2[1] = x + 67;
        triX2[2] = x + 77;

        int[] triY2 = new int[3];
        triY2[0] = y + 63;
        triY2[1] = y + 50;
        triY2[2] = y + 63;

        Polygon tri2 = new Polygon(triX2, triY2, 3);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(GreyThruster);
        g2.fill(tri2);
        g2.draw(tri2);


        int[] triX3 = new int[3];
        triX3[0] = x + 25;
        triX3[1] = x + 32;
        triX3[2] = x + 41;

        int[] triY3 = new int[3];
        triY3[0] = y + 60;
        triY3[1] = y + 47;
        triY3[2] = y + 60;

        Polygon tri3 = new Polygon(triX3, triY3, 3);
        g2.setStroke(new BasicStroke(2));
        Color GreyThruster2 = new Color(80, 80, 80);
        g2.setColor(GreyThruster2);
        g2.fill(tri3);
        g2.draw(tri3);


        int[] triX4 = new int[3];
        triX4[0] = x + 58;
        triX4[1] = x + 67;
        triX4[2] = x + 76;

        int[] triY4 = new int[3];
        triY4[0] = y + 60;
        triY4[1] = y + 47;
        triY4[2] = y + 60;

        Polygon tri4 = new Polygon(triX4, triY4, 3);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(GreyThruster2);
        g2.fill(tri4);
        g2.draw(tri4);

        //Thruster Rings
        Color Glowing = new Color(237, 237, 200);


        Ellipse2D.Double Ring = new Ellipse2D.Double(x + 18, y + 72, w - 70, h - 45);
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Glowing);
        g2.draw(Ring);

        Ellipse2D.Double Ring2 = new Ellipse2D.Double(x + 52, y + 72, w - 70, h - 45);
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Glowing);
        g2.draw(Ring2);


        //Second Layer Of rings
        Color Glowing2 = new Color(220, 220, 180);

        Ellipse2D.Double Ring3 = new Ellipse2D.Double(x + 17, y + 92, w - 65, h - 40);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Glowing2);
        g2.draw(Ring3);

        Ellipse2D.Double Ring4 = new Ellipse2D.Double(x + 51, y + 92, w - 65, h - 40);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Glowing2);
        g2.draw(Ring4);


        //Third Layer of rings
        Color Glowing3 = new Color(207, 207, 170);

        Ellipse2D.Double Ring5 = new Ellipse2D.Double(x + 13, y + 120, w - 55, h - 35);
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Glowing3);
        g2.draw(Ring5);


        Ellipse2D.Double Ring6 = new Ellipse2D.Double(x + 47, y + 120, w - 55, h - 35);
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Glowing3);
        g2.draw(Ring6);


        //Space ship
        Ellipse2D.Double Ship = new Ellipse2D.Double(x, y, w, h);
        Color Grey4 = new Color(100, 100, 100);
        g2.setColor(Grey4);
        g2.fill(Ship);

        Color SBlue = new Color(0, 200, 200);


        //Ships Circle top
        Color ShipTop = new Color(210, 210, 255);


        //startAngle is 0 and arcAngle is 180, the curve of the purple section on top
        g2.setColor(ShipTop);
        g2.fillArc(x + 26, y + 5, w - 53, h - 10, 0, 180);

        //startAngle is 0 and arcAngle is -180, the curve of the purple section on bottom
        g2.setColor(ShipTop);
        g2.fillArc(x + 26, y + 20, w - 53, h - 45, 0, -180);


        //Ship Guns
        Color Gun = new Color(60, 60, 60);

        Line2D.Double SG1 = new Line2D.Double(x, y + 30, x - 10, y + 30);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Gun);
        g2.draw(SG1);

        Line2D.Double SG2 = new Line2D.Double(x - 10, y + 30, x - 10, y + 10);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Gun);
        g2.draw(SG2);

        Line2D.Double SG3 = new Line2D.Double(x + 100, y + 30, x + 110, y + 30);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Gun);
        g2.draw(SG3);

        Line2D.Double SG4 = new Line2D.Double(x + 110, y + 30, x + 110, y + 10);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Gun);
        g2.draw(SG4);

        Line2D.Double TG1 = new Line2D.Double(x + 20, y + 10, x + 20, y - 40);
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Gun);
        g2.draw(TG1);

        Line2D.Double TG2 = new Line2D.Double(x + 80, y + 10, x + 80, y - 40);
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Gun);
        g2.draw(TG2);


        //Gun base

        Rectangle GunBase = new Rectangle(x + 17, y + 6, w - 93, h - 55);
        g2.draw(GunBase);
        g2.setColor(Gun);
        g2.fill(GunBase);


        Rectangle GB2 = new Rectangle(x + 76, y + 6, w - 93, h - 55);
        g2.draw(GB2);
        g2.setColor(Gun);
        g2.fill(GB2);


        Ellipse2D.Double ShipLight = new Ellipse2D.Double(x + 10, y + 15, w - 95, h - 53);
        g2.setColor(SBlue);
        g2.fill(ShipLight);

        Ellipse2D.Double ShipLight2 = new Ellipse2D.Double(x + 10, y + 37, w - 95, h - 53);
        g2.setColor(SBlue);
        g2.fill(ShipLight2);


        Ellipse2D.Double SL4 = new Ellipse2D.Double(x + 83, y + 15, w - 95, h - 53);
        g2.setColor(SBlue);
        g2.fill(SL4);

        Ellipse2D.Double SL5 = new Ellipse2D.Double(x + 83, y + 37, w - 95, h - 53);
        g2.setColor(SBlue);
        g2.fill(SL5);


        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(2));

    }

    /**
     *  This method gathers key input to move the ship
     * @param key - This param uses user key inputs to decide how the spaceships moves
     */

    // TRYING TO ADD MOVEMENT
    public void move(KeyEvent key) {
        int gameHeight = 800; // Example boundary
        int gameWidth = 670;  // Example boundary

        if (key.getKeyCode() == KeyEvent.VK_UP) {
            // Check if top of spaceship is within bounds
            if (y - speed >= 0) {
                y -= speed;
            }
        } else if (key.getKeyCode() == KeyEvent.VK_DOWN) {
            // Check if bottom of spaceship is within bounds
            if (y + h + speed <= gameHeight) {
                y += speed;
            }
        } else if (key.getKeyCode() == KeyEvent.VK_LEFT) {
            // Check if left of spaceship is within bounds
            if (x - speed >= 0) {
                x -= speed;
            }
        } else if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Check if right of spaceship is within bounds
            if (x + w + speed <= gameWidth) {
                x += speed;
            }
        }
    }

    /**
     * getBounds gathers the size of the spaceship and returns it
     * @return - The size of the spaceship
     */

    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

    /**
     * This method checks to see if the SpaceShip is in contact with the astroid
     * @param enemy - The enemy is the astroid
     * @return - Returns if they are colliding
     */

    /**public Boolean whereTFisTheAstroid(Astroid enemy) {
        Ellipse2D.Double shipBody = new Ellipse2D.Double(x, y, w, h);


        if (shipBody.contains(enemy.getBounds())) {
            return true;
        }
        else return false;
    }*/
}
