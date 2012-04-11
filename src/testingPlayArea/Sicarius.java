package testingPlayArea;

/**
 * File NAME:
 * Creator: Varun Nayyar
 * Date: 26/03/12
 * Desc:
 */
public class Sicarius extends Card{
    private final static String NAME = "Sicarius";
    private final static String TYPE = "Character";
    private final static String DESCRIPTION = "Eliminates an opposing, face-up character card." +
            "The opposing card and the Sicarius are both discarded.";
    private final static int COST = 9;
    private final static int DEFENCE = 2;




    public Sicarius(PlayArea playArea){
        super(NAME, TYPE , DESCRIPTION,COST, DEFENCE, playArea);

    }

    public void activate(int player){

    }
}
