package Roma;
/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 19/03/12
 * Desc:
 */

import Roma.Cards.Card;

import java.util.*;

public class CardManager {
    private final List<Card> playingDeck = new ArrayList<Card>();
    private final List<Card> discardPile = new ArrayList<Card>();

    public CardManager(){
        //Will insert all cards, and shuffle
    }
    
    public void shuffle(){
        Collections.shuffle(playingDeck);
    }

    public Card drawCard(){
        Card temp = playingDeck.remove(0);
        if(playingDeck.isEmpty()){
            playingDeck.addAll(discardPile);
            discardPile.clear();
            shuffle();
        }
        return temp;
    }
    //
    public void insertCard(Card theCard){
        playingDeck.add(0, theCard);
    }

    public void discard(Card theCard){
        discardPile.add(0, theCard);
    }

    public int getPlayingSize(){
        return playingDeck.size();
    }

    public int getDiscardSize(){
        //System.err.println("lol");
        return discardPile.size();


    }
    
    public String toString(){
        return "Playing deck is: " + playingDeck.toString() + "\nAnd Discard Pile is: " + discardPile.toString();

    }

}