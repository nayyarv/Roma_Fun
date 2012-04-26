package Roma;

import Roma.Cards.Card;

import java.util.*;

public class Player {
    private final static int CANCEL = -1;
    private String name;
    private PlayArea playArea;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private ArrayList<Dice> freeDice;
    private Scanner input;

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

    //TODO: Fill out player actions
    //Main method that allows players to perform an action
    public boolean takeAction() {
        int option = 0;
        Card chosenCard = null;
        Dice chosenDie = null;

        printDiceList(freeDice);
        printCardList(hand);

        //choose an action
        System.out.println("Select option:\n" +
                           "1) View action dice\n" +
                           "2) View hand\n" +
                           "3) Show game stats\n" +
                           "4) Free dice available\n" +
                           "5) End turn");
        option = input.nextInt();

        if(option == 1){
            chosenDie = chooseDie(freeDice);
        } else if(option == 2){
            chosenCard = chooseCard(hand);
        } else if(option == 3){
            playArea.printStats();
        } else if(option == 4){

        } else if(option == 5){

        } else if(option == 6){

        } else {

        }

        return false;
    }

    private void printDiceList(ArrayList<Dice> diceList) {
        System.out.println("Dice:");
        int i = 0;
        for(Dice dice : diceList){
            i++;
            System.out.println(i + ") " + dice.getValue());
        }
    }

    public void printCardList(List<Card> cardList){
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
    public Card chooseCard(List<Card> cardList){
        Card choice = null;
        int action = 0;
        boolean validChoice = false;

        printCardList(cardList);

        while(!validChoice){
            System.out.println("-------------------------------------");
            System.out.print("Possible actions:\n" +
                    "1) Choose card\n" +
                    "2) Check description\n" +
                    "3) Print card list\n" +
                    "4) Cancel\n");

            action = input.nextInt();

            if(action == 1){
                System.out.print("Card number: ");
                choice = cardList.remove(input.nextInt() - 1);
                validChoice = true;
            } else if(action == 2){
                System.out.print("Check which card number: ");
                action = input.nextInt();
                cardList.get(action).toString();
            } else if(action == 3){
                printCardList(cardList);
            } else if(action == 4){
                choice = null;
                validChoice = true;
            } else {
                System.out.println("Please choose a valid action");
            }
        }

        return choice;
    }

    //choose a dice disc
    //return int
    public Dice chooseDie(List<Dice> diceList){
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
            number = input.nextInt();
            if(number < 1 || number > diceList.size()){
                System.out.println("Please choose a valid die");
            } else {
                validChoice = true;
            }
        }

        choice = diceList.get(number - 1);

        return choice;
    }

    //input value
    //

    //Or maybe just have a function that requests a number from the player?
    //With "autoResponse" values when in testing mode?

    public void rollActionDice() {
        freeDice = playArea.getDiceHolder().rollPlayerDice(playerID);
    }

    public void drawCard() {
        int diceChoice = 0;

        //player chooses which dice to use
        diceChoice = 0;

        hand.add(playArea.getCardManager().drawCard(freeDice.remove(diceChoice).getValue()));
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
}