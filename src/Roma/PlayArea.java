package Roma;

import Roma.Cards.*;

import java.util.ArrayList;

public class PlayArea {
    //#defines
    private final int NUM_INIT_CARDS = 5;
    private final int PAD_LENGTH = 20;

    //Object pointers
    private Roma mainProgram;
    private CardManager cardManager;
    private DiceHolder diceHolder;
    private MoneyManager moneyManager;
    private VictoryTokens victoryTokens;
    private DiceDiscs diceDiscs;
    private Player players[];
    private BattleManager battleManager;
    private PlayerInterface playerInterface;

    private GameRules gameRules;


    private ArrayList<WrapperMaker> enterHandList = new ArrayList<WrapperMaker>();
    private ArrayList<WrapperMaker> enterPlayList = new ArrayList<WrapperMaker>();
    private ArrayList<Wrapper> endTurnList = new ArrayList<Wrapper>();
    private ArrayList<Wrapper> endActionList = new ArrayList<Wrapper>();

    //Variables
    private int turn = 0;

    public boolean isGameOver() {
        return gameOver;
    }

    private boolean gameOver = false;


    public PlayArea(Roma mainProgram) {
        cardManager = new CardManager(this);
        diceHolder = new DiceHolder();
        moneyManager = new MoneyManager();
        victoryTokens = new VictoryTokens(this);
        diceDiscs = new DiceDiscs(this);
        players = new Player[Roma.MAX_PLAYERS];
        battleManager = new BattleManager(this);
        this.mainProgram = mainProgram;
        playerInterface = new PlayerInterface();
        gameRules = new GameRules(this);

        for (int i = 0; i < Roma.MAX_PLAYERS; i++) {
            players[i] = Player.makeRealPlayer(i, this);
        }
        gameRules.getAndSwapCards();
        gameRules.layAllCardsInHand();
    }


    public void endGame(){
        gameOver = true;
        mainProgram.endGame();
    }

    public Player[] getAllPlayers() {
        return players;
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
        while (!gameOver) {
            Player player = players[turn % Roma.MAX_PLAYERS];
            playerTurn(player);
        }
    }

    public PlayerInterface getPlayerInterface() {
        return playerInterface;
    }

    public void playerTurn(Player player) {
        boolean endTurn = false;
        char roll = 'b';

        System.out.println("It's " + player.getName() + "'s turn");

        gameRules.deductVictoryTokens(player.getPlayerID());
        diceDiscs.clearPlayerDice(player.getPlayerID());
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

        while (!gameOver && !endTurn) {
            endTurn = players[turn % Roma.MAX_PLAYERS].takeAction();
        }
        battleManager.clearDefenseModActive(); // reset temporary defense modifiers
        turn++;
    }

    public void printStats() {
        String cardName;

        for(int player = 0; player < Roma.MAX_PLAYERS; player++){
            System.out.println("-------------------------------------");
            System.out.println("Player: " + players[player].getName());
            System.out.println("Victory Tokens: " + victoryTokens.getPlayerTokens(player) +
                                "  \tMoney: " + moneyManager.getPlayerMoney(player));
            System.out.println("Cards in hand: " + players[player].handSize());
            System.out.println("Cards in play: ");
            for(int position = 0; position < DiceDiscs.CARD_POSITIONS; position++){
                cardName = diceDiscs.getCardName(player, position);
                if(position == 6){
                    System.out.print("Bribery) " + String.format("%1$-" + PAD_LENGTH + "s",cardName) +
                            " : Dice on disc: ");
                } else {
                    System.out.print("      " + (position + 1) + ") " + String.format("%1$-" + PAD_LENGTH + "s",cardName) +
                            " : Dice on disc: ");
                }
                for(Dice die : diceDiscs.checkForDice(player, position)){
                    System.out.print(die.getValue() + " ");
                }
                System.out.println();
            }
        }
    }

    public BattleManager getBattleManager() {
        return battleManager;
    }

    private void endActionPhase(){

    }

    /**
     * This is the section where the methods required for the interface
     * and our own tests are located
     * @param testing
     */

    @Deprecated
    public PlayArea(String testing){
        assert testing.equalsIgnoreCase("testing");
        System.err.println("In Testing phase");
        cardManager = new CardManager(this);
        diceHolder = new DiceHolder();
        moneyManager = new MoneyManager();
        victoryTokens = new VictoryTokens(this);
        diceDiscs = new DiceDiscs(this);
        players = new Player[Roma.MAX_PLAYERS];
        battleManager = new BattleManager(this);

        for (int i = 0; i < Roma.MAX_PLAYERS; i++) {
            players[i] = Player.makeDummyPlayer(i, this);
        }
    }

    public void testGetSpecificCardforHand(String cardName, int playerID){
        //for testing
        Card obtain = cardManager.getCardfromDeck(cardName);
        players[playerID].addCardToHand(obtain);

    }

    public void testFillHand(int playerID){
        players[playerID].addCardListToHand(cardManager.drawNCards(Roma.NUM_INIT_CARDS));
    }

}
