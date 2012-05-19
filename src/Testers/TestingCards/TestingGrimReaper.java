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
public class TestingGrimReaper {

    public static void main(String []args){
        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(Card.ONAGER);



        GameStateImplementer gameStateImplementer = new GameStateImplementer();

        gameStateImplementer.setPlayerHand(0, hand);
        int[] num = {1,1,1};
        gameStateImplementer.setWhoseTurn(0);
        gameStateImplementer.setPlayerSestertii(0, 100);
        //Set action dice not working
        gameStateImplementer.setActionDice(num);
        Card[] diceDiscs1 = {Card.CENTURIO, Card.CONSUL, Card.CONSUL, Card.CONSUL,
                Card.CONSUL, Card.CONSUL, Card.CENTURIO};
        Card[] diceDiscs2 = {Card.KAT, Card.CONSUL, Card.GRIMREAPER, Card.CONSUL,
                Card.CONSUL, Card.CONSUL, Card.KAT};
        gameStateImplementer.setPlayerCardsOnDiscs(0, diceDiscs1);
        gameStateImplementer.setPlayerCardsOnDiscs(1, diceDiscs2);
        gameStateImplementer.printStats();
        System.err.println(gameStateImplementer.getDiscard());
        gameStateImplementer.runGame();
        System.err.println(gameStateImplementer.getDiscard());

    }


}
