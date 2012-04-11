package testingPlayArea;/*
 * Author: Andrew Lem
 * Date: 12/03/2012
 * Desc: A random generator
 */


import java.util.Random;

public class RandomInt {
    private int min;
    private int max;

    public RandomInt(int ... range){

        if(range.length < 0 || range.length > 2){
            System.out.println("testingPlayArea.RandomInt: Wrong argument values!");
        } else if(range.length == 0){
            // default - 6 sided die
            min = 0;
            max = 6;
        } else if(range.length == 1){
            // 1 argument - used as max
            min = 0;
            max = range[0];
        } else if(range.length == 2){
            // 2 arguments - works as (min, max)
            min = range[0];
            if(min > range[1]){
                min = range[1];
                max = range[0];
            } else {
                max = range[1];
            }
        } else {
            System.out.println("testingPlayArea.RandomInt: ERROR!");
        }
    }

    public int randomInt(){
        int value;

        Random generator = new Random();

        value = generator.nextInt() % (max - min) + min;

        if(value < 0){
            value *= -1;
        }

        return value+1;
    }
}
