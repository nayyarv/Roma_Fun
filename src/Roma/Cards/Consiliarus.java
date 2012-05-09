package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Consiliarus extends CardBase {
    public final static String NAME = "Consiliarus";
    final static String TYPE = CardBase.CHARACTER;
    final static String DESCRIPTION = "The player picks up their character cards and can then lay them again " +
            "on any dice disc. Buildings can be covered.";
    final static int COST = 4;
    final static int DEFENCE = 4;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public Consiliarus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);
    }


    public boolean activate(Player player, int position) {
        boolean activated = true;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        ArrayList<CardBase> characterCardBases = diceDiscs.setOfCards(player,CHARACTER);//all the cards

        while (!characterCardBases.isEmpty()){
            playArea.printStats();
            player.printCardList(characterCardBases);
            CardBase cardBase = player.chooseCard(characterCardBases);
            //TODO: Allow players to see the dice discs while placing their cards
            if (cardBase ==null) { //i.e. cancelled
                System.out.println("You must choose a card");
            } else {
                int choice = player.chooseCardDisc();
                if (choice!=-1){//I.e not cancel
                    diceDiscs.layCard(player.getPlayerID(), choice, cardBase);
                } else {
                    characterCardBases.add(cardBase);
                }
            }
        }

        return activated;
    }
}
