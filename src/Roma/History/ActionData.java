package Roma.History;

import Roma.Roma;

import java.util.ArrayList;

/**
 * File Name: ActionData
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
     */

    //#defines
    public static final String DICE = "Dice"; //0-6
    public static final String MONEY = "Money";
    public static final String CARD = "Card";

    //action types
    private boolean layCard = false;
    private boolean useDice = false;
    private boolean actionTaken = false;

    //For using action dice
    private int actionDiceIndex;
    private String discType;
    private int diceValue;
    private int battleDice;
    private String cardName;

    //For laying a card
    private int cardIndex;
    private int targetDisc;

    //For activating a card
    private ArrayList<Integer> activationData;


    //Called from playerInterface
    public ActionData(){

    }

    public boolean isLayCard() {
        return layCard;
    }

    public void setLayCard(boolean layCard) {
        this.layCard = layCard;
    }

    public boolean isUseDice() {
        return useDice;
    }

    public void setUseDice(boolean useDice) {
        this.useDice = useDice;
    }

    public boolean isActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(boolean actionTaken) {
        this.actionTaken = actionTaken;
    }

    public int getActionDiceIndex() {
        return actionDiceIndex;
    }

    public void setActionDiceIndex(int actionDiceIndex) {
        this.actionDiceIndex = actionDiceIndex;
    }

    public String getDiscType() {
        return discType;
    }

    public void setDiscType(String discType) {
        this.discType = discType;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }

    public int getBattleDice() {
        return battleDice;
    }

    public void setBattleDice(int battleDice) {
        this.battleDice = battleDice;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public void setCardIndex(int cardIndex) {
        this.cardIndex = cardIndex;
    }

    public int getTargetDisc() {
        return targetDisc;
    }

    public void setTargetDisc(int targetDisc) {
        this.targetDisc = targetDisc;
    }

    public ArrayList<Integer> getActivationData() {
        return activationData;
    }

    public void setActivationData(ArrayList<Integer> activationData) {
        this.activationData = activationData;
    }
}
