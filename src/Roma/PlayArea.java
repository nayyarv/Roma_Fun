package Roma;

import Roma.Cards.*;
import Roma.History.*;
import Roma.PlayerInterfaceFiles.*;

import java.util.ArrayList;

public class PlayArea {
    //#defines
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
    private CardFactory cardFactory;

    //TODO: Use these functions
    private ArrayList<WrapperMaker> enterHandList = new ArrayList<WrapperMaker>();
    private ArrayList<WrapperMaker> enterPlayList = new ArrayList<WrapperMaker>();
    private ArrayList<Wrapper> endTurnList = new ArrayList<Wrapper>();
    private ArrayList<Wrapper> endNextTurnList = new ArrayList<Wrapper>();
    private ArrayList<Wrapper> endActionList = new ArrayList<Wrapper>();

    //Variables
    private boolean testing = false;
    private int turn = 0;
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
        playerInterface = new GamePlayerInterface();
        gameRules = new GameRules(this);
        cardFactory = new CardFactory(this);

        for (int i = 0; i < Roma.MAX_PLAYERS; i++) {
            players[i] = Player.makeRealPlayer(i, this);
        }
        gameRules.getAndSwapCards();
        gameRules.layAllCardsInHand();
    }


    public void endGame(){
        gameOver = true;
        if(!testing) mainProgram.gameOverMessage();
    }

    public void runGame() {
        while (!gameOver) {
            Player player = players[turn % Roma.MAX_PLAYERS];
            playerTurn(player);
        }
    }

    public void playerTurn(Player player) {
        boolean endTurn = false;

        startTurnPhase(player);
        PlayState playState = new PlayState(this, player);

        while (!gameOver && !endTurn) {
            ActionData action = new ActionData(player.getPlayerID());
            diceHolder.rollBattleDice();
            action.setBattleDice(diceHolder.getBattleValue()[0]);
            //read player input through player interface and and store into action data

            try {
                endTurn = player.planningPhase(action);
            } catch (CancelAction cancelAction) {
                cancelAction.message();
            }

            //perform stored actions if commit = true
            if(action.isCommit()){
                player.performActions(action);

                //end action phase
                //add actionData to turnHistory
                clearEndActionWrappers();
            }
            resetAllPlayable();
        }

        clearEndTurnWrappers();
        //battleManager.clearDefenseModActive(); // reset temporary defense modifiers
        turn++;
    }

    public void resetAllPlayable() {
        ArrayList<CardHolder> cardList;

        cardList = cardManager.getPlayingDeck();
        resetPlayable(cardList);
        cardList = cardManager.getDiscardPile();
        resetPlayable(cardList);
        for(int i = 0; i < Roma.MAX_PLAYERS; i++){
            cardList = players[i].getHand();
            resetPlayable(cardList);
        }
        cardList = diceDiscs.listActiveCards();
        resetPlayable(cardList);
    }

    private void resetPlayable(ArrayList<CardHolder> cardList) {
        for(CardHolder card : cardList){
            card.setPlayable(false);
        }
    }


    private void startTurnPhase(Player player) {
        PlayerInterface.printOut("It's " + player.getName() + "'s turn", true);
        gameRules.deductVictoryTokens(player.getPlayerID());
        diceDiscs.clearPlayerDice(player.getPlayerID());
        player.rollActionDice();
        transferNextToThis();
    }

    //TODO: move to playerInterface
    //TODO: add display last card discarded
    public void printStats() {
        String cardName;

        for(int player = 0; player < Roma.MAX_PLAYERS; player++){
            PlayerInterface.printOut("-------------------------------------", true);
            PlayerInterface.printOut("Player: " + players[player].getName(), true);
            PlayerInterface.printOut("Victory Tokens: " + victoryTokens.getPlayerTokens(player) +
                    "  \tMoney: " + moneyManager.getPlayerMoney(player), true);
            PlayerInterface.printOut("Cards in hand: " + players[player].handSize(), true);
            PlayerInterface.printOut("Cards in play: ", true);
            for(int position = 0; position < DiceDiscs.CARD_POSITIONS; position++){
                cardName = diceDiscs.getCardName(player, position);
                if(position == 6){
                    PlayerInterface.printOut("Bribery) " + String.format("%1$-" + PAD_LENGTH + "s", cardName) +
                            " : Dice on disc: ", false);
                } else {
                    PlayerInterface.printOut("      " + (position + 1) + ") " + String.format("%1$-" + PAD_LENGTH + "s", cardName) +
                            " : Dice on disc: ", false);
                }
                for(Dice die : diceDiscs.checkForDice(player, position)){
                    PlayerInterface.printOut(die.getValue() + " ", false);
                }
                PlayerInterface.printOut("", true);
            }
        }
    }


    //All the getters
    public int getTurn(){
        return turn;
    }

    public boolean isGameOver() {
        return gameOver;
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

    public PlayerInterface getPlayerInterface() {
        return playerInterface;
    }

    public BattleManager getBattleManager() {
        return battleManager;
    }

    public void addToEndTurnList(Wrapper wrappers){
        endTurnList.add(wrappers);
    }

    public void addToEndTurnList(ArrayList<Wrapper> wrappers){
        endTurnList.addAll(wrappers);
    }

    public void addToEndNextTurnList(Wrapper wrappers){
        endNextTurnList.add(wrappers);
    }

    public void addToEndNextTurnList(ArrayList<Wrapper> wrappers){
        endNextTurnList.addAll(wrappers);
    }

    public void addToEndActionList(Wrapper wrapper){
        endActionList.add(wrapper);
    }

    public void addToEndActionList(ArrayList<Wrapper> wrappers){
        endActionList.addAll(wrappers);
    }

    public ArrayList<WrapperMaker> getEnterPlayList(){
        return enterPlayList;
    }

    public void addToEnterPlayList(WrapperMaker wrapperMaker){
        enterPlayList.add(wrapperMaker);
    }

    public void removeFromEnterPlayList(WrapperMaker wrapperMaker){
        enterPlayList.remove(wrapperMaker);
    }

    public void transferNextToThis(){
        endTurnList.addAll(endNextTurnList);
        endNextTurnList.clear();
    }

    private void clearEndTurnWrappers() {
        for(Wrapper wrapper : endTurnList){
            wrapper.deleteThisWrapper();
        }
        endTurnList.clear();
    }

    private void clearEndActionWrappers(){
        for(Wrapper wrapper : endActionList){
            wrapper.deleteThisWrapper();
        }
        endActionList.clear();
    }

    public GameRules getGameRules() {
        return gameRules;
    }

    /**
     * This is the section where the methods required for the interface
     * and our own tests are located
     * @param testing "testing", so it isn't called by accident
     */

    @Deprecated
    public PlayArea(String testing){
        assert testing.equalsIgnoreCase("testing");
        this.testing = true;
        System.err.println("In Testing phase");
        cardManager = new CardManager(this);
        diceHolder = new DiceHolder();
        moneyManager = new MoneyManager();
        victoryTokens = new VictoryTokens(this);
        diceDiscs = new DiceDiscs(this);
        players = new Player[Roma.MAX_PLAYERS];
        battleManager = new BattleManager(this);
        playerInterface = new GamePlayerInterface();
        gameRules = new GameRules(this);
        cardFactory = new CardFactory(this);

        for (int i = 0; i < Roma.MAX_PLAYERS; i++) {
            players[i] = Player.makeDummyPlayer(i, this);
        }
    }

    public void testGetSpecificCardforHand(String cardName, int playerID){
        //for testing
        CardHolder obtain = cardManager.getCardfromDeck(cardName);
        players[playerID].addCardToHand(obtain);

    }

    public void testFillHand(int playerID){
        players[playerID].addCardListToHand(cardManager.drawNCards(Roma.NUM_INIT_CARDS));
    }

}
