package Roma;

import Roma.Cards.*;
import Roma.History.ActionData;
import Roma.PlayerInterfaceFiles.PlayerInterface;

import java.util.ArrayList;


//TODO: Refactor Player
public class Player {
    private final String name;
    private int playerID;

    private PlayArea playArea;
    private ArrayList<CardHolder> hand = new ArrayList<CardHolder>();
    private ArrayList<Dice> freeDice;
    private PlayerInterface playerInterface;


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


    public int chooseCardIndexFromList(ArrayList<CardHolder> cardList, String ... typeFilter){
        int index = PlayerInterface.CANCEL;

        return index;
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

    //first action of turn
    public void rollActionDice() {
        final int YES = 1;
        final int NO = 2;

        boolean reroll = false;
        boolean validChoice = false;
        int option = PlayerInterface.CANCEL;

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
    public boolean planningPhase(ActionData actionData) {
        //internal #defines
        final int VIEW_ACTION_DIE = 1;
        final int VIEW_HAND = 2;
        final int SHOW_GAME_STATS = 3;
        final int END_TURN = 4;

        int option = 0;
        Dice chosenDie = null;
        boolean endTurn = false;

        //choose an action
        option = playerInterface.readInput("Select Option:",
                        "View action dice",
                        "View Hand",
                        "Show game stats",
                        "End turn");

        if(option == VIEW_ACTION_DIE){
            actionData.setUseDice(true);
            actionData.setActionDiceIndex(chooseDie(freeDice));
            if(chosenDie != null){
                chosenDie = useActionDie(chosenDie);
                if(chosenDie != null){
                    freeDice.add(chosenDie);
                }
            }
        } else if(option == VIEW_HAND){
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

    private void viewHand() {
        CardHolder chosenCard = null;
        int chosenPosition = 0;

        chosenCard = chooseCard(hand);
        if(chosenCard != null){
            chosenPosition = chooseDiceDisc();
            if(chosenPosition != PlayerInterface.CANCEL){
                if(!layCard(chosenCard, chosenPosition)){
                    hand.add(chosenCard);
                }
            } else {
                hand.add(chosenCard);
            }
        }
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


    public int chooseDiceDisc() {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        final int CANCEL_OPTION = 8;
        String [] dicePrompt = new String[8];

        for (int i=0; i<6;i++){
            dicePrompt[i] = "Dice Disc " + (i+1) + ": " +diceDiscs.getCardName(playerID, i);
        }
        dicePrompt[6] = "Bribery Disc" + diceDiscs.getCardName(playerID, 6);
        dicePrompt[7] = "Cancel";

        int option = -1;
        boolean validChoice = false;

        while(!validChoice){
            option = playerInterface.readInput("Which disc?",
                    dicePrompt);
            if(option > 0 && option <= DiceDiscs.CARD_POSITIONS){
                validChoice = true;
            } else if (option == CANCEL_OPTION) {
                option = PlayerInterface.CANCEL;
                validChoice = true;
            } else {
                System.out.println("Please choose a valid action");
            }
        }
        return option;
    }

    private Dice useActionDie(Dice chosenDie) {
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

        int option = PlayerInterface.CANCEL;
        boolean validChoice = false;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();

        while(!validChoice){
            option = playerInterface.readInput(strPrompt, strOption1, strOption2, strOption3, strOption4, strOption5);
            if(option == ACTIVATE_CARD){
                if(diceDiscs.activateCard(this, chosenDie.getValue(), chosenDie)){
                    chosenDie = null;
                    validChoice = true;
                } else {
                    validChoice = false;
                }
            } else if(option == BRIBERY){
                if(diceDiscs.useBriberyDisc(this, chosenDie)){
                    chosenDie = null;
                    validChoice = true;
                } else {
                    validChoice = false;
                }
            } else if(option == MONEY){
                diceDiscs.useMoneyDisc(playerID, chosenDie);
                chosenDie = null;
                validChoice = true;
            } else if(option == DRAW_CARD){
                diceDiscs.useDrawDisc(playerID, chosenDie);
                drawCards(chosenDie.getValue());
                chosenDie = null;
                validChoice = true;
            } else if(option == CANCEL_OPTION){
                validChoice = true;
            } else {
                System.out.println("Please choose a valid action");
            }
        }

        return chosenDie;
    }

    public void printCardList(ArrayList<CardHolder> cardList){
        int i = 1;
        System.out.println("-------------------------------------");
        for(CardHolder card : cardList){
            System.out.println(i + ") " + card.getName());
            i++;
        }
    }

    //choose from list
    //input: ArrayList (of dice or of cards)
    //return int
    public CardHolder chooseCard(ArrayList<CardHolder> cardList){
        final String strPrompt = "Possible actions:";
        final String strOption1 = "Choose a card";
        final String strOption2 = "Check description";
        final String strOption3 = "Print card list";
        final String strOption4 = "Cancel/End selection";

        final int
                CHOOSE_CARDS = 1,
                CHECK_DESC = 2,
                PRINT_CARDS = 3;

        CardHolder choice = null;
        int action = 0;
        boolean validChoice = false;

        printCardList(cardList);

        if(cardList.size() == 0){
            System.out.println("There are no cards!");
        } else {
            while(!validChoice){
                action = playerInterface.readInput(strPrompt, strOption1, strOption2, strOption3, strOption4);

                if(action == CHOOSE_CARDS){
                    System.out.print("Card number: ");
                    int cardChoice = playerInterface.getIntegerInput(cardList.size());
                    choice = cardList.remove(cardChoice - 1);
                    validChoice = true;
                } else if(action == CHECK_DESC){
                    System.out.print("Check which card number: ");
                    action = playerInterface.getIntegerInput(cardList.size());
                    System.out.println(cardList.get(action - 1).toString());
                } else if(action == PRINT_CARDS){
                    printCardList(cardList);
                } else if(action == PlayerInterface.CANCEL){
                    choice = null;
                    validChoice = true;
                } else {
                    System.out.println("Please choose a valid action");
                }
            }
        }

        return choice;
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
            chosenCard = chooseCard(tempHand);
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

    //TODO: Nothing happening
    public boolean commit() {
        boolean confirm = true;

        // player confirms
        confirm = true;

        if (confirm) {

        }
        return confirm;
    }
}
