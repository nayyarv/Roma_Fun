package Testers;

import Roma.Cards.Card;
import Roma.GamePlayerInterface;
import Roma.PlayArea;
import Roma.PlayerInterface2;

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


        playArea.testFillHand(0);
        playArea.testFillHand(1);


        playArea.getPlayer(0).printHand();
        //playArea.getPlayer(1).printHand();

        playerInterface.printOut("Building Choice");

        playerInterface.getHandIndex(playArea.getPlayer(0).getHand(), Card.BUILDING);



        playerInterface.getHandIndex(playArea.getPlayer(0).getHand(), Card.CHARACTER);



    }
}
