package GraphicsPainters;

import GameObjects.GameGraphics;

import java.awt.*;

/**
 * Created by Cedric on 09.07.2015.
 */
public class WinScreenPainter implements GraphicsPainter {
    private GraphicsPainter mainGame;

    public WinScreenPainter(GraphicsPainter mainGame) {
        this.mainGame = mainGame;
    }

    @Override
    public void paintGraphics(Graphics2D g2, GameGraphics graphics) {
        mainGame.paintGraphics(g2, graphics);
        g2.
    }
}
