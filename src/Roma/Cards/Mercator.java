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
    private final static String NAME = "Mercator";
    private final static String TYPE = Card.CHARACTER;
    private final static String DESCRIPTION = "For 2 sestertii each, the player can buy 1 victory point" +
            " from their opponent as long as there are money and victory points left!" +
            "The opponent gets the money.";
    private final static int COST = 7;
    private final static int DEFENCE = 2;
    private final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 1;

    public Mercator(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);
    }

    public boolean activate(Player player, int position) {
        boolean activated = true;

        PlayArea playArea = super.getPlayArea();
        //playArea.getMoneyManager().loseMoney(player, COST);//or super.getCost;
        int numTokensReqd;

        String prompt = "How many Victory Points would you like from your opponent?: ";
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        numTokensReqd = input.nextInt();
        //TODO: Fix this part
        //if (playArea.getMoneyManager().transferMoney(player, otherPlayer(player), 2 * numTokensReqd)) {
          //  playArea.getVictoryTokens().playerToPlayer(otherPlayer(player), player, numTokensReqd);
        //}

        return activated;
    }

    private int otherPlayer(int player) {
        if (player == Roma.PLAYER_ONE) {
            return Roma.PLAYER_TWO;
        } else {
            return Roma.PLAYER_ONE;
        }
    }
}
