package Roma;

import Roma.PlayerInterfaceFiles.GamePlayerInterface;
import Roma.PlayerInterfaceFiles.PlayerInterface;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RomaGame {
    //Constants
    public final static int MAX_PLAYERS = 2;
    public final static int PLAYER_ONE = 0;
    public final static int PLAYER_TWO = 1;
    public final static int PAUSE_DURATION = 1500;
    public final static int NUM_CARDS_SWAPPED = 2;
    public final static int NUM_INIT_CARDS = 5;

    //Object pointers
    PlayerInterface playerInterface = new GamePlayerInterface();

    private PlayArea game;

    //Variables
    private boolean exit = false;



    public static void main(String[] arg){
        RomaGame game = new RomaGame();
        game.runRoma();
    }

    public void runRoma() {

        //Welcome message
        System.out.printf("Welcome to RomaGame :)\n" +
                "by Andrew Lem and Varun Nayyar\n");
//        try {
//          //  Thread.sleep(PAUSE_DURATION);
//        } catch (InterruptedException e) {
//            // blank
//        }

        //Program running loop
        while (!exit) {
            int choice = playerInterface.readInput("What would you like: ",
                    false, "New Game",
                    "Rules",
                    "Quit");

            if (choice == 1) { // new play area and game
                game = new PlayArea(this);
                game.runGame();

            } else if (choice == 2) {  // open pdf of rules
                //Code found online:
                // http://stackoverflow.com/questions/2546968/open-pdf-file-on-fly-from-java-application
                //Opens the pdf of RomaGame lol
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("RomaGame.pdf");
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
                        // no application registered for PDFs
                        PlayerInterface.printOut("RomaGame.pdf not found!", true);
                    }
                }
            } else if (choice == 3) {  // quit game
                exit = true;
                PlayerInterface.printOut("Quitting RomaGame.\n" +
                        "Good Bye~", true);
                try {
                    Thread.sleep(PAUSE_DURATION);
                } catch (InterruptedException e) {
                    // blank
                }
            } else if (choice == 4) {  // debug choice

            } else {
                PlayerInterface.printOut("Invalid choice. Please choose again.", true);
            }
        }
    }

    public void gameOverMessage() {

        //TODO: actually implement this

    }
}
