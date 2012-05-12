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
    //Objects
    private final ArrayList<Card> playingDeck = new ArrayList<Card>();
    private final ArrayList<Card> discardPile = new ArrayList<Card>();

    private PlayArea playArea;

    //Variabls
    private boolean noMoreCards = false;

    public CardManager(PlayArea playArea) {
        this.playArea = playArea;
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

    public void shuffle(ArrayList<Card> cardList){
        Collections.shuffle(cardList);
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


    public Card getCardfromDeck(String name){
        for(Card card: playingDeck){
            if (name.equalsIgnoreCase(card.getName())){
                playingDeck.remove(card);
                return card;
            }
        }
        return null;
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