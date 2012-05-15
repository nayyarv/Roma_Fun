package Testers;

/**
 * Created by IntelliJ IDEA.
 * User: lema
 * Date: 5/14/12
 * Time: 4:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestingIterator {
    public static void main (String[] args){
        int[] handIndex = new int[10];

        for(int i : handIndex){
            i = 10;
        }

        for(int i : handIndex){
            System.out.println(i);
        }
    }
}
