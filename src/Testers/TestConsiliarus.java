package Testers;

import Roma.*;
import Roma.Cards.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 1/05/12
 * Desc:
 */
public class TestConsiliarus {

    public static void main(String[] args){
        PlayArea playArea = new PlayArea(true);
        Card temp = new Consiliarus(playArea);

        playArea.getCardfromDeckAndAddToHand("Senator", Roma.PLAYER_ONE);
        playArea.getCardfromDeckAndAddToHand("Sicarius", Roma.PLAYER_ONE);
        playArea.getCardfromDeckAndAddToHand("Gladiator", Roma.PLAYER_ONE);
        playArea.getCardfromDeckAndAddToHand("Sicarius", Roma.PLAYER_ONE);
        System.err.println(playArea.getPlayer(Roma.PLAYER_ONE).getHand().toString());




    }
}
