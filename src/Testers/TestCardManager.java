package Testers;

import Roma.CardManager;
import Roma.Cards.Turris;
import Roma.PlayerInterfaceFiles.GamePlayerInterface;
import Roma.PlayArea;
import Roma.PlayerInterfaceFiles.PlayerInterface;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 12/05/12
 * Desc:
 */
public class TestCardManager {

    public static void main(String[] args){

        PlayArea playArea = new PlayArea("testing");
        CardManager cardManager = playArea.getCardManager();
        PlayerInterface playerInterface = new GamePlayerInterface();

        playerInterface.printOut(cardManager.getPlayingSize(), true);
        playerInterface.printOut(cardManager.getCardfromDeck(Turris.NAME).toString(), true);
        playerInterface.printOut(cardManager.getPlayingSize(), true);

        playArea.testFillHand(0);
        playArea.testFillHand(1);

        playArea.runGame();




    }
}
