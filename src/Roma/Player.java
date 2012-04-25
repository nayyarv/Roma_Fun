package Roma;

import Roma.Cards.Card;

import java.util.*;

public class Player {
    private final static int CANCEL = -1;
    private final String name;
    private PlayArea playArea;
    private List<Card> hand = new ArrayList<Card>();
    private List<Dice> freeDice;
    private Scanner input;

    private boolean testing;
    private int playerID;
    private boolean autoRoll;

    public static Player makeRealPlayer(int playerID, PlayArea playArea){
        return new Player(playerID, playArea);
    }

    public static Player makeDummyPlayer(int playerID, PlayArea playArea, boolean testing){
        return new Player(playerID, playArea, testing);
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

        //choose an action
        System.out.println("Select option:\n" +
                "1) Show game stats\n" +
                "2) Free dice available\n");
        option = input.nextInt();

        if(option == 1){

        } else if(option == 2){

        } else if(option == 3){

        } else if(option == 4){
            playArea.printStats();
        } else if(option == 5){

        } else {

        }

        return false;
    }
    
    public void printCardList(List<Card> cardList){
        int i = 1;
        System.out.println("-------------------------------------");
        for(Card card: cardList){
            System.out.println(i + ") " + card.getName());
            i++;
        }
    }
    //TODO: Complete player input functions
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
            System.out.print("Actions:\n" +
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
    public Dice chooseDice(List<Dice> diceList){
        Dice choice = null;
        return choice;
    }

    public List<Dice> getFreeDice() {
        return freeDice;
    }

    //input value
    //

    //Or maybe just have a function that requests a number from the player?
    //With "autoResponse" values when in testing mode?

    public void rollActionDice() {
        boolean reroll = false;

        freeDice = playArea.getDiceHolder().rollPlayerDice(playerID);

        if(playArea.getDiceHolder().checkTriple(playerID)){
            //TODO: ask player if want to reroll
            if(reroll){
                rollActionDice();
            }
        }
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
}
