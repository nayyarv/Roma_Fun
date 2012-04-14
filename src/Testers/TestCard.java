package Testers;


import Roma.Cards.*;
import Roma.*;

import java.util.Scanner;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 30/03/12
 * Desc:
 */
public class TestCard {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Roma game = new Roma();
        PlayArea haha = new PlayArea(game, input);
        Card sic = new Sicarius(haha);
        System.out.println(sic.toString());
    }
}
