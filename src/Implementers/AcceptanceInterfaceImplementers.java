package Implementers;

import framework.interfaces.AcceptanceInterface;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 14/05/12
 * Desc:
 */
public class AcceptanceInterfaceImplementers implements AcceptanceInterface {

    /**
     * Return a {@link framework.interfaces.MoveMaker} that will modify the given GameState.
     * <p/>
     * <p>
     * This MoveMaker will be used by the tests to modify the GameState
     * that was given by getInitialState. The affected GameState is
     * included as a parameter so you can ensure that the MoveMaker will
     * operate on the correct GameState.
     * </p>
     *
     * @param state the GameState that the mover will apply changes to
     * @return a MoveMaker that will modify the given GameState
     */
    @Override
    public MoveMaker getMover(GameState state) {
        return new MoveMakerImplementer(state);
    }

    /**
     * Instantiate a {@link framework.interfaces.GameState} object.
     * <p/>
     * <p>
     * The created GameState should be a mutable new instance as this is called
     * before each test is run.
     * </p>
     * <p/>
     * <p>
     * The state should be set in the initial condition as defined per:
     * TODO: add the crap that makes an initial state here.
     * </p>
     *
     * @return a GameState at the initial state
     */
    @Override
    public GameState getInitialState() {
        return new GameStateImplementer();  //To change body of implemented methods use File | Settings | File Templates.
    }
}
