package Testers;


import Roma.Cards.*;
import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 30/03/12
 * Desc:
 */
public class TestMercator {

    public static void main(String[] args){
        Roma game = new Roma();
        PlayArea haha = new PlayArea(game);
        Card merc = new Mercator(haha);
        haha.getMoneyManager().gainMoney(Roma.PLAYER_ONE, 10);
        haha.getMoneyManager().gainMoney(Roma.PLAYER_TWO, 10);
        merc.activate(Roma.PLAYER_ONE);

        System.out.println(haha.getMoneyManager());
        System.out.println(haha.getVictoryTokens());
    }
}
