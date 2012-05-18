package Roma.PlayerInterfaceFiles;

import Roma.Cards.*;
import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 14/05/12
 * Desc:
 */

public abstract class PlayerInterface {

    public final static int CANCEL = -1;

    abstract public int readInput(String title, boolean cancelOn, String... choices);

    abstract public int readIndex(String title, boolean cancelOn, String... choices);

    abstract public int getIntegerInput(int min, int max);

    abstract public int getIndex(int bound);

    abstract public String getPlayerName(int num);

    abstract public String readString();

    abstract public void printDiceList(ArrayList<Dice> diceList);

    abstract public void printCardList(ArrayList<CardHolder> cardList);

    public static void printOut(Object object, boolean newLine){
        if (newLine){
            System.out.println(object.toString());
        } else {
            System.out.print(object.toString());
        }
    }

    public static String padCentre(String s, int n){//Use n bits
        if(s.length()>=n){
            return s;
        } else {
            int side = (n-s.length())/2;
            int rem = (n-s.length())%2;
            if (rem==1){
                return padRight(padLeft(s, side),side+1);
            } else {
                return padRight(padLeft(s, side),side);
            }
        }

    }

    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$#" + n + "s", s);
    }

    public abstract void printFilteredDiceList(ArrayList<CardHolder> currPlayer, ArrayList<CardHolder> opposingPlayer,
                                               boolean filterCurr, boolean filterOther);

    public abstract void printFilteredCardList(ArrayList<CardHolder> cardList, boolean filter);
}
