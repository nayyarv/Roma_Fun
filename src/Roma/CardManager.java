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

//TODO: Deck creation

public class CardManager {
    //Objects
    private final List<Card> playingDeck = new ArrayList<Card>();
    private final List<Card> discardPile = new ArrayList<Card>();

    //Variabls
    private boolean noMoreCards = false;

    public CardManager(PlayArea playArea) {
        //Will insert all cards, and shuffle
        addNumberOf(new Aesculapinum(playArea), Aesculapinum.OCCURENCES);
        addNumberOf(new Architectus(playArea), Architectus.OCCURENCES);
        addNumberOf(new Basilica(playArea), Basilica.OCCURENCES);
        addNumberOf(new Centurio(playArea), Centurio.OCCURENCES);
        addNumberOf(new Consiliarus(playArea), Consiliarus.OCCURENCES);
        addNumberOf(new Consul(playArea), Consul.OCCURENCES);
        addNumberOf(new Essedum(playArea), Essedum.OCCURENCES);
        addNumberOf(new Forum(playArea), Forum.OCCURENCES);

        addNumberOf(new Gladiator(playArea), Gladiator.OCCURENCES);
        addNumberOf(new Haruspex(playArea), Legat.OCCURENCES);
        addNumberOf(new Legionarius(playArea), Machina.OCCURENCES);
        addNumberOf(new Mercator(playArea), Mercator.OCCURENCES);
        addNumberOf(new Mercatus(playArea), Mercatus.OCCURENCES);
        addNumberOf(new Nero(playArea), Nero.OCCURENCES);
        addNumberOf(new Onager(playArea), Onager.OCCURENCES);

        addNumberOf(new Praetorianus(playArea), Praetorianus.OCCURENCES);
        addNumberOf(new Scaenicus(playArea), Scaenicus.OCCURENCES);
        addNumberOf(new Senator(playArea), Senator.OCCURENCES);
        addNumberOf(new Sicarius(playArea), Sicarius.OCCURENCES);
        addNumberOf(new Templum(playArea), TribunisPlebis.OCCURENCES);
        addNumberOf(new TribunisPlebis(playArea), TribunisPlebis.OCCURENCES);

        addNumberOf(new Turris(playArea), Turris.OCCURENCES);
        addNumberOf(new Velites(playArea), Velites.OCCURENCES);

        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(playingDeck);
    }

    public void addNumberOf(Card card, int num){
        for (int i=0;i<num;i++){
            playingDeck.add(card);
        }
    }
    public Card drawCard(int value) {
        List<Card> tempHand = new ArrayList<Card>();
        int playerChoice = 0;

        for (int i = 0; i < value; i++) {
            tempHand.add(drawACard());
        }

        //player input
        playerChoice = 0;

        return tempHand.get(playerChoice);
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

    public int getPlayingSize() {
        return playingDeck.size();
    }

    public int getDiscardSize() {
        return discardPile.size();
    }

    public String toString() {
        return "Playing deck is: " + playingDeck.toString() + "\nAnd Discard Pile is: " + discardPile.toString();

    }

}