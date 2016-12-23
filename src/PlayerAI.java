/**
 * Created by nasirmac on 23/12/2016.
 */


import java.util.*;

public class PlayerAI extends GameBoard{
    private static String name = "Computer";
    private char token;
    Scanner input = new Scanner(System.in);

    public PlayerAI (char token)
    {
        this.token=token;
    }

    public String getName()
    {
        return name;
    }

    public void setName()
    {
        this.name=name;
    }

    public char getToken()
    {
        return token;
    }

    public void setToken()
    {
        this.token=token;
    }



}

