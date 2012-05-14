package Roma;

import Roma.Cards.Card;
import Roma.Cards.CardHolder;

import java.util.ArrayList;

/**
 * File Name: GameRules.java
 * Creator: Varun Nayyar
 * Date: 12/05/12
 * Desc: All the initial and regular global rules are run by this object
 */
public class GameRules {

    private PlayArea playArea;
    Player[] players;

    public GameRules(PlayArea playArea){
        this.playArea = playArea;
        players =  playArea.getAllPlayers();
    }

    public void getAndSwapCards(){
        CardManager cardManager = playArea.getCardManager();



        ArrayList<CardHolder> initialSet = new ArrayList<CardHolder>();
        //stores the initial cards for the game

        for(int i = 0; i<Roma.MAX_PLAYERS;i++){
            initialSet.addAll(0, cardManager.drawNCards(Roma.NUM_INIT_CARDS));
        } //gets all the cards needed

        //cardManager.shuffle(initialSet);
        //To ensure some extra randomness?

        ArrayList<CardHolder> choices = new ArrayList<CardHolder>();
        //stores the choices of the previous player

        for (int i =0; i<Roma.MAX_PLAYERS;i++){
            choices.clear();
            //add prev choices
            ArrayList<CardHolder> individualHand = new ArrayList<CardHolder>();

            individualHand.addAll(initialSet.subList(
                    (i * Roma.NUM_INIT_CARDS), (i + 1) * Roma.NUM_INIT_CARDS));
            //extracts the first num-init-cards from the initial set

            System.out.println(players[i].getName() +
                    ", these are the " +Roma.NUM_INIT_CARDS+ " cards dealt to you.\n" +
                    "You must choose " + Roma.NUM_CARDS_SWAPPED + " to give to your opponent.\n" +
                    "Choose the first Card");
            //Prompt: move printing to player interface?

            CardHolder temp = null;

            for(int j = 0; j<Roma.NUM_CARDS_SWAPPED;j++, temp = null){
                while(temp == null){
                    temp = players[i].chooseCard(individualHand);
                    if (temp == null) System.out.println("You must choose a card: ");
                }
                choices.add(temp);
                individualHand.remove(temp);
                if(j!=Roma.NUM_CARDS_SWAPPED-1) System.out.println("Choose the next card:");
            }
            players[i].addCardListToHand(individualHand);
            players[(i+1)%Roma.MAX_PLAYERS].addCardListToHand(choices);

        }
    }

    public void layAllCardsInHand() {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();

        Player activePlayer = null;
        ArrayList<CardHolder> hand = null;
        CardHolder chosenCard = null;
        int targetDisc;

        for(int i = 0; i < Roma.MAX_PLAYERS; i++){
            activePlayer = players[i];
            hand = activePlayer.getHand();

            while(!hand.isEmpty()){
                playArea.printStats();
                System.out.println(activePlayer.getName() + ", please lay all your cards");
                chosenCard = null;
                while(chosenCard == null){
                    chosenCard = activePlayer.chooseCard(hand);
                    if(chosenCard == null){
                        System.out.println("You must choose a card");
                    }
                }
                targetDisc = -1; // cancel value
                while(targetDisc == -1){
                    targetDisc = activePlayer.chooseCardDisc();
                    if(targetDisc == -1){
                        System.out.println("You must choose a disc");
                    }
                }
                diceDiscs.layCard(activePlayer.getPlayerID(), targetDisc, chosenCard);
            }
        }
    }

    public void deductVictoryTokens(int playerID) {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        VictoryTokens victoryTokens = playArea.getVictoryTokens();

        Card[] friendlyCards = diceDiscs.getPlayerActives(playerID);
        int countNull = 0;
        for(Card card : friendlyCards){
            if(card == null){
                countNull++;
            }
        }
        System.out.println("There are " + countNull + " empty slots, losing that many players");
        victoryTokens.playerToPool(playerID, countNull);
    }

}
