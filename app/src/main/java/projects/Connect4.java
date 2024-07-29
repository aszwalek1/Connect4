/*
 * This is the main file responsible for 
 * creating a Connect4 game and handling of game
 */
package projects;
import java.util.Scanner;

public class Connect4 {

    private char[][] grid;
    private int rows;     
    private int columns;
    private int winLength;
    private boolean win;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connect4 game = new Connect4();
        int gridX, gridY, winLength;
        
        // Asks user for the board size and winning condition
        // Checks the inputs are valid
        while(true) {
            System.out.println("Enter the row length: ");
            gridY = scanner.nextInt();
            if(gridY > 0) {
                break;
            }
            System.out.println("Invalid input");
        }

        while(true) {
            System.out.println("Enter the column length: ");
            gridX = scanner.nextInt();
            if(gridX > 0) {
                break;
            }
            System.out.println("Invalid input");
        }

        while(true) {
            System.out.println("Enter the number of tokens required to win: ");
            winLength = scanner.nextInt();
            if(winLength > 0 && winLength <= Math.max(gridX, gridY)) {
                break;
            }
            System.out.println("Invalid input");
        }

        // Initialises the grid based on the input values
        game.initialiseGrid(gridX, gridY, winLength);
        game.displayGame();

        char player = 'R'; // 'R' for Red and 'Y' for Yellow
        int turn = 1;
        int possibleTurns = gridX * gridY; // maximum number of turns per game

        while (!game.win && turn <= possibleTurns) {
            game.playTurn(player);
            game.displayGame();

            if (game.checkWin(player)) {
                System.out.println("Player " + player + " has won");
                game.win = true;
            }
            
            // Switch the current player
            player = (player == 'R') ? 'Y' : 'R';
            
            turn++;
        }
        
        scanner.close();
    }

    public void initialiseGrid(int rows, int columns, int winLength) {
        this.rows = rows;
        this.columns = columns;
        this.winLength = winLength;
        this.win = false;
        
        grid = new char[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                grid[row][column] = ' '; 
            }
        }
    }

    public void displayGame() {
        // Displays the column numbers
        System.out.print(" ");
        for (int i = 1; i < columns + 1; i++) {
            System.out.print(" " + i + "  ");
        }
        System.out.println();
        for (int i = 0; i < columns; i++) {
            System.out.print("----");
        }
        System.out.println();

        // Displays the cells in the game
        for (int row = 0; row < rows; row++) {
            System.out.print("|");
            for (int column = 0; column < columns; column++) {
                System.out.print(" " + grid[row][column] + " |");
            }
            System.out.println();
            for (int column = 0; column < columns; column++) {
                System.out.print("----");
            }
            System.out.println();
        }
    }

    public void playTurn(char player) {
        Scanner scanner = new Scanner(System.in);
        int column;
        
        while (true) {
            System.out.println("Next Player: \"" + player + "\"");
            System.out.print("Please choose the column to play: ");
            // Change the index to start from 0
            column = scanner.nextInt() - 1; 

            if (column >= 0 && column < columns) {
                // Find available cell in the column from the bottom
                for (int row = rows - 1; row >= 0; row--) {
                    if (grid[row][column] == ' ') {
                        grid[row][column] = player;
                        // Finish move
                        return; 
                    }
                }
                System.out.println("This column is full");
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    /**
     * @param rowDirection 1 is up, 0 is none, -1 is up
     * @param colDirection 1 is right, 0 is none, -1 is left
     */

    private boolean checkWin(char player) {
        // Check all possible lines for each cell
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (grid[row][col] == player) {
                    // Check horizontally
                    if (col + winLength <= columns && checkLine(row, col, 0, 1, player)) {
                        return true;
                    }
                    // Check vertically
                    if (row + winLength <= rows && checkLine(row, col, 1, 0, player)) {
                        return true;
                    }
                    // Check diagonally downward
                    if (row + winLength <= rows && col + winLength <= columns && checkLine(row, col, 1, 1, player)) {
                        return true;
                    }
                    // Check diagonally upward
                    if (row - winLength + 1 >= 0 && col + winLength <= columns && checkLine(row, col, -1, 1, player)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkLine(int startingRow, int startingCol, int rowDirection, int colDirection, char player) {
        int count = 0;
        int row = startingRow;
        int col = startingCol;

        while (row >= 0 && row < rows && col >= 0 && col < columns) {
            if (grid[row][col] == player) {
                count++;
                if (count == winLength) {
                    return true;
                }
            } else {
                break;
            }
            row += rowDirection;
            col += colDirection;
        }
        return false;
    }
}
