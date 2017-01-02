/**
 * Created by nasir uddin on 23/12/2016.
 */
/*
 * Author:	Nasir Uddin
 * Project:	Simple TIC-TAC-TOE / Tre i rad game
 * Year: 2016
 * Version: 2.0
 * Description:	This version has three option 1. play with computer 2. play with another human and 3. play with computer vs computer
 * This is the main class which will implement the game and the entry point of the programme
 * Introduce user about the game, take user input (whether play with computer or human ), keep track about winners and show score report
 */

import java.util.*;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);


        //Welcome
        System.out.println("****************************");
        System.out.println("THREE IN A ROW / TIC-TAC-TOE ");
        System.out.println("****************************");
        System.out.println("WELCOME TO TIC-TAC-TOE");
        System.out.println();
        System.out.println(" About:"
                + "\n ========================"
                + "\n This is a Tic-Tac-Toe (Tre i rad) game which is developed in JAVA using IntelliJ IDEA."
                + "\n This version of the have has three different option : 0) Computer vs Computer; 1)- Human vs Computer ; 2) Human vs Human."
                + "\n The developer of this game is Nasir Uddin (https://se.linkedin.com/in/nasir-uddin-090a475)  \n");
        System.out.println("The 3x3 grid is arranged in the following manner:");
        System.out.println("  1   2   3 ");
        System.out.println("1   |  |    ");
        System.out.println("  -- -- --  ");
        System.out.println("2   |  |    ");
        System.out.println("  -- -- --  ");
        System.out.println("3   |  |    ");
        System.out.println("To play in a square, please simply enter the corresponding cell position (col and row no. 1, 2, ...etc.)");
        System.out.println("Have fun :-)");
        System.out.println("******************");
        System.out.println("*** Nasir Uddin ***");
        System.out.println("******************");
        System.out.println();
        //-------------------------------------------------------
        boolean keepPlaying = true; //variable to check if the game is running
        int xWinCounter = 0;
        int oWinCounter = 0;
        int nrOfPlayers;
        String playerOne;
        String playerTwo;

        System.out.println("How do you want to play the game?");
        System.out.println("Press '1': If you are a human and want to play with a random computer.");
        System.out.println("Press '2': If you are a human and want to play with another human.");
        System.out.println("Press '0': If you wish you watch/play game as AI/computer vs AI/computer :-) ");
        do //input validation. Check whether  input is int and 0-2
        {
            while (!keyboard.hasNextInt()) //check that the input is an integer
            {
                System.out.println("That's not a number, please enter 0-2");
                keyboard.next();
            }
            nrOfPlayers = keyboard.nextInt();
            if (nrOfPlayers < 0 || nrOfPlayers > 2) //check whether input is 0-2
                System.out.println("Invalid number, please enter 0-2");
        } while (nrOfPlayers < 0 || nrOfPlayers > 2);

        //human vs human player
        if (nrOfPlayers == 2) {
            //create player one (X)
            Player playerX = new Player('X');
            playerX.setName();
            playerOne = playerX.getName();

            //Create player two(O)
            Player playerO = new Player('O');
            playerO.setName();
            playerTwo = playerO.getName();


        } else if (nrOfPlayers == 1) // if human vs computer player
        {
            Player playerX = new Player('X');  //create human player one  (X)
            playerX.setName();
            playerOne = playerX.getName();

            //create computer/AI player (O)
            PlayerAI playerO = new PlayerAI('O');
            playerTwo = playerO.getName(); //Use static AI name, Computer
        } else {
            //create computer/AI player one (X)
            PlayerAI playerX = new PlayerAI('X');
            playerOne = playerX.getName();

            //crate computer/AI player two  (O)
            PlayerAI playerO = new PlayerAI('O');
            playerTwo = playerO.getName();
        }


        while (keepPlaying) {
            GameBoard myGame = new GameBoard(); //Create object GameBoard
            myGame.drawBoard(); //run the drawBoard method
            int turnCounter = 1; //determine (bestämma) that it is the first round

            while (myGame.gameActive() && turnCounter < 10 && keepPlaying) {
                //If the round is evenly divisible (jämnt delbart) by 2, the player 2's turn
                if (turnCounter % 2 == 0 && nrOfPlayers == 2)
                    myGame.askPlayer(playerTwo, 'O');
                else if (nrOfPlayers == 2)
                    myGame.askPlayer(playerOne, 'X');
                else if (turnCounter % 2 == 0 && nrOfPlayers == 1)
                    myGame.askPlayerAI(playerTwo, 'O');
                else if (nrOfPlayers == 1)
                    myGame.askPlayer(playerOne, 'X');
                else if (turnCounter % 2 == 0 && nrOfPlayers == 0)
                    myGame.askPlayerAI(playerTwo, 'O');
                else if (nrOfPlayers == 0)
                    myGame.askPlayerAI(playerOne, 'X');
                turnCounter++;

                System.out.println("\n");
                myGame.drawBoard();
                myGame.checkForWinner(turnCounter);
                /*
                * Keep track of who has won how many times
                * If the game ends in early player O's turn means that X won and vice versa.
                */
                if (myGame.gameActive() == false && turnCounter != 10) {
                    if (turnCounter % 2 == 0) {
                        xWinCounter++;
                    } else {
                        oWinCounter++;
                    }
                }
            }

            System.out.println("Player X: " + playerOne + " has won " + xWinCounter
                    + " times and Player O: " + playerTwo + " has won "
                    + oWinCounter + " times");

            System.out.println("Want to play again? Yes or no");
            System.out.println("Enter y to continue; or any letter to exit the game.");
            char response = keyboard.next().charAt(0);
            if (keepPlaying = (response == 'y'));  // continue until 'y'

           /*
            ********* alternative code for play again, but have bug :-( ************
            System.out.println("Want to play again? Yes or no");
            String choice = keyboard.nextLine(); //create variable for choice
            if (choice.equalsIgnoreCase("no")) //see if the input is no, make everything lowercase
            {
                keepPlaying = false; //if the player selects no, make game as inactive
            }
        }
        ************** end alternative code **************
        */
           else

            {
                System.out.println("****** Thanks for playing !******");
                System.out.println("****** Remember ! Our life is a 'real game' and you never play alone !! ******");
                System.out.println("****** See you again !! Live Long and Prosper!!! ******");
                System.out.println("******** by  Nasir Uddin ********");
            }

        }

    }

}
