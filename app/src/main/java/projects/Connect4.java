/*
 * This is the main file responsible for 
 * creating a Connect4 game and handling of game
 */
package projects;
import java.util.Scanner;

public class Connect4 {
    public String initialiseGrid() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Asks user for the board size and winning condition
        System.out.println("Enter the row length: ");
        int gridX = scanner.nextInt();
        System.out.println("Enter the column length: ");
        int gridY = scanner.nextInt();
        System.out.println("Enter the number of tokens required to win: ");
        int winLength = scanner.nextInt();
    }
}
