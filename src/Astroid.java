import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * The Astroid class represents a falling asteroid object in the game.
 * Each asteroid has a position, velocity, size, and optional image.
 * It moves vertically down the screen and can bounce horizontally.
 */

public class Astroid {
    int x;
    int y;
    int w;
    int h;
    private static final double MAX_SPEED = 0.125;
    private double xVel, yVel;
    public double exactX, exactY;
    private int width, height; //width and height of dot
    private BufferedImage astroidPng;
    private static final double Scale = 0.15;
    private static final double MinSpeed=0.1;
    private static final double SpeedRange=0.2;
    private static final int ResetOffset=50;

    /**
     * Constructs an Astroid object at a given starting position.
     * Loads the asteroid image and initializes its size and velocity.
     *
     * @param x - the starting x-coordinate
     * @param y0 - the starting y-coordinate
     */

    public Astroid(int x, int y0) {
        this.x = x;
        this.exactX = x;
        this.exactY = y0;
        y = y0;

        URL imageUrl = Astroid.class.getResource("/Astroid.png");
        try {
            if (imageUrl != null) {
                astroidPng = ImageIO.read(imageUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (astroidPng != null) {
            w = (int) (astroidPng.getWidth() * Scale);
            h = (int) (astroidPng.getHeight() * Scale);
        } else {
            w = 80;   // fallback size so the grey circle still works
            h = 80;
        }

        xVel = 0;
        yVel = (Math.random() * SpeedRange + MinSpeed);
    }

    /**
     * Draws the asteroid on the screen.
     * If an image is available, it is drawn. Otherwise, a fallback ellipse is used.
     *
     * @param g2 - The Graphics2D object used for rendering
     */

    public void draw(Graphics2D g2) {

        if (astroidPng != null) {
            g2.drawImage(astroidPng, x, y, w - ResetOffset, h - ResetOffset, null);
        } else {

            Ellipse2D.Double Astroid = new Ellipse2D.Double(x, y, w, h);
            Color Grey4 = new Color(100, 100, 100);
            g2.setColor(Grey4);
            g2.fill(Astroid);
        }
    }

    /**
     *  Returns the bounding rectangle used for collision detection.
     *  Padding is applied to make collisions more accurate visually.
     *
     * @return - A Rectangle representing the asteroid's hitbox
     */

    public Rectangle getBounds() {


        int padding = 10;
        return new Rectangle(x + padding, y + padding, w - (padding * 2), h - (padding * 4));
    }

    /**
     * Updates the asteroid's position based on elapsed time and velocity.
     * Handles horizontal bouncing and resets the asteroid when it leaves the screen.
     *
     * @param diff - The time difference (in milliseconds) since the last update
     * @param maxX - The maximum horizontal boundary of the screen
     * @param maxY - The maximum vertical boundary of the screen
     * @return - True if the asteroid reset after leaving the screen, false otherwise
     */

    public boolean move(long diff, int maxX, int maxY) {

        if (diff > 100) diff = 16;

        exactX += xVel * diff;
        exactY += yVel * diff;

        y = (int) exactY;
        x = (int) exactX;

        // SIDE BOUNCING (Keep this)
        if (x > maxX - w || x < 0) {
            xVel = -xVel;
        }


        if (y > maxY + ResetOffset) {
            exactY = -h - ResetOffset; // Start just above the screen
            y = (int) exactY;


            // This accounts for the width of the asteroid AND the window's frame.
            int innerWidthLimit = 565 - w;

            if (innerWidthLimit > 10) {
                exactX = 10 + (Math.random() * innerWidthLimit);
            } else {
                exactX = 10;
            }

            x = (int) exactX;
            yVel = Math.abs(yVel);
            xVel = 0; // Keeping it straight down


            return true;

        }
        return false;
    }
}