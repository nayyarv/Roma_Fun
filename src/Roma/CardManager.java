package Roma;
/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 19/03/12
 * Desc:
 */

import Roma.Cards.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardManager {
    //Objects
    private final List<Card> playingDeck = new ArrayList<Card>();
    private final List<Card> discardPile = new ArrayList<Card>();

    //Variabls
    private boolean noMoreCards = false;

    public CardManager(){
        //Will insert all cards, and shuffle
    }
    
    public void shuffle(){
        Collections.shuffle(playingDeck);
    }
    
    public Card drawCard(int value){
    	List<Card> tempHand = new ArrayList<Card>();
    	int playerChoice = 0;
    	
    	for(int i = 0; i < value; i++){
    		tempHand.add(drawACard());
    	}
    	
    	//player input
    	playerChoice = 0;
    	
    	return tempHand.get(playerChoice);
    }

    public Card drawACard(){
        Card temp = playingDeck.remove(0);
        if(playingDeck.isEmpty() && !discardPile.isEmpty()){
            playingDeck.addAll(discardPile);
            discardPile.clear();
            shuffle();
        } else if(playingDeck.isEmpty() && discardPile.isEmpty()){
            noMoreCards = true;
        }
        return temp;
    }
    //
    public void insertCard(Card theCard){
        playingDeck.add(0, theCard);
    }

    public void discard(Card theCard){
        discardPile.add(0, theCard);
        if(noMoreCards){
            playingDeck.addAll(discardPile);
            discardPile.clear();
            shuffle();
            noMoreCards = false;
        }
    }

    public int getPlayingSize(){
        return playingDeck.size();
    }

    public int getDiscardSize(){
        return discardPile.size();
    }
    
    public String toString(){
        return "Playing deck is: " + playingDeck.toString() + "\nAnd Discard Pile is: " + discardPile.toString();

    }

}