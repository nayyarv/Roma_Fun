package Roma;

import Roma.Cards.Card;

import java.util.ArrayList;

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

    public boolean activateCard(Player player, int position, Dice die) {
        BattleManager battleManager = playArea.getBattleManager();

        position--;
        boolean activateEnabled = activeCards[player.getPlayerID()][position].isActivateEnabled();

        activateEnabled &= battleManager.checkBlock(player.getPlayerID(), position);

        if(activateEnabled){
            discs.get(position).add(die);
            activateEnabled = activeCards[player.getPlayerID()][position].activate(player, position);
        } else {
            System.out.println("That card can't be activated");
        }

        return activateEnabled;
    }

    public void useBriberyDisc(Player player, Dice die){
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
