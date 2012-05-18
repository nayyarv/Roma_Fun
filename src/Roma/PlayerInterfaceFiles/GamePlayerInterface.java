package Roma.PlayerInterfaceFiles;

import Roma.Cards.CardHolder;
import Roma.Dice;
import Roma.DiceDiscs;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * File Name:
 * Creator: Varun Nayyar & Andrew Lem
 * Date: 27/04/12
 * Desc: Handles all input and output for
 */
public class GamePlayerInterface extends PlayerInterface {
    private Scanner input;
    private static final int INVALID_INPUT = -100;


    public GamePlayerInterface(){
        input = new Scanner(System.in);
    }

    @Override
    public int readInput(String title, boolean cancelOn, String... choices){
        int integerInput;
        int min = cancelOn ? 0 : 1;
        do {
            showOptions(title, choices);
            if (cancelOn) printOut("0) Cancel", true);
            integerInput = getIntegerInput();
        } while (!checkIntegerInBounds(integerInput, min, choices.length));

        if(integerInput==0){
            integerInput=CANCEL;
        }
        return integerInput;
    }

    @Override
    public int readIndex(String title, boolean cancelOn, String... choices){
        int indexInput;
        indexInput = readInput(title, cancelOn, choices);
        if(indexInput != CANCEL){
            indexInput--;
        }
        return indexInput;
    }

    private void showOptions(String title, String ... options){
        printLine();
        printOut(title, true);
        for(int i=0;i<options.length;i++){
            printOut((i+1)+") " + options[i], true);
        }
    }

    private void printLine(){
        printOut("-------------------------------------", true);
    }

    private boolean checkIntegerInBounds(int input, int ... range) {
        int min, max;
        boolean inBounds = false;
        if(range.length > 1){
            min = range[0];
            max = range[1];
            if(input >= min && input <= max){
                inBounds = true;
            } else if (input == INVALID_INPUT){
                printOut("Must Input a number", true);
            } else {
                printOut("Out of Range", true);
            }
        } else if(range.length == 1){
            min = range[0];
            if(input >= min){
                inBounds = true;
            } else if (input == INVALID_INPUT){
                printOut("Must Input a number", true);
            } else {
                printOut("Out of Range", true);
            }
        }
        return inBounds;
    }



    //Keeps reading till valid input is recieved
    @Override
    public int getIntegerInput(int ... range){
        int min, max, read = CANCEL;
        if(range.length > 1){
            min = range[0];
            max = range[1];
            do{
                read=getIntegerInput();
            } while (!checkIntegerInBounds(read, min,  max));
        } else if(range.length == 1) {
            min = range[0];
            do{
                read=getIntegerInput();
            } while (!checkIntegerInBounds(read, min));
        }
        return read;
    }


    //Keeps reading till valid input is recieved
    @Override
    public int getIndex(int bound){
        return (getIntegerInput(1, bound)-1);
    }

    //Simply returns input
    private int getIntegerInput(){
        if(input.hasNextInt()){
            return input.nextInt();
        } else {
            input.next(); //clear the current input
            return (INVALID_INPUT); //a quick hack
        }
    }

    @Override
    public String getPlayerName(int num){
        printOut("Name of player" + (num + 1) + ": ", true);
        return readString();
    }

    @Override
    public String readString(){
        if(input.hasNextLine()){
            return input.nextLine();
        } else {
            return "Anon"; //Since we have no input
        }
    }
    //choose from list
    //input: ArrayList (of dice or of cards)
    //return int
    @Override
    public void printCardList(ArrayList<CardHolder> cardList){
        int i = 1;
        printLine();
        for(CardHolder card : cardList){
            PlayerInterface.printOut(i + ") " + card.getName(), true);
            i++;
        }
    }

    @Override
    public void printFilteredDiceList (ArrayList<CardHolder> currPlayer, ArrayList<CardHolder> opposingPlayer,
                                       boolean filterCurr, boolean filterOther){

        printOut("DiceDiscs", true);
        printOut(padLeft(padCentre("Current Player", 25),14) + padCentre("Opposing Player", 25), true);

        String curr;
        String opp;

        for(int i = 0;i< DiceDiscs.CARD_POSITIONS-1;i++){
            printOut(padRight((i+1)+") Dice Disc "+(i+1),2) + ":"  , false);
            curr = Filter(currPlayer.get(i), filterCurr);
            opp = Filter(opposingPlayer.get(i), filterOther);
            printOut(padCentre(curr,25)+padCentre(opp, 25), true);
        }

        curr = Filter(currPlayer.get(DiceDiscs.BRIBERY_INDEX), filterCurr);
        opp = Filter(currPlayer.get(DiceDiscs.BRIBERY_INDEX), filterOther);

        printOut(("7) Bribery Disc: "+padCentre(curr,25)+padCentre(opp, 25)), true);
    }

    @Override
    public void printFilteredCardList(ArrayList<CardHolder> cardList, boolean  shouldFilter){
        if (shouldFilter){
            int i = 1;
            for(CardHolder card: cardList){
                printOut(i+") ", false);
                printOut(Filter(card, false), true);
                i++;
            }
        } else {
            printCardList(cardList);
        }
    }

    private String Filter(CardHolder card, boolean applyFilter){
        if(card==null){
            return ("#Empty#");
        } else if (!applyFilter){
            return card.getName();
        } else if(card.getPlayable()) {
            return (card.getName());
        } else {
            return ("###"+card.getName()+"###");
        }
    }

    @Override
    public void printDiceList(ArrayList<Dice> diceList) {
        PlayerInterface.printOut("Dice:", true);
        int i = 0;
        for(Dice dice : diceList){
            i++;
            PlayerInterface.printOut(i + ") " + dice.getValue(), true);
        }
    }
}