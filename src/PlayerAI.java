/**
 * Created by nasir uddin on 23/12/2016.
 */

/*
 * Author:	Nasir Uddin
 * Class computer/AI player is extended/child/sub/derived class of GameBoard.
 */

import java.util.*;

public class PlayerAI extends GameBoard{

    //Create  static variable 'name' and kept private to control its value through setter
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

