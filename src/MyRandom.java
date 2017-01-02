/**
 * Created by nasir uddin on 23/12/2016.
 */

/*  Author:	Nasir Uddin
 * MyRandom is an utility/helper class
 * Serving as a container for all method
 * Have something to do with random numbers
 * We have no instance variables or instance methods but only have static method
 *
 */


import java.util.Random;

public class MyRandom {

    //Create a constructor is private
    // It can not be called anywhere but inside the class

    private MyRandom()
    {

    }

    // Method to generate a random integers up to a given number

    public static int getInt(int max)
    {
        Random rand = new Random();
        //example
        // If max = 5
        // Gives either 0,1,2,3,4,5
        // Because we wrote max + 1
        int  returned = rand.nextInt(max+1);


        return returned;
    }
    // Method for generating a random number between min and max

    public static int getInt(int min, int max)
    {
        Random rand = new Random();
        int returned = rand.nextInt((max-min)+1) + min;
        //example
        // Max = 10
        // Min = 2
        // rand.nextInt(10-2)+1)-->rand.nextInt(9)
        // This will give 0,1,2,3,4,5,6,7,8
        // Add min 2 when we will always have a value greater than or equal to min
        return returned;
    }

}
