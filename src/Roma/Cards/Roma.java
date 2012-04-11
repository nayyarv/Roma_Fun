package Roma.Cards;

public class Roma {
    public static final int MAX_PLAYERS = 2;
	
	private boolean gameOver = false;
	
	public Roma(){
	}
	
	public void endGame(){
		gameOver = true;;
	}
	
	public boolean getGameOver(){
		return gameOver;
	}
}
