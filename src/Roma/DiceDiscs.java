package Roma;

import Roma.Cards.*;

import java.util.ArrayList;

//TODO: create a dice disc class that will handle temporary and passive defense changes

public class DiceDiscs {
    private static final boolean DEBUG = true;
    public static final int BRIBERY_POSITION = 6;
    public static final int CARD_POSITIONS = 7;
    public static final int TURRIS_LAID = 1;
    public static final int TURRIS_DISCARD = -1;

    private final PlayArea playArea;

    private CardBase[][] activeCardBases = new CardBase[Roma.MAX_PLAYERS][CARD_POSITIONS];
    private ArrayList<ArrayList<Dice>> discs = new ArrayList<ArrayList<Dice>>();
    private ArrayList<Dice> moneyDisc = new ArrayList<Dice>();
    private ArrayList<Dice> cardDisc = new ArrayList<Dice>();

    public DiceDiscs(PlayArea playArea) {
        this.playArea = playArea;
        for(int i = 0; i < CARD_POSITIONS; i++){
                for(int j = 0; j < Roma.MAX_PLAYERS; j++){
                    activeCardBases[j][i] = null;
                }
            discs.add(new ArrayList<Dice>());
        }
    }

    public CardBase[] getPlayerActives(int playerID){
        return activeCardBases[playerID];
    }

    public ArrayList<CardBase> setOfCards(Player player, String type){
        assert (type.equalsIgnoreCase(CardBase.BUILDING)||type.equalsIgnoreCase(CardBase.CHARACTER));
        int playerID = player.getPlayerID();
        ArrayList<CardBase> set = new ArrayList<CardBase>();
        for (int i=0; i< activeCardBases[playerID].length;i++){
            if(activeCardBases[playerID][i]!=null){ //add if not null
                set.add(activeCardBases[playerID][i]);
                activeCardBases[playerID][i] = null; // remove cards
            }
        }
        return set;
    }

    public void clearDice() {
        for(ArrayList<Dice> disc : discs){
            disc.clear();
        }
        moneyDisc.clear();
        cardDisc.clear();
    }

    public void layCard(int playerID, int position, CardBase newCardBase) {
        BattleManager battleManager = playArea.getBattleManager();
        if(newCardBase.getName() == Turris.NAME){
            battleManager.modDefenseModPassive(playerID, TURRIS_LAID);
        }

        //TODO: Check position declarations
        position--;
        if(activeCardBases[playerID][position] !=  null){
            playArea.getCardManager().discard(activeCardBases[playerID][position]);
        }
        activeCardBases[playerID][position] = newCardBase;
    }

    public boolean activateCard(Player player, int position, Dice die) {
        BattleManager battleManager = playArea.getBattleManager();
        boolean activateEnabled = false;
        position--;

        //TODO: Change position-- to the player interface

        if(activeCardBases[player.getPlayerID()][position] != null){
            if(DEBUG){
                System.out.println("Card activating: " + activeCardBases[player.getPlayerID()][position].getName());
            }
            activateEnabled = activeCardBases[player.getPlayerID()][position].isActivateEnabled();

            activateEnabled &= battleManager.checkBlock(player.getPlayerID(), position);

            if(activateEnabled){
                discs.get(position).add(die);
                activateEnabled = activeCardBases[player.getPlayerID()][position].activate(player, position);
                if(activateEnabled){
                    discs.get(position).add(die);
                }
            } else {
                System.out.println("That card can't be activated");
            }
        } else {
            System.out.println("No card there!");
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

        if(activeCardBases[player][position] != null){
            cardName = activeCardBases[player][position].getName();
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

    public CardBase getTargetCard(int playerID, int position){
        return activeCardBases[playerID][position];
    }

    public void discardTarget(int playerID, int position){
        CardManager cardManager = playArea.getCardManager();
        BattleManager battleManager = playArea.getBattleManager();

        CardBase cardBase = activeCardBases[playerID][position];
        if(cardBase.getName() == Turris.NAME){
            battleManager.modDefenseModPassive(playerID, TURRIS_DISCARD);
        }

        cardManager.discard(activeCardBases[playerID][position]);
        activeCardBases[playerID][position] = null;
    }

    public boolean checkAdjacent(int playerID, int position, String cardName){
        boolean adjacent = false;

        if(position > 1){
            if(activeCardBases[playerID][position - 1] != null && activeCardBases[playerID][position - 1].getName() == cardName){
                adjacent = true;
            }
        }
        if(position < 6){
            if(activeCardBases[playerID][position + 1] != null && activeCardBases[playerID][position + 1].getName() == cardName){
                adjacent = true;
            }
        }

        return adjacent;
    }

    public boolean checkAdjacentDown(int playerID, int position, String cardName){
        boolean adjacent = false;
        if(position > 1){
            if(activeCardBases[playerID][position - 1] != null && activeCardBases[playerID][position - 1].getName() == cardName){
                adjacent = true;
            }
        }
        return adjacent;
    }

    public boolean checkAdjacentUp(int playerID, int position, String cardName){
        boolean adjacent = false;
        if(position < 6){
            if(activeCardBases[playerID][position + 1] != null && activeCardBases[playerID][position + 1].getName() == cardName){
                adjacent = true;
            }
        }
        return adjacent;
    }

    public void returnTarget(int targetPlayerID, int position){
        Player targetPlayer = playArea.getPlayer(targetPlayerID);

        targetPlayer.addCardToHand(activeCardBases[targetPlayerID][position]);
        activeCardBases[targetPlayerID][position] = null;
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
