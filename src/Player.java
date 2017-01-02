/*
 * Author:	Nasir Uddin
 * This class is for player
 */

import java.util.*;

public class Player {
    private String name;
    private char token;
    Scanner input = new Scanner(System.in);

    public Player (char token)
    {
        this.token=token;
    }

    public String getName()
    {
        return name;
    }

    public void setName()
    {
        System.out.println("player "+token+" Please enter your name:");
        this.name=input.nextLine();
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
