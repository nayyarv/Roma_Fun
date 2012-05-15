package Testers;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 15/05/12
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThrowExperiment {
    Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        int num = 10;
        ThrowExperiment throwExperiment = new ThrowExperiment();

        do{
            try {
                System.out.print("Input a single digit number: ");
                num = throwExperiment.readSingleDigit();
                System.out.println("Number read: " + num);
            } catch (CancelOption cancelOption) {
                cancelOption.errorMessage();
            }
        } while(num != 0);
    }

    private class CancelOption extends Exception{
        private void errorMessage(){
            System.out.println("Invalid input detected");
        }

    }

    private int readSingleDigit() throws CancelOption{
        int value;
        value = input.nextInt();
        if(value < 0 || value > 9) throw new CancelOption();

        return value;
    }
}
