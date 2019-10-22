package GameObjects;

import Contants.ScoreConstants;

/**
 * Created by Tonye-Ce on 07.07.15.
 */
public class GameScore {

    private int score[];

    public GameScore() {
        score = new int[2];
        score[ScoreConstants.PLAYER_1] = 0;
        score[ScoreConstants.PLAYER_2] = 0;
    }

    public void score(int player) {
        score[player] = (score[player] + 1) % (ScoreConstants.WINNING_SCORE+1);
    }

    public int getWinner() {
        if (score[ScoreConstants.PLAYER_1] >= ScoreConstants.WINNING_SCORE) {
            return 1;
        }
        else if (score[ScoreConstants.PLAYER_2] >= ScoreConstants.WINNING_SCORE) {
            return 2;
        }
        else {
            return 0;
        }
    }
    
    public int getScore(int player) {
        return score[player];
    }
}
