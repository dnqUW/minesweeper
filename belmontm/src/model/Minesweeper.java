/*
 * TCSS 360 - Assignment 1
 */

package model;

import java.util.Scanner;

/**
 * Starts the Minesweeper application. 
 * 
 * This takes input from System.in of the form :
 * The first line of each field contains two integers n and m (0 < n,m <= 100) which stands for
 * the number of lines and columns of the field respectively. The next n
 * lines contain exactly m characters and represent the field. Each safe square is represented
 * by an "." character (without the quotes) and each mine square is represented by an "*"
 * character (also without the quotes). The first field line where n = m = 0 represents the
 * end of input and should not be processed.
 * 
 * The output takes the from:
 * Field #x:
 * 
 * Where x stands for the number of the field (starting from 1). The next n
 * lines should contain the field with the "." characters replaced by the
 * number of adjacent mines to that square. There must be an empty line between field outputs.
 * 
 * @author Mathew Belmont (belmomat@uw.edu)
 * @version Winter 2025
 */
public final class Minesweeper {
    
    /**
     * This is a String that represents the new line delimiter for the user's OS.
     */
    static final String LINE_BREAK = System.getProperty("line.separator");
    
    private Minesweeper() {
    }

    public static void main(final String[] theArgs) {
        
        final Scanner scan = new Scanner(System.in);
        int fieldCount = 0;
        
        while (true) {
            fieldCount++;
        
            final int[][] bufferedMap = parseInput(scan);
            
            if (bufferedMap == null) {
                break;
            } else {
                final int[][] hintMap = createBufferedHintMap(bufferedMap);
                
                System.out.println(createOutput(fieldCount, hintMap));
            }
        }
        
        scan.close();       
    }
    
    /**
     * Parses a single well-formatted input field.
     * 
     * @param theScan: holding the input stream to parse
     * @return a buffered (surrounded by 0s) 2D array representing the input map
     * where "." are 0s and "*" are their ASCII value of 42.
     */
    static int[][] parseInput(final Scanner theScan) {
            
        final int rowCount = theScan.nextInt();
        final int columnCount = theScan.nextInt();
        
        int [][] bufferedMap = null;
        
        if (rowCount > 0 && columnCount > 0) {
            bufferedMap = new int [rowCount + 2][columnCount + 2];
            
            for (int i = 1; i <= rowCount; i++) {
                final String nextRow = theScan.next();
                for (int j = 1; j <= columnCount; j++) {
                    if (nextRow.charAt(j - 1) == '*') {
                        bufferedMap[i][j] = '*';
                    }
                }
            }
            
        }
        
        return bufferedMap;
    }
    
    /**
     * Converts a bufferedHintMap into a formatted String.
     * 
     * @param theCount: the index of the field we are outputting.
     * @param theBufferedMap: a buffered (surrounded by non-data) 2D array representing a 
     * hintMap where "*" are their ASCII value of 42 
     * and all other elements contain the number of mines neighboring it.
     * @return A String  containing:
     * Field #theCount:
     * 
     * The next n lines contain theBufferedMap as a String with a new line for each row.
     * 42s will be converted back to their ASCII character. There is an empty line after.
     */
    static String createOutput(final int theCount, final int[][] theBufferedMap) {
        
        final StringBuilder result = new StringBuilder();
        
        result.append("Field #");
        result.append(theCount);
        result.append(':');
        result.append(LINE_BREAK);
        
        for (int i = 1; i < theBufferedMap.length - 1; i++) {
            for (int j = 1; j < theBufferedMap[0].length - 1; j++) {
                if (theBufferedMap[i][j] == '*') {
                    result.append('*');
                } else {
                    result.append(theBufferedMap[i][j]);
                }
            }
            result.append(LINE_BREAK);
        }
        
        return result.toString();
    }
    
    /**
     * Replaces 0s in theBufferedMap with the number of surrounding mines.
     * 
     * @param theBufferedMap: a buffered (surrounded by 0s) 2D array representing the input map
     * where "." are 0s and "*" are their ASCII value of 42.
     * @return a buffered (surrounded by non-data) 2D array representing a 
     * hintMap where "*" are their ASCII value of 42 
     * and all other elements contain the number of mines neighboring it.
     */
    static int[][] createBufferedHintMap(final int[][] theBufferedMap) {
        
        for (int i = 1; i < theBufferedMap.length - 1; i++) {
            for (int j = 1; j < theBufferedMap[i].length - 1; j++) {
                if (theBufferedMap[i][j] == '*') {
                    updateNeighbors(i, j, theBufferedMap);
                }
            }
        }
        
        return theBufferedMap;
    }
    
    /**
     * Adds 1 to the elements surrounding the given index unless they are also a mine.
     * 
     * @param theRow: the row index of the mine that was found.
     * @param theColumn: the column index of the mine that was found.
     * @param theBufferedMap: a buffered (surrounded by non-data) 2D array representing a 
     * hintMap where "*" are their ASCII value of 42 
     * and all other elements contain the number of mines neighboring it.
     */
    private static void updateNeighbors(final int theRow,
                                        final int theColumn, final int[][] theBufferedMap) {
        
        if (theBufferedMap[theRow - 1][theColumn - 1] != '*') { // upper left
            theBufferedMap[theRow - 1][theColumn - 1]++;
        }
        if (theBufferedMap[theRow - 1][theColumn] != '*') { // upper
            theBufferedMap[theRow - 1][theColumn]++;
        }
        if (theBufferedMap[theRow - 1][theColumn + 1] != '*') { // upper right
            theBufferedMap[theRow - 1][theColumn + 1]++;
        }
        if (theBufferedMap[theRow][theColumn - 1] != '*') { // left
            theBufferedMap[theRow][theColumn - 1]++;
        }
        if (theBufferedMap[theRow][theColumn + 1] != '*') { // right
            theBufferedMap[theRow][theColumn + 1]++;
        }
        if (theBufferedMap[theRow + 1][theColumn - 1] != '*') { // lower left
            theBufferedMap[theRow + 1][theColumn - 1]++;
        }
        if (theBufferedMap[theRow + 1][theColumn] != '*') { // lower
            theBufferedMap[theRow + 1][theColumn]++;
        }
        if (theBufferedMap[theRow + 1][theColumn + 1] != '*') { // lower right
            theBufferedMap[theRow + 1][theColumn + 1]++;
        }
    }
}
