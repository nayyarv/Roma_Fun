package Testers;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 15/05/12
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestFunctionArgumentModify {
    public static void main(String[] args){
        int a = 0;
        AnObject anObject = new AnObject();
        testingFunction(a);
        System.out.println(a);
        testingFunction2(anObject);
    }

    private static void testingFunction(int a){
        a++;
    }

    private static void testingFunction2(AnObject a){
        a = null;
    }

    private static class AnObject{
        String string = "bye~";
    }
}
