/**
 * Created by nasirmac on 23/12/2016.
 */

/**
 * Created by nasirmac on 20/12/2016.
 */

/*
 * Author:	Nasir Uddin
 * Project:	Simple TIC-TAC-TOE / Tre i råd (svenska) game
 * Year: 2016
 * Version: 2.0
 * Description:	This version has three option 1. play with computer 2. play with another human and 3. play with computer vs computer
 *
 *
 */


import java.util.*;

public class GameBoard {
    private char[][] gameBoard; //deklarera variabel för spelbräde
    private boolean gameIsGoing;

    public GameBoard() //konstruktor för spelbräde
    {
        this.gameIsGoing = true;
        this.gameBoard = new char[3][3];

        for(int row=0; row < gameBoard.length; row++)
        {
            Arrays.fill(gameBoard[row], ' '); //fyll brädet med tomma rutor
        }
    }   //slut på konstruktor

    //Metod för att rita brädet till skärmen
    public void drawBoard() {
        for (int row=0; row < gameBoard.length; row++)
        {
            for (int column=0; column < gameBoard[0].length; column++)
            {
                System.out.print(" "+gameBoard[row][column]+" ");
                if(column == 0 || column == 1)
                    System.out.print("|"); //ritar lodräta avskiljare
            }
            if (row == 0 || row == 1)
                System.out.print("\n---+---+---\n");
            //ritar vågräta avskiljare
        }
        System.out.println();
    }   //slut på drawBoard

    //metod för att kontrollera om brädet är fullt och om någon vunnit
    public boolean gameActive()
    {
        return gameIsGoing;
    }

    /*
    Metod för att be användare välja rad och kolumn,
    validera inputen och anropa makeMove()
    */
    public void askPlayer(String playerName, char player)
    {
        Scanner keyboard = new Scanner(System.in);
        int row, column;
        do
        {
            System.out.printf("Player %s please enter a row (1-3): ", playerName);
            while (!keyboard.hasNextInt())
            {
                System.out.println("Invalid entry, please try again");
                keyboard.next(); //felhantering vid input
                //gör så att spelet inte kraschar om man skriver in något annat än heltal
            }
            row = keyboard.nextInt();
            //printf formateringsmetod

            System.out.printf("Player %s please enter a column (1-3): ", playerName);
            while (!keyboard.hasNextInt())
            {
                System.out.println("Invalid entry, please try again");
                keyboard.next(); //felhantering vid input
            }
            column = keyboard.nextInt();

        } while (notValid(row,column));

        makeMove(player,row-1,column-1);
        System.out.printf("Player %s put: "+"row "+row+" column "+column, player);
        //row och column -1 för att datorn börjar på index 0 och inte 1
    }   //slut på metoden askPlayer

    //Metod för att slumpa drag hos AI spelare
    public void askPlayerAI(String playerName, char player)
    {
        int row, column;
        do
        {
            row = MyRandom.getInt(1,3);
            column = MyRandom.getInt(1,3);

        } while (notValid(row,column));

        makeMove(player,row-1,column-1);
        System.out.printf("Player %s put: "+"row "+row+" column "+column, player);
        //row och column -1 för att datorn börjar på index 0 och inte 1
    }   //slut på metoden askPlayerAI

    /*
    metod för att kontrollera om någon vunnit
    Kolla after samma värde 3 i rad
    returnea sant om någon vunnit, annars falskt
    */
    public boolean checkForWinner(int turnCounter)
    {
        //gå igenom varje rad och se om någon vunnit
        for (int row=0; row < gameBoard.length; row++)
        {
            //kolla om det är 3 i rad vågrätt, exkludera tomma platser
            if(gameBoard[row][0] == gameBoard[row][1] &&
                    gameBoard[row][1] == gameBoard[row][2] &&
                    gameBoard[row][0] != ' ')
            {
                System.out.println("The winner is "+gameBoard[row][0]);
                gameIsGoing = false;
            }
        }

        //gå igenom varje kolumn och se om någon vunnit
        for (int column=0; column < gameBoard.length; column++)
        {
            //kolla om det är 3 i rad lodrätt, exkludera tomma platser
            if(gameBoard[0][column] == gameBoard[1][column] &&
                    gameBoard[1][column] == gameBoard[2][column] &&
                    gameBoard[0][column] != ' ')
            {
                System.out.println("The winner is "+gameBoard[0][column]);
                gameIsGoing = false;
            }
        }

        //gå igenom diagonalerna och se om någon vunnit
        if(gameBoard[0][0] == gameBoard[1][1] &&
                gameBoard[1][1] == gameBoard[2][2] &&
                gameBoard[0][0] != ' ')
        {
            System.out.println("The winner is "+gameBoard[0][0]);
            gameIsGoing = false;
        }

        if(gameBoard[2][0] == gameBoard[1][1] &&
                gameBoard[1][1] == gameBoard[0][2] &&
                gameBoard[1][1] != ' ')
        {
            System.out.println("The winner is "+gameBoard[1][1]);
            gameIsGoing = false;
        }
        if(turnCounter == 10 && gameIsGoing) //om ingen vunnit efter sista draget, oavgjort
        {
            System.out.println("It's a draw!");
            gameIsGoing = false;
        }

        return true;
    }

    /*
    metod för att validera att det valde draget är en giltig rad och kolumn
    samt om positionen är tom
    */
    public boolean notValid(int row, int column)
    {
        //om vald rad är över 3 eller under 1 eller om platsen är tagen, ogiltigt
        if (row > 3 || row < 1 || column > 3 || row < 1 || !isEmpty(row, column))
        {
            System.out.println("that move is illegal, please try again.");
            return true;
        }

        else
            return false;
    }

    /*
    Metod för att kontrollera om platsen är tom
    */
    public boolean isEmpty(int row, int column)
    {
        if(gameBoard[row-1][column-1] == ' ')
            return true;
        else
            return false;
    }

    /*
    Metod för att validera spelarens drag
    returnerar true om draget utfördes utan fel
    */
    public boolean makeMove(char player, int row, int column)
    {
        boolean makeMove;
        if(row >=0 && row <=2 && column >=0 && column <=2)
        //se om draget är inom brädets gränser
        {
            if(gameBoard[row][column] != ' ')
                //kolla så att platsen inte är upptagen
                makeMove = false;
            else
            {
                gameBoard[row][column] = player;
                makeMove = true;
                //om ledig returnera giltigt drag
            }
        }
        else
            makeMove = false;
        //annars returnera ogiltig drag
        return makeMove;
    }
}

