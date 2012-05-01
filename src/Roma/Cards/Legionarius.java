package Roma.Cards;

import Roma.PlayArea;
import Roma.Player;
/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Legionarius extends Card {
    private final static String NAME = "Legionarius";
    private final static String TYPE = Card.CHARACTER;
    private final static String DESCRIPTION = "Attacks the opponent's card which is directly opposite, " +
            "whether it is a character or a building card.";
    private final static int COST = 4;
    private final static int DEFENCE = 5;
    private final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 3;

    public Legionarius(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;

        return activated;
    }
}
