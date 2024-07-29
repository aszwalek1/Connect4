package projects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class Connect4Test {
    private Connect4 game;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        game = new Connect4();
        game.initialiseGrid(5, 4, 3); 
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testInitialiseGrid() {
        char[][] grid = getGrid(game);
        assertEquals(5, grid.length, "Expected row length is 5");
        assertEquals(4, grid[0].length, "Expected column length is 4");

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                assertEquals(' ', grid[row][col], "Cell should be empty");
            }
        }
    }

    // Helper method to access the grid of the game using reflection
    private char[][] getGrid(Connect4 game) {
        try {
            Field field = Connect4.class.getDeclaredField("grid");
            field.setAccessible(true);
            return (char[][]) field.get(game);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDisplayGame() {
        game.displayGame();
        String displayedGame = outContent.toString();
        int expectedNoOfLines = 20 + 5; // for this grid there are 20 cells and there are additional lines at the end
        int actualNoOfLines = countVerticalLines(displayedGame, '|'); 

        assertEquals(expectedNoOfLines, actualNoOfLines, "Incorrect number of cells");

        System.setOut(originalOut);
    }

    // Helper method to count the lines in the grid
    private int countVerticalLines(String gameString, char verticalLine) {
        int count = 0;
        // Iterate over the string to check characters
        for (char ch : gameString.toCharArray()) {
            if (ch == verticalLine) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void testPlayTurn() {
        // Simulated player inputs '2'
        String userInput = "2\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        game.playTurn('Y');
        char[][] grid = getGrid(game);

        // Check that the right cell was filled with 'Y'
        assertEquals('Y', grid[4][1], "Should be player Y");
        // Check that other cells are empty
        for (int row = 0; row < 4; row++) {
            assertEquals(' ', grid[row][1], "Other cells should be empty");
        }
        System.setIn(originalIn);
    }

    @Test
    public void testCheckWin() {
        char[][] grid = getGrid(game);

        // Fill the first row with 'R' for a horizontal win
        for (int col = 0; col < 4; col++) {
            grid[4][col] = 'R';
        }

        // Set the grid back to the game
        setGrid(game, grid);

        // Check that 'R' wins
        assertTrue(game.checkWin('R'), "Player 'R' should have won");

    }

    // Helper method to enable setting the grid manually
    private void setGrid(Connect4 game, char[][] newGrid) {
        try {
            Field field = Connect4.class.getDeclaredField("grid");
            field.setAccessible(true);
            field.set(game, newGrid);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
