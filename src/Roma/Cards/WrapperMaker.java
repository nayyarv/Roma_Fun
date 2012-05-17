package Roma.Cards;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 10/05/12
 * Desc:
 */
public class WrapperMaker {
    private final int costShift;
    private final int costScale;
    private final int defenseShift;
    private final int defenseScale;
    private ArrayList<Wrapper> wrapperList = new ArrayList<Wrapper>();

    public WrapperMaker(int costShift, int costScale, int defenseShift, int defenseScale){
        this.costShift = costShift;
        this.costScale = costScale;
        this.defenseShift = defenseShift;
        this.defenseScale = defenseScale;
    }

    public Wrapper insertWrapper(CardHolder card){
        Wrapper wrapper = new Wrapper(card);

        wrapper.setCostScale(costScale);
        wrapper.setCostShift(costShift);
        wrapper.setDefenseScale(defenseScale);
        wrapper.setDefenseShift(defenseShift);

        wrapperList.add(wrapper);

        return wrapper;
    }

    public ArrayList<Wrapper> getWrapperList(){
        return wrapperList;
    }

    public void clearWrapperList(){
        wrapperList.clear();
    }
}
