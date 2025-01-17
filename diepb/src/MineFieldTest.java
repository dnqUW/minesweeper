/*
 * Bao Thinh Diep
 * TCSS 360 assignment 1 2025
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class implements test cases for MineField class.
 *
 * @author Bao Thinh Diep
 * @version 1.0
 */
public class MineFieldTest {
	 private MineField mineField;

	    @BeforeEach
	    void setUp() {
	        mineField = new MineField();
	    }

	    @Test
	    void testIsValidIndex_ValidIndices() {
	        String[][] field = {
	                {"0", "0", "0"},
	                {"0", "*", "0"},
	                {"0", "0", "0"}
	        };
	        mineField.setMyMineField(field);
	        assertEquals(true, mineField.isValidIndex(0, 0));
	    }

	    @Test
	    void testIsValidIndex_InvalidNegativeRow() {
	        String[][] field = {
	                {"0", "0", "0"},
	                {"0", "*", "0"},
	                {"0", "0", "0"}
	        };
	        mineField.setMyMineField(field);
	        assertEquals(false, mineField.isValidIndex(-1, 0));
	    }

	    @Test
	    void testIsValidIndex_InvalidNegativeColumn() {
	        String[][] field = {
	                {"0", "0", "0"},
	                {"0", "*", "0"},
	                {"0", "0", "0"}
	        };
	        mineField.setMyMineField(field);
	        assertEquals(false, mineField.isValidIndex(0, -1));
	    }
	    
	    @Test
	    void testSetMyMineField() {
	        String[][] field = {
	                {".", ".", "."},
	                {".", ".", "."},
	                {".", ".", "*"}
	        };
	        mineField.setMyMineField(field);
	        String expectedString = "000\n000\n00*";
	        assertEquals(expectedString, mineField.toString());
	    }
	    
	    @Test
	    void testIncreaseMineCount_OneMineAtTofLeft() {
	    	String[][] field = {
	                {"*", "0"},
	                {"0", "0"},
	                {"0", "0"}
	        };
	    	
	    	 mineField.setMyMineField(field);
		     mineField.procedureHint();
		     String expectedString = "*1\n11\n00";
		     assertEquals(expectedString, mineField.toString());
	    }
	    
	    @Test
	    void testIncreaseMineCount_TwoMineAtFirstLine() {
	    	String[][] field = {
	                {"*", "*"},
	                {"0", "0"},
	                {"0", "0"}
	        };
	    	
	    	 mineField.setMyMineField(field);
		     mineField.procedureHint();
		     String expectedString = "**\n22\n00";
		     assertEquals(expectedString, mineField.toString());
	    }
	    
	    @Test
	    void testIncreaseMineCount_NoMines() {
	    	String[][] field = {
	                {"0", "0"},
	                {"0", "0"},
	                {"0", "0"}
	        };
	    	
	    	 mineField.setMyMineField(field);
		     mineField.procedureHint();
		     String expectedString = "00\n00\n00";
		     assertEquals(expectedString, mineField.toString());
	    }
	    
	    @Test
	    void testIncreaseMineCount_AllMines() {
	    	String[][] field = {
	                {"*", "*"},
	                {"*", "*"},
	                {"*", "*"}
	        };
	    	
	    	 mineField.setMyMineField(field);
		     mineField.procedureHint();
		     String expectedString = "**\n**\n**";
		     assertEquals(expectedString, mineField.toString());
	    }

	    @Test
	    void testUpdateSurroundingCells_mineIsInTheCenter() {
	        String[][] field = {
	                {"0", "0", "0"},
	                {"0", "*", "0"},
	                {"0", "0", "0"}
	        };
	        mineField.setMyMineField(field);
	        mineField.procedureHint();
	        String expectedString = "111\n1*1\n111";
	        assertEquals(expectedString, mineField.toString());
	       
	    }
	    
	    @Test
	    void testUpdateSurroundingCells_TwoMinesSideBySide() {
	        String[][] field = {
	                {"0", "0", "0"},
	                {"0", "*", "*"},
	                {"0", "0", "0"}
	        };
	        mineField.setMyMineField(field);
	        mineField.procedureHint();
	        String expectedString = "122\n1**\n122";
	        assertEquals(expectedString, mineField.toString());
	        
	    }


	    @Test
	    void testToString() {
	        String[][] field = {
	                {"0", "0", "0"},
	                {"0", "*", "0"},
	                {"0", "0", "0"}
	        };
	        mineField.setMyMineField(field);
	        String expectedString = "000\n0*0\n000";
	        assertEquals(expectedString, mineField.toString());
	    }

}
