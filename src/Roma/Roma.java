package Roma;

import java.util.*;
import java.io.*;
import java.awt.*;

//TODO: change most constructors to have a static construction method

public class Roma {
    //Constants
    public final static int MAX_PLAYERS = 2;
    public final static int PLAYER_ONE = 0;
    public final static int PLAYER_TWO = 1;
    public final static int PAUSE_DURATION = 1500;

    //Object pointers
    PlayerInterface playerInterface = new PlayerInterface();
    private PlayArea game;

    //Variables
    private boolean autoTesting;
    private boolean manualTesting;
    private boolean gameOver = false;
    private boolean exit = false;

    public Roma() {
        this.autoTesting = false;
    }

    public Roma(boolean autoTesting) {
        this.autoTesting = autoTesting;
        printTesting();
    }

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
                gameOver = false;
                game = new PlayArea(this);
                game.runGame();

            } else if (choice == 2) {  // open pdf of rules
                //Code found online: http://stackoverflow.com/questions/2546968/open-pdf-file-on-fly-from-java-application
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
                manualTesting = !manualTesting;
                printTesting();
            } else {
                System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public void newGame() {
        gameOver = false;
    }

    public void endGame() {
        gameOver = true;
        //TODO: actually implement this
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public boolean isAutoTesting() {
        return autoTesting;
    }

    public boolean isManualTesting() {
        return manualTesting;
    }

    public void printTesting() {
        if (autoTesting) {
            System.out.println("Auto testing mode ENABLED. Print statements suppressed.");
        } else if (manualTesting) {
            System.out.println("Manual testing mode ENABLED. Extra debug statements ENABLED.");
        } else {
            System.out.println("Testing modes DISABLED. Game will run normally.");
        }
    }
}
