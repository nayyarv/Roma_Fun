package Roma.Cards;

import Roma.DiceDiscs;
import Roma.PlayArea;
import Roma.Player;
import Roma.PlayerInterfaceFiles.CancelAction;
import Roma.PlayerInterfaceFiles.PlayerInterface;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Nero extends CardBase {
    public final static String NAME = "Nero";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "Destroys any face-up opposing building card. " +
            "The destroyed card and Nero are both discarded.";
    final static int COST = 8;
    final static int DEFENCE = 9;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 1;

    @Override
    public CardHolder makeOne(PlayArea playArea) {
        CardBase card = new Nero(playArea);
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
            card = new Nero(playArea);
            cardHolder = new CardHolder(card, playArea);
            card.setContainer(cardHolder);
            card.setCardHolder(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    Nero(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        ArrayList<Integer> activationData = player.getActivationData();
        CardHolder[][] activeCards = diceDiscs.getActiveCards();
        int targetPlayerID = player.getOtherPlayerID();
        CardHolder card;
        int targetIndex;

        for (int i = 0; i < DiceDiscs.CARD_POSITIONS; i++) {
            card = activeCards[targetPlayerID][i];
            if (card != null && card.getType().equalsIgnoreCase(Card.BUILDING)) {
                card.setPlayable(true);
            }
        }
        PlayerInterface.printOut("Destroy which enemy building? (Nero is also discarded)", true);
        targetIndex = player.getDiceDiscIndex(activeCards, false, true);

        player.commit();
        activationData.add(targetIndex);
    }

    //activationData: [targetIndex]

    @Override
    public void activate(Player player, int position) {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        CardHolder[] playerActives = diceDiscs.getPlayerActives(player.getPlayerID());
        int targetPlayerID = player.getOtherPlayerID();
        ArrayList<Integer> activationData = player.getActivationData();
        int targetIndex = activationData.remove(0);

        diceDiscs.discardTarget(targetPlayerID, targetIndex);
        playerActives[position].goingToDiscard(player.getPlayerID(), targetIndex);
    }
}
