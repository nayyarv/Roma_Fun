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

    abstract public int readInput(String title, String ... choices);

    abstract public int readIndex(String title, String ... choices);

    abstract public int getIntegerInput(int min, int max);

    abstract public int getIndex(int bound);

    abstract public String getPlayerName(int num);

    abstract public String readString();

    abstract public int getCardIndexFiltered(ArrayList<CardHolder> cardList, String type, int... chosen);

    abstract public void printDiceList(ArrayList<Dice> diceList);

    abstract public void printCardList(ArrayList<CardHolder> cardList);

    abstract public int getDiscIndex(ArrayList<CardHolder> myDiscs,
                                     ArrayList<CardHolder> enemyDisc, String type, int ... chosen);

    public static void printOut(Object object, boolean newLine){
        if (newLine){
            System.out.println(object.toString());
        } else {
            System.out.print(object.toString());
        }
    }
}
