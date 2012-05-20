package Testers;

import Implementers.AcceptanceInterfaceImplementers;
import framework.Rules;
import framework.cards.Card;
import framework.interfaces.AcceptanceInterface;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;
import framework.interfaces.activators.ConsulActivator;
import framework.interfaces.activators.MercatorActivator;
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
    MoveMaker moveMaker = acceptance.getMover(gameState);

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
        moveMaker.placeCard(Card.TRIBUNUSPLEBIS, 3);
        gameState.isGameCompleted();
        moveMaker.chooseCardToActivate(3).complete();


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
        moveMaker.placeCard(Card.LEGAT, 3);
        moveMaker.chooseCardToActivate(3).complete();
//        for (Card currentCard : gameState.getPlayerHand(0)) {
//        	System.out.println(currentCard);
//        }


        // Check that player 0 has gained 7 victory points, but player
        // 1's score has not changed
        assert(gameState.getPlayerVictoryPoints(0) == 17);
        assert(gameState.getPlayerVictoryPoints(1) == 10);
        assert(gameState.getPlayerSestertii(0) == 0);
        assert(gameState.getPlayerSestertii(1) == 100);

        moveMaker.endTurn();
        moveMaker.placeCard(Card.LEGIONARIUS, Rules.DICE_DISC_1);
        moveMaker.placeCard(Card.AESCULAPINUM, Rules.DICE_DISC_2);
        moveMaker.placeCard(Card.CONSUL, Rules.DICE_DISC_4);
        moveMaker.placeCard(Card.ESSEDUM, Rules.DICE_DISC_6);
        moveMaker.placeCard(Card.MACHINA, Rules.BRIBE_DISC);
        moveMaker.endTurn();
        gameState.setActionDice(new int [] {3, 3, 4});

        assert(gameState.getPlayerVictoryPoints(0) == 11);
        assert(gameState.getPlayerVictoryPoints(1) == 3);
        assert(gameState.getPlayerSestertii(0) == 0);
        assert(gameState.getPlayerSestertii(1) == 100 - 4 - 5 - 3 - 6 - 4);

        // Activate the Legat again
        moveMaker.chooseCardToActivate(3).complete();

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

        moveMaker.placeCard(Card.MERCATOR, Rules.DICE_DISC_1);

        //the correct amount of money should be deducted
        //game removed from hand and get placed on the field
        assert(gameState.getPlayerHand(0).size() == 1);
        assert(!gameState.getPlayerHand(0).contains(Card.MERCATOR));
        Card[] field;
        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[0] == Card.MERCATOR);

        assert(gameState.getPoolVictoryPoints() == 36 - 2 - 6);
        assert(!gameState.isGameCompleted());

        MercatorActivator activator = (MercatorActivator) moveMaker.chooseCardToActivate(Rules.DICE_DISC_1);

        activator.chooseMercatorBuyNum(1);
        activator.complete();
        assert(gameState.getPlayerVictoryPoints(0) == 2 + 1);
        assert(gameState.getPlayerSestertii(0) == 100 - 2*1 - 7);
        assert(gameState.getPlayerVictoryPoints(1) == 6 - 1);
        assert(gameState.getPlayerSestertii(1) == 100 + 2*1);
        assert(gameState.getPoolVictoryPoints() == 36 - 2 - 6);

        activator = (MercatorActivator) moveMaker.chooseCardToActivate(Rules.DICE_DISC_1);

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

        moveMaker.placeCard(Card.CONSUL, Rules.DICE_DISC_1);

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

        ConsulActivator activator = (ConsulActivator) moveMaker.chooseCardToActivate(1);

        System.out.println(Arrays.toString(gameState.getActionDice()));

        activator.chooseWhichDiceChanges(2);
        activator.chooseConsulChangeAmount(-1);
        activator.complete();

        System.out.println(Arrays.toString(gameState.getActionDice()));

        assert(gameState.getActionDice().length == 2);
        assert(gameState.getActionDice()[0] == 1);
        assert(gameState.getActionDice()[1] == 1);
        activator = (ConsulActivator) moveMaker.chooseCardToActivate(1);

        activator.chooseWhichDiceChanges(1);
        activator.chooseConsulChangeAmount(1);
        activator.complete();

        assert(gameState.getActionDice().length == 1);
        assert(gameState.getActionDice()[0] == 2);

        assert(gameState.getPoolVictoryPoints() == 36 - 15*Rules.NUM_PLAYERS);
        assert(!gameState.isGameCompleted());

        System.out.println("Testing Consul passed!!!\n");
    }

}
