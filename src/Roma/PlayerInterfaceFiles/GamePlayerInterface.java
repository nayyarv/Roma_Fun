package Roma.PlayerInterfaceFiles;

import Roma.Cards.Card;
import Roma.Cards.CardHolder;
import Roma.Dice;
import Roma.DiceDiscs;

import java.util.ArrayList;
import java.util.Scanner;

//TODO: change all print statements throughout the project to reroute through player interface

/**
 * File Name:
 * Creator: Varun Nayyar & Andrew Lem
 * Date: 27/04/12
 * Desc: Handles all input and output for
 */
public class GamePlayerInterface extends PlayerInterface {
    private Scanner input;


    public GamePlayerInterface(){
        input = new Scanner(System.in);
    }

    public int readInput(String title, String ... choices){
        int integerInput;
        do {
            showOptions(title, choices);
            integerInput = getIntegerInput();
        } while (!checkInBounds(integerInput, choices.length));

        if(integerInput == choices.length){
            integerInput = CANCEL;
        }
        return integerInput;
    }

    public int readIndex(String title, String ... choices){
        int integerInput;
        do {
            showOptions(title, choices);
            integerInput = getIntegerInput();
        } while (!checkInBounds(integerInput, choices.length));


        if(integerInput == choices.length){
            integerInput = CANCEL;
        } else {
            integerInput--;
        }
        return integerInput;
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


    private boolean checkInBounds(int input, int max){
        boolean inBounds = false;
        if(input>0&&input<=max){
            inBounds = true;
        } else if(input==CANCEL){
            PlayerInterface.printOut("Invalid Input", true);
        } else {
            PlayerInterface.printOut("Out of range", true);
        }
        return inBounds;
    }

    //Keeps reading till valid input is recieved
    public int getIntegerInput(int bound){
        int read;
        do{
            read=getIntegerInput();
        } while (!checkInBounds(read, bound));
        return read;
    }

    //Keeps reading till valid input is recieved
    public int getIndex(int bound){
        return (getIntegerInput(bound)-1);
    }

    //Simply returns input
    private int getIntegerInput(){
        if(input.hasNextInt()){
            return input.nextInt();
        } else {
            input.next(); //clear the current input
            return CANCEL;
        }
    }

    public String getPlayerName(int num){
        printOut("Name of player" + (num + 1) + ": ", true);
        return readString();
    }

    public String readString(){
        if(input.hasNextLine()){
            return input.nextLine();
        } else {
            return "Anon"; //Since we have no input
        }
    }

    @Override
    public int getDiscIndex(ArrayList<CardHolder> myDiscs, ArrayList<CardHolder> enemyDisc, String type, int ... chosen){
        assert ((type.equalsIgnoreCase(Card.BUILDING))||(type.equalsIgnoreCase(Card.CHARACTER)));
        boolean contains;
        int i;
        String name;

        for(i=0; i< DiceDiscs.CARD_POSITIONS;i++){
            if(enemyDisc.get(i)==null){
                name = "Empty";
            } else {
                name = enemyDisc.get(i).getName();
            }
            printOut(name+"("+(i+1)+")", true);

            contains = ArrayContains(i, chosen);

            printFilter(myDiscs.get(i),type,contains);
        }
        printOut((i+1)+") Cancel", true);

        int input;
        do {
            input = getIntegerInput(DiceDiscs.CARD_POSITIONS+1)-1;
            contains = ArrayContains(input, chosen);
        } while ((input!=DiceDiscs.CARD_POSITIONS)&&!chosenRightType(myDiscs.get(input), type ,contains));

        if (input == DiceDiscs.CARD_POSITIONS) input = CANCEL;
        return input;

    }

    //TODO: merge with player.getCardIndex
    @Override
    public int getCardIndexFiltered(ArrayList<CardHolder> cardList, String type, int... chosen) {
        assert ((type.equalsIgnoreCase(Card.BUILDING))||(type.equalsIgnoreCase(Card.CHARACTER)));
        boolean contains;

        int i = 1;
        for(CardHolder card: cardList){
            printOut(i+")", true);
            contains = ArrayContains(i-1, chosen);
            printFilter(card, type, contains);
            i++;
        }
        printOut(i+") Cancel", true);


        printOut("Which option: ", true);
        int input;
        do {
            input = getIntegerInput(cardList.size()+1)-1;
            contains = ArrayContains(input, chosen);
        } while ((input!= cardList.size())&&!chosenRightType(cardList.get(input),type, contains));

        if (input== cardList.size()) input = CANCEL;
        return input;
    }

    //choose from list
    //input: ArrayList (of dice or of cards)
    //return int
    @Override


    public void printCardList(ArrayList<CardHolder> cardList){
        int i = 1;
        PlayerInterface.printOut("-------------------------------------", true);
        for(CardHolder card : cardList){
            PlayerInterface.printOut(i + ") " + card.getName(), true);
            i++;
        }
    }

    private boolean chosenRightType(CardHolder card, String type, boolean contains){
        boolean correct = (card!=null)&&(card.getType().equalsIgnoreCase(type));
        // check's the chosen card is of the correct type
        if(!correct){
            printOut("Incorrect card chosen," +
                    " expecting a "+ type +" card, instead received a", true);
            if (card==null){
                printOut("n empty Card", true);
            } else if (contains){
                printOut("n already chosen card", true);
            } else {
                printOut(" "+ card.getType()+" card", true);
            }
        }
        return correct;
    }

    private void printFilter(CardHolder card, String type, boolean contains){
        if(card==null){
            printOut("#Empty#", true);
        } else if(card.getType().equalsIgnoreCase(type)&&!contains){
            printOut(card.getName(), true);
        } else {
            printOut("###"+card.getName()+"###", true);
        }
    }

    private boolean ArrayContains(int key, int ... chosen){
        for (int num: chosen){
            if (num==key){
                return true;
            }
        }
        return false;
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