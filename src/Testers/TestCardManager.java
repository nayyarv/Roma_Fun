package Testers;

import Roma.CardManager;
import Roma.Cards.Turris;
import Roma.PlayArea;
import Roma.PlayerInterface;

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
        PlayerInterface playerInterface = new PlayerInterface();

        playerInterface.printOut(cardManager.getPlayingSize());
        playerInterface.printOut(cardManager.getCardfromDeck(Turris.NAME).toString());
        playerInterface.printOut(cardManager.getPlayingSize());





    }
}
