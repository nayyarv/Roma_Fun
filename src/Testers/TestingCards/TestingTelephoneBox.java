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

public class TestingTelephoneBox {

    public static void main(String []args){
        ArrayList<Card> hand = new ArrayList<Card>();

        GameStateImplementer gameStateImplementer = new GameStateImplementer("testing");

        gameStateImplementer.setWhoseTurn(0);

        hand.add(Card.TURRIS);
        hand.add(Card.TURRIS);
        hand.add(Card.TURRIS);
        hand.add(Card.TURRIS);
        hand.add(Card.TURRIS);
        hand.add(Card.TURRIS);
        gameStateImplementer.setPlayerHand(0, hand);
        gameStateImplementer.setPlayerHand(1, hand);

        int[] num = {1,3,5};
        gameStateImplementer.setActionDice(num);
        gameStateImplementer.setPlayerSestertii(0, 1000);
        gameStateImplementer.setPlayerSestertii(1, 1000);
        gameStateImplementer.setPlayerVictoryPoints(0, 10);
        gameStateImplementer.setPlayerVictoryPoints(1, 10);

        Card[] diceDiscs1 = {Card.LEGIONARIUS, Card.LEGIONARIUS, Card.LEGIONARIUS, Card.LEGIONARIUS,
                Card.LEGIONARIUS, Card.LEGIONARIUS, Card.TELEPHONEBOX};
        gameStateImplementer.setPlayerCardsOnDiscs(0, diceDiscs1);
        Card[] diceDiscs2 = {Card.SICARIUS, Card.FORUM, Card.TEMPLUM, Card.SICARIUS,
                Card.SICARIUS, Card.SICARIUS, Card.TELEPHONEBOX};
        gameStateImplementer.setPlayerCardsOnDiscs(1, diceDiscs2);

        gameStateImplementer.isGameCompleted();
        gameStateImplementer.runGame();
        gameStateImplementer.isGameCompleted();

        System.err.println(gameStateImplementer.getDiscard());
    }
}
