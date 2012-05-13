package Testers;

import Roma.*;
import Roma.Cards.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 12/05/12
 * Desc:
 */
public class TestCardManager {

    public static void main(String[] args){

        CardManager cardManager= new CardManager(new PlayArea(new Roma(true)));
        PlayerInterface playerInterface = new PlayerInterface();

        playerInterface.printOut(cardManager.getPlayingSize());
        playerInterface.printOut(cardManager.getCardfromDeck(Turris.NAME).toString());
        playerInterface.printOut(cardManager.getPlayingSize());





    }
}
