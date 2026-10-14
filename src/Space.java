import java.awt.*;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;


import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * This is the black background and also where you call in the objects too put onto of the background
 * It also implements all of the on screen drawings words etc...
 * It also handels the Key events so the spaceship and other aspects that involve key presses work
 */

public class Space extends JComponent implements KeyListener {

    SpaceShip ship1, ship2;
    Stars star1;
    Moon moon1;
    Astroid as1;

    private SpaceShip player;
    private Astroid enemy;
    private int score;
    private int lScore;
    private int lives = 3; // Start with 3 lives
    private boolean isGameOver = false;
    private int highScore = 0;

    //new code
    private final ScoreClient scoreClient = new ScoreClient();
    private volatile List<ScoreClient.Entry> leaderboard = new ArrayList<>();
    private volatile String leaderboardStatus = "";
    private String playerName = null;
    private boolean scoreSubmitted = false;

    private boolean isStarted = false;
    public static final int WIDTH=600;
    public static final int HEIGHT=800;
    private static final int UIPadding=10;
    private static final int ScoreYOffset=40;
    private static final int LivesYOffset=90;
    private static final int HighScoreXOffset=80;
    private static final int StartGameX=175;
    private static final int StartGameY=250;
    private static final int GameOverY=400;
    private static final int RestartX=210;
    private static final int RestartY=460;

    private static final int Ship1X= 320;
    private static final int Ship1Y= 400;
    private static final int ShipWidth= 100;
    private static final int ShipHeight= 60;

    private static final int StarCount= 300;

    private static final int MoonX=100;
    private static final int MoonY= 100;
    private static final int MoonWidth=100;
    private static final int MoonHeight=100;
    private static final int AsteroidStartX=150;
    private static final int AsteroidStartY=0;
    private static final int BaseScore=0;

    private static final int AsteroidResetY=-50;

    private static final int StartingLives=3;
    private static final int PointsPerHit=10;


    Random random = new Random();

    private ArrayList<Bullet> bullets = new ArrayList<>();

    /**
     * This is where you call in the objects and give them a location based on their x,y,w,h
     */

    public Space() {
        ship1 = new SpaceShip(Ship1X, Ship1Y, ShipWidth, ShipHeight);
        star1 = new Stars(StarCount, WIDTH, HEIGHT);
        moon1 = new Moon(MoonX, MoonY, MoonWidth, MoonHeight);

        //player = new SpaceShip(50, 50, 50, 50);
        enemy = new Astroid(AsteroidStartX, AsteroidStartY);
        score = BaseScore;

        // Ensure KeyListener works
        setFocusable(true);
        addKeyListener(this);

    }

    /**
     * This Parameter draws the background and puts text onto the screen, using the graphics 2D method through g2.
     *
     * @param g the <code>Graphics</code> object to protect , Graphics g2 is graphics2d which draws,colors,
     * and makes the shape of the spaceship
     */


    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;


        //black background -----
        Rectangle square = new Rectangle();
        square.setBounds(0, 0, WIDTH,HEIGHT);
        Color Black = new Color(0, 0, 0);
        g2.setColor(Black);
        g2.fill(square);

        //HUD ------
        g2.setColor(Color.yellow);
        g.drawString("Score:" + score, UIPadding, HEIGHT-ScoreYOffset);
        g.drawString("Lives: " + lives, UIPadding,HEIGHT-LivesYOffset); // Displaying the lives
        g2.drawString("Best: " + highScore, WIDTH-HighScoreXOffset, HEIGHT-ScoreYOffset);

        //GAME OBJECTS ------
        star1.draw(g2);
        ship1.draw(g2);
        moon1.draw(g2);
        enemy.draw(g2);

        player = ship1;

        //DRAW ALL BULLETS ------
        for (Bullet b : bullets) {
            b.draw(g2);
        }
        //START SCREEN ------
        if(!isStarted) {
            Font newFont = new Font("Arial", Font.PLAIN, 50);
            g2.setFont(newFont);
            g2.setColor(Color.WHITE);
            g2.drawString("Start Game", StartGameX, StartGameY);
        }

        //might want to move background to a separate class and call its draw() method
        //just like the player and enemy objects

        if (isGameOver) {
            g2.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black overlay
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setColor(Color.RED);
            g2.setFont(new Font("Arial", Font.BOLD, 60));
            g2.drawString("GAME OVER", StartGameX, GameOverY);

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.drawString("Press 'R' to Restart", RestartX, RestartY);
            g2.setColor(Color.YELLOW);
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.drawString(leaderboardStatus, RestartX, RestartY + 50);
            // v - new lines of code
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 16));
            int rank = 1;
            for (ScoreClient.Entry entry : leaderboard) {
                g2.drawString(String.format("%2d. %-20s %5d", rank, entry.name, entry.score),
                        RestartX - 60, RestartY + 50 + rank * 24);
                rank++;
            }
        }
    }

    private static final long serialVersionUID = 1L;

    private Space background;

    /**
     * This method checks to see if your current score is higher then the current one and if it is higher
     * it then replaces that as the new highscore
     * @param currentScore - Keeps track of the current high score
     */

    public void updateHighScoreRecursive(int currentScore) {
        // Base Case: If highScore is already equal to or greater than the current score, stop.
        if (highScore >= currentScore) {
            return;
        }

        // Recursive Step: Increment highScore and call itself again
        highScore++;
        updateHighScoreRecursive(currentScore);
    }
    /** New Code idk if works
     */
    /** Asks for a name once per run of the program. Runs on the Swing thread. */
    private String askName() {
        final String[] result = {"PLAYER"};
        try {
            SwingUtilities.invokeAndWait(() -> {
                String typed = JOptionPane.showInputDialog(this, "Enter your name for the leaderboard:");
                if (typed != null && !typed.isBlank()) {
                    result[0] = typed.trim();
                }
            });
        } catch (Exception e) {
            // keep the default name
        }
        return result[0];
    }

    /** Sends the final score to the server, then loads the top 10. Runs on its own thread. */
    private void submitScoreAndLoadLeaderboard() {
        final int finalScore = score;
        leaderboardStatus = "Saving score...";

        new Thread(() -> {
            if (playerName == null) {
                playerName = askName();
            }
            boolean saved = scoreClient.submitScore(playerName, finalScore);
            leaderboard = scoreClient.getTopScores();

            if (!saved || leaderboard.isEmpty()) {
                leaderboardStatus = "Leaderboard offline";
            } else {
                leaderboardStatus = "TOP 10";
            }
        }).start();
    }



    /**
     * This method checks to see if the game is still running based on the lives left
     * It also moves the astroid down the screen, then resets it if the astroid is destroyed or goes off screen
     * Also updates the highscore of the game
     * @param elapsedTime
     */
    public void updateTime(long elapsedTime) {


        star1.move(this.getHeight());

        /**if (isGameOver) {
            return;
        }
        */
        if (isGameOver) {
            if (!scoreSubmitted) {
                scoreSubmitted = true;
                submitScoreAndLoadLeaderboard();
            }
            return;
        }

        if (!isStarted || isGameOver) {
            return;
        }

        enemy.move(elapsedTime, this.getWidth(), this.getHeight());

        if (enemy.y > this.getHeight()) {
            lives--; // Lose a life

            int maxAllowedX = getWidth() - enemy.w;

            int newX = (int)(Math.random() * (maxAllowedX));

            enemy.x = newX;
            enemy.exactX = newX;

            enemy.y = AsteroidResetY;       // Reset to top
            enemy.exactY = AsteroidResetY;

            if (lives <= 0) {
                isGameOver = true;
                return;

            }

        }


        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.move();

            // Check if bullet hit the asteroid
            if (b.getBounds().intersects(enemy.getBounds())) {
                score += PointsPerHit;
                bullets.remove(i);
                i--;

                updateHighScoreRecursive(score);

                int newX = random.nextInt(550);
                enemy.x = newX;
                enemy.exactX = newX;
                enemy.y = AsteroidResetY;
                enemy.exactY = AsteroidResetY;

                continue;
            }

            // Remove if off screen
            if (!b.isActive()) {
                bullets.remove(i);
                i--;
            }
        }

        if (ship1.getBounds().intersects(enemy.getBounds())) {

            lives--; // Take a life instead of resetting score

            // Reset asteroid immediately so it doesn't mess up my lives
            int newX = random.nextInt(550);
            enemy.x = newX;
            enemy.exactX = newX;
            enemy.y = AsteroidResetY;
            enemy.exactY = AsteroidResetY;

            // Check if that was your last life
            if (lives <= 0) {
                isGameOver = true;
            }
        }
    }


        @Override
        public void keyTyped (KeyEvent e){
            // probably want to leave this blank
            // this method will be called whenever a key is pressed AND released

        }

        @Override
        public void keyPressed (KeyEvent e) {

            //this method called whenever a key is pressed (or pressed and held down)
            //pass key information to any objects that need to update based on the key
            //you could also figure out which key it is and call only those objects who need
            //to know about that particular key

            // If game hasn't started, any key press starts it
            if (!isStarted) {
                isStarted = true;
                repaint(); // Refresh to hide the text
                return;
            }


            if (isGameOver && e.getKeyCode() == KeyEvent.VK_R) {
                lives = StartingLives;              // Reset lives
                //v - 3 new lines of code
                scoreSubmitted = false;
                leaderboard = new ArrayList<>();
                bullets.clear();   // also fixes the old bug where bullets survived a restart

                //score += PointsPerHit;          // Reset score
                score =0;
                isGameOver = false; // Start the game again

                // Reset asteroid position so it doesn't start off-screen
                enemy.y = AsteroidResetY;
                enemy.exactY = AsteroidResetY;
                enemy.x = (int) (Math.random() * (WIDTH-19));
                enemy.exactX = enemy.x;

                repaint(); // Force the screen to refresh immediately
                return;    // Exit the method so we don't move the ship yet
            }


            if (!isGameOver) {
                player.move(e);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    // Spawn at the tip of the ship
                    bullets.add(new Bullet(ship1.x + (ship1.w / 2) - 2, ship1.y));
                }
            }
        }

        @Override
        public void keyReleased (KeyEvent e){
            //called whenever a key is released
            // TODO Auto-generated method stub
        }
    }








