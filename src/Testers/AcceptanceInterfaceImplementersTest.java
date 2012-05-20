package Testers;

import Implementers.AcceptanceInterfaceImplementers;
import framework.interfaces.AcceptanceInterface;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;
import junit.framework.TestCase;

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

        gameState.setWhoseTurn(0);
        gameState.setPlayerSestertii(0, 100);
        gameState.setActionDice(new int[] {6,3,5});
        gameState.isGameCompleted(); // Am currently printing stats through this
        moveMaker.activateMoneyDisc(6);
        gameState.isGameCompleted();

        moveMaker.activateMoneyDisc(3);
        gameState.isGameCompleted();

        moveMaker.activateMoneyDisc(5);
        gameState.isGameCompleted();
    }


}
