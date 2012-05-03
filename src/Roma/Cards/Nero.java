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
public class Nero extends Card {
    public final static String NAME = "Nero";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "Destroys any face-up opposing building card. " +
            "The destroyed card and Nero are both discarded.";
    final static int COST = 8;
    final static int DEFENCE = 9;
    final static boolean ACTIVATE_ENABLED = true;


    public final static int OCCURENCES = 1;

    public Nero(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        //TODO: refactor input to interface or player
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> validInput = new ArrayList<Integer>();
        boolean inputValid = false;
        int chosenInput = -1;

        boolean activated = true;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        int targetPlayerID = (player.getPlayerID() + 1) % Roma.MAX_PLAYERS;

        Card[] enemyCards = diceDiscs.getPlayerActives(targetPlayerID);

        System.out.println("Available Targets:");
        for(int i = 0; i < enemyCards.length; i++){
            if(enemyCards[i] != null && enemyCards[i].getType() == Card.BUILDING){
                System.out.println((i + 1) + ") " + enemyCards[i].getName());
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

        diceDiscs.discardTarget(targetPlayerID, chosenInput);
        diceDiscs.discardTarget(player.getPlayerID(), position);

        return activated;
    }
}
