package Roma.PlayerInterfaceFiles;

import Roma.Cards.Card;
import Roma.Cards.CardHolder;
import Roma.Dice;

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
        printOut(title,true);
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
            System.out.println("Invalid Input");
        } else {
            System.out.println("Out of range");
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
        printOut("Name of player" + (num + 1) + ": ", false);
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
    public void printOut(Object object, boolean newLine){
        if (newLine){
            System.out.println(object.toString());
        } else {
            System.out.print(object.toString());
        }
    }

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
            printOut(name+"("+(i+1)+")", false);

            contains = ArrayContains(i, chosen);

            printFilter(myDiscs.get(i),type,contains);
        }
        printOut((i+1)+") Cancel",true);

        int input;
        do {
            input = getIntegerInput(DiceDiscs.CARD_POSITIONS+1)-1;
            contains = ArrayContains(input, chosen);
        } while ((input!=DiceDiscs.CARD_POSITIONS)&&!chosenRightType(myDiscs.get(input), type ,contains));

        if (input == DiceDiscs.CARD_POSITIONS) input = CANCEL;
        return input;

    }

    //TODO: flesh out function
    @Override
    public int getCardIndexFiltered(ArrayList<CardHolder> cardList, String type, int... chosen) {
        assert ((type.equalsIgnoreCase(Card.BUILDING))||(type.equalsIgnoreCase(Card.CHARACTER)));
        boolean contains;

        printFilteredList(cardList, type, chosen);

        printOut("Which option: " , false);
        int input;
        do {
            input = getIntegerInput(cardList.size()+1)-1;
            contains = ArrayContains(input, chosen);
        } while ((input!= cardList.size())&&!chosenRightType(cardList.get(input),type, contains));

        if (input== cardList.size()) input = CANCEL;
        return input;
    }

    @Override
    public int getCardIndex(ArrayList<CardHolder> cardList) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private boolean chosenRightType(CardHolder card, String type, boolean contains){
        boolean correct = (card!=null)&&(card.getType().equalsIgnoreCase(type));
        // check's the chosen card is of the correct type
        if(!correct){
            printOut("Incorrect card chosen," +
                    " expecting a "+ type +" card, instead received a", false);
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

    private void printFilteredList(ArrayList<CardHolder> cardList, String type, int ... chosen){
        int i = 1;

        for(CardHolder card: cardList){
            System.out.print(i+")");
            if(card==null){
                printOut("#Empty#", true);
            } else if(card.getType().equalsIgnoreCase(type)&&!ArrayContains(i,chosen)){
                printOut(card.getName(), true);
            } else {
                printOut("###"+card.getName()+"###", true);
            }
            i++;
        }

        printOut(i+") Cancel",true);
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
        System.out.println("Dice:");
        int i = 0;
        for(Dice dice : diceList){
            i++;
            System.out.println(i + ") " + dice.getValue());
        }
    }
}