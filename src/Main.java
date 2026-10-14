import javax.swing.*;
import java.awt.*;


/**
 * This is the main class where you run the project
 */

public class Main {

    public static final int SLEEP=20;
    public static final int WIDTH=600;
    public static final int HEIGHT=800;

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //KeyStuff comp=new KeyStuff();
        Space component = new Space();

        //frame.add(component);
        frame.addKeyListener(component);
        //frame.add(comp);
        frame.add(component);

        frame.setVisible(true);
        frame.requestFocus();

        long oldTime=System.currentTimeMillis();
        while (true) {
            long timeElapsed=System.currentTimeMillis()-oldTime;
            oldTime +=timeElapsed;
            component.updateTime(timeElapsed);
            frame.repaint();
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

