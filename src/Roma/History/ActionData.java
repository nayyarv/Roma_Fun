package Roma.History;

import Roma.PlayerInterfaceFiles.PlayerInterface;

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

    public static final int CANCEL = PlayerInterface.CANCEL;

    //#defines for action dice
    public static final String DICE = "Dice"; //0-5     //used
    public static final String BRIBERY = "Bribery";     //used
    public static final String MONEY = "Money";         //used
    public static final String CARD = "Card";

    public final int playerID;

    //action types
    private boolean layCard = false;    //used
    private boolean useDice = false;    //used
    private boolean commit = false;     //used

    //For using action dice
    private int actionDiceIndex = CANCEL;    //used
    private String discType = "";        //used 1 2 3 4
    private int position = CANCEL;
    private int diceValue = CANCEL;          //used
    private int battleDice = CANCEL;         //used
    private String cardName = "";        //used
    private int drawCardIndex = CANCEL;

    //For laying a card
    private int cardIndex = CANCEL;          //used
    private int targetDisc = CANCEL;         //used

    //For activating a card
    private ArrayList<Integer> activationData;


    //Called from playerInterface
    public ActionData(int playerID){
        this.playerID = playerID;
        activationData = new ArrayList<Integer>();
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

    public boolean isCommit() {
        return commit;
    }

    public void setCommit(boolean commit) {
        this.commit = commit;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public int getDrawCardIndex() {
        return drawCardIndex;
    }

    public void setDrawCardIndex(int drawCardIndex) {
        this.drawCardIndex = drawCardIndex;
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
        this.activationData.addAll(activationData);
    }

    public String toString(){
        return "PlayerID: " + playerID
                + "\nlayCard: " + layCard
                + "\nuseDice: " + useDice
                + "\ncommit: " + commit
                + "\nactionDieIndex: " + actionDiceIndex
                + "\ndiscType: " + discType
                + "\nposition: " + position
                + "\ndiceValue: " + diceValue
                + "\nbattleDice: " + battleDice
                + "\ncardName: " + cardName
                + "\ndrawCardIndex: " + drawCardIndex
                + "\ncardIndex:" + cardIndex
                + "\ntargetDisc:" + targetDisc
                + "\nactivationData:" + activationData
                + "\n";
    }
}
