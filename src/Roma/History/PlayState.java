package Roma.History;

import Roma.*;
import Roma.Cards.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 15/05/12
 * Desc:
 */
public class PlayState {
   /*
    * Stores the State of the game at the beginning of each turn
    * Everything in order:
    * Stores Deck, Discard, Hands, DiceDiscs
    * Money, Victory Tokens
    * Action Dice Values
    */

    int turn;
    private ArrayList<String> deckData = new ArrayList<String>();
    private ArrayList<String> discardData = new ArrayList<String>();
    private ArrayList<ArrayList<String>> hand = new ArrayList<ArrayList<String>>();
    private String[][] discs = new String[RomaGame.MAX_PLAYERS][DiceDiscs.CARD_POSITIONS];
    private int[][] lives = new int[RomaGame.MAX_PLAYERS][DiceDiscs.CARD_POSITIONS];
    private int[] money = new int[RomaGame.MAX_PLAYERS];
    private int[] victory = new int[RomaGame.MAX_PLAYERS];
    private int victoryPool;
    private int[] actionDice = new int[DiceHolder.DICE_PER_PLAYER];
    private String[][][] fromPast;

    private ArrayList<ActionData> actionHistory = new ArrayList<ActionData>();

    public PlayState(PlayArea playArea, Player player){
        CardManager cardManager = playArea.getCardManager();
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        MoneyManager moneyManager = playArea.getMoneyManager();
        VictoryTokens victoryTokens = playArea.getVictoryTokens();

        ArrayList<String> playerHand = null;
        ArrayList<CardHolder> cardList = null;
        CardHolder[] playerActiveCards = null;
        ArrayList<Dice> freeDice = null;

        //turn number
        turn = playArea.getTurn();

        //store deck data
        cardList = cardManager.getPlayingDeck();
        for(Card card : cardList){
            deckData.add(card.getName());
        }

        //store discard data
        cardList = cardManager.getDiscardPile();
        for (Card card : cardList){
            discardData.add(card.getName());
        }

        for(int i = 0; i < RomaGame.MAX_PLAYERS; i++){

            //store hand data
            cardList = playArea.getPlayer(i).getHand();
            playerHand = new ArrayList<String>();
            for(Card card : cardList){
                playerHand.add(card.getName());
            }
            hand.add(playerHand);
            playerActiveCards = diceDiscs.getPlayerActives(i);

            //store active cards
            for(int j = 0; j < playerActiveCards.length; j++){
                if(playerActiveCards[j] != null){
                    discs[i][j] = playerActiveCards[j].getName();
                    lives[i][j] = playerActiveCards[j].countLives();
                } else {
                    discs[i][j] = "";
                }
            }

            //store money
            money[i] = moneyManager.getPlayerMoney(i);

            //store victory tokens
            victory[i] = victoryTokens.getPlayerTokens(i);
        }

        //store victory token pool value
        victoryPool = victoryTokens.getPoolTokens();

        //store action dice of current player
        freeDice = player.getFreeDice();
        for(int i = 0; i < freeDice.size(); i++){
            actionDice[i] = freeDice.get(i).getValue();
        }

        fromPast = diceDiscs.fromPastToString();

    }

    public void addActionHistory(ActionData actionData){
        actionHistory.add(actionData);
    }

    public String toString(){
        ArrayList<String> cardList;
        String output = "turn: " + turn
                + "\ndeckData: " + deckData
                + "\ndiscardData: " + discardData
                + "\n";
        for(int i = 0; i < RomaGame.MAX_PLAYERS; i++){
            cardList = hand.get(i);
            output += "playerID: " + i
                    + "\nhand: " + cardList
                    + "\ndiscs: " + Arrays.toString(discs[i])
                    + "\nlives: " + Arrays.toString(lives[i])
                    + "\nmoney: " + money[i]
                    + "\nvictory: " + victory[i]
                    + "\n";
        }
        output += "victoryPool: " + victoryPool
                + " \nactionDice: " + Arrays.toString(actionDice)
                + "\n";
        output += actionHistory.toString();
        return output;
    }

    public int getTurn() {
        return turn;
    }

    public ArrayList<String> getDeckData() {
        return deckData;
    }

    public ArrayList<String> getDiscardData() {
        return discardData;
    }

    public ArrayList<ArrayList<String>> getHand() {
        return hand;
    }

    public String[][] getDiscs() {
        return discs;
    }

    public int[][] getLives() {
        return lives;
    }

    public int[] getMoney() {
        return money;
    }

    public int[] getVictory() {
        return victory;
    }

    public int getVictoryPool() {
        return victoryPool;
    }

    public int[] getActionDice() {
        return actionDice;
    }

    public ArrayList<ActionData> getActionHistory() {
        return actionHistory;
    }

    public String[][][] getFromPast() {
        return fromPast;
    }
}
