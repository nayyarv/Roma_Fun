package Roma.Cards;

import Roma.*;
import Roma.PlayerInterfaceFiles.CancelAction;
import Roma.PlayerInterfaceFiles.PlayerInterface;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Essedum extends CardBase {
    private static int DEFENSE_SHIFT = -2;
    public final static String NAME = "Essedum";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "The defence value of the opponent's face-up cards is reduced by 2.";
    final static int COST = 6;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    @Override
    public CardHolder makeOne(PlayArea playArea) {
        CardBase card = new Essedum(playArea);
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
            card = new Essedum(playArea);
            cardHolder = new CardHolder(card, playArea);
            card.setContainer(cardHolder);
            card.setCardHolder(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }


    Essedum(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        PlayerInterface.printOut("Reduce enemy active cards Defense by 2", true);
        player.commit();
    }

    //activationData: no data

    @Override
    public void activate(Player player, int position) {
        WrapperMaker wrapperMaker = new WrapperMaker();
        wrapperMaker.setDefenseShift(DEFENSE_SHIFT);
        Wrapper wrapper;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        int targetPlayerID = player.getOtherPlayerID();
        CardHolder[] enemyActives = diceDiscs.getPlayerActives(targetPlayerID);

        for (CardHolder card : enemyActives) {
            if (card != null) {
                wrapper = wrapperMaker.insertWrapper(card);
                playArea.addToEndTurnList(wrapper);
            }
        }
    }
}
