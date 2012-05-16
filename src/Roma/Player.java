package Roma;

import Roma.Cards.*;
import Roma.History.ActionData;
import Roma.PlayerInterfaceFiles.CancelAction;
import Roma.PlayerInterfaceFiles.PlayerInterface;

import java.util.ArrayList;


//TODO: Refactor Player
public class Player {
    public final int CANCEL = PlayerInterface.CANCEL;
    private final String name;
    private int playerID;

    private PlayArea playArea;
    private ArrayList<CardHolder> hand = new ArrayList<CardHolder>();
    private ArrayList<Dice> freeDice;
    private PlayerInterface playerInterface;
    private ActionData currentAction;


    //static constructors
    public static Player makeRealPlayer(int playerID, PlayArea playArea){
        return new Player(playerID, playArea);
    }

    public static Player makeDummyPlayer(int playerID, PlayArea playArea){
        return new Player(playerID, playArea, true);
    }

    private Player(int playerID, PlayArea playArea) {
        this.playArea = playArea;
        this.playerID = playerID;
        playerInterface = playArea.getPlayerInterface();
        this.name = playerInterface.getPlayerName(playerID);
    }

    private Player(int playerID, PlayArea playArea, boolean testing){
        this.playArea = playArea;
        this.playerID = playerID;
        playerInterface = playArea.getPlayerInterface();
        this.name = "dummyPlayer" + playerID;
    }


    //Simple Getters

    public String getName() {
        return name;
    }

    public int getPlayerID() {
        return playerID;
    }

    public ArrayList<CardHolder> getHand() {
        return hand;
    }

    public PlayerInterface getPlayerInterface() {
        return playerInterface;
    }

    public ArrayList<Dice> getFreeDice() {
        return freeDice;
    }


    //small simple functions

    public void addCardToHand(CardHolder c){
        if(c!=null) hand.add(c);
    }

    public void addCardListToHand(ArrayList<CardHolder> cardList){
        hand.addAll(cardList);
    }

    public int handSize() {
        return hand.size();
    }


    public int chooseCardIndexFromList(ArrayList<CardHolder> cardList, String ... typeFilter){
        int index = CANCEL;

        return index;
    }

    public void commit() throws CancelAction{
        final String strPrompt = "Commit?";
        final String strOption1 = "Yes";
        final String strOption2 = "No";
        int option;

        option = playerInterface.readInput(strPrompt, strOption1, strOption2);

        if(option == 1){
            currentAction.setCommit(true);
        } else if(option == 2){
            throw new CancelAction();
        }
    }

    //first action of turn
    public void rollActionDice() {
        final int YES = 1;
        final int NO = 2;

        boolean reroll = false;
        boolean validChoice = false;
        int option = CANCEL;

        freeDice = playArea.getDiceHolder().rollPlayerDice(playerID);
        playerInterface.printDiceList(freeDice);

        if(playArea.getDiceHolder().checkTriple(playerID)){
            while(!validChoice){
                option = playerInterface.readInput("You rolled a triple, would you like to reroll?",
                        "Yes",
                        "No");
                if(option == YES){
                    reroll = true;
                    validChoice = true;
                } else if (option == NO){
                    reroll = false;
                    validChoice = true;
                } else {
                    System.out.println("Please choose either yes or no.");
                }
            }
            if(reroll){
                rollActionDice();
            }
        }
    }

    //Main method that allows players to perform an action
    public boolean planningPhase(ActionData actionData) throws CancelAction{
        //internal #defines
        final String strPrompt = "Select Option:";
        final String strOption1 = "View action dice";
        final String strOption2 = "View Hand";
        final String strOption3 = "Show game stats";
        final String strOption4 = "End turn";
        final int VIEW_ACTION_DIE = 1;
        final int VIEW_HAND = 2;
        final int SHOW_GAME_STATS = 3;
        final int END_TURN = 4;

        int option = 0;
        boolean endTurn = false;

        currentAction = actionData;

        //choose an action
        option = playerInterface.readInput(strPrompt, strOption1, strOption2, strOption3, strOption4);

        if(option == VIEW_ACTION_DIE){
            currentAction.setUseDice(true);
            viewActionDice();
        } else if(option == VIEW_HAND){
            currentAction.setLayCard(true);
            viewHand();
        } else if(option == SHOW_GAME_STATS){
            playArea.printStats();
        } else if(option == END_TURN){
            endTurn = true;
        } else {
            playerInterface.printOut("Please choose a valid option.", true);
        }

        return endTurn;
    }

    private void viewActionDice() throws CancelAction {
        int chosenDieIndex;
        int chosenDieValue;

        chosenDieIndex = chooseDie(freeDice);
        if(chosenDieIndex == CANCEL) throw new CancelAction();
        chosenDieValue = freeDice.get(chosenDieIndex).getValue();

        currentAction.setActionDiceIndex(chosenDieIndex);
        currentAction.setDiceValue(chosenDieValue);

        useActionDie(chosenDieValue);
    }

    private void useActionDie(int chosenDieValue) throws CancelAction {
        final int ACTIVATE_CARD = 1;
        final int BRIBERY = 2;
        final int MONEY = 3;
        final int DRAW_CARD = 4;
        final int CANCEL_OPTION = 5;
        final String strPrompt = "Use on:";
        final String strOption1 = "Activate card";
        final String strOption2 = "Bribery Disc";
        final String strOption3 = "Money Disc";
        final String strOption4 = "Card Disc";
        final String strOption5 = "Cancel";

        int option = CANCEL;
        boolean validChoice = false;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        int position = chosenDieValue - 1;

        while(!validChoice){
            option = playerInterface.readInput(strPrompt, strOption1, strOption2, strOption3, strOption4, strOption5);
            if(option == ACTIVATE_CARD){
                if(diceDiscs.gatherData(this, position)){
                    currentAction.setDiscType(ActionData.DICE);
                    currentAction.setCardName(diceDiscs.getCardName(playerID, position));
                    validChoice = true;
                } else {
                    validChoice = false;
                }
            } else if(option == BRIBERY){
                if(diceDiscs.useBriberyDisc(this, chosenDieValue)){
                    currentAction.setDiscType(ActionData.BRIBERY);
                    currentAction.setCardName(diceDiscs.getCardName(playerID, DiceDiscs.BRIBERY_INDEX));
                    validChoice = true;
                } else {
                    validChoice = false;
                }
            } else if(option == MONEY){
                commit();
                currentAction.setDiscType(ActionData.MONEY);
                validChoice = true;
            } else if(option == DRAW_CARD){
                commit();
                currentAction.setDiscType(ActionData.CARD);
                validChoice = true;
            } else if(option == CANCEL_OPTION){
                throw new CancelAction();
            } else {
                System.out.println("Please choose a valid action");
            }
        }
    }

    private void viewHand() throws CancelAction{
        int chosenCardIndex = CANCEL;
        int chosenPosition = CANCEL;

        chosenCardIndex = playerInterface.getCardIndex(hand);
        if(chosenCardIndex == CANCEL) throw new CancelAction();
        currentAction.setCardIndex(chosenCardIndex);

        chosenPosition = chooseDiceDiscIndex();
        if(chosenPosition == CANCEL) throw new CancelAction();
        currentAction.setTargetDisc(chosenPosition);
    }

    public int chooseDiceDiscIndex() throws CancelAction{
        final String strPrompt = "Which disc?";
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        final int CANCEL_OPTION = 8;
        String [] dicePrompt = new String[8];

        for (int i=0; i<6;i++){
            dicePrompt[i] = "Dice Disc " + (i+1) + ": " +diceDiscs.getCardName(playerID, i);
        }
        dicePrompt[6] = "Bribery Disc" + diceDiscs.getCardName(playerID, DiceDiscs.BRIBERY_INDEX);
        dicePrompt[7] = "Cancel";

        int option = -1;
        boolean validChoice = false;

        while(!validChoice){
            option = playerInterface.readInput(strPrompt, dicePrompt);
            if(option > 0 && option <= DiceDiscs.CARD_POSITIONS){
                validChoice = true;
            } else if (option == CANCEL_OPTION) {
                throw new CancelAction();
            } else {
                System.out.println("Please choose a valid action");
            }
        }
        option--;
        return option;
    }

    private void useMoneyDisc(){
        diceDiscs.planMoneyDisc(playerID, chosenDieValue);
        chosenDieValue = null;
    }

    private void useCardDisc(){
        diceDiscs.useDrawDisc(playerID, chosenDieValue);
        drawCards(chosenDieValue.getValue());
        chosenDieValue = null;
    }

    public boolean layCard(CardHolder chosenCard, int chosenPosition){
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        MoneyManager moneyManager = playArea.getMoneyManager();
        boolean laid = true;

        if(moneyManager.loseMoney(playerID, chosenCard.getCost())){
            diceDiscs.layCard(playerID, chosenPosition, chosenCard);
        } else {
            laid = false;
        }
        
        return laid;
    }



    //choose a dice disc
    //return int
    public int chooseDie(ArrayList<Dice> diceList){
        final String strPrompt = "Which die do you want to use?";
        String[] diceValues = new String[diceList.size() + 1];
        int diceIndex = -1;
        boolean validChoice = false;

        for(int i = 0; i < diceList.size(); i++){
            diceValues[i] = diceList.get(i).getValue().toString();
        }
        diceValues[diceList.size()] = "Cancel";

        diceIndex = playerInterface.readIndex(strPrompt, diceValues);

        return diceIndex;
    }

    //input value
    //

    //Or maybe just have a function that requests a number from the player?
    //With "autoResponse" values when in testing mode?



    public void drawCards(int value) {
        ArrayList<CardHolder> tempHand = new ArrayList<CardHolder>();
        CardManager cardManager = playArea.getCardManager();
        CardHolder chosenCard = null;

        System.out.println("Drawing " + value + " cards...");

        for (int i = 0; i < value; i++) {
            tempHand.add(cardManager.drawACard());
        }

        while(chosenCard == null){
            chosenCard = chooseCardIndex(tempHand);
            if(chosenCard == null){
                System.out.println("You have to choose a card to draw.");
            }
        }

        hand.add(chosenCard);
    }

    public void checkPlayable() {
        MoneyManager moneyManager = playArea.getMoneyManager();
        for (CardHolder card : hand) {
            if (card.getCost() < moneyManager.getPlayerMoney(playerID)) {
                card.setPlayable(true);
            }
        }
    }
}
