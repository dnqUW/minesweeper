package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class CreateOutputTests {
    
    /**
     * This is a string that represents the new line delimiter for the user's OS.
     */
    static final String LINE_BREAK = System.getProperty("line.separator");
    
    @Test
    void test3By5Mix() {
        
        final int[][] testData = {{1, 2, 2, 1, 0, 0, 0},
                                  {1, 42, 42, 1, 0, 0, 0},
                                  {1, 3, 3, 2, 0, 0, 0},
                                  {0, 1, 42, 1, 0, 0, 0},
                                  {0, 1, 1, 1, 0, 0, 0}};
        
        final StringBuilder expected = new StringBuilder();
        expected.append("Field #1:");
        expected.append(LINE_BREAK);
        expected.append("**100");
        expected.append(LINE_BREAK);
        expected.append("33200");
        expected.append(LINE_BREAK);
        expected.append("1*100");
        expected.append(LINE_BREAK);
        
        assertEquals(expected.toString(), Minesweeper.createOutput(1, testData));
    }
    
    @Test
    void test1By1Mines() {
        
        final int[][] testData = {{1, 1, 1},
                                  {1, 42, 1},
                                  {1, 1, 1}};
        
        final StringBuilder expected = new StringBuilder();
        expected.append("Field #2:");
        expected.append(LINE_BREAK);
        expected.append("*");
        expected.append(LINE_BREAK);
        
        assertEquals(expected.toString(), Minesweeper.createOutput(2, testData));
    }
    
    @Test
    void testBlockOfMines() {
        
        final int[][] testData = {{-17, 145, 1, 0},
                                  {145, 42, 42, 1},
                                  {1, 42, 42, 145},
                                  {0, 1, 145, -17}};
        
        final StringBuilder expected = new StringBuilder();
        expected.append("Field #-3:");
        expected.append(LINE_BREAK);
        expected.append("**");
        expected.append(LINE_BREAK);
        expected.append("**");
        expected.append(LINE_BREAK);
        
        assertEquals(expected.toString(), Minesweeper.createOutput(-3, testData));
    }
    
    @Test
    void testNoMines() {
        final int[][] testData = {{0, 0, 0},
                                  {0, 0, 0},
                                  {0, 0, 0}};
        
        final StringBuilder expected = new StringBuilder();
        expected.append("Field #47:");
        expected.append(LINE_BREAK);
        expected.append("0");
        expected.append(LINE_BREAK);
        
        final int[][] result = Minesweeper.createBufferedHintMap(testData);

        assertEquals(expected.toString(), Minesweeper.createOutput(47, testData));
    }
}
