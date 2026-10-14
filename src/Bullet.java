import java.awt.*;

/**
 * This class makes the bullet and controls the speed as which it is shot out
 */

public class Bullet {
    private int x, y;
    private final int speed = 20; // Gotta be faster than the ship
    private final int width = 5;
    private final int height = 15;
    private boolean active = true;

    /**
     *
     * @param startX - The starting X point of the bullet
     * @param startY - The starting Y point of the bullet
     */

    public Bullet(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    /**
     * Controls how fast the bullet moves
     * and marks it for removal once its off the screen
     */

    public void move() {
        y -= speed; // Move up the screen
        if (y < -height) {
            active = false; // Mark for removal once off-screen
        }
    }

    /**
     * Draws the Bullet
     * @param g2 - g2 is used draw the bullet
     */

    public void draw(Graphics2D g2) {
        g2.setColor(Color.YELLOW);
        g2.fillOval(x, y, width, height);
    }

    /**
     * Gets the bounds of the bullet
     * @return - Returns the bounds
     */

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Checks to see if the bullet is still active
     * @return - Returns active
     */

    public boolean isActive() {
        return active;
    }

}