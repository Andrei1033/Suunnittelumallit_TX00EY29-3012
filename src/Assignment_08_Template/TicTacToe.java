package Assignment_08_Template;

import java.util.Scanner;

public class TicTacToe extends Game {

    private Scanner scanner = new Scanner(System.in);

    private char[][] board;
    private int numberOfMoves;
    private int winner;

    /*public*/
    @Override
    public void initializeGame(int numberOfPlayers) {
        board = new char[3][3];

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                board[row][column] = ' ';
            }
        }
        numberOfMoves = 0;
        winner = -1;
    }

    @Override
    public boolean endOfGame() {
        winner = findWinner();

        if (winner != -1) {
            return true;
        }

        return numberOfMoves == 9;
    }

    @Override
    public void playSingleTurn(int player) {
        displayBoard();

        char symbol = player == 0 ? 'X' : 'O';

        while (true) {
            System.out.println("Player " + player + " (" + symbol + "), enter row and column (1-3):");

            int row = scanner.nextInt() - 1;
            int column = scanner.nextInt() - 1;

            if (row < 0 || row >= 3 || column < 0 || column >= 3) {
                System.out.println("Invalid position.");
                continue;
            }

            if (board[row][column] != ' ') {
                System.out.println("Position already taken.");
                continue;
            }

            board[row][column] = symbol;
            numberOfMoves++;
            break;
        }
    }

    @Override
    public void displayWinner() {

        displayBoard();

        if (winner == -1) {
            System.out.println("The game is a draw.");
        }
        else {
            char symbol = winner == 0 ? 'X' : 'O';
            System.out.println("Player " + winner + " (" + symbol + ") wins!");
        }
    }

    /*private*/

    private int playerFromSymbol(char symbol) {
        if (symbol == 'X') {
            return 0;
        }

        return 1;
    }

    private int findWinner() {

        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != ' '
                    && board[row][0] == board[row][1]
                    && board[row][1] == board[row][2]) {

                return playerFromSymbol(board[row][0]);
            }
        }

        // Check columns
        for (int column = 0; column < 3; column++) {
            if (board[0][column] != ' '
                    && board[0][column] == board[1][column]
                    && board[1][column] == board[2][column]) {

                return playerFromSymbol(board[0][column]);
            }
        }

        // Check main diagonal
        if (board[0][0] != ' '
                && board[0][0] == board[1][1]
                && board[1][1] == board[2][2]) {

            return playerFromSymbol(board[0][0]);
        }

        // Check other diagonal
        if (board[0][2] != ' '
                && board[0][2] == board[1][1]
                && board[1][1] == board[2][0]) {

            return playerFromSymbol(board[0][2]);
        }

        return -1;
    }

    private void displayBoard() {
        System.out.println();

        for (int row = 0; row < 3; row++) {
            System.out.println(
                    " " + board[row][0] +
                            " | " + board[row][1] +
                            " | " + board[row][2]
            );

            if (row < 2) {
                System.out.println("---+---+---");
            }
        }
    }
}
