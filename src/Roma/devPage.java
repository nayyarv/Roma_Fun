package Roma;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: nayyarv
 * Date: 10/03/12
 * Time: 11:18 PM
 * description: Developers page
 */
public class DevPage {
    //We use this to Testers.TestCardDeck our individual modules

    public DevPage() {
        //??
    }

    public void welcomeDev() {
        Scanner input = new Scanner(System.in);
        boolean stop = false;
        while (!stop) {
            //Roma.cleanScreen();
            System.out.println("Welcome to The Developers Page");
            System.out.println("Would you like to Testers.TestCardDeck:");
            System.out.println("1: The dice?");
            System.out.println("2: Card Deck");
            System.out.println("2: Back");

            //Will make this more interesting later

            int status = input.nextInt(); //very poor error handling :(
            while (status <= 0 || status > 2) {
                System.out.println("Horrid Input Bro - Enter Choice again: ");
                status = input.nextInt();
            }

            if (status == 1) {
                System.out.println("The Dice give");
                RandomInt john = new RandomInt();
                for (int i = 0; i < 5; i++) {
                    System.out.print(john.randomInt() + ", ");
                }


            } else {
                stop = true;
            }
        }

    }
}
