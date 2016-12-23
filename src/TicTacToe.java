/**
 * Created by nasirmac on 23/12/2016.
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
                + "\n This is a Tic-Tac-Toe game. It is developed in JAVA using IntelliJ IDEA."
                + "\n This version of the have has three different option : 0) Computer vs Computer; 1)- Human vs Computer ; 2) Human vs Human."
                + "\n The developer of this game is Nasir Uddin  \n");
        System.out.println("The 3x3 grid is arranged in the following manner:");
        System.out.println("  1   2   3 ");
        System.out.println("1   |  |    ");
        System.out.println("  -- -- --  ");
        System.out.println("2   |  |    ");
        System.out.println("  -- -- --  ");
        System.out.println("3   |  |    ");
        System.out.println("To play in a square, please simply enter the corresponding cell position (col or row no. 1, 2, ...etc.)");
        System.out.println("Have fun :-)");
        System.out.println("******************");
        System.out.println("*** Nasir Uddin ***");
        System.out.println("******************");
        System.out.println();
        //-------------------------------------------------------
        boolean keepPlaying = true; //variabel för att kontrollera om spelet är igång
        int xWinCounter = 0;
        int oWinCounter = 0;
        int nrOfPlayers;
        String playerOne;
        String playerTwo;

        System.out.println("How do you want to play the game?");
        System.out.println("Press '1': If you are a human and want to play with dump computer.");
        System.out.println("Press '2': If you are a human and want to play with another human.");
        System.out.println("Press '0': If you wish you watch/play game as AI vs AI :-) ");
        do //input validation. Kollar om input är int och 0-2
        {
            while (!keyboard.hasNextInt()) //kontrollera att input är ett heltal
            {
                System.out.println("That's not a number, pleaser enter 0-2");
                keyboard.next();
            }
            nrOfPlayers = keyboard.nextInt();
            if (nrOfPlayers < 0 || nrOfPlayers > 2) //kontrollera att input är 0-2
                System.out.println("Invalid number, please enter 0-2");
        } while (nrOfPlayers < 0 || nrOfPlayers > 2);

        if (nrOfPlayers == 2) {
            //skapa spelare ett (X)
            Player playerX = new Player('X');
            playerX.setName(); //frågar efter input för namn
            playerOne = playerX.getName();

            //Skapa spelare två (O)
            Player playerO = new Player('O');
            playerO.setName();
            playerTwo = playerO.getName();
        } else if (nrOfPlayers == 1) {
            //skapa spelare ett (X)
            Player playerX = new Player('X');
            playerX.setName();
            playerOne = playerX.getName();

            //Skapa AI spelare (O)
            PlayerAI playerO = new PlayerAI('O');
            playerTwo = playerO.getName(); //använder statiskt AI namn, Computer
        } else {
            //skapa AI spelare (X)
            PlayerAI playerX = new PlayerAI('X');
            playerOne = playerX.getName();

            //Skapa AI spelare (O)
            PlayerAI playerO = new PlayerAI('O');
            playerTwo = playerO.getName();
        }


        while (keepPlaying) {
            GameBoard myGame = new GameBoard(); //skapa objekt GameBoard
            myGame.drawBoard(); //kör metoden drawBoard
            int turnCounter = 1; //bestämt att det är första rundan

            while (myGame.gameActive() && turnCounter < 10 && keepPlaying) {
                //om rundan är jämnt delbart med 2 är det spelare 2's tur
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
                håller reda på vem som vunnit hur många gånger
                Om spelet är slut i början av spelare O's tur innebär det att X
                vunnit och vice versa.
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

           /* System.out.println("Want to play again? Yes or no");
            String choice = keyboard.nextLine(); //skapa variabel för valet
            if (choice.equalsIgnoreCase("no")) //se om input är no, se allt som små bokstäver
            {
                keepPlaying = false; //om spelare väljer no, sätt spelet som inaktivt
            }
        }
        */
           else

            {System.out.println("Thanks for playing!");
            }

        }

    }

}
