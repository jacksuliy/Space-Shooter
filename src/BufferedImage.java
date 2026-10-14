import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This class loads an image into Java and stores it as a BufferedImage
 * so it can be drawn onto a graphical component.
 */

public class BufferedImage {

    private java.awt.image.BufferedImage image;

    /**
     * Constructs a image based off the file we loaded
     * @param path , the path from the image to get it to load
     */
    public BufferedImage(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("we got problems");
            e.printStackTrace();
        }
    }

    /**
     * Draws the image onto the screen using the given Graphics object.
     *
     * @param g - The Graphics object used for drawing
     * @param width - The desired width of the image
     * @param height - The desired height of the image
     */
    public void draw(Graphics g, int width, int height) {
        if (image != null) {
            g.drawImage(image, 0, 0, 20, 20, null);
        }
    }

    /**
     * Returns the width of the loaded image.
     *
     * @return - The image width, or 0 if no image is loaded
     */

    public int getWidth() {
        if (image != null) {
            return image.getWidth();
        } else {
            return 0;
        }
    }

    /**
     * Returns the height of the loaded image.
     *
     * @return - The image height, or 0 if no image is loaded
     */

    public int getHeight() {
        if (image != null) {
            return image.getHeight();
        } else {
            return 0;
        }
    }
}






