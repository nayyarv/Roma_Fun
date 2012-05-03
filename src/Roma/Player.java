package Roma;

import Roma.Cards.Card;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private final static int CANCEL = -1;
    private final String name;

    private PlayArea playArea;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private ArrayList<Dice> freeDice;
    private Scanner input;
    private PlayerInterface playerInterface;


    private boolean testing;
    private int playerID;
    private boolean autoRoll;

    public static Player makeRealPlayer(int playerID, PlayArea playArea){
        return new Player(playerID, playArea);
    }

    public static Player makeDummyPlayer(int playerID, PlayArea playArea){
        return new Player(playerID, playArea, true);
    }

    private Player(int playerID, PlayArea playArea) {
        this.playArea = playArea;
        this.playerID = playerID;
        this.input = new Scanner(System.in);
        System.out.print("Name of player" + (playerID + 1) + ": ");
        this.name = input.nextLine();
        playerInterface = new PlayerInterface();

    }

    private Player(int playerID, PlayArea playArea, boolean testing){
        this.playArea = playArea;
        this.playerID = playerID;
        this.input = null;
        this.name = "dummyPlayer" + playerID;

    }



    public String getName() {
        return name;
    }

    //Main method that allows players to perform an action
    public boolean takeAction() {
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
            chosenDie = chooseDie(freeDice);
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
            System.out.println("Please choose a valid option.");
        }

        return endTurn;
    }

    private void viewHand() {
        Card chosenCard = null;
        int chosenPosition = -1;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        MoneyManager moneyManager = playArea.getMoneyManager();

        chosenCard = chooseCard(hand);
        if(chosenCard != null){
            chosenPosition = chooseCardDisc();
            if(moneyManager.loseMoney(playerID, chosenCard.getCost())){
                diceDiscs.layCard(playerID, chosenPosition, chosenCard);
            } else {
                hand.add(chosenCard);
            }
        }
    }

    public int chooseCardDisc() {
        final int CANCEL_OPTION = 8;

        int option = -1;
        int discTarget;
        boolean validChoice = false;

        while(!validChoice){
            option = playerInterface.readInput("Which disc?",
                    "Dice Disc 1",
                    "Dice Disc 2",
                    "Dice Disc 3",
                    "Dice Disc 4",
                    "Dice Disc 5",
                    "Dice Disc 6",
                    "Bribery Disc",
                    "Cancel");
            if(option > 0 && option <= DiceDiscs.CARD_POSITIONS){
                validChoice = true;
            } else if (option == CANCEL_OPTION) {
                option = CANCEL;
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

        int option = CANCEL;
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

    private void printDiceList(ArrayList<Dice> diceList) {
        System.out.println("Dice:");
        int i = 0;
        for(Dice dice : diceList){
            i++;
            System.out.println(i + ") " + dice.getValue());
        }
    }

    public void printHand(){
        printCardList(hand);
    }

    public void printCardList(ArrayList<Card> cardList){
        int i = 1;
        System.out.println("-------------------------------------");
        for(Card card: cardList){
            System.out.println(i + ") " + card.getName());
            i++;
        }
    }

    //choose from list
    //input: ArrayList (of dice or of cards)
    //return int
    public Card chooseCard(ArrayList<Card> cardList){
        final String strPrompt = "Possible actions:";
        final String strOption1 = "Choose a card";
        final String strOption2 = "Check description";
        final String strOption3 = "Print card list";
        final String strOption4 = "Cancel/End selection";

        Card choice = null;
        int action = 0;
        boolean validChoice = false;

        printCardList(cardList);

        if(cardList.size() == 0){
            System.out.println("There are no cards!");
        } else {
            while(!validChoice){
                action = playerInterface.readInput(strPrompt, strOption1, strOption2, strOption3, strOption4);

                if(action == 1){
                    System.out.print("Card number: ");
                    int cardChoice = playerInterface.getIntegerInput(cardList.size());
                    choice = cardList.remove(cardChoice - 1);
                    validChoice = true;
                } else if(action == 2){
                    System.out.print("Check which card number: ");
                    action = input.nextInt();
                    System.out.println(cardList.get(action - 1).toString());
                } else if(action == 3){
                    printCardList(cardList);
                } else if(action == 4){
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
    public Dice chooseDie(ArrayList<Dice> diceList){
        Dice choice = null;
        int number = -1;
        boolean validChoice = false;

        while(!validChoice){
            System.out.println("Which die do you want to use?");
            int i = 0;
            for(Dice die : diceList){
                i++;
                System.out.println(i + ") " + die.getValue());
            }
            i++;
            System.out.println(i + ") Cancel");
            number = input.nextInt();
            if(number < 1 || number > diceList.size() + 1){
                System.out.println("Please choose a valid die");
            } else {
                validChoice = true;
                if(number <= diceList.size()){
                    choice = diceList.remove(number - 1);
                }
            }
        }

        return choice;
    }

    public ArrayList<Dice> getFreeDice() {
        return freeDice;
    }

    //input value
    //

    //Or maybe just have a function that requests a number from the player?
    //With "autoResponse" values when in testing mode?

    public void rollActionDice() {
        final int YES = 1;
        final int NO = 2;

        boolean reroll = false;
        boolean validChoice = false;
        int option = CANCEL;

        freeDice = playArea.getDiceHolder().rollPlayerDice(playerID);
        printDiceList(freeDice);

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

    public void drawCards(int value) {
        ArrayList<Card> tempHand = new ArrayList<Card>();
        CardManager cardManager = playArea.getCardManager();
        Card chosenCard = null;

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

    public void layCard() {
        Card card = null;
        int playerChoice = 0;
        int position = 3;

        // player chooses a card in hand
        do {
            playerChoice = 0;
            card = hand.get(playerChoice);
            position = 4;
        } while (!commit() && !card.isPlayable());


        //playArea.getDiceDiscs().placeCard(card, position);
    }

    public void checkPlayable() {
        MoneyManager moneyManager = playArea.getMoneyManager();
        for (Card card : hand) {
            if (card.getCost() < moneyManager.getPlayerMoney(playerID)) {
                card.setPlayable(true);
            }
        }
    }

    public boolean commit() {
        boolean confirm = true;

        // player confirms
        confirm = true;

        if (confirm) {

        }
        return confirm;
    }

    public boolean getAutoRoll() {
        return autoRoll;
    }

    public void setAutoRoll(boolean autoRoll) {
        this.autoRoll = autoRoll;
    }

    public int handSize() {
        return hand.size();
    }

    public void addCardToHand(Card c){
        if(c!=null) hand.add(c);
    }

    public void addCardListToHand(ArrayList<Card> cardList){
        hand.addAll(cardList);
    }

    public int getPlayerID() {
        return playerID;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public PlayerInterface getPlayerInterface() {
        return playerInterface;
    }
}
