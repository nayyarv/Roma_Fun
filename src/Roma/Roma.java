package Roma;

import Roma.PlayerInterfaceFiles.*;

import java.io.*;
import java.awt.*;

//TODO: change most constructors to have a static construction method

public class Roma {
    //Constants
    public final static int MAX_PLAYERS = 2;
    public final static int PLAYER_ONE = 0;
    public final static int PLAYER_TWO = 1;
    public final static int PAUSE_DURATION = 1500;
    public final static int NUM_CARDS_SWAPPED = 2;
    public final static int NUM_INIT_CARDS = 5;

    //Object pointers
    PlayerInterface2 playerInterface = new GamePlayerInterface();

    private PlayArea game;

    //Variables
    private boolean exit = false;



    public static void main(String[] arg){
        Roma game = new Roma();
        game.runRoma();
    }

    public void runRoma() {

        //Welcome message
        System.out.printf("Welcome to Roma :)\n" +
                "by Andrew Lem and Varun Nayyar\n");
//        try {
//          //  Thread.sleep(PAUSE_DURATION);
//        } catch (InterruptedException e) {
//            // blank
//        }

        //Program running loop
        while (!exit) {
            int choice = playerInterface.readInput("What would you like: ",
                    "New Game",
                    "Rules",
                    "Quit");

            if (choice == 1) { // new play area and game
                game = new PlayArea(this);
                game.runGame();

            } else if (choice == 2) {  // open pdf of rules
                //Code found online:
                // http://stackoverflow.com/questions/2546968/open-pdf-file-on-fly-from-java-application
                //Opens the pdf of Roma lol
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("Roma.pdf");
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
                        // no application registered for PDFs
                        System.out.println("Roma.pdf not found!");
                    }
                }
            } else if (choice == 3) {  // quit game
                exit = true;
                System.out.println("Quitting Roma.\n" +
                        "Good Bye~");
                try {
                    Thread.sleep(PAUSE_DURATION);
                } catch (InterruptedException e) {
                    // blank
                }
            } else if (choice == 4) {  // debug choice

            } else {
                System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public void gameOverMessage() {

        //TODO: actually implement this

    }
}
