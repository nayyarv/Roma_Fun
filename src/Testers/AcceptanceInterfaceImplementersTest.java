package Testers;

import Implementers.AcceptanceInterfaceImplementers;
import framework.cards.Card;
import framework.interfaces.AcceptanceInterface;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 20/05/12
 * Desc:
 */
public class AcceptanceInterfaceImplementersTest extends TestCase {

    //Each time, we create a new Acceptance Implementer
    AcceptanceInterface acceptance = new AcceptanceInterfaceImplementers();
    GameState gameState= acceptance.getInitialState();
    MoveMaker moveMaker = acceptance.getMover(gameState);

    public void testMoneyDiscs() throws Exception {

        System.out.println("Testing money discs");
        gameState.setWhoseTurn(0);
        gameState.setPlayerSestertii(0, 100);
        gameState.setActionDice(new int[] {6,3,5});
        gameState.isGameCompleted(); // Am currently printing stats through this
        moveMaker.activateMoneyDisc(6);
        gameState.isGameCompleted();

        moveMaker.activateMoneyDisc(5);
        gameState.isGameCompleted();

        moveMaker.activateMoneyDisc(3);
        gameState.isGameCompleted();

        assert (gameState.getPlayerSestertii(0)==114);
        System.out.println("Testing money discs passed !!\n");
    }

    public void testCardDisc() throws Exception{

        System.out.println("Testing card disc");
        gameState.setWhoseTurn(0);
        gameState.setActionDice(new int[] {6,3,5});

        List<Card> deck = new ArrayList<Card>();

        deck.add(Card.VELITES);
        deck.add(Card.FORUM);
        deck.add(Card.TRIBUNUSPLEBIS);
        deck.add(Card.AESCULAPINUM);
        deck.add(Card.TEMPLUM);
        deck.add(Card.CONSUL);
        deck.add(Card.PRAETORIANUS);

        gameState.setDeck(deck);

        moveMaker.activateCardsDisc(6, Card.AESCULAPINUM);

        System.out.println(gameState.getDiscard());
        gameState.isGameCompleted();

        System.out.println("Testing card disc passed!!\n");

    }

    public void testEndturn() throws Exception{
        System.out.println("Testing End turn\n");
        gameState.setWhoseTurn(0);
        gameState.setActionDice(new int[] {6,3,5});

        moveMaker.activateMoneyDisc(6);
        gameState.isGameCompleted();

        moveMaker.endTurn();
        gameState.setActionDice(new int[] {2,2,2});
        moveMaker.activateMoneyDisc(2);
        gameState.isGameCompleted();
        System.out.println("Testing End turn passed!!\n");
    }

    public void testCardLaying() throws Exception{

        System.out.println("Testing Card Laying\n");
        gameState.setWhoseTurn(0);
        gameState.setPlayerSestertii(0, 100);
        Collection<Card> hand = new ArrayList<Card>();

        hand.add(Card.CONSUL);
        hand.add(Card.LEGIONARIUS);
        hand.add(Card.LEGAT);
        hand.add(Card.PRAETORIANUS);
        hand.add(Card.ARCHITECTUS);


        gameState.setPlayerHand(0, hand);

        System.out.println(gameState.getPlayerHand(0));

        moveMaker.placeCard(Card.ARCHITECTUS,3);
        moveMaker.placeCard(Card.PRAETORIANUS,5);
        gameState.isGameCompleted();
        moveMaker.placeCard(Card.LEGAT,3);

        gameState.isGameCompleted();

        System.out.println("Testing Card laying passed!!\n");



    }

}
