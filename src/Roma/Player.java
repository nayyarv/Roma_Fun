package Roma;

import Roma.Cards.Card;

import java.util.*;

public class Player {
    private final String name;
    private PlayArea playArea;
    private List<Card> hand = new ArrayList<Card>();
    private List<Dice> freeDice;
    private Scanner input;

    private boolean testing;
    private int playerID;
    private boolean autoRoll;

    public Player(int playerID, PlayArea playArea) {
        this.playArea = playArea;
        this.playerID = playerID;
        this.input = playArea.getInput();
        System.out.println("Name of player" + playerID + ": ");
        this.name = input.nextLine();
    }

    public Player(int playerID, PlayArea playArea, boolean testing) {
        this.playArea = playArea;
        this.playerID = playerID;
        this.input = null;
        this.name = "dummyPlayer" + playerID;
    }

    public String getName() {
        return name;
    }

    public boolean takeAction() {
        int option = 0;

        //choose an action
        System.out.println("Select option:\n" +
                "1) Show game stats\n" +
                "2) Free dice available\n");
        option = input.nextInt();

        if (option == 1) {
            //playArea.printStats();
        }

        return false;
    }

    //choose from list
    //input: ArrayList (of dice or of cards)
    //return int

    //choose a dice disc
    //return int

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

    public void VarunprintCardList(List<Card> cardList) {
        System.out.println("----_---__--__");
        int i = 1;
        for (Card c : cardList) {
            System.out.println(i + ")" + c.getName());
            i++;
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
