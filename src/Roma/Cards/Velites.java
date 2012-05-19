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
public class Velites extends CardBase {
    public final static String NAME = "Velites";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "Attacks any opposing character card " +
            "(does not have to be directly opposite). The battle die is thrown once.";
    final static int COST = 8;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    @Override
    public CardHolder makeOne(PlayArea playArea) {
        CardBase card = new Velites(playArea);
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
            card = new Velites(playArea);
            cardHolder = new CardHolder(card, playArea);
            card.setContainer(cardHolder);
            card.setCardHolder(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    Velites(PlayArea playArea) {
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
            if (card != null && card.getType().equalsIgnoreCase(Card.CHARACTER)) {
                card.setPlayable(true);
            }
        }
        PlayerInterface.printOut("Attack which enemy Character?", true);
        targetIndex = player.getDiceDiscIndex(activeCards, false, true);

        player.commit();
        activationData.add(targetIndex);
    }

    //activationData: [targetIndex]
    //reads battle value from currentAction
    @Override
    public void activate(Player player, int position) {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        int targetPlayerID = player.getOtherPlayerID();
        ArrayList<Integer> activationData = player.getActivationData();
        int targetIndex = activationData.remove(0);
        int battleValue = player.getBattleValue();

        diceDiscs.battle(targetPlayerID, targetIndex, battleValue);
    }
}
