
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.Color;
import java.awt.geom.AffineTransform;

 /**
  * The Stars class generates and renders a scrolling starfield background.
  * Stars are divided into three sizes (small, medium, large) to create
  * A parallax effect, where closer stars move faster than distant ones.
  *
  * This class handles both the drawing and movement of stars.
 */

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Constructs a starfield with a specified number of stars and screen dimensions.
 * Stars are randomly distributed across the screen and grouped by size.
 *
 * @param stars  the total number of stars to generate
 * @param width  the width of the screen area
 * @param height the height of the screen area
 */

public class Stars {
    int n_stars, width, height;
    int[] xBig, yBig;
    int[] xSmall, ySmall;
    int[] xBiggest, yBiggest;

    private static final int SmallStarSpeed=1;
    private static final int MediumStarSpeed=2;
    private static final int LargeStarSpeed=3;

    public Stars(int stars, int width, int height) {
        n_stars = stars;
        this.width = width;
        this.height = height;

        int n_big = n_stars / 4;          // Medium-sized stars
        int n_small = n_stars - 2 * n_big; // Small stars
        int n_biggest = n_big;            // Largest stars

        xBig = new int[n_big];
        yBig = new int[n_big];

        xSmall = new int[n_small];
        ySmall = new int[n_small];

        xBiggest = new int[n_biggest];
        yBiggest = new int[n_biggest];

        // Small stars
        for (int i = 0; i < n_small; i++) {
            xSmall[i] = (int) (Math.random() * width);
            ySmall[i] = (int) (Math.random() * height);
        }

        // Medium stars
        for (int i = 0; i < n_big; i++) {
            xBig[i] = (int) (Math.random() * width);
            yBig[i] = (int) (Math.random() * height);
        }

        // Biggest stars
        for (int i = 0; i < n_biggest; i++) {
            xBiggest[i] = (int) (Math.random() * width);
            yBiggest[i] = (int) (Math.random() * height);
        }
    }

    /**
     * Draws all stars onto the screen using the provided Graphics2D context.
     * Applies a slight rotation to give stars a more dynamic appearance.
     *
     @param g2 the Graphics2D object used for rendering
     */

    public void draw(Graphics2D g2) {
        Color starColor = new Color(240, 240, 200);
        g2.setColor(starColor);

        // Save original transform
        AffineTransform original = g2.getTransform();

        // Draw small stars
        for (int i = 0; i < xSmall.length; i++) {
            g2.setTransform(original); // reset rotation
            g2.rotate(Math.PI / 4, xSmall[i], ySmall[i]);
            g2.fillRect(xSmall[i], ySmall[i], 1, 1);
        }

        // Draw medium stars
        for (int i = 0; i < xBig.length; i++) {
            g2.setTransform(original);
            g2.rotate(Math.PI / 4, xBig[i], yBig[i]);
            g2.fillRect(xBig[i], yBig[i], 2, 2);
        }

        // Draw biggest stars
        for (int i = 0; i < xBiggest.length; i++) {
            g2.setTransform(original);
            g2.rotate(Math.PI / 4, xBiggest[i], yBiggest[i]);
            g2.fillRect(xBiggest[i], yBiggest[i], 3, 3);
        }

        // Restore original transform at the end
        g2.setTransform(original);
    }

    /**
     * Updates the position of all stars to simulate downward movement.
     * Stars that move off the bottom of the screen are repositioned at the top
     * with a new random horizontal position.
     *
     * Movement speeds vary by star size to create a depth (parallax) effect:
     * - Small stars move slowest (farthest away)
     * - Medium stars move at moderate speed
     * - Large stars move fastest (closest)
     *
     * @param screenHeight the height of the screen boundary
     */

    public void move(int screenHeight) {
        //Move Small stars (Slowest - looks very far away)
        for (int i = 0; i < ySmall.length; i++) {
            ySmall[i] += SmallStarSpeed;
            if (ySmall[i] > screenHeight) {
                ySmall[i] = 0;
                xSmall[i] = (int) (Math.random() * width);
            }
        }

        //Move Medium stars (Medium speed)
        for (int i = 0; i < yBig.length; i++) {
            yBig[i] += MediumStarSpeed;
            if (yBig[i] > screenHeight) {
                yBig[i] = 0;
                xBig[i] = (int) (Math.random() * width);
            }
        }

        //Move Biggest stars (Fastest - looks closest)
        for (int i = 0; i < yBiggest.length; i++) {
            yBiggest[i] += LargeStarSpeed;
            if (yBiggest[i] > screenHeight) {
                yBiggest[i] = 0;
                xBiggest[i] = (int) (Math.random() * width);
            }
        }
    }
}