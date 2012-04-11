package Roma;

public class Roma {
    public final static int MAX_PLAYERS = 2;
    public final static int PLAYER_ONE = 0;
    public final static int PLAYER_TWO = 1;

    private boolean gameOver = false;

    public Roma() {
    }

    public void endGame() {
        gameOver = true;
    }

    public boolean getGameOver() {
        return gameOver;
    }
}
