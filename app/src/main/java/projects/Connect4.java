/*
 * This is the main file responsible for 
 * creating a Connect4 game and handling of game
 */
package projects;
import java.util.Scanner;

public class Connect4 {

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

        game.initialiseGrid(gridX, gridY);

        scanner.close();
    }

    public void initialiseGrid(int rows, int columns) {
        char[][] grid = new char[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                grid[row][column] = ' '; 
            }
        }
    }
}
