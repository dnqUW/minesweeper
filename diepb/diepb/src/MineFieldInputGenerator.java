import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MineFieldInputGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt for output file name
        System.out.print("Enter the output file name (with .txt extension): ");
        String fileName = scanner.nextLine();

        try (FileWriter writer = new FileWriter(fileName)) {
            boolean addMore = true;

            while (addMore) {
                // Prompt for minefield dimensions and percentage of mines
                System.out.print("Enter the number of rows: ");
                int rows = scanner.nextInt();
                System.out.print("Enter the number of columns: ");
                int cols = scanner.nextInt();
                System.out.print("Enter the percentage of mines (0-100): ");
                int minePercentage = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                // Validate percentage
                if (minePercentage < 0 || minePercentage > 100) {
                    System.out.println("Invalid percentage! Please enter a value between 0 and 100.");
                    continue;
                }

                // Generate the minefield
                char[][] minefield = generateMinefield(rows, cols, minePercentage);

                // Write minefield dimensions
                writer.write(rows + " " + cols + "\n");

                // Write the minefield to the file
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        writer.write(minefield[i][j]);
                    }
                    writer.write("\n");
                }

                // Ask if the user wants to add another minefield
                System.out.print("Do you want to add another minefield? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                addMore = response.equals("yes");
            }

            // End marker for input
            writer.write("0 0\n");
            System.out.println("Minefield input file generated: " + fileName);

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Generates a minefield with the given dimensions and mine percentage.
     *
     * @param rows          Number of rows in the minefield.
     * @param cols          Number of columns in the minefield.
     * @param minePercentage Percentage of cells to be filled with mines.
     * @return A 2D character array representing the minefield.
     */
    private static char[][] generateMinefield(int rows, int cols, int minePercentage) {
        char[][] minefield = new char[rows][cols];
        int totalCells = rows * cols;
        int mineCount = (totalCells * minePercentage) / 100;

        // Initialize all cells as empty
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                minefield[i][j] = '.';
            }
        }

        // Randomly place mines
        Random random = new Random();
        while (mineCount > 0) {
            int randomRow = random.nextInt(rows);
            int randomCol = random.nextInt(cols);

            // Place a mine if the cell is not already a mine
            if (minefield[randomRow][randomCol] != '*') {
                minefield[randomRow][randomCol] = '*';
                mineCount--;
            }
        }

        return minefield;
    }
}
