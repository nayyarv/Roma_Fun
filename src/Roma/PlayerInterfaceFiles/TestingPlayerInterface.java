package Roma.PlayerInterfaceFiles;

import Roma.Cards.CardHolder;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 14/05/12
 * Desc:
 */
public class TestingPlayerInterface extends PlayerInterface {


    @Override
    public int readInput(String title, String... choices) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getIntegerInput(int bound) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getPlayerName(int num) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String readString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void printOut(Object object, boolean newLine) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getHandIndex(ArrayList<CardHolder> hand, String type, int ... chosen) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
