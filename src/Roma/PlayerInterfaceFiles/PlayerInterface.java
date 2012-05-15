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

    abstract public int getIntegerInput(int bound);

    abstract public String getPlayerName(int num);

    abstract public String readString();

    abstract public void printOut(Object object, boolean newLine);

    abstract public int getCardIndexFiltered(ArrayList<CardHolder> cardList, String type, int... chosen);

    abstract public int getCardIndex(ArrayList<CardHolder> cardList);

    abstract public void printDiceList(ArrayList<Dice> diceList);
}
