package Roma.Cards;

import Roma.DiceDiscs;
import Roma.PlayArea;
import Roma.Player;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Machina extends CardBase {

    public final static String NAME = "Machina";
    final static String TYPE = Card.BUILDING;
    final static String DESCRIPTION = "The player picks up their building cards and lays " +
            "them again on any dice discs. Character cards can be covered.";
    final static int COST = 4;
    final static int DEFENCE = 4;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public static ArrayList<Card> playSet(PlayArea playArea){
        ArrayList<Card> set = new ArrayList<Card>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Machina(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    private Machina(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        ArrayList<Card> buildingCards = diceDiscs.setOfCards(player, Card.BUILDING);//all the cards

        while (!buildingCards.isEmpty()){
            playArea.printStats();
            player.printCardList(buildingCards);
            Card card = player.chooseCard(buildingCards);
            //TODO: Allow players to see the dice discs while placing their cards
            if (card ==null) { //i.e. cancelled
                System.out.println("You must choose a card");
            } else {
                int choice = player.chooseCardDisc();
                if (choice!=-1){//I.e not cancel
                    diceDiscs.layCard(player.getPlayerID(), choice, card);
                } else {
                    buildingCards.add(card);
                }
            }
        }


        return activated;
    }

}
