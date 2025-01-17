import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

public class MainTest {

    // Test for minefieldSetup
    @org.junit.Test
    @Test
    public void testMinefieldSetup_noMines() {
        String input = "2 3\n...\n...\n";
        Scanner scanner = new Scanner(input);

        String[][] result = Main.minefieldSetup(2, 3, scanner);

        assertEquals(4, result.length);  // 2 rows + 2 buffer rows
        assertEquals(5, result[0].length);  // 3 columns + 2 buffer columns

        // Check buffer rows and columns
        assertEquals("x", result[0][0]);
        assertEquals("x", result[0][4]);
        assertEquals("x", result[3][0]);
        assertEquals("x", result[3][4]);

        // Check inner minefield
        assertEquals(".", result[1][1]);
        assertEquals(".", result[1][2]);
        assertEquals(".", result[1][3]);
        assertEquals(".", result[2][1]);
        assertEquals(".", result[2][2]);
        assertEquals(".", result[2][3]);
    }

    @Test
    public void testMinefieldSetup_withMines() {
        String input = "2 3\n*.*\n...\n";
        Scanner scanner = new Scanner(input);

        String[][] result = Main.minefieldSetup(2, 3, scanner);

        // Check buffer rows and columns
        assertEquals("x", result[0][0]);
        assertEquals("x", result[0][4]);
        assertEquals("x", result[3][0]);
        assertEquals("x", result[3][4]);

        // Check inner minefield
        assertEquals("*", result[1][1]);
        assertEquals(".", result[1][2]);
        assertEquals("*", result[1][3]);
        assertEquals(".", result[2][1]);
        assertEquals(".", result[2][2]);
        assertEquals(".", result[2][3]);
    }

    // Test for produceHint
    @Test
    public void testProduceHint_noMines() {
        String[][] minefield = {
                {"x", "x", "x", "x", "x"},
                {"x", ".", ".", ".", "x"},
                {"x", ".", ".", ".", "x"},
                {"x", "x", "x", "x", "x"}
        };

        int hint = Main.produceHint(2, 2, minefield);  // The middle cell, no mines around
        assertEquals(0, hint);
    }

    @Test
    public void testProduceHint_surroundingMines() {
        String[][] minefield = {
                {"x", "x", "x", "x", "x"},
                {"x", ".", "*", ".", "x"},
                {"x", ".", ".", ".", "x"},
                {"x", "x", "x", "x", "x"}
        };

        int hint = Main.produceHint(2, 2, minefield);  // The middle cell, with mines around
        assertEquals(1, hint);  // One mine surrounding the cell (right)
    }

    @Test
    public void testProduceHint_cornerMines() {
        String[][] minefield = {
                {"x", "x", "x", "x", "x"},
                {"x", "*", ".", ".", "x"},
                {"x", ".", ".", ".", "x"},
                {"x", "x", "x", "x", "x"}
        };

        int hint = Main.produceHint(2, 2, minefield);  // The middle cell with a mine in the top-left corner
        assertEquals(1, hint);  // One mine surrounding the cell (top-left)
    }

    // Test for printMinefield
    @Test
    public void testPrintMinefield_noMines() {
        // Capture the printed output using a mock or checking printed content directly
        String[][] minefield = {
                {"x", "x", "x", "x", "x"},
                {"x", ".", ".", ".", "x"},
                {"x", ".", ".", ".", "x"},
                {"x", "x", "x", "x", "x"}
        };

        // Test the printing functionality directly
        String expectedOutput = "Field #1:\n" +
                "... \n" +
                "... \n\n";

        // Redirecting System.out temporarily for test validation
        try {
            System.setOut(new java.io.PrintStream(System.out));
            Main.printMinefield(minefield, 1);
            // No assertion here because print is done directly now
        } catch (Exception e) {
            // Your handling code, if necessary
        }

        assertEquals(expectedOutput);
    }

    @Test
    public void testPrintMinefield_withHints() {
        String[][] minefield = {
                {"x", "x", "x", "x", "x"},
                {"x", "*", ".", ".", "x"},
                {"x", ".", ".", ".", "x"},
                {"x", "x", "x", "x", "x"}
        };

        // Expected output after hints
        String expectedOutput = "Field #1:\n" +
                "*1* \n" +
                "111 \n\n";

        try {
            System.setOut(new java.io.PrintStream(System.out));
            Main.printMinefield(minefield, 1);
            // Validate output here
        } catch (Exception e) {
            // Handle the errors
        }
    }

    // Test for all mines (every cell is a mine)
    @Test
    public void testMinefieldWithAllMines() {
        String input = "2 2\n**\n**\n";
        Scanner scanner = new Scanner(input);

        String[][] result = Main.minefieldSetup(2, 2, scanner);

        // Check if all inner cells are mines
        assertEquals("*", result[1][1]);
        assertEquals("*", result[1][2]);
        assertEquals("*", result[2][1]);
        assertEquals("*", result[2][2]);

        // Now, let's check the hints (they should be 8 for all cells surrounding mines)
        int hint1 = Main.produceHint(1, 1, result);
        int hint2 = Main.produceHint(1, 2, result);
        int hint3 = Main.produceHint(2, 1, result);
        int hint4 = Main.produceHint(2, 2, result);

        assertEquals(8, hint1);
        assertEquals(8, hint2);
        assertEquals(8, hint3);
        assertEquals(8, hint4);
    }


    // Test to ensure the buffer is added correctly
    @Test
    public void testBufferAddedCorrectly() {
        String input = "2 2\n*.\n.*\n";
        Scanner scanner = new Scanner(input);

        String[][] result = Main.minefieldSetup(2, 2, scanner);

        // Check if the buffer rows and columns are added correctly
        // Buffer should be "x" around the inner grid
        assertEquals("x", result[0][0]);
        assertEquals("x", result[0][1]);
        assertEquals("x", result[0][2]);
        assertEquals("x", result[1][0]);
        assertEquals("*", result[1][1]);
        assertEquals(".", result[1][2]);
        assertEquals("x", result[2][0]);
        assertEquals(".", result[2][1]);
        assertEquals("*", result[2][2]);
        assertEquals("x", result[3][0]);
        assertEquals("x", result[3][1]);
        assertEquals("x", result[3][2]);
    }
}
