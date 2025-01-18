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
 * @author Bao Thinh Diep, Mathew Belmont, Derek Quach
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
	    final String[][] field = {
	        { "0", "0", "0" },
	        { "0", "*", "0" },
	        { "0", "0", "0" }
	    };
	    mineField.setMyMineField(field);
	    assertEquals(true, mineField.isValidIndex(0, 0));
	}

	@Test
	void testIsValidIndex_InvalidNegativeRow() {
	    final String[][] field = {
	        { "0", "0", "0" },
	        { "0", "*", "0" },
	        { "0", "0", "0" }
	    };
	    mineField.setMyMineField(field);
	    assertEquals(false, mineField.isValidIndex(-1, 0));
	}

	@Test
	void testIsValidIndex_InvalidNegativeColumn() {
	    final String[][] field = {
	        { "0", "0", "0" },
	        { "0", "*", "0" },
	        { "0", "0", "0" }
	    };
	    mineField.setMyMineField(field);
	    assertEquals(false, mineField.isValidIndex(0, -1));
	}

	@Test
	void testIncreaseMineCount_OneMineAtTopLeft() {
	    final String[][] field = {
	        { "*", "0" },
	        { "0", "0" },
	        { "0", "0" }
	    };

	    mineField.setMyMineField(field);
	    mineField.procedureHint();

	    final String expectedString = 
	        "*1\n" +
	        "11\n" +
	        "00";

	    assertEquals(expectedString, mineField.toString());
	}

	@Test
	void testIncreaseMineCount_TwoMineAtFirstLine() {
	    final String[][] field = {
	        { "*", "*" },
	        { "0", "0" },
	        { "0", "0" }
	    };

	    mineField.setMyMineField(field);
	    mineField.procedureHint();

	    final String expectedString = 
	        "**\n" +
	        "22\n" +
	        "00";

	    assertEquals(expectedString, mineField.toString());
	}

	@Test
	void testIncreaseMineCount_NoMines() {
	    final String[][] field = {
	        { "0", "0" },
	        { "0", "0" },
	        { "0", "0" }
	    };

	    mineField.setMyMineField(field);
	    mineField.procedureHint();

	    final String expectedString = 
	        "00\n" +
	        "00\n" +
	        "00";

	    assertEquals(expectedString, mineField.toString());
	}

	@Test
	void testIncreaseMineCount_AllMines() {
	    final String[][] field = {
	        { "*", "*" },
	        { "*", "*" },
	        { "*", "*" }
	    };

	    mineField.setMyMineField(field);
	    mineField.procedureHint();

	    final String expectedString = 
	        "**\n" +
	        "**\n" +
	        "**";

	    assertEquals(expectedString, mineField.toString());
	}

	@Test
	void testUpdateSurroundingCells_mineIsInTheCenter() {
	    final String[][] field = {
	        { "0", "0", "0" },
	        { "0", "*", "0" },
	        { "0", "0", "0" }
	    };
	    mineField.setMyMineField(field);
	    mineField.procedureHint();

	    final String expectedString = 
	        "111\n" +
	        "1*1\n" +
	        "111";

	    assertEquals(expectedString, mineField.toString());
	}
	
	@Test
	void testUpdateSurroundingMines_DotIsInTheCenter() {
	    final String[][] field = {
	        { "*", "*", "*" },
	        { "*", ".", "*" },
	        { "*", "*", "*" }
	    };
	    mineField.setMyMineField(field);
	    mineField.procedureHint();

	    final String expectedString = 
	        "***\n" +
	        "*8*\n" +
	        "***";

	    assertEquals(expectedString, mineField.toString());
	}

	@Test
	void testUpdateSurroundingCells_TwoMinesSideBySide() {
	    final String[][] field = {
	        { "0", "0", "0" },
	        { "0", "*", "*" },
	        { "0", "0", "0" }
	    };
	    mineField.setMyMineField(field);
	    mineField.procedureHint();

	    final String expectedString = 
	        "122\n" +
	        "1**\n" +
	        "122";

	    assertEquals(expectedString, mineField.toString());
	}

	@Test
	void testToString() {
	    final String[][] field = {
	        { "0", "0", "0" },
	        { "0", "*", "0" },
	        { "0", "0", "0" }
	    };
	    mineField.setMyMineField(field);

	    final String expectedString = 
	        "000\n" +
	        "0*0\n" +
	        "000";

	    assertEquals(expectedString, mineField.toString());
	}

}
