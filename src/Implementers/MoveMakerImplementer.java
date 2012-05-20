package Implementers;

import Implementers.ImplementedActivators.ConsulActivatorImpl;
import Implementers.ImplementedActivators.MercatorActivatorImpl;
import Implementers.ImplementedActivators.dummyActivator;
import Implementers.ImplementedActivators.simpleActivator;
import Roma.Cards.CardHolder;
import Roma.DiceDiscs;
import Roma.History.ActionData;
import Roma.PlayArea;
import Roma.Player;

import framework.cards.Card;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;
import framework.interfaces.activators.CardActivator;
import framework.interfaces.activators.SicariusActivator;

import java.util.Collection;
import java.util.List;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 14/05/12
 * Desc:
 */
public class MoveMakerImplementer implements MoveMaker{

    GameState gameState;
    PlayArea playArea;


    public MoveMakerImplementer(GameState state, PlayArea playArea) {
        gameState = state;
        this.playArea = playArea;
    }

    /**
     * Activate the card that is currently on the given dice disc.
     * <p/>
     * TODO: fix dis shit
     * <p/>
     * <p>
     * This will never be called if:
     * <ul>
     * <li>the player does not have the appropriate action dice to
     * activate the chosen card</li>
     * <li>the card cannot be activated at the current time</li>
     * <li>the ActionData parameter does not match the activated
     * card</li>
     * <li>the dice disc is not present in the current game</li>
     * </ul>
     * </p>
     *
     * @param disc       the disc where the card to be activated is
     //* @param ActivateData needed by that specific card
     * @throws UnsupportedOperationException if the move is not yet
     *                                       implemented
     */
    @Override
    public CardActivator chooseCardToActivate(int disc) throws UnsupportedOperationException {
        int currPlayer = gameState.getWhoseTurn();
        int discIndex = disc-1;
        Player player = playArea.getPlayer(currPlayer);
        DiceDiscs diceDiscs = playArea.getDiceDiscs();


        ActionData currentAction = new ActionData(currPlayer);

        int chosenDieIndex = diceReqdIndex(disc);

        currentAction.setUseDice(true);

        currentAction.setActionDiceIndex(chosenDieIndex);
        currentAction.setDiceValue(disc);



        CardHolder target = diceDiscs.getTargetCard(currPlayer, discIndex);
        Card chosen = gameState.getPlayerCardsOnDiscs(currPlayer)[discIndex];

        assert (target!=null);


        if(target.isActivateEnabled()){
            currentAction.setPosition(discIndex);
            currentAction.setDiscType(ActionData.DICE);
            currentAction.setCardName(target.getName());
            player.setCurrentAction(currentAction);

            return getCorrectActivator(chosen, player);
        } else {
            return new dummyActivator();
        }
    }

    /**
     * Activate the Bribe Disc with the given action die.
     * <p/>
     * <p>
     * After this method is called:
     * <ul>
     * <li>the appropriate action die will have been used</li>
     * <li>the correct amount of sestertii will have been removed from the
     * player's Sestertii</li>
     * <li>the card on the disc will be activated and
     * </ul>
     * </p>
     * <p/>
     * <p/>
     * This will never be called if:
     * <ul>
     * <li>if the user does not have an unused action die of the given
     * value</li>
     * <li>the card cannot be activated at the current time</li>
     * <li> there is no card on this disc </li>
     * <li>the ActionData parameter does not match the activated
     * card</li>
     * </ul>
     *
     * @param diceToUse which value dice to activate the disc with
     * @throws UnsupportedOperationException if the move is not yet
     *                                       implemented
     */
    @Override
    public CardActivator activateBribeDisc(int diceToUse) throws UnsupportedOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Place a card from the current player's hand on to the selected
     * dice disc.
     * <p/>
     * <p>
     * After this method is called:
     * <ul>
     * <li>the selected card is removed from the current player's hand
     * </li>
     * <li>the selected card will be present on the dice disc</li>
     * <li>the required amount of Sestertii will have been deducted from
     * the player's hand</li>
     * <li>any extra changes specific to the placed card will be in
     * effect</li>
     * </ul>
     * </p>
     * <p/>
     * <p>
     * This will never be called if:
     * <ul>
     * <li>the player's hand doesn't contain a card of the given
     * type</li>
     * <li>the player has insufficient Sestertii to place the given
     * card</li>
     * <li>the dice disc is not valid for the current game</li>
     * </ul>
     * </p>
     *
     * @param toPlace       the type of card to be placed
     * @param discToPlaceOn the disc on which the card will be placed
     * @throws UnsupportedOperationException if the move is not yet
     *                                       implemented
     */
    @Override
    public void placeCard(Card toPlace, int discToPlaceOn) throws UnsupportedOperationException {
        int currPlayer = gameState.getWhoseTurn();
        Player player = playArea.getPlayer(currPlayer);
        ActionData currentAction = new ActionData(currPlayer);

        //TODO:         transferNextToThis();

        int chosenCardIndex = handIndex(toPlace);

        //Set action we are making
        currentAction.setLayCard(true);



        //Which card are we choosing?
        currentAction.setCardIndex(chosenCardIndex);

        //Which disc
        currentAction.setTargetDisc(discToPlaceOn-1);

        //
        player.performActions(currentAction);

        //TODO:clearEndActionWrappers();
        //TODO: resetAllPlayable()


    }


    /**
     * Activate the cards disc with the given action die, and choose
     * to keep the given card.
     * <p/>
     * <p>
     * After this method is called:
     * <ul>
     * <li>the appropriate number of cards will be removed from the
     * deck</li>
     * <li>an instance of the card given will be in the player's
     * hand</li>
     * <li>the other cards removed from the deck will be present at the
     * top of the discard pile in unspecified order</li>
     * <li>the appropriate action die will have been used</li>
     * </ul>
     * </p>
     * <p/>
     * <p/>
     * This will never be called if:
     * <ul>
     * <li>if the user does not have an unused action die of the given
     * value</li>
     * <li>the cards drawn from the deck do not include the given
     * card</li>
     * </ul>
     *
     * @param diceToUse which value dice to use to activate the disc
     * @param chosen    which card to keep from the group drawn from the
     *                  deck
     * @throws UnsupportedOperationException if the move is not yet
     *                                       implemented
     */
    @Override
    public void activateCardsDisc(int diceToUse, Card chosen) throws UnsupportedOperationException {
        int chosenDieIndex = diceReqdIndex(diceToUse);
        Player currPlayer = playArea.getPlayer(gameState.getWhoseTurn());
        ActionData currentAction = new ActionData(currPlayer.getPlayerID());

        //TODO:         transferNextToThis();
        currentAction.setUseDice(true);

        currentAction.setActionDiceIndex(chosenDieIndex);
        currentAction.setDiceValue(diceToUse);


        currentAction.setDiscType(ActionData.CARD);

        // Now find the cardIndex of card chosen

        int cardIndex = cardIndex(chosen,diceToUse);

        currentAction.setDrawCardIndex(cardIndex);

        currentAction.setCommit(true);
        currPlayer.performActions(currentAction);


        //TODO:clearEndActionWrappers();
        //TODO: resetAllPlayable()


    }

    /**
     * Activate the Money Disc with the given action die.
     * <p/>
     * <p>
     * After this method is called:
     * <ul>
     * <li>the appropriate action die will have been used</li>
     * <li>the correct amount of sestertii will have been added to the
     * player's Sestertii</li>
     * </ul>
     * </p>
     * <p/>
     * <p/>
     * This will never be called if:
     * <ul>
     * <li>if the user does not have an unused action die of the given
     * value</li>
     * </ul>
     *
     * @param diceToUse which value dice to activate the disc with
     * @throws UnsupportedOperationException if the move is not yet
     *                                       implemented
     */
    @Override
    public void activateMoneyDisc(int diceToUse) throws UnsupportedOperationException {
        int chosenDieIndex = diceReqdIndex(diceToUse);
        Player currPlayer = playArea.getPlayer(gameState.getWhoseTurn());
        ActionData currentAction = new ActionData(gameState.getWhoseTurn());
        //as the creation is not done in PlayArea - i have to create my own

        assert (currentAction!=null); //World's worst warning lol
                                      //Not really, if you're out of RAM it will be null lol

        //TODO:         transferNextToThis();

        //We're using a dice
        currentAction.setUseDice(true);

        //Set the dice data
        currentAction.setActionDiceIndex(chosenDieIndex);
        currentAction.setDiceValue(diceToUse);

        //Set the target
        currentAction.setDiscType(ActionData.MONEY);

        //Ensure that we can make the action
        //Necessary?
        currentAction.setCommit(true);
        currPlayer.performActions(currentAction);

        //TODO:clearEndActionWrappers();
        //TODO: resetAllPlayable()

        //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * End the turn of the current player.
     * <p/>
     * <p>
     * After this method is called:
     * <ul>
     * <li>The old next player is now the current player</li>
     * <li>the appropriate number of dice will have been rolled</li>
     * <li>the appropriate number of victory points will have been
     * removed for vacant dice discs</li>
     * </ul>
     * </p>
     * <p/>
     * <p>
     * There are no restrictions on the calling of this method.
     * </p>
     *
     * @throws UnsupportedOperationException if the move is not yet
     *                                       implemented
     */
    @Override
    public void endTurn() throws UnsupportedOperationException {
        //TODO: clearEndTurnWrappers();

        int nextPlayer = (gameState.getWhoseTurn()+1)%2;
        gameState.setWhoseTurn(nextPlayer);
        playArea.startTurnPhase(playArea.getPlayer(nextPlayer));


    }

    //TODO:Can i just throw unsupported operationException
    //TODO: Opinion Andrew?
    //TODO:
    //TODO:

    //Get's the index required
    private int diceReqdIndex(int diceToUse)throws UnsupportedOperationException{
        int currPlayer = gameState.getWhoseTurn();
        int [] dice = gameState.getActionDice();
        for (int i=0; i<dice.length;i++){
            if(dice[i]==diceToUse) return i;
        }
        //we didn't find the dice - so i throw unsupported Operation
        throw new UnsupportedOperationException();
    }

    private int cardIndex(Card chosen, int maxVal) throws UnsupportedOperationException{
        List<Card> deck = gameState.getDeck();
        for (int i = 0; i<maxVal;i++){
            if (chosen.toString().equalsIgnoreCase(deck.get(i).toString())){
                return i;
            }
        }
        //Not found
        throw new UnsupportedOperationException();
    }

    private int handIndex(Card chosen) throws UnsupportedOperationException{
        Collection<Card> hand = gameState.getPlayerHand(gameState.getWhoseTurn());
        int i = 0;
        for (Card card: hand){
            if(card.toString().equalsIgnoreCase(chosen.toString())){
                return i;
            }
            i++;
        }
        //haven't found the card
        throw new UnsupportedOperationException();
    }

    //Should I use this instead?
    private void invalidAction() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    private CardActivator getCorrectActivator(Card chosen, Player player){
        CardActivator activator = new dummyActivator();
        if (chosen.equals(Card.AESCULAPINUM)){

        } else if (chosen.equals(Card.ARCHITECTUS)){

        } else if (chosen.equals(Card.CENTURIO)){

        } else if (chosen.equals(Card.CONSILIARIUS)){

        } else if (chosen.equals(Card.CONSUL)){
            activator = new ConsulActivatorImpl(player);
        } else if (chosen.equals(Card.ESSEDUM)){
            activator = new simpleActivator(player);
        } else if (chosen.equals(Card.FORUM)){

        } else if (chosen.equals(Card.GLADIATOR)){

        } else if (chosen.equals(Card.HARUSPEX)){

        } else if (chosen.equals(Card.LEGAT) ){
            activator = new simpleActivator(player);
        } else if (chosen.equals(Card.LEGIONARIUS)){

        } else if (chosen.equals(Card.MACHINA)){

        } else if (chosen.equals(Card.MERCATOR)){
            activator = new MercatorActivatorImpl(player);
        } else if (chosen.equals(Card.MERCATUS)){
            activator = new simpleActivator(player);
        } else if (chosen.equals(Card.NERO)){

        } else if (chosen.equals(Card.NERO)){

        } else if (chosen.equals(Card.ONAGER)){

        } else if (chosen.equals(Card.PRAETORIANUS)){

        } else if (chosen.equals(Card.SCAENICUS)){

        } else if (chosen.equals(Card.SENATOR)){

        } else if (chosen.equals(Card.SICARIUS)){

        } else if (chosen.equals(Card.TELEPHONEBOX)){

        } else if(chosen.equals(Card.TRIBUNUSPLEBIS)){
            activator =  new simpleActivator(player);
        } else if (chosen.equals(Card.VELITES)){

        } else {
            throw new UnsupportedOperationException();
        }
        return activator;
    }
}
