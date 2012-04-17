package Roma;

import Roma.Cards.Card;

//TODO: Review dice disc class

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
