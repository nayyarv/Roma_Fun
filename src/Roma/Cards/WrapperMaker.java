package Roma.Cards;

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

    public WrapperMaker(int costShift, int costScale, int defenseShift, int defenseScale){
        this.costShift = costShift;
        this.costScale = costScale;
        this.defenseShift = defenseShift;
        this.defenseScale = defenseScale;
    }

    public Wrapper insertWrapper(Card card){
        Wrapper wrapper = new Wrapper(card.getContents(), card);

        card.getContents().setContainer(wrapper);
        card.setContents(wrapper);
        wrapper.setCostScale(costScale);
        wrapper.setCostShift(costShift);
        wrapper.setDefenseScale(defenseScale);
        wrapper.setDefenseShift(defenseShift);

        return wrapper;
    }
}
