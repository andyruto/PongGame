import Contants.*;
import Contants.WindowConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Tonye-Ce on 07.07.15.
 */

public class Graphics extends JPanel implements ActionListener, KeyListener {

    World world = new World(WindowConstants.WIDTH, WindowConstants.HEIGHT);

    Timer stepper = new Timer(1/60 * 1000, this);

    Ball ball;
    Paddle[] paddles = new Paddle[2];
    GameScore score;


    public static void main(String args[]) {
        // frame erstellen
        JFrame frame = new JFrame("Pong Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension((int) WindowConstants.WIDTH,
                (int) WindowConstants.HEIGHT + GraphicsConstants.INFO_HEIGHT));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // instanz von spiel erstellen
        Graphics graphics = new Graphics();

        // panel zu frame hinzuf�gen & eventhandling einstellen
        frame.add(graphics);
        frame.addKeyListener(graphics);

        // fenster sichtbar machen
        frame.setVisible(true);
    }

    public Graphics() {
        super();

        paddles = world.getPeddles();
        ball = world.getBall();
        score = world.getScore();

        stepper.start();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        // graphics Objekt für neue fuktionen zu Graphics2D casten
        Graphics2D g2 = (Graphics2D) g;

        // draw paddles
        for (int i = 0; i < 2; i++) {
            g2.fillRect((int) (paddles[i].getPosition().getX() - PaddleConstants.WIDTH/2),
                    (int) (paddles[i].getPosition().getY() - PaddleConstants.HEIGHT/2),
                    (int) PaddleConstants.WIDTH, (int) PaddleConstants.HEIGHT);
        }

        // draw ball
        g2.setColor(GraphicsConstants.COLOR_BALL);
        g2.fillOval((int) (ball.getPosition().getX() - BallConstants.RADIUS),
                (int) (ball.getPosition().getY() - BallConstants.RADIUS),
                (int) BallConstants.RADIUS * 2, (int) BallConstants.RADIUS * 2);

        // draw info area
            // background
        g2.setColor(GraphicsConstants.INFO_COLOR);
        g2.fillRect(0, (int) WindowConstants.WORLD_HEIGHT,
                (int) WindowConstants.WORLD_WIDTH, GraphicsConstants.INFO_HEIGHT);
            // player1 score
        drawScore(ScoreConstants.DOT_OFFSET_X + ScoreConstants.DOT_RADIUS,
                (int) (WindowConstants.WORLD_HEIGHT + GraphicsConstants.INFO_HEIGHT/2), g2, 1, ScoreConstants.PLAYER_2);
            // player2 score
        drawScore((int) WindowConstants.WORLD_WIDTH - ScoreConstants.DOT_RADIUS - ScoreConstants.DOT_OFFSET_X,
                (int) (WindowConstants.WORLD_HEIGHT + GraphicsConstants.INFO_HEIGHT/2), g2, -1, ScoreConstants.PLAYER_1);
    }

    private void drawScore(int x, int y, Graphics2D g2, int direction, int player) {
        // draw empty dots
        g2.setColor(ScoreConstants.COLOR_EMPTY);
        for (int i = 0; i < ScoreConstants.WINNING_SCORE; i++) {
            drawDot(x + i*direction*(ScoreConstants.DOT_OFFSET_X + ScoreConstants.DOT_RADIUS*2), y, g2);
        }

        // draw full dots
        g2.setColor(ScoreConstants.COLOR_FULL);
        for (int i = 0, n = score.getScore(player); i < n; i++) {
            drawDot(x + i*direction*(ScoreConstants.DOT_OFFSET_X + ScoreConstants.DOT_RADIUS*2), y, g2);
        }
    }

    private void drawDot(int x, int y,Graphics2D g2) {
        g2.fillOval(x - ScoreConstants.DOT_RADIUS, y - ScoreConstants.DOT_RADIUS,
                ScoreConstants.DOT_RADIUS*2, ScoreConstants.DOT_RADIUS*2);
    }

    private void step() {
        world.update();
        repaint();

        int winner = score.getWinner();
        if (winner != 0) {
            JOptionPane.showMessageDialog(this, "Congrats!!!\n" +
                    "Player" + winner +", you won the game " + ".");

            score.reset();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == stepper) {
            step();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        world.keyPressed(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        world.keyReleased(e.getKeyChar());
    }
}
