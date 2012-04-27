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

    public PlayerInterface(){
        //Empty constructor??
        john = new Scanner(System.in);
    }

    public int readInput(String title, String ... choices){
        int len = choices.length;
        int ans;
        System.out.println("-------------------------------------");
        System.out.println(title);

        for(int i=0;i<len;i++){
            System.out.println(i+1+") " + choices[i]);
        }
        //TODO - better read in
        ans = john.nextInt();
        return ans;
    }

}
