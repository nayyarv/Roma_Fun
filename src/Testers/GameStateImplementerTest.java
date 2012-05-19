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

    }

    public void testGetDiscard() throws Exception {

    }

    public void testSetDiscard() throws Exception {

    }

    public void testGetPlayerSestertii() throws Exception {

    }

    public void testSetPlayerSestertii() throws Exception {

    }

    public void testGetPlayerVictoryPoints() throws Exception {

    }

    public void testSetPlayerVictoryPoints() throws Exception {

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

    public void testGetPoolVictoryPoints() throws Exception {

    }

    public void testIsGameCompleted() throws Exception {

    }
}
