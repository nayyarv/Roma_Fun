package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Machina extends Card {

    private final static String NAME = "Machina";
    private final static String TYPE = Card.BUILDING;
    private final static String DESCRIPTION = "The player picks up their building cards and lays " +
            "them again on any dice discs. Character cards can be covered.";
    private final static int COST = 4;
    private final static int DEFENCE = 4;
    private final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public Machina(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;

        return activated;
    }

}
