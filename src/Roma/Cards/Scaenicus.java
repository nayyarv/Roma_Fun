package Roma.Cards;

import Roma.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Scaenicus extends CardBase {
    public final static String NAME = "Scaenicus";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "He performs no action of his own but can copy the action of any of " +
            "the player's own face-up character cards, and the next time round that of another.";
    final static int COST = 8;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = true;


    public final static int OCCURENCES = 2;

    public static ArrayList<Card> playSet(PlayArea playArea){
        ArrayList<Card> set = new ArrayList<Card>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Scaenicus(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    private Scaenicus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    //TODO: handle infinite loop selecting self
    public boolean activate(Player player, int position) {
        //TODO: refactor input to interface or player
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> validInput = new ArrayList<Integer>();
        boolean inputValid = false;
        int chosenInput = -1;

        boolean activated = true;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        int targetPlayerID = (player.getPlayerID() + 1) % Roma.MAX_PLAYERS;

        Card[] friendlyCards = diceDiscs.getPlayerActives(targetPlayerID);

        System.out.println("Available Targets:");
        for(int i = 0; i < friendlyCards.length; i++){
            if(friendlyCards[i] != null && friendlyCards[i].getType() == Card.CHARACTER){
                System.out.println((i + 1) + ") " + friendlyCards[i].getName());
                validInput.add(i + 1);
            } else {
                System.out.println((i + 1) + ") #");
            }
        }

        while(!inputValid){
            chosenInput = input.nextInt();
            for(int number : validInput){
                if(chosenInput == number){
                    inputValid = true;
                }
            }
        }
        chosenInput--;

        friendlyCards[chosenInput].activate(player, position);

        return activated;
    }

}
