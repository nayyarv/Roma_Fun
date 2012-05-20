package Testers;

import Implementers.AcceptanceInterfaceImplementers;
import framework.Rules;
import framework.cards.Card;
import framework.interfaces.AcceptanceInterface;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;
import framework.interfaces.activators.*;
import junit.framework.TestCase;

import java.util.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class TestCardActivators extends TestCase {

    AcceptanceInterface acceptance = new AcceptanceInterfaceImplementers();
    GameState gameState= acceptance.getInitialState();
    MoveMaker move = acceptance.getMover(gameState);


    private int getIndexFromPile (Card toFind, List<Card> pile) {
        int index = -1;
        boolean found = false;
        for (int i = 0; i < pile.size() && !found; i++) {
            //System.out.println("Finding: " + toFind + " Found: " + pile.get(i));
            if (pile.get(i).equals(toFind)) {
                found = true;
                index = i;
            }
        }
        return index;
    }

    public void testTribPlebLaying() throws Exception{
        System.out.println("Testing TribPleb");
        gameState.setPlayerVictoryPoints(0, 10);
        gameState.setPlayerVictoryPoints(1, 10);
        gameState.setPlayerSestertii(0, 10);
        gameState.setPlayerSestertii(1, 0);

        // Set up the game state for the test
        gameState.setWhoseTurn(0);
        gameState.setActionDice(new int [] {3, 3, 4});

        Collection<Card> hand = new ArrayList<Card>();
        hand.add(Card.TRIBUNUSPLEBIS);
        hand.add(Card.TRIBUNUSPLEBIS);
        gameState.setPlayerHand(0, hand);

        // Place the Tribunus Plebis on disc 3 and activate it
        move.placeCard(Card.TRIBUNUSPLEBIS, 3);
        gameState.isGameCompleted();
        move.chooseCardToActivate(3).complete();


        // Check that player 0 has gained a victory point, but player
        // 1's score has not changed
        assert(gameState.getPlayerVictoryPoints(0) == 11);
        assert(gameState.getPlayerVictoryPoints(1) == 9);
        assert(gameState.getPlayerSestertii(0) == 5);
        assert(gameState.getPlayerSestertii(1) == 0);


        System.out.println("Testing TribPleb passed!!\n");

    }

    public void testLegat() throws Exception{

        System.out.println("Testing Legat");
        Card [] ourSide = {Card.NOT_A_CARD,
                Card.NOT_A_CARD,
                Card.NOT_A_CARD,
                Card.NOT_A_CARD,
                Card.NOT_A_CARD,
                Card.NOT_A_CARD,
                Card.NOT_A_CARD};

        Card [] opponentSideInitial = {Card.NOT_A_CARD,
                Card.NOT_A_CARD,
                Card.NOT_A_CARD,
                Card.NOT_A_CARD,
                Card.NOT_A_CARD,
                Card.NOT_A_CARD,
                Card.NOT_A_CARD};

        // Place 5 cards on the opponent's side
        gameState.setPlayerCardsOnDiscs(0, ourSide);
        gameState.setPlayerCardsOnDiscs(1, opponentSideInitial);

        // Set up the player stats
        gameState.setPlayerVictoryPoints(0, 10);
        gameState.setPlayerVictoryPoints(1, 10);
        gameState.setPlayerSestertii(0, 5);
        gameState.setPlayerSestertii(1, 100);

        List<Card> hand = new ArrayList<Card>();
        hand.add(Card.LEGIONARIUS);
        hand.add(Card.AESCULAPINUM);
        hand.add(Card.CONSUL);
        hand.add(Card.ESSEDUM);
        hand.add(Card.MACHINA);
        gameState.setPlayerHand(1, hand);

        // Set up the game state for the test
        gameState.setWhoseTurn(0);
        gameState.setActionDice(new int [] {3, 3, 4});

        hand = new ArrayList<Card>();
        hand.add(Card.LEGAT);
        gameState.setPlayerHand(0, hand);

        // Place the Legat on disc 3 and activate it
        move.placeCard(Card.LEGAT, 3);
        move.chooseCardToActivate(3).complete();
//        for (Card currentCard : gameState.getPlayerHand(0)) {
//        	System.out.println(currentCard);
//        }


        // Check that player 0 has gained 7 victory points, but player
        // 1's score has not changed
        assert(gameState.getPlayerVictoryPoints(0) == 17);
        assert(gameState.getPlayerVictoryPoints(1) == 10);
        assert(gameState.getPlayerSestertii(0) == 0);
        assert(gameState.getPlayerSestertii(1) == 100);

        move.endTurn();
        move.placeCard(Card.LEGIONARIUS, Rules.DICE_DISC_1);
        move.placeCard(Card.AESCULAPINUM, Rules.DICE_DISC_2);
        move.placeCard(Card.CONSUL, Rules.DICE_DISC_4);
        move.placeCard(Card.ESSEDUM, Rules.DICE_DISC_6);
        move.placeCard(Card.MACHINA, Rules.BRIBE_DISC);
        move.endTurn();
        gameState.setActionDice(new int [] {3, 3, 4});

        assert(gameState.getPlayerVictoryPoints(0) == 11);
        assert(gameState.getPlayerVictoryPoints(1) == 3);
        assert(gameState.getPlayerSestertii(0) == 0);
        assert(gameState.getPlayerSestertii(1) == 100 - 4 - 5 - 3 - 6 - 4);

        // Activate the Legat again
        move.chooseCardToActivate(3).complete();

        // Check that player 0 has gained 2 victory points, but player
        // 1's score has not changed
        assert(gameState.getPlayerVictoryPoints(0) == 13);
        assert(gameState.getPlayerVictoryPoints(1) == 3);
        assert(gameState.getPlayerSestertii(0) == 0);
        assert(gameState.getPlayerSestertii(1) == 100 - 4 - 5 - 3 - 6 - 4);

        System.out.println("Testing Legat passed!!!\n");
    }


    public void testMercator() throws Exception{

        System.out.println("Testing Mercator\n");

        List<Card> deck = new LinkedList<Card>();
        gameState.setDiscard(deck);

        Card[] discs = new Card[Rules.NUM_DICE_DISCS];
        for (int i = 0; i < Rules.NUM_DICE_DISCS; i++) {
            discs[i] = Card.NOT_A_CARD;
        }
        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            gameState.setPlayerCardsOnDiscs(i, discs);
        }

        List<Card> discard = new LinkedList<Card>();
        discard.add(Card.AESCULAPINUM);
        discard.add(Card.BASILICA);
        discard.add(Card.CENTURIO);
        discard.add(Card.CONSILIARIUS);
        discard.add(Card.CONSUL);
        discard.add(Card.ESSEDUM);
        discard.add(Card.FORUM);
        discard.add(Card.GLADIATOR);
        discard.add(Card.HARUSPEX);
        discard.add(Card.LEGAT);
        discard.add(Card.LEGIONARIUS);
        gameState.setDiscard(discard);
        discard = gameState.getDiscard();

        gameState.setWhoseTurn(0);
        List<Card> hand;
        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            gameState.setPlayerSestertii(i, 100);
            gameState.setPlayerVictoryPoints(i, 2);
            hand = new LinkedList<Card>();
            hand.add(Card.MERCATOR);
            hand.add(Card.ARCHITECTUS);
            gameState.setPlayerHand(i, hand);
        }

        gameState.setPlayerVictoryPoints(1, 6);

        gameState.setActionDice(new int[] {1,1,1});

        move.placeCard(Card.MERCATOR, Rules.DICE_DISC_1);

        //the correct amount of money should be deducted
        //game removed from hand and get placed on the field
        assert(gameState.getPlayerHand(0).size() == 1);
        assert(!gameState.getPlayerHand(0).contains(Card.MERCATOR));
        Card[] field;
        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[0] == Card.MERCATOR);

        assert(gameState.getPoolVictoryPoints() == 36 - 2 - 6);
        assert(!gameState.isGameCompleted());

        MercatorActivator activator = (MercatorActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);

        activator.chooseMercatorBuyNum(1);
        activator.complete();
        assert(gameState.getPlayerVictoryPoints(0) == 2 + 1);
        assert(gameState.getPlayerSestertii(0) == 100 - 2*1 - 7);
        assert(gameState.getPlayerVictoryPoints(1) == 6 - 1);
        assert(gameState.getPlayerSestertii(1) == 100 + 2*1);
        assert(gameState.getPoolVictoryPoints() == 36 - 2 - 6);

        activator = (MercatorActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);

        activator.chooseMercatorBuyNum(5);
        activator.complete();
        assert(gameState.getPlayerVictoryPoints(0) == 2 + (1+5));
        assert(gameState.getPlayerSestertii(0) == 100 - 2*(1+5) - 7);
        assert(gameState.getPlayerVictoryPoints(1) == 0);
        assert(gameState.getPlayerSestertii(1) == 100 + 2*(1+5));
        assert(gameState.getPoolVictoryPoints() == 36 - 2 - 6);

        assert(gameState.getActionDice().length == 1);
        assert(gameState.isGameCompleted());

        System.out.println("Testing Mercator passed!!!\n");

    }


    public void testConsul() throws Exception{
        System.out.println("Testing Consul\n");
        List<Card> deck = new LinkedList<Card>();
        gameState.setDiscard(deck);

        Card[] discs = new Card[Rules.NUM_DICE_DISCS];
        for (int i = 0; i < Rules.NUM_DICE_DISCS; i++) {
            discs[i] = Card.NOT_A_CARD;
        }
        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            gameState.setPlayerCardsOnDiscs(i, discs);
        }

        List<Card> discard = new LinkedList<Card>();
        discard.add(Card.AESCULAPINUM);
        discard.add(Card.BASILICA);
        discard.add(Card.CENTURIO);
        discard.add(Card.CONSILIARIUS);
        discard.add(Card.CONSUL);
        discard.add(Card.ESSEDUM);
        discard.add(Card.FORUM);
        discard.add(Card.GLADIATOR);
        discard.add(Card.HARUSPEX);
        discard.add(Card.LEGAT);
        discard.add(Card.LEGIONARIUS);
        gameState.setDiscard(discard);
        discard = gameState.getDiscard();

        gameState.setWhoseTurn(0);
        List<Card> hand;
        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            gameState.setPlayerSestertii(i, 100);
            gameState.setPlayerVictoryPoints(i, 15);
            hand = new LinkedList<Card>();
            hand.add(Card.CONSUL);
            hand.add(Card.FORUM);
            hand.add(Card.FORUM);
            gameState.setPlayerHand(i, hand);
        }

        gameState.setActionDice(new int[] {1,2,1});

        move.placeCard(Card.CONSUL, Rules.DICE_DISC_1);

        assert(gameState.getPlayerSestertii(1) == 100);
        assert(gameState.getPlayerHand(0).size() == 2);
        assert(!gameState.getPlayerHand(0).contains(Card.ARCHITECTUS));
        Card[] field;
        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[0] == Card.CONSUL);
        assert(field[1] == Card.NOT_A_CARD);
        assert(field[2] == Card.NOT_A_CARD);

        assert(gameState.getPoolVictoryPoints() == 36 - 15*Rules.NUM_PLAYERS);
        assert(!gameState.isGameCompleted());

        System.out.println(Arrays.toString(gameState.getActionDice()));

        ConsulActivator activator = (ConsulActivator) move.chooseCardToActivate(1);

        System.out.println(Arrays.toString(gameState.getActionDice()));

        activator.chooseWhichDiceChanges(2);
        activator.chooseConsulChangeAmount(-1);
        activator.complete();

        System.out.println(Arrays.toString(gameState.getActionDice()));

        assert(gameState.getActionDice().length == 2);
        assert(gameState.getActionDice()[0] == 1);
        assert(gameState.getActionDice()[1] == 1);
        activator = (ConsulActivator) move.chooseCardToActivate(1);

        activator.chooseWhichDiceChanges(1);
        activator.chooseConsulChangeAmount(1);
        activator.complete();

        assert(gameState.getActionDice().length == 1);
        assert(gameState.getActionDice()[0] == 2);

        assert(gameState.getPoolVictoryPoints() == 36 - 15*Rules.NUM_PLAYERS);
        assert(!gameState.isGameCompleted());

        System.out.println("Testing Consul passed!!!\n");
    }

    public void testNero() throws Exception{
        System.out.println("Testing Nero\n");
        gameState.setWhoseTurn(0);

        Collection<Card> hand = new ArrayList<Card>();
        hand.add(Card.NERO);
        hand.add(Card.NERO);
        hand.add(Card.NERO);

        gameState.setPlayerHand(0, hand);

        //the opponent's field would have 3 building cards
        Card[] field = new Card[Rules.NUM_DICE_DISCS];
        for(int i = 0; i < field.length; i++) {
            field[i] = Card.NOT_A_CARD;
        }
        field[0] = Card.FORUM;
        field[1] = Card.NERO;
        field[3] = Card.ONAGER;

        gameState.setPlayerCardsOnDiscs(1, field);

        gameState.setPlayerSestertii(0, 24);
        gameState.setActionDice(new int[] {1,3,4});

        //place the Nero here
        move.placeCard(Card.NERO, Rules.DICE_DISC_1);

        assert(gameState.getPlayerSestertii(0) == 16);
        assert(gameState.getPlayerHand(0).size() == 2);
        assert(gameState.getPlayerHand(0).contains(Card.NERO));

        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[0] == Card.NERO);

        //kill the Forum
        NeroActivator activator = (NeroActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
        activator.chooseDiceDisc(Rules.DICE_DISC_1);
        activator.complete();

        //Nero would die
        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[0] == Card.NOT_A_CARD);
        assert(gameState.getDiscard().contains(Card.NERO));

        //Forum died as well
        field = gameState.getPlayerCardsOnDiscs(1);
        assert(field[0] == Card.NOT_A_CARD);
        assert(gameState.getDiscard().contains(Card.FORUM));

        //place another Nero
        move.placeCard(Card.NERO, Rules.DICE_DISC_3);

        assert(gameState.getPlayerSestertii(0) == 8);
        assert(gameState.getPlayerHand(0).size() == 1);
        assert(gameState.getPlayerHand(0).contains(Card.NERO));

        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[2] == Card.NERO);

        gameState.isGameCompleted();
        //kill the OnagerBehaviour
        activator = (NeroActivator) move.chooseCardToActivate(Rules.DICE_DISC_3);
        activator.chooseDiceDisc(Rules.DICE_DISC_4);
        activator.complete();


        //Nero would die
        field = gameState.getPlayerCardsOnDiscs(0);
        gameState.isGameCompleted();
        assert(field[2] == Card.NOT_A_CARD);
        assert(gameState.getDiscard().contains(Card.NERO));

        //OnagerBehaviour died as well
        field = gameState.getPlayerCardsOnDiscs(1);
        assert(field[3] == Card.NOT_A_CARD);
        assert(gameState.getDiscard().contains(Card.ONAGER));

        //place another Nero
        move.placeCard(Card.NERO, Rules.DICE_DISC_4);

        assert(gameState.getPlayerSestertii(0) == 0);
        assert(gameState.getPlayerHand(0).size() == 0);
        assert(!gameState.getPlayerHand(0).contains(Card.NERO));

        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[3] == Card.NERO);

        System.out.println("Testing Nero passed!!\n");

    }


    public void testSicarius() throws Exception {
        System.out.println("Testing Sicarius\n");
        gameState.setWhoseTurn(0);

        Collection<Card> hand = new ArrayList<Card>();
        hand.add(Card.SICARIUS);
        hand.add(Card.SICARIUS);
        hand.add(Card.SICARIUS);

        gameState.setPlayerHand(0, hand);

        //the opponent's field would have 3 character cards
        Card[] field = new Card[Rules.NUM_DICE_DISCS];
        for(int i = 0; i < field.length; i++) {
            field[i] = Card.NOT_A_CARD;
        }
        field[0] = Card.SICARIUS;
        field[2] = Card.GLADIATOR;
        field[5] = Card.NERO;

        gameState.setPlayerCardsOnDiscs(1, field);

        gameState.setPlayerSestertii(0, 27);
        gameState.setActionDice(new int[] {1,3,1});

        //place the Sicarius here
        move.placeCard(Card.SICARIUS, Rules.DICE_DISC_1);

        assert(gameState.getPlayerSestertii(0) == 18);
        assert(gameState.getPlayerHand(0).size() == 2);
        assert(gameState.getPlayerHand(0).contains(Card.SICARIUS));

        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[0] == Card.SICARIUS);

        //kill the gladiator
        SicariusActivator activator = (SicariusActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
        activator.chooseDiceDisc(Rules.DICE_DISC_3);
        activator.complete();

        //sicarius would die
        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[0] == Card.NOT_A_CARD);
        assert(gameState.getDiscard().contains(Card.SICARIUS));

        //gladiator died as well
        field = gameState.getPlayerCardsOnDiscs(1);
        assert(field[2] == Card.NOT_A_CARD);
        assert(gameState.getDiscard().contains(Card.GLADIATOR));

        //place another sicarius
        move.placeCard(Card.SICARIUS, Rules.DICE_DISC_3);

        assert(gameState.getPlayerSestertii(0) == 9);
        assert(gameState.getPlayerHand(0).size() == 1);
        assert(gameState.getPlayerHand(0).contains(Card.SICARIUS));

        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[2] == Card.SICARIUS);

        //kill the nero
        activator = (SicariusActivator) move.chooseCardToActivate(Rules.DICE_DISC_3);
        activator.chooseDiceDisc(Rules.DICE_DISC_6);
        activator.complete();

        //sicarius would die
        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[2] == Card.NOT_A_CARD);
        assert(gameState.getDiscard().contains(Card.SICARIUS));

        //nero died as well
        field = gameState.getPlayerCardsOnDiscs(1);
        assert(field[5] == Card.NOT_A_CARD);
        assert(gameState.getDiscard().contains(Card.NERO));

        //place another Sicarius
        move.placeCard(Card.SICARIUS, Rules.DICE_DISC_4);

        assert(gameState.getPlayerSestertii(0) == 0);
        assert(gameState.getPlayerHand(0).size() == 0);
        assert(!gameState.getPlayerHand(0).contains(Card.SICARIUS));

        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[3] == Card.SICARIUS);

        //advance to next player's turn
        move.endTurn();

        gameState.setActionDice(new int[] {1,1,1});

        //kill the opponent Sicarius
        activator = (SicariusActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
        activator.chooseDiceDisc(Rules.DICE_DISC_4);
        activator.complete();

        //Sicarius would die
        field = gameState.getPlayerCardsOnDiscs(1);
        assert(field[0] == Card.NOT_A_CARD);
        assert(gameState.getDiscard().contains(Card.SICARIUS));

        //the other Sicarius died as well
        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[3] == Card.NOT_A_CARD);


        System.out.println("Testing Sicarius passed!!\n");

    }


    public void testPraetorianus() throws Exception {
        System.out.println("Testing Praetorianus\n");




        System.out.println("Testing Praetorianus passed!!\n");

    }


    public void testAesculapinum() throws Exception {
        System.out.println("Testing Aesculapinum\n");

        List<Card> deck = new LinkedList<Card>();
        gameState.setDiscard(deck);

        Card[] discs = new Card[Rules.NUM_DICE_DISCS];
        for (int i = 0; i < Rules.NUM_DICE_DISCS; i++) {
            discs[i] = Card.NOT_A_CARD;
        }
        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            gameState.setPlayerCardsOnDiscs(i, discs);
        }

        List<Card> discard = new LinkedList<Card>();
        discard.add(Card.CENTURIO);
        discard.add(Card.SICARIUS);
        discard.add(Card.CENTURIO);
        discard.add(Card.CONSILIARIUS);
        discard.add(Card.CONSUL);
        discard.add(Card.SCAENICUS);
        discard.add(Card.KAT);
        discard.add(Card.GLADIATOR);
        discard.add(Card.HARUSPEX);
        discard.add(Card.LEGAT);
        discard.add(Card.LEGIONARIUS);
        gameState.setDiscard(discard);
        discard = gameState.getDiscard();

        gameState.setWhoseTurn(0);
        List<Card> hand;
        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            gameState.setPlayerSestertii(i, 100);
            gameState.setPlayerVictoryPoints(i, 15);
            hand = new LinkedList<Card>();
            hand.add(Card.AESCULAPINUM);
            hand.add(Card.ARCHITECTUS);
            gameState.setPlayerHand(i, hand);
        }

        gameState.setActionDice(new int[] {1,1,1});

        move.placeCard(Card.AESCULAPINUM, Rules.DICE_DISC_1);

        //the correct amount of money should be deducted
        //game removed from hand and get placed on the field
        assert(gameState.getPlayerSestertii(0) == 100 - 5);
        assert(gameState.getPlayerHand(0).size() == 1);
        assert(!gameState.getPlayerHand(0).contains(Card.AESCULAPINUM));
        Card[] field;
        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[0] == Card.AESCULAPINUM);

        assert(gameState.getPoolVictoryPoints() == 36 - 15*Rules.NUM_PLAYERS);
        assert(!gameState.isGameCompleted());

        AesculapinumActivator activator = (AesculapinumActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
        activator.chooseCardFromPile(getIndexFromPile(Card.CENTURIO, gameState.getDiscard()));
        activator.complete();
        assert(gameState.getPlayerHand(0).contains(Card.CENTURIO));
        assert(gameState.getActionDice().length == 2);
        assert(gameState.getActionDice()[0] == 1);
        assert(gameState.getActionDice()[1] == 1);

        activator = (AesculapinumActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
        activator.chooseCardFromPile(getIndexFromPile(Card.SICARIUS, gameState.getDiscard()));
        activator.complete();

        assert(gameState.getPlayerHand(0).contains(Card.SICARIUS));
        assert(gameState.getActionDice().length == 1);
        assert(gameState.getActionDice()[0] == 1);

        activator = (AesculapinumActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
        activator.chooseCardFromPile(getIndexFromPile(Card.CENTURIO, gameState.getDiscard()));
        activator.complete();
        assert(gameState.getPlayerHand(0).contains(Card.CENTURIO));
        assert(gameState.getActionDice() == null || gameState.getActionDice().length == 0);

        assert(gameState.getPoolVictoryPoints() == 36 - 15*Rules.NUM_PLAYERS);
        assert(!gameState.isGameCompleted());

        move.endTurn();
        assert(gameState.getPlayerVictoryPoints(1) == 8);
        assert(gameState.getPlayerSestertii(1) == 100);
        assert(gameState.getPlayerHand(1).contains(Card.AESCULAPINUM));
        assert(gameState.getPlayerHand(0).contains(Card.SICARIUS));
        assert(gameState.getPlayerHand(0).contains(Card.CENTURIO));
        move.endTurn();

        gameState.setActionDice(new int[] {1,1,1});
        activator = (AesculapinumActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
        activator.chooseCardFromPile(getIndexFromPile(Card.CONSILIARIUS, gameState.getDiscard()));
        activator.complete();
        assert(gameState.getPlayerHand(0).contains(Card.CONSILIARIUS));
        assert(gameState.getActionDice().length == 2);
        assert(gameState.getActionDice()[0] == 1);
        assert(gameState.getActionDice()[1] == 1);

        activator = (AesculapinumActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
        activator.chooseCardFromPile(getIndexFromPile(Card.CONSUL, gameState.getDiscard()));
        activator.complete();
        assert(gameState.getPlayerHand(0).contains(Card.CONSUL));
        assert(gameState.getActionDice().length == 1);
        assert(gameState.getActionDice()[0] == 1);

        activator = (AesculapinumActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
        activator.chooseCardFromPile(getIndexFromPile(Card.SCAENICUS, gameState.getDiscard()));
        activator.complete();
        assert(gameState.getPlayerHand(0).contains(Card.SCAENICUS));
        assert(!gameState.getPlayerHand(0).contains(Card.KAT));
        assert(gameState.getActionDice() == null || gameState.getActionDice().length == 0);
        assert(!gameState.isGameCompleted());



        System.out.println("Testing Aesculapinum passed!!\n");

    }


    public void testArchit() throws Exception {
        System.out.println("Testing Archit\n");

        gameState.setWhoseTurn(0);

        List<Card> discard = new ArrayList<Card>();
        gameState.setDiscard(discard);

        Collection<Card> hand = new ArrayList<Card>();
        hand.add(Card.ARCHITECTUS);
        hand.add(Card.TURRIS);
        hand.add(Card.MACHINA);
        hand.add(Card.FORUM);
        hand.add(Card.LEGAT);
        hand.add(Card.ONAGER);

        gameState.setPlayerHand(0, hand);

        //no cards on the field
        Card[] field = new Card[Rules.NUM_DICE_DISCS];
        for(int i = 0; i < field.length; i++) {
            field[i] = Card.NOT_A_CARD;
        }

        //player has enough Sestertiis to lay cards
        gameState.setPlayerSestertii(0, 30);

        gameState.setActionDice(new int[] {1,1,5});

        //===================== test1 ======================
        move.placeCard(Card.ARCHITECTUS, Rules.DICE_DISC_1);

        //the correct amount of money should be deducted
        //game removed from hand and get placed on the field
        assert(gameState.getPlayerSestertii(0) == 27);
        assert(gameState.getPlayerHand(0).size() == 5);
        assert(!gameState.getPlayerHand(0).contains(Card.ARCHITECTUS));

        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[0] == Card.ARCHITECTUS);

        //if you lay a building card now, it should still cost you money
        move.placeCard(Card.FORUM, Rules.DICE_DISC_3);
        assert(gameState.getPlayerSestertii(0) == 22);
        assert(gameState.getPlayerHand(0).size() == 4);
        assert(!gameState.getPlayerHand(0).contains(Card.FORUM));

        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[2] == Card.FORUM);

        //activate Architectus
        ArchitectusActivator activator = (ArchitectusActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
        activator.layCard(Card.MACHINA, 5);
        // activator.layCard(Card.LEGAT, 7);
        activator.layCard(Card.ONAGER, 3);
        activator.complete();

        //cards shouldn't have cost money to lay
        assert(gameState.getPlayerSestertii(0) == 22);

        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[0] == Card.ARCHITECTUS);
        assert(field[2] == Card.ONAGER);
        assert(field[4] == Card.MACHINA);
        assert(field[6] == Card.NOT_A_CARD);

        hand = gameState.getPlayerHand(0);
        assert(!hand.contains(Card.ONAGER));
        assert(!hand.contains(Card.MACHINA));
        assert(hand.contains(Card.LEGAT));

        discard = gameState.getDiscard();
        assert(discard.contains(Card.FORUM));

        //building cards should cost money to lay again
        move.placeCard(Card.TURRIS, 6);

        assert(gameState.getPlayerSestertii(0) == 22 - 6);

        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[5] == Card.TURRIS);

        hand = gameState.getPlayerHand(0);
        assert(!hand.contains(Card.TURRIS));


        System.out.println("Testing Archit passed!!\n");

    }

    public void testSen() throws Exception {
        System.out.println("Testing Senator\n");
        // Set up the player stats
        gameState.setPlayerVictoryPoints(0, 10);
        gameState.setPlayerVictoryPoints(1, 10);
        gameState.setPlayerSestertii(0, 3);
        gameState.setPlayerSestertii(1, 0);

        // Set up the game state for the test
        gameState.setWhoseTurn(0);
        gameState.setActionDice(new int [] {3, 3, 4});
        Collection<Card> hand = new ArrayList<Card> ();
        Collections.addAll(hand, Card.SENATOR,
                Card.VELITES,
                Card.PRAETORIANUS,
                Card.ESSEDUM,
                Card.CENTURIO,
                Card.SICARIUS,
                Card.ARCHITECTUS);
        gameState.setPlayerHand(0, hand);



        // Place the Tribunus Plebis on disc 3 and activate it
        move.placeCard(Card.SENATOR, 3);
        //This test is a stub and needs card activation testing
        SenatorActivator activator = (SenatorActivator) move.chooseCardToActivate(Rules.DICE_DISC_3);

        activator.layCard(Card.CENTURIO, 1);
        activator.layCard(Card.VELITES, Rules.DICE_DISC_2);

        System.out.println(Arrays.toString(gameState.getPlayerCardsOnDiscs(0)));

        activator.complete();
        assert (gameState.getPlayerCardsOnDiscs(0)[0].equals(Card.CENTURIO));
        assert (gameState.getPlayerCardsOnDiscs(0)[1].equals(Card.VELITES));
        assert (gameState.getPlayerCardsOnDiscs(0)[2].equals(Card.SENATOR));



        gameState.isGameCompleted();

        // Check that player has lost the necessary sestertii from laying
        // these cards
        assert(gameState.getPlayerVictoryPoints(0) == 10);
        assert(gameState.getPlayerVictoryPoints(1) == 10);
        assert(gameState.getPlayerSestertii(0) == 0);
        assert(gameState.getPlayerSestertii(1) == 0);




        activator = (SenatorActivator) move.chooseCardToActivate(Rules.DICE_DISC_3);
        activator.layCard(Card.SICARIUS, 3);
        activator.complete();

        assert (gameState.getPlayerCardsOnDiscs(0)[2].equals(Card.SICARIUS));

        System.out.println("Testing Senator passed!!\n");

    }

    public void testXXX() throws Exception {
        System.out.println("Testing x\n");


        System.out.println("Testing x passed!!\n");

    }
}
