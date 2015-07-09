package GraphicsPainters;

import Contants.*;
import GameObjects.GameGraphics;

import java.awt.*;

/**
 * Created by Cedric on 09.07.2015.
 */
public class MainGamePainter implements GraphicsPainter {
    GameGraphics graphics;

    @Override
    public void paintGraphics(Graphics2D g2, GameGraphics graphics) {
        this.graphics = graphics;

        // draw paddles
        for (int i = 0; i < 2; i++) {
            g2.fillRect((int) (graphics.getPaddles()[i].getPosition().getX() - PaddleConstants.WIDTH/2),
                    (int) (graphics.getPaddles()[i].getPosition().getY() - PaddleConstants.HEIGHT/2),
                    (int) PaddleConstants.WIDTH, (int) PaddleConstants.HEIGHT);
        }

        // draw ball
        g2.setColor(GraphicsConstants.COLOR_BALL);
        g2.fillOval((int) (graphics.getBall().getPosition().getX() - BallConstants.RADIUS),
                (int) (graphics.getBall().getPosition().getY() - BallConstants.RADIUS),
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
        for (int i = 0, n = graphics.getScore().getScore(player); i < n; i++) {
            drawDot(x + i*direction*(ScoreConstants.DOT_OFFSET_X + ScoreConstants.DOT_RADIUS*2), y, g2);
        }
    }

    private void drawDot(int x, int y,Graphics2D g2) {
        g2.fillOval(x - ScoreConstants.DOT_RADIUS, y - ScoreConstants.DOT_RADIUS,
                ScoreConstants.DOT_RADIUS*2, ScoreConstants.DOT_RADIUS*2);
    }
}
