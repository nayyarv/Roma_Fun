package Testers;

import Implementers.GameStateImplementer;
import framework.cards.Card;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 19/05/12
 * Desc:
 */
public class GameStateImplementerTest extends TestCase {

    GameStateImplementer gameStateImplementer = new GameStateImplementer();

    public void testSetWhoseTurn() throws Exception {
        System.out.println("Testing setWhoseturn");
        System.out.println(gameStateImplementer.getWhoseTurn());
        gameStateImplementer.setWhoseTurn(0);
        assert (gameStateImplementer.getWhoseTurn()==0);
        gameStateImplementer.setWhoseTurn(1);
        assert (gameStateImplementer.getWhoseTurn()==1);
        System.out.println("Turn functions passed!!\n");
    }

    public void testSetDeck() throws Exception {
        System.out.println("Testing setDeck and getDeck");
        List<Card> cardList= new ArrayList<Card>();
        Collections.addAll(cardList, Card.values());
        cardList.remove(Card.GRIMREAPER);
        cardList.remove(Card.TELEPHONEBOX);
        cardList.remove(Card.KAT);

        gameStateImplementer.setDeck(cardList);

        List<Card> deck = gameStateImplementer.getDeck();

        System.out.println("Cardlist size = "+cardList.size()+" deck size = "+deck.size());

        assert (deck.size()==cardList.size());
        System.out.println("Deck functions passed!!!\n");

    }


    public void testSetDiscard() throws Exception {
        //TODO: check top card in discard pile
        System.out.println("Testing testDiscard and getDiscard");
        List<Card> cardList= new ArrayList<Card>();
        Collections.addAll(cardList, Card.values());
        cardList.remove(Card.GRIMREAPER);
        cardList.remove(Card.TELEPHONEBOX);
        cardList.remove(Card.KAT);

        gameStateImplementer.setDiscard(cardList);

        List<Card> deck = gameStateImplementer.getDiscard();

        System.out.println("Cardlist size = "+cardList.size()+" deck size = "+deck.size());

        assert (deck.size()==cardList.size());
        System.out.println("Discard functions passed!!!\n");

    }



    public void testPlayerSestertii() throws Exception {
        gameStateImplementer.setPlayerSestertii(0,10);
        assert (gameStateImplementer.getPlayerSestertii(0)==10);
        assert !(gameStateImplementer.getPlayerSestertii(0)==100);
        gameStateImplementer.setPlayerSestertii(0,100);
        assert (gameStateImplementer.getPlayerSestertii(0)==100);
        assert !(gameStateImplementer.getPlayerSestertii(0)==10);

        gameStateImplementer.setPlayerSestertii(1,1000);
        assert (gameStateImplementer.getPlayerSestertii(1)==1000);
        assert !(gameStateImplementer.getPlayerSestertii(1)==10);
        assert !(gameStateImplementer.getPlayerSestertii(1)==100);

        gameStateImplementer.setPlayerSestertii(1,10020);
        assert (gameStateImplementer.getPlayerSestertii(1)==10020);
        gameStateImplementer.gameStats();
        System.out.println("Money functions passed!!!\n");

    }

    public void testPlayerVictoryPoints() throws Exception {
        System.out.println("Testing Victory Points");

        System.out.println(gameStateImplementer.getPlayerVictoryPoints(0));
        assert (gameStateImplementer.getPlayerVictoryPoints(1)==10);
        assert (gameStateImplementer.getPoolVictoryPoints()==16);

        gameStateImplementer.setPlayerVictoryPoints(0, 5);
        assert (gameStateImplementer.getPlayerVictoryPoints(0)==5);
        assert (gameStateImplementer.getPlayerVictoryPoints(1)==10);
        assert (gameStateImplementer.getPoolVictoryPoints()==21);

        gameStateImplementer.setPlayerVictoryPoints(1, 20);
        assert (gameStateImplementer.getPlayerVictoryPoints(0)==5);
        assert (gameStateImplementer.getPlayerVictoryPoints(1)==20);
        assert (gameStateImplementer.getPoolVictoryPoints()==11);

        System.out.println("VP functions passed!!!\n");
    }

    public void testGetPlayerHand() throws Exception {

    }

    public void testSetPlayerHand() throws Exception {

    }

    public void testGetPlayerCardsOnDiscs() throws Exception {

    }

    public void testSetPlayerCardsOnDiscs() throws Exception {

    }

    public void testGetActionDice() throws Exception {

    }

    public void testIsGameCompleted() throws Exception {

        assert !(gameStateImplementer.isGameCompleted());
        gameStateImplementer.setPlayerVictoryPoints(0, 40);
        assert (gameStateImplementer.isGameCompleted());

    }
}
