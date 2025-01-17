package model;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class CreateBufferedHintMapTests {

    @Test
    void testMiddleMine() {
        
        final int[][] testData = {{0, 0, 0},
                                  {0, 42, 0},
                                  {0, 0, 0}};
        
        final int[][] expected = {{1, 1, 1},
                                  {1, 42, 1},
                                  {1, 1, 1}};

        final int[][] result = Minesweeper.createBufferedHintMap(testData);
        
        assertTrue(Arrays.deepEquals(expected, result));
    }
    
    @Test
    void testSurroundedByMines() {
        
        final int[][] testData = {{0, 0, 0, 0, 0},
                                  {0, 42, 42, 42, 0},
                                  {0, 42, 0, 42, 0},
                                  {0, 42, 42, 42, 0},
                                  {0, 0, 0, 0, 0}};
        
        final int[][] expected = {{1, 2, 3, 2, 1},
                                  {2, 42, 42, 42, 2},
                                  {3, 42, 8, 42, 3},
                                  {2, 42, 42, 42, 2},
                                  {1, 2, 3, 2, 1}};

        final int[][] result = Minesweeper.createBufferedHintMap(testData);
        
        assertTrue(Arrays.deepEquals(expected, result));
    }
    
    @Test
    void testNoMines() {
        final int[][] testData = {{0, 0, 0},
                                  {0, 0, 0},
                                  {0, 0, 0}};

        final int[][] expected = {{0, 0, 0},
                                  {0, 0, 0},
                                  {0, 0, 0}};
        
        final int[][] result = Minesweeper.createBufferedHintMap(testData);

        assertTrue(Arrays.deepEquals(expected, result));
    }
    
    @Test
    void testAllMines() {
        final int[][] testData = {{42, 42, 42},
                                  {42, 42, 42},
                                  {42, 42, 42}};

        final int[][] expected = {{42, 42, 42},
                                  {42, 42, 42},
                                  {42, 42, 42}};
        
        final int[][] result = Minesweeper.createBufferedHintMap(testData);

        assertTrue(Arrays.deepEquals(expected, result));
    }
    
    @Test
    void test3By5Mix() {
        
        final int[][] testData = {{0, 0, 0, 0, 0, 0, 0},
                                  {0, 42, 42, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 42, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0}};
        
        final int[][] expected = {{1, 2, 2, 1, 0, 0, 0},
                                  {1, 42, 42, 1, 0, 0, 0},
                                  {1, 3, 3, 2, 0, 0, 0},
                                  {0, 1, 42, 1, 0, 0, 0},
                                  {0, 1, 1, 1, 0, 0, 0}};
        
        
        final int[][] result = Minesweeper.createBufferedHintMap(testData);
        
        
        assertTrue(Arrays.deepEquals(expected, result));
    }
}
