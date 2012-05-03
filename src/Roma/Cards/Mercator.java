package Roma.Cards;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 30/03/12
 * Desc:
 */

import Roma.*;

import java.util.Scanner;

public class Mercator extends Card {
    public final static String NAME = "Mercator";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "For 2 sestertii each, the player can buy 1 victory point" +
            " from their opponent as long as there are money and victory points left!" +
            "The opponent gets the money.";
    final static int COST = 7;
    final static int DEFENCE = 2;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 1;

    public Mercator(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);
    }

    public boolean activate(Player player, int position) {
        final String STR_PROMPT = "How many Victory Points would you like from your opponent? (max 3 or 0 to cancel): ";
        final int MAX_PURCHASE = 3;
        final int MIN_PURCHASE = 1;

        boolean activated = true;
        Scanner input = new Scanner(System.in);
        MoneyManager moneyManager = playArea.getMoneyManager();
        VictoryTokens victoryTokens = playArea.getVictoryTokens();
        boolean validInput = false;

        //playArea.getMoneyManager().loseMoney(player, COST);//or super.getCost;
        int numTokensRead;

        System.out.println(STR_PROMPT);

        while(!validInput){
            numTokensRead = input.nextInt();
            if(numTokensRead <= MAX_PURCHASE && numTokensRead >= MIN_PURCHASE){
                if (moneyManager.transferMoney(player.getPlayerID(), otherPlayer(player.getPlayerID()), 2 * numTokensRead)) {
                    victoryTokens.playerToPlayer(otherPlayer(player.getPlayerID()), player.getPlayerID(), numTokensRead);
                    validInput = true;
                }
            } else if (numTokensRead == 0) {
                validInput = true;
                activated = false;
            } else {
                System.out.println("Please give a valid number");
            }
        }

        return activated;
    }
}
