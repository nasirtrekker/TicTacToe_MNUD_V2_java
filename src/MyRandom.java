/**
 * Created by nasirmac on 23/12/2016.
 */



import java.util.Random;

public class MyRandom {

    //MyRandom är en utility-class (sv. hjälpklass) (vi skapar den)
    //som tjänstgör som en behållare för metoder som alla
    //har något att göra med slumptal
    //vi har en privat konstruktor för att inte kunna skapa några objekt.
    //Vi har inga instansvariabler eller instansmetoder utan bara
    //statiskta metoder (klassmetoder)
    //ett annat exempel på en utility-class är Math från java.lang.Math
    //som innehåller användbara metoder för matematiska beräkningar


    //skapar en konstruktor som är private
    //dvs den går inte att anropa någon annanstans än inne i klassen

    private MyRandom()
    {

    }

    //metod som genererat ett slumpmässigt
    //heltal upp till ett givet tal
    public static int getInt(int max)
    {
        Random rand = new Random();
        //exempel
        //om max=5
        //ger antingen 0,1,2,3,4,5
        //eftersom vi skrev max+1
        int  returned = rand.nextInt(max+1);


        return returned;
    }
    //metod för att generera ett slumptal mellan
    //min och max
    public static int getInt(int min, int max)
    {
        Random rand = new Random();
        int returned = rand.nextInt((max-min)+1) + min;
        //exempel
        //max=10
        //min=2
        //rand.nextInt(10-2)+1)-->rand.nextInt(9)
        //detta kommer ge 0,1,2,3,4,5,6,7,8
        //adderar min=2 då kommer vi alltid att få ett värde
        //större än eller lika med min
        return returned;
    }

}
