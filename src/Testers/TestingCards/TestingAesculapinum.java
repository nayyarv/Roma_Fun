package Testers.TestingCards;

import Implementers.GameStateImplementer;
import framework.cards.Card;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 19/05/12
 * Desc:
 */

public class TestingAesculapinum {

    public static void main(String []args){
        ArrayList<Card> hand = new ArrayList<Card>();

        GameStateImplementer gameStateImplementer = new GameStateImplementer("testing");

        gameStateImplementer.setWhoseTurn(0);

        hand.add(Card.ONAGER);
        hand.add(Card.TEMPLUM);
        hand.add(Card.TEMPLUM);
        hand.add(Card.TEMPLUM);
        gameStateImplementer.setPlayerHand(0, hand);
        ArrayList<Card> newDiscardPile = new ArrayList<Card>();
        newDiscardPile.add(Card.KAT);
        newDiscardPile.add(Card.TURRIS);
        newDiscardPile.add(Card.CONSUL);
        gameStateImplementer.setDiscard(newDiscardPile);

        int[] num = {1,1,1};
        gameStateImplementer.setActionDice(num);
        gameStateImplementer.setPlayerSestertii(0, 1000);
        gameStateImplementer.setPlayerSestertii(1, 1000);
        gameStateImplementer.setPlayerVictoryPoints(0, 17);
        gameStateImplementer.setPlayerVictoryPoints(1, 17);

        Card[] diceDiscs1 = {Card.AESCULAPINUM, Card.NERO, Card.NERO, Card.NERO,
                Card.NERO, Card.NERO, Card.NERO};
        gameStateImplementer.setPlayerCardsOnDiscs(0, diceDiscs1);
        Card[] diceDiscs2 = {Card.KAT, Card.CONSUL, Card.TURRIS, Card.TURRIS,
                Card.CONSUL, Card.CONSUL, Card.SICARIUS};
        gameStateImplementer.setPlayerCardsOnDiscs(1, diceDiscs2);
        System.err.println("Cards in deck: \n" + gameStateImplementer.getDeck());
        System.err.println("Cards in discard: \n" + gameStateImplementer.getDiscard());

        gameStateImplementer.isGameCompleted();
        gameStateImplementer.runGame();

        System.err.println(gameStateImplementer.getDiscard());
    }


}
