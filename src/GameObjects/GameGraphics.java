package GameObjects;

import Contants.*;
import Contants.WindowConstants;
import GraphicsPainters.GraphicsPainter;
import GraphicsPainters.MainGamePainter;
import GraphicsPainters.WinScreenPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Tonye-Ce on 07.07.15.
 */

public class GameGraphics extends JPanel implements ActionListener, KeyListener {

    private World world = new World(WindowConstants.WIDTH, WindowConstants.HEIGHT);
    private Timer stepper = new Timer(1/60 * 1000, this);
    private Ball ball;
    private Paddle[] paddles = new Paddle[2];
    private GameScore score;

    private static final GraphicsPainter mainGame = new MainGamePainter();
    private static final GraphicsPainter winScreen = new WinScreenPainter(mainGame);

    public static void main(String args[]) {
        // frame erstellen
        JFrame frame = new JFrame("Pong Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension((int) WindowConstants.WIDTH,
                (int) WindowConstants.HEIGHT + GraphicsConstants.INFO_HEIGHT));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // instanz von spiel erstellen
        GameGraphics graphics = new GameGraphics();

        // panel zu frame hinzuf�gen & eventhandling einstellen
        frame.add(graphics);
        frame.addKeyListener(graphics);

        // fenster sichtbar machen
        frame.setVisible(true);
    }

    public GameGraphics() {
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

        mainGame.paintGraphics(g2, this);
    }

    private void step() {
        world.update();

        if (score.getWinner() != 0) {
            stepper.stop();
        }

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == stepper) {
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

    public GameScore getScore() {
        return score;
    }

    public Paddle[] getPaddles() {
        return paddles;
    }

    public Ball getBall() {
        return ball;
    }
}
