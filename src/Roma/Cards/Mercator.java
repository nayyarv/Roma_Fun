package Roma.Cards;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 30/03/12
 * Desc:
 */

import Roma.MoneyManager;
import Roma.PlayArea;
import Roma.Player;
import Roma.PlayerInterfaceFiles.CancelAction;
import Roma.PlayerInterfaceFiles.PlayerInterface;
import Roma.VictoryTokens;

import java.util.ArrayList;

public class Mercator extends CardBase {
    public final static String NAME = "Mercator";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "For 2 sestertii each, the player can buy 1 victory point" +
            " from their opponent as long as there are money and victory points left!" +
            "The opponent gets the money.";
    final static int COST = 7;
    final static int DEFENCE = 2;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 1;

    @Override
    public CardHolder makeOne(PlayArea playArea) {
        CardBase card = new Mercator(playArea);
        CardHolder cardHolder = new CardHolder(card, playArea);
        card.setContainer(cardHolder);
        card.setCardHolder(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea) {
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        CardBase card;

        for (int i = 0; i < OCCURENCES; i++) {
            card = new Mercator(playArea);
            cardHolder = new CardHolder(card, playArea);
            card.setContainer(cardHolder);
            card.setCardHolder(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    Mercator(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);
    }

    private final int COST_PER_TOKEN = 2;

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        MoneyManager moneyManager = playArea.getMoneyManager();
        ArrayList<Integer> activationData = player.getActivationData();
        int numberOfTokens = CANCEL;
        PlayerInterface playerInterface = playArea.getPlayerInterface();
        boolean validInput = false;
        int playerMoney = moneyManager.getPlayerMoney(player.getPlayerID());

        PlayerInterface.printOut("Buy tokens from your opponent for 2 money each", true);
        while (!validInput) {
            PlayerInterface.printOut("You have " + playerMoney + " money, how many tokens do you want to buy?", true);
            numberOfTokens = playerInterface.getIntegerInput(0);
            if (moneyManager.enoughMoney(player.getPlayerID(), COST_PER_TOKEN * numberOfTokens)) {
                validInput = true;
            } else {
                PlayerInterface.printOut("Not enough money to buy that many tokens!", true);
            }
        }
        player.commit();
        activationData.add(numberOfTokens);
    }

    //activationData: [numberOfTokens]

    @Override
    public void activate(Player player, int position) {
        MoneyManager moneyManager = playArea.getMoneyManager();
        VictoryTokens victoryTokens = playArea.getVictoryTokens();
        ArrayList<Integer> activationData = player.getActivationData();
        int numTokens = activationData.remove(0);
        int targetPlayerID = player.getOtherPlayerID();

        moneyManager.transferMoney(player.getPlayerID(), targetPlayerID, 2 * numTokens);
        victoryTokens.playerToPlayer(targetPlayerID, player.getPlayerID(), numTokens);
    }
}
