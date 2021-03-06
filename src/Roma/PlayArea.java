package Roma;

import Roma.Cards.CardHolder;
import Roma.Cards.Wrapper;
import Roma.Cards.WrapperMaker;
import Roma.History.*;
import Roma.PlayerInterfaceFiles.CancelAction;
import Roma.PlayerInterfaceFiles.GamePlayerInterface;
import Roma.PlayerInterfaceFiles.PlayerInterface;

import java.util.ArrayList;

public class PlayArea {
    public static final String BREAK_LINE = PlayerInterface.BREAK_LINE;

    //#defines
    private final int PAD_LENGTH = 20;

    //Object pointers
    private RomaGame mainProgram;
    private CardManager cardManager;
    private DiceHolder diceHolder;
    private MoneyManager moneyManager;
    private VictoryTokens victoryTokens;
    private DiceDiscs diceDiscs;
    private Player players[];
    private PlayerInterface playerInterface;
    private GameRules gameRules;

    private ArrayList<WrapperMaker> enterHandList = new ArrayList<WrapperMaker>();
    private ArrayList<WrapperMaker> enterPlayList = new ArrayList<WrapperMaker>();
    private ArrayList<Wrapper> endTurnList = new ArrayList<Wrapper>();
    private ArrayList<Wrapper> endNextTurnList = new ArrayList<Wrapper>();
    private ArrayList<Wrapper> endActionList = new ArrayList<Wrapper>();

    //Variables
    private boolean testing = false;
    private int turn = 0;
    private boolean gameOver = false;

    private TurnHistory turnHistory;
    private TimeWarp timeWarp = null;

    public PlayArea(RomaGame mainProgram) {
        this.mainProgram = mainProgram;
        turnHistory = new TurnHistory();
        cardManager = new CardManager(this);
        diceHolder = new DiceHolder();
        moneyManager = new MoneyManager();
        victoryTokens = new VictoryTokens(this);
        diceDiscs = new DiceDiscs(this);
        players = new Player[RomaGame.MAX_PLAYERS];
        playerInterface = new GamePlayerInterface();
        gameRules = new GameRules(this);

        for (int i = 0; i < RomaGame.MAX_PLAYERS; i++) {
            players[i] = Player.makeRealPlayer(i, this);
        }
        gameRules.getAndSwapCards();
        gameRules.layAllCardsInHand();
    }

    public void resetGameOverFlag(){
        gameOver = false;
    }

    public void endGame(){
        gameOver = true;
        if(!testing) mainProgram.gameOverMessage();
    }

    public void runGame() {
        while (!gameOver) {
            Player player = players[turn % RomaGame.MAX_PLAYERS];
            playerTurn(player);
        }
    }

    public void playerTurn(Player player) {
        boolean endTurn = false;

        startTurnPhase(player);
        PlayState playState = new PlayState(this, player);

        while (!gameOver && !endTurn) {
            ActionData action = new ActionData(this);
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
                playState.addActionHistory(action);
            }
            if(timeWarp != null){
                endTurn = true;
            }
            resetAllPlayable();
        }
        // reset temporary defense modifiers
        clearEndTurnWrappers();
        turn++;
        turnHistory.addPlayState(playState);
        if(timeWarp != null){
            try {
                timeWarp.warpTime();
            } catch (TimeParadox timeParadox) {
                endGame(); //TODO
            }
            timeWarp = null;
        }
        turnHistory.setCurrentTurnNumber(turn);
    }

    public void resetAllPlayable() {
        ArrayList<CardHolder> cardList;

        cardList = cardManager.getPlayingDeck();
        resetPlayable(cardList);
        cardList = cardManager.getDiscardPile();
        resetPlayable(cardList);
        for(int i = 0; i < RomaGame.MAX_PLAYERS; i++){
            cardList = players[i].getHand();
            resetPlayable(cardList);
        }
        cardList = diceDiscs.listActiveCards();
        resetPlayable(cardList);
    }

    private void resetPlayable(ArrayList<CardHolder> cardList) {
        for(CardHolder card : cardList){
            if(card != null) card.setPlayable(false);
        }
    }


    public void startTurnPhase(Player player) {
        PlayerInterface.printOut(BREAK_LINE, true);
        PlayerInterface.printOut("Turn: " + turn + " \tIt's " + player.getName() + "'s turn", true);
        gameRules.deductVictoryTokens(player.getPlayerID());
        diceDiscs.clearPlayerDice(player.getPlayerID());
        if(timeWarp == null){
            player.rollActionDice();
        }
        transferNextToThis();
        diceDiscs.arriveFromPast();
    }

    //All the getters
    public int getTurn(){
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
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

    public void clearEndTurnWrappers() {
        for(Wrapper wrapper : endTurnList){
            wrapper.deleteThisWrapper();
        }
        endTurnList.clear();
    }

    public void clearEndActionWrappers(){
        for(Wrapper wrapper : endActionList){
            wrapper.deleteThisWrapper();
        }
        endActionList.clear();
    }

    public void clearEnterPlayList(){
        enterPlayList.clear();
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
        //System.err.println("In Testing phase");
        turnHistory = new TurnHistory();
        cardManager = new CardManager(this);
        diceHolder = new DiceHolder();
        moneyManager = new MoneyManager();
        victoryTokens = new VictoryTokens(this);
        diceDiscs = new DiceDiscs(this);
        players = new Player[RomaGame.MAX_PLAYERS];
        playerInterface = new GamePlayerInterface();
        gameRules = new GameRules(this);

        for (int i = 0; i < RomaGame.MAX_PLAYERS; i++) {
            players[i] = Player.makeDummyPlayer(i, this);
        }
    }

    public void testGetSpecificCardforHand(String cardName, int playerID){
        //for testing
        CardHolder obtain = cardManager.getCardfromDeck(cardName);
        players[playerID].addCardToHand(obtain);

    }

    public void testFillHand(int playerID){
        players[playerID].addCardListToHand(cardManager.drawNCards(RomaGame.NUM_INIT_CARDS));
    }

    public ArrayList<PlayState> getPlayStateHistory(){
        return turnHistory.getHistory();
    }

    public TurnHistory getTurnHistory() {
        return turnHistory;
    }

    public void setTurnHistory(TurnHistory turnHistory) {
        this.turnHistory = turnHistory;
    }

    //time machine stuff below
    //HACK HACK HACK HACK HACK >:D

    public TimeWarp getTimeWarp(){
        return timeWarp;
    }

    public PlayState autoTurnStart(){
        Player player = players[turn % RomaGame.MAX_PLAYERS];
        startTurnPhase(player);
        diceDiscs.arriveFromPast();
        return new PlayState(this, player);
    }

    public void autoAction(PlayState playState, ActionData action){
        Player player = players[turn % RomaGame.MAX_PLAYERS];
        player.performActions(action);
        clearEndActionWrappers();
        playState.addActionHistory(action);
        resetAllPlayable();
    }

    public void autoTurnEnd(PlayState playState){
        clearEndTurnWrappers();
        turn++;
        turnHistory.setCurrentTurnNumber(turn);
        turnHistory.addPlayState(playState);
    }

    public void setTimeWarp(TimeWarp timeWarp) {
        this.timeWarp = timeWarp;
    }
}
