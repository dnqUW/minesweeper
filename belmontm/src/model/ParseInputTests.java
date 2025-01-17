package model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class ParseInputTests {
    
    /**
     * This is a string that represents the new line delimiter for the user's OS.
     */
    static final String LINE_BREAK = System.getProperty("line.separator");
    
    @Test
    void test1by1Mines() {
        
        final StringBuilder builder = new StringBuilder();
        builder.append("1 1");
        builder.append(LINE_BREAK);
        builder.append('*');
        
        final Scanner testData = new Scanner(builder.toString());
        
        final int[][] expected = {{0, 0, 0},
                                  {0, 42, 0},
                                  {0, 0, 0}};
        
        final int[][] result = Minesweeper.parseInput(testData);
        
        testData.close();
        
        assertTrue(Arrays.deepEquals(expected, result));
    }
    
    @Test
    void test100by100Mines() {
        
        final int length = 100 + 2;
        
        final StringBuilder builder = new StringBuilder();
        final int[][] expected = new int[length][length];
        
        builder.append("100 100");
        builder.append(LINE_BREAK);
        
        for (int i = 1; i < length - 1; i++) {
            for (int j = 1; j < length - 1; j++) {
                builder.append('*');
                expected[i][j] = '*';
            }
            builder.append(LINE_BREAK);
        }
        
        final Scanner testData = new Scanner(builder.toString());
        
        final int[][] result = Minesweeper.parseInput(testData);
        
        testData.close();
        
        assertTrue(Arrays.deepEquals(expected, result));
    }
    
    @Test
    void test1by1NoMines() {
        
        final StringBuilder builder = new StringBuilder();
        builder.append("1 1");
        builder.append(LINE_BREAK);
        builder.append('.');
        
        final Scanner testData = new Scanner(builder.toString());
        
        final int[][] expected = {{0, 0, 0},
                                  {0, 0, 0},
                                  {0, 0, 0}};
        
        final int[][] result = Minesweeper.parseInput(testData);
        
        testData.close();
        
        assertTrue(Arrays.deepEquals(expected, result));
    }
    
    @Test
    void test100by100NoMines() {
        
        final int length = 100 + 2;
        
        final StringBuilder builder = new StringBuilder();
        final int[][] expected = new int[length][length];
        
        builder.append("100 100");
        builder.append(LINE_BREAK);
        
        for (int i = 1; i < length - 1; i++) {
            for (int j = 1; j < length - 1; j++) {
                builder.append('.');
            }
            builder.append(LINE_BREAK);
        }
        
        final Scanner testData = new Scanner(builder.toString());
        
        final int[][] result = Minesweeper.parseInput(testData);
        
        testData.close();
        
        assertTrue(Arrays.deepEquals(expected, result));
    }
    
    @Test
    void test3By5Mix() {
        
        final StringBuilder builder = new StringBuilder();
        final int[][] expected = {{0, 0, 0, 0, 0, 0, 0},
                                  {0, 42, 42, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 42, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0}};
        
        builder.append("3 5");
        builder.append(LINE_BREAK);
        builder.append("**...");
        builder.append(LINE_BREAK);
        builder.append(".....");
        builder.append(LINE_BREAK);
        builder.append(".*...");
        
        final Scanner testData = new Scanner(builder.toString());
        
        final int[][] result = Minesweeper.parseInput(testData);
        
        testData.close();
        
        assertTrue(Arrays.deepEquals(expected, result));
    }
    
    @Test
    void test00() {
        
        final Scanner testData = new Scanner("0 0");
        
        assertNull(Minesweeper.parseInput(testData));
        
        testData.close();
    }
}
