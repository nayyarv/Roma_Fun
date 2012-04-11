package Testers;

import testingPlayArea.*;

import java.util.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 30/03/12
 * Desc:
 */
public class TestCard {

    public static void main(String[] args){
        Roma game = new Roma();
        PlayArea haha = new PlayArea(game);
        Card sic = new Sicarius(haha);
        System.out.println(sic.toString());





    }
}
