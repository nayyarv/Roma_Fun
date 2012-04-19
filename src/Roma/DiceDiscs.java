package Roma;

import Roma.Cards.Card;

//TODO: Review dice disc class
//maybe create a separate dice disc class within dice discs
//maybe create moneyDisc and cardDisc within CardManager and MoneyManager and have a method that passes the object

//TODO: Create either a separate battle class or a private battle class within dice disc
// needs to handle environment effects and battle calculations
// or make it an activate card object that does all the logic check for cards

public class DiceDiscs {
    private boolean[][][] discs = new boolean[Roma.MAX_PLAYERS][Dice.DICE_SIZE][DiceHolder.DICE_PER_PLAYER];
    private Card[][] activeCards = new Card[Roma.MAX_PLAYERS][Dice.DICE_SIZE];
    private PlayArea playArea;
    private int[] moneyDisc = new int[DiceHolder.DICE_PER_PLAYER];
    private int[] cardDisc = new int[DiceHolder.DICE_PER_PLAYER];

    public DiceDiscs(PlayArea playArea) {
        this.playArea = playArea;
        cleanDiscs();
    }

    public void cleanDiscs() {
        for (int i = 0; i < Roma.MAX_PLAYERS; i++) {
            for (int j = 0; j < Dice.DICE_SIZE; j++) {
                for (int k = 0; k < DiceHolder.DICE_PER_PLAYER; k++) {
                    discs[i][j][k] = false;
                    moneyDisc[k] = 0;
                    cardDisc[k] = 0;
                }
            }
        }
    }

    public void layCard(int player, int position, Card newCard) {
        if (activeCards[player][position] != null) {
            playArea.getCardManager().discard(activeCards[player][position]);
        }
        activeCards[player][position] = newCard;
    }

    public void activateCard(int player, int position) {
        activeCards[player][position].activate(player);
    }
}
