package Roma;

import java.util.Scanner;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 27/04/12
 * Desc:
 */
public class PlayerInterface {
    private Scanner john;
    public final static int CANCEL = -1;

    public PlayerInterface(){
        //Empty constructor??
        john = new Scanner(System.in);
    }

    public int readInput(String title, String ... choices){
        int len = choices.length;
        System.out.println("-------------------------------------");
        System.out.println(title);

        for(int i=0;i<len;i++){
            System.out.println(i+1+") " + choices[i]);
        }
        return getIntegerInput();
    }

    private int getIntegerInput(){
        if(john.hasNextInt()){
            return john.nextInt();
        } else {
            john.next(); //clear the current input
            return CANCEL;
        }

    }

}
