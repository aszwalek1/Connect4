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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connect4 game = new Connect4();
        
        // Asks user for the board size and winning condition
        System.out.println("Enter the row length: ");
        int gridX = scanner.nextInt();
        System.out.println("Enter the column length: ");
        int gridY = scanner.nextInt();
        System.out.println("Enter the number of tokens required to win: ");
        int winLength = scanner.nextInt();

        // Initialises the grid based on the input values
        game.initialiseGrid(gridX, gridY);
        game.displayGame();

        char startingPlayer = 'R';
        int turn = 1;
        boolean win = false;

        scanner.close();
    }

    public void initialiseGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
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

    public void playTurn() {

    }
}
