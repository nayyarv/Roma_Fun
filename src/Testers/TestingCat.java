package Testers;

import Implementers.GameStateImplementer;
import framework.cards.Card;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 19/05/12
 * Desc:
 */
public class TestingCat {

    public static void main(String []args){
        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(Card.ONAGER);



        GameStateImplementer gameStateImplementer = new GameStateImplementer();

        gameStateImplementer.setPlayerHand(0, hand);
        int[] num = {1,1};
        gameStateImplementer.setWhoseTurn(0);
        gameStateImplementer.setPlayerSestertii(0, 100);
        //Set action dice not working
        gameStateImplementer.setActionDice(num);
        Card[] diceDiscs = {Card.KAT, Card.CONSUL, Card.NOT_A_CARD, Card.NOT_A_CARD,
                Card.NOT_A_CARD, Card.NOT_A_CARD, Card.NOT_A_CARD};
        gameStateImplementer.setPlayerCardsOnDiscs(0, diceDiscs);
        gameStateImplementer.printStats();
        System.err.println(gameStateImplementer.getDiscard());
        gameStateImplementer.runGame();
        System.err.println(gameStateImplementer.getDiscard());

    }


}
