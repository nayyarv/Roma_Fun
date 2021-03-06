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
    private static final int CARDS_IN_DECK = 52;
    //Objects
    private ArrayList<CardHolder> playingDeck = new ArrayList<CardHolder>();
    private ArrayList<CardHolder> discardPile = new ArrayList<CardHolder>();

    private PlayArea playArea;

    //Variables
    private boolean noMoreCards = false;

    public CardManager(PlayArea playArea) {
        this.playArea = playArea;
        //Will insert all cards, and shuffle
        playingDeck.addAll(Aesculapinum.playSet(playArea));
        playingDeck.addAll(Architectus.playSet(playArea));
        playingDeck.addAll(Basilica.playSet(playArea));
        playingDeck.addAll(Centurio.playSet(playArea));
        playingDeck.addAll(Consiliarius.playSet(playArea));

        playingDeck.addAll(Consul.playSet(playArea));
        playingDeck.addAll(Essedum.playSet(playArea));
        playingDeck.addAll(Forum.playSet(playArea));
        playingDeck.addAll(Gladiator.playSet(playArea));
        playingDeck.addAll(Haruspex.playSet(playArea));

        playingDeck.addAll(Legat.playSet(playArea));
        playingDeck.addAll(Legionarius.playSet(playArea));
        playingDeck.addAll(Machina.playSet(playArea));
        playingDeck.addAll(Mercator.playSet(playArea));
        playingDeck.addAll(Mercatus.playSet(playArea));

        playingDeck.addAll(Nero.playSet(playArea));
        playingDeck.addAll(Onager.playSet(playArea));
        playingDeck.addAll(Praetorianus.playSet(playArea));
        playingDeck.addAll(Scaenicus.playSet(playArea));
        playingDeck.addAll(Senator.playSet(playArea));

        playingDeck.addAll(Sicarius.playSet(playArea));
        playingDeck.addAll(Templum.playSet(playArea));
        playingDeck.addAll(TribunusPlebis.playSet(playArea));
        playingDeck.addAll(Turris.playSet(playArea));
        playingDeck.addAll(Velites.playSet(playArea));

        playingDeck.addAll(Kat.playSet(playArea));
        playingDeck.addAll(GrimReaper.playSet(playArea));
        playingDeck.addAll(TelephoneBox.playSet(playArea));

        shuffle();
    }

    public void shuffle() {
        shuffle(playingDeck);
    }

    public void shuffle(ArrayList<CardHolder> cardList){
        Collections.shuffle(cardList);
    }

    private void addNumberOf(CardHolder card, int num){
        for (int i=0;i<num;i++){
            playingDeck.add(card);
        }
    }

    public CardHolder drawACard() {
        CardHolder temp = null;
        if(!playingDeck.isEmpty()){
            temp = playingDeck.remove(0);
        }
        if (playingDeck.isEmpty() && !discardPile.isEmpty()) {
            playingDeck.addAll(discardPile);
            discardPile.clear();
            shuffle();
        } else if (playingDeck.isEmpty() && discardPile.isEmpty()) {
            noMoreCards = true;
        }
        return temp;
    }

    public ArrayList<CardHolder> drawNCards(int number){
        ArrayList<CardHolder> drawHand = new ArrayList<CardHolder>();
        CardHolder card;
        for (int i=0; i<number;i++){
            if(!noMoreCards){
                drawHand.add(drawACard());
            }
        }
        return drawHand;
    }

    //
    public void insertCard(CardHolder theCard) {
        playingDeck.add(0, theCard);
    }

    public void discard(CardHolder theCard) {
        discardPile.add(0, theCard);
        if (noMoreCards) {
            playingDeck.addAll(discardPile);
            discardPile.clear();
            shuffle();
            noMoreCards = false;
        }
    }


    public CardHolder getCardfromDeck(String name){
        for(CardHolder card: playingDeck){
            if (name.equalsIgnoreCase(card.getName())){
                playingDeck.remove(card);
                return card;
            }
        }
        return null;
    }


    public void discard(ArrayList<CardHolder> cardList) {
        discardPile.addAll(0, cardList);
        if (noMoreCards) {
            playingDeck.addAll(discardPile);
            discardPile.clear();
            shuffle();
            noMoreCards = false;
        }
    }

    public ArrayList<CardHolder> getPlayingDeck() {
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

    public CardHolder getTopDiscard(){
        if (discardPile.isEmpty()){
            return null;
        } else {
            return discardPile.get(0);
        }

    }

    public ArrayList<CardHolder> getDiscardPile() {
        return discardPile;
    }

    public void setPlayingDeck(ArrayList<CardHolder> newDeck) {
        playingDeck = newDeck;
    }

    public void setDiscardPile(ArrayList<CardHolder> newDiscard) {
        discardPile = newDiscard;
    }

    public ArrayList<CardHolder> viewTopCards(int value) {
        ArrayList<CardHolder> temp = new ArrayList<CardHolder>();

        for(int i = 0; i < value; i++){
            temp.add(playingDeck.get(i));
        }

        return temp;
    }
}