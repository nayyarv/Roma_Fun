package Roma;

/**
 * Created by IntelliJ IDEA.
 * User: Varun Nayyar
 * Date: 10/03/12
 * Time: 9:48 PM
 * description: The interface screen
 */

//For opening pdf and reading in data
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ClientInterface {

    public ClientInterface(){

    }

    public void welcome(){
        Scanner input = new Scanner( System.in );
        int status;
        boolean stop=false;

        while (!stop){
            //Roma.cleanScreen();
            System.out.println("Welcome to Roma :)\n" +
                    "This port developed by Andrew Lem and Varun Nayyar\n" +
                    "Choose Your Options\n" +
                    "1: New Game\n" +
                    "2: Rules\n" +
                    "3: Quit\n" +
                    "4: Dev Team ONLY\n" +
                    "Enter Choice: ");

            status= input.nextInt(); //very poor error handling :(

            while(status <= 0||status > 4){
                System.out.println("Horrid Input Bro - Enter Choice again: ");
                status= input.nextInt();
            }

            if (status == 1||status == 2) {
                //New Game
                boolean back = false;
                while (!back){
                    System.out.println("Under Construction");
                    System.out.println("Would you like to go back [Y/N]? ");
                    //back = Roma.yesNo();
                }

            } else if (status == 10) {
                //Code found online: http://stackoverflow.com/questions/2546968/open-pdf-file-on-fly-from-java-application
                //Opens the pdf of Roma lol
                //Currently illegal - but I dont want to waste time on copying instructions across
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("Roma.pdf");
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
                        // no application registered for PDFs
                        System.out.println("Your computer sucks :P");
                    }
                }
            } else if (status == 3) {
                //Exit
                //Might have an "Are you sure" as well :P
                System.out.println("Quitting");
                stop=true;
                //System.exit(0);
            } else if (status == 4) {
                // Roma.DevPage class for code testing - It would be nice for later on
                //System.out.println("If you were a dev, you wouldn't need this page");
                DevPage dev = new DevPage();
                dev.welcomeDev();
            }
        }
    }
}
