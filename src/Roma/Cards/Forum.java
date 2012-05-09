package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Forum extends CardBase {

    public final static String NAME = "Forum";
    final static String TYPE = Card.BUILDING;
    final static String DESCRIPTION = "Requires 2 action dice: one to activate the Forum and the other to " +
            "determine how many victory points the player receives";
    final static int COST = 5;
    final static int DEFENCE = 5;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 6;

    public static ArrayList<Card> playSet(PlayArea playArea){
        ArrayList<Card> set = new ArrayList<Card>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Forum(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    private Forum(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        ArrayList<Dice> freeDice = player.getFreeDice();
        Dice chosenDie = null;
        VictoryTokens victoryTokens = playArea.getVictoryTokens();

        if(freeDice.isEmpty()){
            activated = false;
            System.out.println("Not enough free action dice!");
        } else {
            System.out.println("Please choose a second die to use");
            chosenDie = player.chooseDie(freeDice);
            if(chosenDie != null){
                diceDiscs.addDiceToDisc(position, chosenDie);
                victoryTokens.playerFromPool(player.getPlayerID(), chosenDie.getValue());
                // check for adjacent Templum
                if(diceDiscs.checkAdjacent(player.getPlayerID(), position, Templum.NAME) && !freeDice.isEmpty()){
                    System.out.println("Would you like to use a 3rd die?");
                    chosenDie = null;
                    chosenDie = player.chooseDie(freeDice);
                    if(chosenDie != null){
                        diceDiscs.addDiceToDisc(position, chosenDie);
                        victoryTokens.playerFromPool(player.getPlayerID(), chosenDie.getValue());
                    }
                }
                // check for adjacent Basilicas
                if(diceDiscs.checkAdjacentDown(player.getPlayerID(), position, Basilica.NAME)){
                    System.out.println("You get 2 extra victory tokens from your adjacent Basilica!");
                }
                if(diceDiscs.checkAdjacentUp(player.getPlayerID(), position, Basilica.NAME)){
                    System.out.println("You get 2 extra victory tokens from your adjacent Basilica!");
                }
            } else {
                activated = false;
                System.out.println("Card activation cancelled.");
            }
        }


        return activated;
    }

}
