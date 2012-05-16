package Testers;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 16/05/12
 * Time: 9:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestRemoveAllFunction {

    public static void main (String[] args){
        TestRemoveAllFunction testRemoveAllFunction = new TestRemoveAllFunction();
        ArrayList<BlankCard> removeList = testRemoveAllFunction.generateRemoveList();
        ArrayList<BlankCard> extras = testRemoveAllFunction.generateExtras();
        testRemoveAllFunction.addCards(extras);
        testRemoveAllFunction.printCardList();
        testRemoveAllFunction.shuffle();
        testRemoveAllFunction.printCardList();
        testRemoveAllFunction.removeAll(removeList);
        testRemoveAllFunction.printCardList();
        removeList = testRemoveAllFunction.grabSome();
        testRemoveAllFunction.removeAll(removeList);
        testRemoveAllFunction.printCardList();
    }

    private ArrayList<BlankCard> grabSome() {
        ArrayList<BlankCard> removeList = new ArrayList<BlankCard>();
        for(int i = 0; i < 50; i += 2){
            removeList.add(blankCards.get(i));
        }
        return removeList;  //To change body of created methods use File | Settings | File Templates.
    }

    private void shuffle() {
        Collections.shuffle(blankCards);
    }

    ArrayList<BlankCard> blankCards = new ArrayList<BlankCard>();
    private TestRemoveAllFunction(){
        for(int i = 0; i < 50; i++){
            blankCards.add(new BlankCard(i));
        }
    }

    private ArrayList<BlankCard> generateExtras(){
        ArrayList<BlankCard> extras = new ArrayList<BlankCard>();
        for(int i = 0; i < 50; i += 3){
            extras.add(new BlankCard(i));
        }
        return extras;
    }

    private ArrayList<BlankCard> generateRemoveList(){
        ArrayList<BlankCard> removeList = new ArrayList<BlankCard>();
        for(int i = 0; i < 50; i += 2){
            removeList.add(new BlankCard(i));
        }
        return removeList;
    }

    private void removeAll(ArrayList<BlankCard> cardList){
        blankCards.removeAll(cardList);
    }

    private void addCard(BlankCard card){
        blankCards.add(card);
    }

    private void addCards(ArrayList<BlankCard> cards){
        blankCards.addAll(cards);
    }

    private void printCardList(){
        for(BlankCard card : blankCards){
            System.out.print(card.getValue() + " ");
        }
        System.out.println();
    }

    private class BlankCard{
        private final int value;

        private BlankCard(int value){
            this.value = value;
        }
        private int getValue(){
            return value;
        }
    }
}
