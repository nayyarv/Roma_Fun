package Roma;

import Roma.Cards.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * File Name:
 * Creator: Andrew Lem & Varun Nayyar
 * Date: 19/03/12
 * Desc: This Object models the DiceDiscs of the game
 *
 */


//TODO: create a dice disc class that will handle temporary and passive defense changes

public class DiceDiscs {
    private static final boolean DEBUG = true;
    public static final int BRIBERY_POSITION = 6;
    public static final int CARD_POSITIONS = 7;
    public static final int TURRIS_LAID = 1;
    public static final int TURRIS_DISCARD = -1;

    private final PlayArea playArea;
    private PlayerInterface2 playerInterface;

    private CardHolder[][] activeCards = new CardHolder[Roma.MAX_PLAYERS][CARD_POSITIONS];
    //Has the card placed on each disc

    private ArrayList<ArrayList<Dice>> discs = new ArrayList<ArrayList<Dice>>();
    // Holds the dice on each disc

    private ArrayList<Dice> moneyDisc = new ArrayList<Dice>();
    // Holds the dice placed on the money disc

    private ArrayList<Dice> cardDisc = new ArrayList<Dice>();
    //Holds the dice placed on the cardDisc

    public DiceDiscs(PlayArea playArea) {
        this.playArea = playArea;
        playerInterface = playArea.getPlayerInterface();
        for(int i = 0; i < CARD_POSITIONS; i++){
                for(int j = 0; j < Roma.MAX_PLAYERS; j++){
                    activeCards[j][i] = null;
                }
            discs.add(new ArrayList<Dice>());
        }
    }

    //Returns an array of activeCards (Cards on DiceDiscs)
    public CardHolder[] getPlayerActives(int playerID){
        return activeCards[playerID];
    }

    //Gets all the cards of a certain type ~ Building or Character from the activeCards
    public ArrayList<CardHolder> setOfCards(Player player, String type){
        assert (type.equalsIgnoreCase(Card.BUILDING)||type.equalsIgnoreCase(Card.CHARACTER));
        int playerID = player.getPlayerID();
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        for (int i=0; i< activeCards[playerID].length;i++){
            if(activeCards[playerID][i]!=null){ //add if not null
                set.add(activeCards[playerID][i]);
                activeCards[playerID][i] = null; // remove cards
            }
        }
        return set;
    }

    public ArrayList<CardHolder> toList(int playerID){
        ArrayList<CardHolder> discList= new ArrayList<CardHolder>();
        Collections.addAll(discList, activeCards[playerID]);
        return discList;
    }

    //Clears all the dice placed on a disc
    //TODO: This function is not being used, is it deprecated?
    public void clearDice() {
        for(ArrayList<Dice> disc : discs){
            disc.clear();
        }
        moneyDisc.clear();
        cardDisc.clear();
    }

    //This function allows a Card to be placed on a dice Disc
    public void layCard(int playerID, int position, CardHolder newCard) {
        BattleManager battleManager = playArea.getBattleManager();
        if(newCard.getName().equalsIgnoreCase(Turris.NAME)){
            battleManager.modDefenseModPassive(playerID, TURRIS_LAID);
        }

        //TODO: Check position declarations
        position--;
        if(activeCards[playerID][position] !=  null){
            playArea.getCardManager().discard(activeCards[playerID][position]);
            //If we need to place over another card, the current one must be discarded
        }
        activeCards[playerID][position] = newCard;
    }

    public boolean activateCard(Player player, int position, Dice die) {
        BattleManager battleManager = playArea.getBattleManager();
        int playerID = player.getPlayerID();
        boolean activateEnabled = false;
        position--;

        //TODO: Change position-- to the player interface
        // Leave as is - make sure that it is handled in as few places as possible

        if(activeCards[playerID][position] != null){
            // There is a card there
            if(DEBUG){
                playerInterface.printOut("Card activating: " + activeCards[playerID][position].getName(), true);
            }
            activateEnabled = activeCards[playerID][position].isActivateEnabled();
            // Can it be activated(Eg Turris is passive)

            activateEnabled &= battleManager.checkBlock(playerID, position);
            // And Has it been blocked by another card?

            if(activateEnabled){
                discs.get(position).add(die);
                activateEnabled = activeCards[playerID][position].activate(player, position);
                if(activateEnabled){
                    discs.get(position).add(die);
                }
            } else {
                System.out.println("That card can't be activated");
            }
        } else {
            playerInterface.printOut("No card there!", true);
        }

        return activateEnabled;
    }

    public boolean useBriberyDisc(Player player, Dice die){
        boolean activated = false;
        MoneyManager moneyManager = playArea.getMoneyManager();
        int position = BRIBERY_POSITION;
        if(moneyManager.loseMoney(player.getPlayerID(), die.getValue())){
            activated = activateCard(player, position, die);
        }

        return activated;
    }

    public String getCardName(int player, int position){
        String cardName;

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

    public CardHolder getTargetCard(int playerID, int position){
        return activeCards[playerID][position];
    }

    public void discardTarget(int playerID, int position){
        CardManager cardManager = playArea.getCardManager();
        BattleManager battleManager = playArea.getBattleManager();

        CardHolder card = activeCards[playerID][position];
        if(card.getName().equalsIgnoreCase(Turris.NAME)){
            battleManager.modDefenseModPassive(playerID, TURRIS_DISCARD);
        }

        cardManager.discard(activeCards[playerID][position]);
        activeCards[playerID][position] = null;
    }

    public boolean checkAdjacent(int playerID, int position, String cardName){
        boolean adjacent = false;

        if(position > 1){
            if(activeCards[playerID][position - 1] != null && activeCards[playerID][position - 1].getName() == cardName){
                adjacent = true;
            }
        }
        if(position < 6){
            if(activeCards[playerID][position + 1] != null && activeCards[playerID][position + 1].getName() == cardName){
                adjacent = true;
            }
        }

        return adjacent;
    }

    public boolean checkAdjacentDown(int playerID, int position, String cardName){
        boolean adjacent = false;
        if(position > 1){
            if(activeCards[playerID][position - 1] != null && activeCards[playerID][position - 1].getName() == cardName){
                adjacent = true;
            }
        }
        return adjacent;
    }

    public boolean checkAdjacentUp(int playerID, int position, String cardName){
        boolean adjacent = false;
        if(position < 6){
            if(activeCards[playerID][position + 1] != null && activeCards[playerID][position + 1].getName() == cardName){
                adjacent = true;
            }
        }
        return adjacent;
    }

    public void returnTarget(int targetPlayerID, int position){
        Player targetPlayer = playArea.getPlayer(targetPlayerID);

        targetPlayer.addCardToHand(activeCards[targetPlayerID][position]);
        activeCards[targetPlayerID][position] = null;
    }

    public void clearPlayerDice(int playerID){
        ArrayList<Dice> disc;

        for(int i = 0; i < CARD_POSITIONS; i++){
            disc = discs.get(i);
            if(!disc.isEmpty()){
                int j = 0;
                for(Dice die : disc){
                    if(die.getPlayerID() == playerID){
                        disc.remove(j);
                    }
                    j++;
                }
            }
        }
    }

    public void addDiceToDisc(int targetDisc, Dice die){
        discs.get(targetDisc).add(die);
    }
}
