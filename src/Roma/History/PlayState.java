package Roma.History;

import Roma.*;
import Roma.Cards.Card;
import Roma.Cards.CardHolder;

import java.util.ArrayList;

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
    ArrayList<String> deckData = new ArrayList<String>();
    ArrayList<String> discardData = new ArrayList<String>();
    ArrayList<ArrayList<String>> hand = new ArrayList<ArrayList<String>>();
    String[][] discs = new String[RomaGame.MAX_PLAYERS][DiceDiscs.CARD_POSITIONS];
    int[] money = new int[RomaGame.MAX_PLAYERS];
    int[] victory = new int[RomaGame.MAX_PLAYERS];
    int victoryPool;
    int[] actionDice = new int[DiceHolder.DICE_PER_PLAYER];

    ArrayList<ActionData> actionHistory = new ArrayList<ActionData>();

    public PlayState(PlayArea playArea, Player player){
        CardManager cardManager = playArea.getCardManager();
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        MoneyManager moneyManager = playArea.getMoneyManager();
        VictoryTokens victoryTokens = playArea.getVictoryTokens();
        DiceHolder diceHolder = playArea.getDiceHolder();

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
    }

    public void addActionHistory(ActionData actionData){
        actionHistory.add(actionData);
    }

    public String toString(){
        String output = "turn: " + turn
                + "\ndeckData: " + deckData
                + "\ndiscardData: " + discardData
                + "\n";
        for(int i = 0; i < RomaGame.MAX_PLAYERS; i++){
            output += "playerID: " + i
                    + "\nhand: " + hand.get(i)
                    + "\ndiscs: " + discs[i]
                    + "\nmoney: " + money[i]
                    + "\nvictory: " + victory[i]
                    + "\n";
        }
        output += "victoryPool: " + victoryPool
                + " \nactionDice: " + actionDice
                + "\n";
        output += actionHistory.toString();
        return output;
    }
}
