package Roma;

import Roma.Cards.Card;

import java.util.ArrayList;

//TODO: Review dice disc class
//maybe create a separate dice disc class within dice discs
//maybe create moneyDisc and cardDisc within CardManager and MoneyManager and have a method that passes the object

//TODO: Create either a separate battle class or a private battle class within dice disc
// needs to handle environment effects and battle calculations
// or make it an activate card object that does all the logic check for cards

public class DiceDiscs {
    public static final int CARD_POSITIONS = 7;
    private final PlayArea playArea;

    private ArrayList<Card[]> activeCards = new ArrayList<Card[]>();
    private ArrayList<ArrayList<Dice>> discs = new ArrayList<ArrayList<Dice>>();
    private ArrayList<Dice> moneyDisc = new ArrayList<Dice>();
    private ArrayList<Dice> cardDisc = new ArrayList<Dice>();

    public DiceDiscs(PlayArea playArea) {
        this.playArea = playArea;
        for(int i = 0; i < CARD_POSITIONS; i++){
            activeCards.add(new Card[Roma.MAX_PLAYERS]);
                for(int j = 0; j < Roma.MAX_PLAYERS; j++){
                    activeCards.get(i)[j] = null;
                }
            discs.add(new ArrayList<Dice>());
        }
    }

    public void clearDice() {
        for(ArrayList<Dice> disc : discs){
            disc.clear();
        }
        moneyDisc.clear();
        cardDisc.clear();
    }

    public void layCard(int player, int position, Card newCard) {
        activeCards.get(position)[player] = newCard;
    }

    public void activateCard(int player, int position) {
        activeCards.get(position)[player].activate(player);
    }

    public String getCardName(int player, int position){
        String cardName = null;

        if(activeCards.get(position)[player] != null){
            cardName = activeCards.get(position)[player].getName();
        } else {
            cardName = "";
        }

        return cardName;
    }
}
