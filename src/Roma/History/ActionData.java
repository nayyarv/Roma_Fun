package Roma.History;

import Roma.Roma;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 15/05/12
 * Desc:
 */
public class ActionData {
    /*
     * Action Data stores all the actions the player takes
     * 2 main actions possible
     *  - Using action dice (Index of Dice being used)
     *      - String - Disc Type - Dice/Money/Card
     *          - Which Dice Disc/DiceValue? (0-6)
     *              - Battle Dice (Value of dice thrown)
     *  - Laying a card (Index in hand)
     *      - TargetDiscindex (0-6)
     *
     */

    //#defines
    public static String DICE = "Dice"; //0-6
    public static String MONEY = "Money";
    public static String CARD = "Card";

    // Fields to be stored

    //For using action dice
    private int actionDiceIndex;
    private String discType;
    private int diceValue;
    private int battleDice;

    //For laying a card
    private int cardIndex;
    private int targetDisc;

    //For activating a card
    private ArrayList<Integer> activationData;


    //Called from playerInterface
    public ActionData(){

    }


}
