package Roma;

import Roma.Cards.Card;

import java.util.ArrayList;
import java.util.List;
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

    //TODO: Fill out player actions
    //Main method that allows players to perform an action
    public boolean takeAction() {
        //internal #defines
        final int VIEW_ACTION_DIE = 1;
        final int VIEW_HAND = 2;
        final int SHOW_GAME_STATS = 3;
        final int END_TURN = 4;

        int option = 0;
        Card chosenCard = null;
        Dice chosenDie = null;
        boolean endTurn = false;

        printDiceList(freeDice);
        printCardList(hand);

        //choose an action
        option = playerInterface.readInput("Select Option",
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
            chosenCard = chooseCard(hand);
        } else if(option == SHOW_GAME_STATS){
            playArea.printStats();
        } else if(option == END_TURN){
            endTurn = true;
        } else {
            System.out.println("Please choose a valid option.");
        }

        return endTurn;
    }

    private Dice useActionDie(Dice chosenDie) {
        int option;
        int discTarget;
        boolean validChoice = false;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();

        while(!validChoice){
            option = playerInterface.readInput("Use on:",
                    "Activate card",
                    "Bribery Disc",
                    "Money Disc",
                    "Card Disc",
                    "Cancel");
            if(option == 1){
                //TODO - Failing atm
                diceDiscs.activateCard(playerID, chosenDie.getValue(), chosenDie);
                chosenDie = null;
            } else if(option == 2){
                chosenDie = null;
            } else if(option == 3){
                chosenDie = null;
            } else if(option == 4){
                chosenDie = null;
            } else if(option == 5){
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

    public void printCardList(List<Card> cardList){
        int i = 1;
        System.out.println("-------------------------------------\n" +
                "Cards: ");
        for(Card card: cardList){
            System.out.println(i + ") " + card.getName());
            i++;
        }
    }

    //choose from list
    //input: ArrayList (of dice or of cards)
    //return int
    public Card chooseCard(ArrayList<Card> cardList){
        Card choice = null;
        int action = 0;
        boolean validChoice = false;

        printCardList(cardList);

        while(!validChoice){
            action = playerInterface.readInput("Possible actions:",
                    "Choose card",
                    "Check description",
                    "Print card list",
                    "Cancel");

            if(action == 1){
                System.out.print("Card number: ");
                choice = cardList.remove(input.nextInt() - 1);
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

    public int handSize() {
        return hand.size();
    }

    public void addCardToHand(Card c){
        if(c!=null) hand.add(c);
    }

    public void addCardListToHand(ArrayList<Card> cardList){
        hand.addAll(cardList);
    }
}
