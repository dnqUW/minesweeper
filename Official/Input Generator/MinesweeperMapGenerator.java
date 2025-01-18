/*
 * This file was written by chatGPT!
 * Here was the prompt:
 * Write a Java program that prompts the user for the dimensions of a minesweeper map (n and m).
 * The program should also prompt the user for a percentage which represents the percentage of the map that is filled with mines.
 * The program should then write to file the values for n and m separated by a space.
 * Then on a new line the representation of the map using "*" for a mine and "." for a safe space.
 * The program should then ask if the user wants to create another map. if so, repeat the process. If not write "0 0" on a new line and terminate.
 * Make this program append only during the same instance of execution, but start from an empty file otherwise.
 */
package support;

import java.io.*;
import java.util.*;

public class MinesweeperMapGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        File mapFile = new File("minesweeper_map.txt");

        // Ensure the file starts empty when the program is first executed
        try {
            if (mapFile.exists()) {
                // Clear the file before starting the program
                new FileWriter(mapFile, false).close(); // Overwrite file with empty content
            } else {
                // Create a new empty file if it doesn't exist
                mapFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error initializing file: " + e.getMessage());
            scanner.close();
            return;
        }

        while (true) {
            // Prompt user for map dimensions
            System.out.print("Enter the number of rows (n): ");
            int n = scanner.nextInt();
            System.out.print("Enter the number of columns (m): ");
            int m = scanner.nextInt();

            // Prompt user for the percentage of mines
            System.out.print("Enter the percentage of mines: ");
            int minePercentage = scanner.nextInt();

            // Validate inputs
            if (n <= 0 || m <= 0 || minePercentage < 0 || minePercentage > 100) {
                System.out.println("Invalid input. Please enter positive integers for dimensions and a percentage between 0 and 100 for mines.");
                continue;
            }

            // Create the map
            char[][] map = generateMap(n, m, minePercentage, random);

            // Append map to the file after the first map
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(mapFile, true))) {
                // Write map dimensions
                writer.write(n + " " + m);
                writer.newLine();

                // Write map representation
                for (int i = 0; i < n; i++) {
                    writer.write(map[i]);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }

            // Ask if the user wants to create another map
            System.out.print("Do you want to create another map? (y/n): ");
            char response = scanner.next().toLowerCase().charAt(0);
            if (response != 'y') {
                break;
            }
        }

        // Write "0 0" and terminate
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(mapFile, true))) {
            writer.write("0 0");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        System.out.println("Program terminated.");
        scanner.close();
    }

    private static char[][] generateMap(int n, int m, int minePercentage, Random random) {
        char[][] map = new char[n][m];

        // Initialize the map with safe spaces
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], '.');
        }

        // Calculate the number of mines based on the percentage
        int totalCells = n * m;
        int totalMines = (int) ((minePercentage / 100.0) * totalCells);

        // Place the mines randomly
        int minesPlaced = 0;
        while (minesPlaced < totalMines) {
            int row = random.nextInt(n);
            int col = random.nextInt(m);

            // Only place a mine if the cell is empty
            if (map[row][col] != '*') {
                map[row][col] = '*';
                minesPlaced++;
            }
        }

        return map;
    }
}
