package Roma.Cards;

import Roma.PlayArea;
import Roma.Player;
import Roma.PlayerInterfaceFiles.CancelAction;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 19/05/12
 * Desc:
 */
public class Kat extends CardBase{

    public final static String NAME = "Kat";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "Mysterious and revered animal.  " +
            "Its haunting cry lifts the heart of all who hear it.  Has nine lives.";
    final static int COST = 5;
    final static int DEFENCE = 1;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public Kat(PlayArea playArea){
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);
    }


    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void activate(Player player, int position) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CardHolder makeOne(PlayArea playArea) {
        Card card = new Kat(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }
}
