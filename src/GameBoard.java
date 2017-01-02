/**
 * Created by nasir uddin on 23/12/2016.
 */


/*
 * Author:	Nasir Uddin
 * This class is for playing the game, check winner, updating and validating players move etc
 */


import java.util.*;

public class GameBoard {
    private char[][] gameBoard; //declare the variable gameboard
    private boolean gameIsGoing;

    public GameBoard() //Constructor for gameboard
    {
        this.gameIsGoing = true;
        this.gameBoard = new char[3][3];

        for(int row=0; row < gameBoard.length; row++)
        {
            Arrays.fill(gameBoard[row], ' '); // Fill the board with empty boxes(rutor)
        }
    }   //end of constructor

    //Method for drawing board to the screen
    public void drawBoard() {
        for (int row=0; row < gameBoard.length; row++)
        {
            for (int column=0; column < gameBoard[0].length; column++)
            {
                System.out.print(" "+gameBoard[row][column]+" ");
                if(column == 0 || column == 1)
                    System.out.print("|"); //Draws the vertical separator
            }
            if (row == 0 || row == 1)
                System.out.print("\n---+---+---\n");
            //Draws the  horizontal separator
        }
        System.out.println();
    }   //complete drawBoard

    //method to check whether the board is full or if someone won
    public boolean gameActive()
    {
        return gameIsGoing;
    }

    /*
    Method to ask users to select row and column,
    validate the input and call makeMove()
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
                keyboard.next(); //error handling from input
                // Make sure that the game does not crash if user enter anything other than integer
            }
            row = keyboard.nextInt();

            //printf formatting method
            System.out.printf("Player %s please enter a column (1-3): ", playerName);
            while (!keyboard.hasNextInt())
            {
                System.out.println("Invalid entry, please try again");
                keyboard.next(); //error handling from input
            }
            column = keyboard.nextInt();

        } while (notValid(row,column));

        makeMove(player,row-1,column-1);
        System.out.printf("Player %s put: "+"row "+row+" column "+column, player);
        //row and column -1 to do indexing from 0 , not 1
    }   //end of  askPlayer method

    //Method to randomize feature of AI/computer players
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
        //row and column -1 to do indexing from 0 , but not from 1
    }   //End of askPlayerAI method


    /*
    * method to check if someone won
   * check whether there is '3 in a row'
   * Return true if someone won otherwise false
    */
    public boolean checkForWinner(int turnCounter)
    {
        //go through each row and check if anyone won
        for (int row=0; row < gameBoard.length; row++)
        {
            //check whether there is any horizontal '3 in a row', excluding empty places
            if(gameBoard[row][0] == gameBoard[row][1] &&
                    gameBoard[row][1] == gameBoard[row][2] &&
                    gameBoard[row][0] != ' ')
            {
                System.out.println("The winner is "+gameBoard[row][0]);
                gameIsGoing = false;
            }
        }

        //go through each column and check if anyone won
        for (int column=0; column < gameBoard.length; column++)
        {
            //check whether there is any vertical '3 in a row', excluding empty places
            if(gameBoard[0][column] == gameBoard[1][column] &&
                    gameBoard[1][column] == gameBoard[2][column] &&
                    gameBoard[0][column] != ' ')
            {
                System.out.println("The winner is "+gameBoard[0][column]);
                gameIsGoing = false;
            }
        }

        //go through the diagonals and see if anyone won
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
        if(turnCounter == 10 && gameIsGoing) //if no one has won after the last move, it will be a draw!
        {
            System.out.println("It's a draw!");
            gameIsGoing = false;
        }

        return true;
    }

    /*
    method to validate that the selected feature is a valid row and column
    and whether the position is empty
    */
    public boolean notValid(int row, int column)
    {
        //if the selected row /column position  is more than 3 or less than 1 or if the cell is taken, then invalid
        if (row > 3 || row < 1 || column > 3 || column < 1 || !isEmpty(row, column))
        {
            System.out.println("that move is illegal, please try again.");
            return true;
        }

        else
            return false;
    }

    /*
    Method to check if the place/cell is empty
    */
    public boolean isEmpty(int row, int column)
    {
        if(gameBoard[row-1][column-1] == ' ')
            return true;
        else
            return false;
    }

    /*
    Method for validating the player's move
    returns true if the move is successfully performed
    */
    public boolean makeMove(char player, int row, int column)
    {
        boolean makeMove;
        if(row >=0 && row <=2 && column >=0 && column <=2)
        //check if the move is within the board's boundaries
        {
            if(gameBoard[row][column] != ' ') //check that the place is not empty
                makeMove = false;
            else
            {
                gameBoard[row][column] = player;
                makeMove = true; //if empty/vacant cell then return valid moves
            }
        }
        else
            makeMove = false; //otherwise return invalid move/feature
        return makeMove;
    }
}

