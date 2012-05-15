package Testers;

import Roma.*;
import Roma.Cards.Card;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 14/05/12
 * Desc:
 */
public class TestGamePlayerInterface {

    public static void main (String[] args){
        PlayArea playArea = new PlayArea("testing");
        PlayerInterface2 playerInterface = playArea.getPlayerInterface();
        GameRules gameRules = playArea.getGameRules();

        playArea.testFillHand(0);

        gameRules.layAllCardsInHand();


        playerInterface.printOut("Discs that are buildings");
        playerInterface.getHandIndex(playArea.getDiceDiscs().toList(0), Card.BUILDING);
//
//        playArea.getPlayer(0).printHand();
//
//        Player player1 = playArea.getPlayer(0);
//
//        Player player2 = playArea.getPlayer(1);
//
//        DiceDiscs diceDiscs = playArea.getDiceDiscs();
//
//
//
//        //playArea.getPlayer(1).printHand();
//
//        playerInterface.printOut("Building Choice");
//
//        playerInterface.getHandIndex(playArea.getPlayer(0).getHand(), Card.BUILDING);
//
//
//
//        playerInterface.getHandIndex(playArea.getPlayer(0).getHand(), Card.CHARACTER);



    }
}
