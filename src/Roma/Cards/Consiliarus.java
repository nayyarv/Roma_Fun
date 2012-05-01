package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Consiliarus extends Card {
    private final static String NAME = "Consiliarus";
    private final static String TYPE = Card.CHARACTER;
    private final static String DESCRIPTION = "The player picks up their character cards and can then lay them again " +
            "on any dice disc. Buildings can be covered.";
    private final static int COST = 4;
    private final static int DEFENCE = 4;
    private final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public Consiliarus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);
    }


    public boolean activate(Player player, int position) {
        boolean activated = true;
        ArrayList<Card> characterCards = new ArrayList<Card>();
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        //diceDiscs.getCardName()



        return activated;
    }
}
