package Roma;

import Roma.Cards.CardHolder;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 14/05/12
 * Desc:
 */
public abstract class PlayerInterface2 {

    public final static int CANCEL = -1;

    abstract public int readInput(String title, String ... choices);

    abstract public int getIntegerInput(int bound);

    abstract public String getPlayerName(int num);

    abstract public String readString();

    abstract public void printOut(Object object, boolean newLine);

    abstract public int getHandIndex(ArrayList<CardHolder> hand, String type);

}
