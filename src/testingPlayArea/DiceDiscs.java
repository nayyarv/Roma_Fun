package testingPlayArea;

public class DiceDiscs {
	private boolean[][][] discs = new boolean[PlayArea.MAX_PLAYERS][DiceHolder.DICE_SIZE][DiceHolder.DICE_PER_PLAYER];
	private int[] moneyDisc = new int[DiceHolder.DICE_PER_PLAYER];
	private int[] cardDisc = new int[DiceHolder.DICE_PER_PLAYER];
	private Card[][] activeCards = new Card[PlayArea.MAX_PLAYERS][DiceHolder.DICE_SIZE];
	private PlayArea playArea;
	
	public DiceDiscs(PlayArea playArea){
		this.playArea = playArea;
		cleanDiscs();
	}
	
	public void cleanDiscs(){
		for(int i = 0; i < PlayArea.MAX_PLAYERS; i++){
			for(int j = 0; j < DiceHolder.DICE_SIZE; j++){
				for(int k = 0; k < DiceHolder.DICE_PER_PLAYER; k++){
					discs[i][j][k] = false;
					moneyDisc[k] = 0;
					cardDisc[k] = 0;
				}
			}
		}
	}
	
	public void layCard(int player, int position, Card  newCard){
		if(activeCards[player][position] != null){
			//playArea.discard(activeCards[player][position]);
		}
		activeCards[player][position] = newCard;
	}
	
	public void activateCard(int player, int position){
		activeCards[player][position].activate(player);
	}
}
