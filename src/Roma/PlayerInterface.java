package Roma;

import java.util.Scanner;

//TODO: change all print statements throughout the project to reroute through player interface

/**
 * File Name:
 * Creator: Varun Nayyar & Andrew Lem
 * Date: 27/04/12
 * Desc: Handles all input and output for
 */
public class PlayerInterface {
    private Scanner input;
    public final static int CANCEL = -1;

    public PlayerInterface(){
        //Empty constructor??
        input = new Scanner(System.in);
    }

    public int readInput(String title, String ... choices){
        int integerInput;
        do {
            showOptions(title, choices);
            integerInput = getIntegerInput();
        } while (!checkInBounds(integerInput, choices.length));

        return integerInput;
    }

    private void showOptions(String title, String ... options){
        printOut("-------------------------------------");
        printOut(title);
        for(int i=0;i<options.length;i++){
            printOut((i+1)+") " + options[i]);
        }
    }

    private int getIntegerInput(){
        if(input.hasNextInt()){
            return input.nextInt();
        } else {
            input.next(); //clear the current input
            return CANCEL;
        }
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


    public int getIntegerInput(int bound){
        int read;
        do{
            read=getIntegerInput();
        } while (!checkInBounds(read, bound));
        return read;
    }

    public void printOut(String string){
        System.out.println(string);
    }

    public void printOut(Object object){
        System.out.println(object.toString());
    }
}