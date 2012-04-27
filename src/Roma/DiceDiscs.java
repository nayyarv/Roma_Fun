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
    public static final int BRIBERY_POSITION = 6;
    public static final int CARD_POSITIONS = 7;
    private final PlayArea playArea;

    private Card[][] activeCards = new Card[Roma.MAX_PLAYERS][CARD_POSITIONS];
    private ArrayList<ArrayList<Dice>> discs = new ArrayList<ArrayList<Dice>>();
    private ArrayList<Dice> moneyDisc = new ArrayList<Dice>();
    private ArrayList<Dice> cardDisc = new ArrayList<Dice>();

    public DiceDiscs(PlayArea playArea) {
        this.playArea = playArea;
        for(int i = 0; i < CARD_POSITIONS; i++){
                for(int j = 0; j < Roma.MAX_PLAYERS; j++){
                    activeCards[j][i] = null;
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
        position--;
        playArea.getCardManager().discard(activeCards[player][position]);
        activeCards[player][position] = newCard;
    }

    public void activateCard(int player, int position, Dice die) {
        position--;
        discs.get(position).add(die);
        activeCards[player][position].activate(player);
    }

    public void useBriberyDisc(int player, Dice die){
        int position = BRIBERY_POSITION;
        activateCard(player, position, die);
    }

    public String getCardName(int player, int position){
        String cardName = null;

        if(activeCards[player][position] != null){
            cardName = activeCards[player][position].getName();
        } else {
            cardName = "";
        }

        return cardName;
    }

    public ArrayList<Dice> checkForDice(int playerID, int position){
        ArrayList<Dice> dicePresent = new ArrayList<Dice>();
        ArrayList<Dice> disc = discs.get(position);

        if(!disc.isEmpty()){
            for(Dice die : disc){
                if(die.getPlayerID() == playerID){
                    dicePresent.add(die);
                }
            }
        }

        return dicePresent;
    }

    public void useMoneyDisc(int playerID, Dice chosenDie) {
        MoneyManager moneyManager = playArea.getMoneyManager();

        moneyDisc.add(chosenDie);
        moneyManager.gainMoney(playerID, chosenDie.getValue());
    }

    public void useDrawDisc(int playerID, Dice chosenDie) {
        cardDisc.add(chosenDie);
    }
}
