package Testers;


import Roma.Cards.CardBase;
import Roma.Cards.Sicarius;
import Roma.PlayArea;
import Roma.Roma;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 30/03/12
 * Desc:
 */
public class TestCard {

    public static void main(String[] args) {
        Roma game = new Roma();
        PlayArea haha = new PlayArea(game);
        CardBase sic = new Sicarius(haha);
        System.out.println(sic.toString());
    }
}
