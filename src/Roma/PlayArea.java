package Roma;

import Roma.Cards.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayArea {
    //#defines
    private final int NUM_INIT_CARDS = 4;

    //Object pointers
    private Roma mainProgram;
    private CardManager cardManager;
    private DiceHolder diceHolder;
    private MoneyManager moneyManager;
    private VictoryTokens victoryTokens;
    private DiceDiscs diceDiscs;
    private Player players[];

    //Variables
    private int turn = 0;


    public PlayArea(Roma mainProgram) {
        cardManager = new CardManager(this);
        diceHolder = new DiceHolder();
        moneyManager = new MoneyManager();
        victoryTokens = new VictoryTokens(mainProgram);
        diceDiscs = new DiceDiscs(this);
        players = new Player[Roma.MAX_PLAYERS];
        this.mainProgram = mainProgram;

        for (int i = 0; i < Roma.MAX_PLAYERS; i++) {
            players[i] = Player.makeRealPlayer(i, this);
        }
        getAndSwapCards();

    }

    public void getAndSwapCards(){
        //TODO - swap cards
        ArrayList<Card> newHand = new ArrayList<Card>();
        for(int i = 0; i<Roma.MAX_PLAYERS;i++){
            newHand.addAll(0, getFirstHand());
        } //gets all the cards needed

        //players[0].printCardList(temp);
        Card choice1 = null;
        Card choice2 = null;
        for (int i =0; i<Roma.MAX_PLAYERS;i++){
            //System.out.println("NEXT Player");
            players[i].addCardToHand(choice1);
            players[i].addCardToHand(choice2);
            //add prev choices
            ArrayList<Card> individualHand = new ArrayList<Card>();
            individualHand.addAll(newHand.subList((i * NUM_INIT_CARDS), (i + 1) * NUM_INIT_CARDS));
            System.out.println(players[i].getName() +
                    ", these are the 4 cards dealt to you.\n" +
                    "You must choose 2 to give to your opponent.\n" +
                    "Choose the first Card");
            choice1 = players[i].chooseCard(individualHand);
            System.out.println("Choose the second card:");
            choice2 = players[i].chooseCard(individualHand);

            players[i].addCardListToHand(individualHand);
        }
        players[0].addCardToHand(choice1);
        players[0].addCardToHand(choice2);
    }

    public ArrayList<Card> getFirstHand(){
        ArrayList<Card> tempHand = new ArrayList<Card>();
        for (int i=0; i<NUM_INIT_CARDS;i++){
            tempHand.add(cardManager.drawACard());
        }
        return tempHand;
    }

    public CardManager getCardManager() {
        return cardManager;
    }

    public DiceHolder getDiceHolder() {
        return diceHolder;
    }

    public MoneyManager getMoneyManager() {
        return moneyManager;
    }

    public VictoryTokens getVictoryTokens() {
        return victoryTokens;
    }

    public DiceDiscs getDiceDiscs() {
        return diceDiscs;
    }

    public Player getPlayer(int playerID) {
        return players[playerID];
    }

    public void runGame() {
        while (!mainProgram.getGameOver()) {
            Player player = players[turn];
            playerTurn(player);
        }
    }

    public void playerTurn(Player player) {
        boolean endTurn = false;
        char roll = 'b';

        System.out.println("It's " + player.getName() + "'s turn");

        player.rollActionDice();

        //TODO: set up auto roll option
//        if (player.getAutoRoll()) {
//            player.rollActionDice();
//        } else {
//            System.out.println("Press space to roll action dice." +
//                    "Press 'a' for automated dice roll for the rest of the game.");
//            while (!(roll == ' ' || roll == 'a')) {
//                roll = mainProgram.getInput().nextChar
//            }
//            if (roll == 'a') {
//                player.setAutoRoll(true);
//            }
//        }


        while (!mainProgram.getGameOver() && !endTurn) {
            endTurn = players[turn].takeAction();
        }
        turn++;
    }

    public Scanner getInput(){
        return mainProgram.getInput();
    }

    public void printStats() {
        for(int player = 0; player < Roma.MAX_PLAYERS; player++){
            System.out.println("-------------------------------------");
            System.out.println("Player: " + players[player].getName());
            System.out.println("Victory Tokens: " + victoryTokens.getPlayerTokens(player) +
                                "\tSestertii: " + moneyManager.getPlayerMoney(player));
            System.out.println("Cards in hand: " + players[player].handSize());
            System.out.println("Cards in play: ");
            for(int position = 0; position < DiceDiscs.CARD_POSITIONS; position++){
                System.out.print(position + ") " + diceDiscs.getCardName(player, position) +
                        " : Dice on disc: ");
                for(Dice die : diceDiscs.checkForDice(player, position)){
                    System.out.print(die.getValue() + " ");
                }
                System.out.println();
            }
        }
    }
}
