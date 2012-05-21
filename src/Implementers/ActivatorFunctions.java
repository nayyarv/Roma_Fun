package Implementers;


import Implementers.ImplementedActivators.*;
import Roma.Dice;
import Roma.Player;
import framework.cards.Card;
import framework.interfaces.activators.CardActivator;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class ActivatorFunctions {

    public static int getDieindex(Player player, int diceReqd){

        ArrayList<Dice> freeDice = new ArrayList<Dice>();
        freeDice.addAll(player.getFreeDice());
        int currActionIndex = player.getCurrentAction().getActionDiceIndex();

        freeDice.remove(currActionIndex);


        Dice dice = freeDice.get(0);
        int i;
        for(i=0; i<freeDice.size()&&dice.getValue()!=diceReqd;i++){
            dice = freeDice.get(i);
        }
        System.out.println("Found "+ diceReqd+" at "+ i);
        return i;

    }

    public static CardActivator getCorrectActivator(Card chosen, Player player){
        CardActivator activator = new dummyActivator();
        if (chosen.equals(Card.AESCULAPINUM)){
            activator = new SelectorImpl(player);
        } else if (chosen.equals(Card.ARCHITECTUS)){
            activator = new layerImpl(player);
        } else if (chosen.equals(Card.CENTURIO)){
            activator = new CenturioActivatorImpl(player);
        } else if (chosen.equals(Card.CONSILIARIUS)){
            activator = new RearrangerImpl(player);
        } else if (chosen.equals(Card.CONSUL)){
            activator = new ConsulActivatorImpl(player);
        } else if (chosen.equals(Card.ESSEDUM)){
            activator = new simpleActivator(player);
        } else if (chosen.equals(Card.FORUM)){
            activator = new ForumActivatorImpl(player);
        } else if (chosen.equals(Card.GLADIATOR)){
            activator = new Assassin(player);
        } else if (chosen.equals(Card.HARUSPEX)){
            activator = new SelectorImpl(player);
        } else if (chosen.equals(Card.LEGAT) ){
            activator = new simpleActivator(player);
        } else if (chosen.equals(Card.LEGIONARIUS)){
            activator = new LegionariusActivatorImpl(player);
        } else if (chosen.equals(Card.MACHINA)){
            activator = new RearrangerImpl(player);
        } else if (chosen.equals(Card.MERCATOR)){
            activator = new MercatorActivatorImpl(player);
        } else if (chosen.equals(Card.MERCATUS)){
            activator = new simpleActivator(player);
        } else if (chosen.equals(Card.NERO)){
            activator = new Assassin(player);
        } else if (chosen.equals(Card.ONAGER)){
            activator = new FighterImpl(player);
        } else if (chosen.equals(Card.PRAETORIANUS)){
            activator = new Assassin(player);
        } else if (chosen.equals(Card.SCAENICUS)){

        } else if (chosen.equals(Card.SENATOR)){
            activator = new layerImpl(player);
        } else if (chosen.equals(Card.SICARIUS)){
            activator = new Assassin(player);
        } else if (chosen.equals(Card.TELEPHONEBOX)){

        } else if(chosen.equals(Card.TRIBUNUSPLEBIS)){
            activator =  new simpleActivator(player);
        } else if (chosen.equals(Card.VELITES)){
            activator = new FighterImpl(player);
        } else {
            throw new UnsupportedOperationException();
        }
        return activator;
    }


}
