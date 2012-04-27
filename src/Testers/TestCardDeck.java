package Testers;

import Roma.*;
import Roma.Cards.*;

/**
 * File name:
 * Creator: Varun Nayyar
 * Date: 19/03/12
 * Desc:
 */
public class TestCardDeck {

    static CardManager test = new CardManager(new PlayArea(new Roma()));

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            //test.insertCard(new Card(i));
        }//Dummy fill function
        testDisplay();
        Card tmp = test.drawACard();
        System.out.println("Drawing a Card. It is: " + tmp);
        testDisplay();
        System.out.println("Putting a card back. It is 100");
        //test.insertCard(new Card(100));
        testDisplay();
        test.insertCard(tmp);
        testDisplay();
        test.shuffle();
        testDisplay();

        for (int i = 0; i < 15; i++) {
            System.out.println();
            test.discard(test.drawACard());
            testDisplay();
        }
    }

    public static void testDisplay() {
        //System.out.println("Size of is: " + test.getSize());
        System.out.println(test.toString());
    }

}
