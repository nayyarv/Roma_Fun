/**
 * File name:
 * Creator: Varun Nayyar
 * Date: 19/03/12
 * Desc:
 */
public class Card {
    private int cardNum;
    public Card(int num){
        cardNum = num;
    }
    public int getCardNum(){
        return cardNum;
    }
    
    public String toString(){
        return "" + cardNum;
    }
}
