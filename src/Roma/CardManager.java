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

public class CardManager {
    private final static int CARDS_IN_DECK = 52;
    //Objects
    private final ArrayList<Card> playingDeck = new ArrayList<Card>();
    private final ArrayList<Card> discardPile = new ArrayList<Card>();

    //Variabls
    private boolean noMoreCards = false;

    public CardManager(PlayArea playArea) {
        //Will insert all cards, and shuffle
        playingDeck.addAll(Aesculapinum.playSet(playArea));
        playingDeck.addAll(Architectus.playSet(playArea));
        playingDeck.addAll(Basilica.playSet(playArea));
        playingDeck.addAll(Centurio.playSet(playArea));

        playingDeck.addAll(Consiliarus.playSet(playArea));
        playingDeck.addAll(Consul.playSet(playArea));
        playingDeck.addAll(Essedum.playSet(playArea));
        playingDeck.addAll(Forum.playSet(playArea));

        playingDeck.addAll(Gladiator.playSet(playArea));
        playingDeck.addAll(Haruspex.playSet(playArea));
        playingDeck.addAll(Legionarius.playSet(playArea));

        playingDeck.addAll(Mercator.playSet(playArea));
        playingDeck.addAll(Mercatus.playSet(playArea));
        playingDeck.addAll(Nero.playSet(playArea));
        playingDeck.addAll(Onager.playSet(playArea));

        playingDeck.addAll(Praetorianus.playSet(playArea));
        playingDeck.addAll(Scaenicus.playSet(playArea));
        playingDeck.addAll(Senator.playSet(playArea));

        playingDeck.addAll(Sicarius.playSet(playArea));
        playingDeck.addAll(Templum.playSet(playArea));
        playingDeck.addAll(TribunisPlebis.playSet(playArea));

        playingDeck.addAll(Turris.playSet(playArea));
        playingDeck.addAll(Velites.playSet(playArea));

        assert(playingDeck.size() == CARDS_IN_DECK);
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(playingDeck);
    }

    private void addNumberOf(Card card, int num){
        for (int i=0;i<num;i++){
            playingDeck.add(card);
        }
    }

    public Card drawACard() {
        Card temp = playingDeck.remove(0);
        if (playingDeck.isEmpty() && !discardPile.isEmpty()) {
            playingDeck.addAll(discardPile);
            discardPile.clear();
            shuffle();
        } else if (playingDeck.isEmpty() && discardPile.isEmpty()) {
            noMoreCards = true;
        }
        return temp;
    }

    public ArrayList<Card> drawNCards(int number){
        ArrayList<Card> drawHand = new ArrayList<Card>();
        for (int i=0; i<number;i++){
            drawHand.add(drawACard());
        }
        return drawHand;
    }

    //
    public void insertCard(Card theCard) {
        playingDeck.add(0, theCard);
    }

    public void discard(Card theCard) {
        discardPile.add(0, theCard);
        if (noMoreCards) {
            playingDeck.addAll(discardPile);
            discardPile.clear();
            shuffle();
            noMoreCards = false;
        }
    }

    public void discard(ArrayList<Card> cardList) {

        /** TODO - check discard order here
          * TODO - choose the top card discarded
          */
        discardPile.addAll(0, cardList);
        if (noMoreCards) {
            playingDeck.addAll(discardPile);
            discardPile.clear();
            shuffle();
            noMoreCards = false;
        }
    }

    public ArrayList<Card> getPlayingDeck() {
        return playingDeck;
    }

    public int getPlayingSize() {
        return playingDeck.size();
    }

    public int getDiscardSize() {
        return discardPile.size();
    }

    public String toString() {
        return "Playing deck is: " + playingDeck.toString() + "\nAnd Discard Pile is: " + discardPile.toString();

    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }
}