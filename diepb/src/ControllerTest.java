/*
 * Bao Thinh Diep
 * TCSS 360 assignment 1 2025
 */

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class implements test cases for Controller class.
 *
 * @author Bao Thinh Diep
 * @version 1.0
 */
public class ControllerTest {
	private Controller controller;

	@BeforeEach
	void setUp() {
		controller = new Controller();
	}

	/*
	 * ********************************* Test cases for readInput
	 * *********************************
	 */

	/**
	 * Test method for {@link Controller#readInput(java.util.Scanner)}
	 */
	@Test
	void testReadInput_AllMines_100x100() {
		// Input: 100x100 with all mines
		StringBuilder input = new StringBuilder();
		input.append("100 100\n");
		for (int i = 0; i < 100; i++) {
			input.append("*".repeat(100)).append("\n");
		}
		input.append("0 0");

		// Test logic
		Scanner scanner = new Scanner(input.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		MineField firstMineField = mineFields.get(0);

		// Expected output
		StringBuilder expectedInput = new StringBuilder();
		for (int i = 0; i < 100; i++) {
			expectedInput.append("*".repeat(100)).append("\n");
		}
		// remove last empty line
		expectedInput.deleteCharAt(expectedInput.length() - 1);

		scanner.close();
		
		// we only care about the data so we trim() the result strings.
		assertEquals(expectedInput.toString().trim(), firstMineField.toString().trim());

	}

	/**
	 * @Test method for {@link Controller#readInput(java.util.Scanner)}
	 */
	@Test
	void testReadInput_NoMines_100x100() {
		// Input: 100x100 with no mines
		StringBuilder input = new StringBuilder();
		input.append("100 100\n");
		for (int i = 0; i < 100; i++) {
			input.append(".".repeat(100)).append("\n");
		}
		input.append("0 0");

		// Test logic
		Scanner scanner = new Scanner(input.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		MineField firstMineField = mineFields.get(0);

		// Expected output
		StringBuilder expectedInput = new StringBuilder();
		for (int i = 0; i < 100; i++) {
			expectedInput.append(".".repeat(100)).append("\n");
		}

		scanner.close();
		assertEquals(expectedInput.toString().trim(), firstMineField.toString().trim());
	}

	/**
	 * Test method for {@link Controller#readInput(java.util.Scanner)}.
	 *
	 */
	@Test
	void testReadInput_1x1_Mine() {
		// Input: 1x1 with a single mine
		String input = "1 1\n*\n0 0";

		// Test logic
		Scanner scanner = new Scanner(input);
		List<MineField> mineFields = controller.readInput(scanner);
		MineField firstMineField = mineFields.get(0);

		// Expected output
		String expectedInput = "*";

		scanner.close();
		assertEquals(expectedInput, firstMineField.toString());
	}

	/**
	 * Test method for {@link Controller#readInput(java.util.Scanner)}.
	 * 
	 */
	@Test
	void testReadInput_1x1_NoMine() {
		// Input: 1x1 with no mine
		String input = "1 1\n.\n0 0";

		// Test logic
		Scanner scanner = new Scanner(input);
		List<MineField> mineFields = controller.readInput(scanner);
		MineField firstMineField = mineFields.get(0);

		// Expected output
		String expectedInput = ".";

		scanner.close();
		assertEquals(expectedInput, firstMineField.toString());
	}

	/**
	 * Test method for {@link Controller#readInput(java.util.Scanner)}.
	 * 
	 */
	@Test
	void testReadInput_1x100() {
		// Input: 1x100 alternating mines
		StringBuilder input = new StringBuilder();
		input.append("1 100\n");
		input.append("*.".repeat(50)).append("\n");
		input.append("0 0");

		// Test logic
		Scanner scanner = new Scanner(input.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		MineField firstMineField = mineFields.get(0);

		// Expected output
		String expectedInput = "*.".repeat(50);

		scanner.close();
		assertEquals(expectedInput, firstMineField.toString());
	}

	/**
	 * Test method for {@link Controller#readInput(java.util.Scanner)}.
	 * 
	 */
	@Test
	void testReadInput_100x1() {
		// Input: 100x1 alternating mines
		StringBuilder input = new StringBuilder();
		input.append("100 1\n");
		for (int i = 0; i < 100; i++) {
			input.append(i % 2 == 0 ? "*\n" : ".\n");
		}
		input.append("0 0");

		// Test logic
		Scanner scanner = new Scanner(input.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		MineField firstMineField = mineFields.get(0);

		// Expected output
		StringBuilder expectedInput = new StringBuilder();
		for (int i = 0; i < 100; i++) {
			expectedInput.append(i % 2 == 0 ? "*\n" : ".\n");
		}

		scanner.close();
		assertEquals(expectedInput.toString().trim(), firstMineField.toString().trim());
	}

	/**
	 * Test method for {@link Controller#readInput(java.util.Scanner)}.
	 * 
	 */
	void testReadInput_AllMines_3x3() {
		// Input: 100x100 with all mines
		StringBuilder input = new StringBuilder();
		input.append("3 3\n");
		input.append(".*.\n");
		input.append(".*.\n");
		input.append("...\n");
		input.append("0 0");

		// Test logic
		Scanner scanner = new Scanner(input.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		MineField firstMineField = mineFields.get(0);

		// Expected output
		StringBuilder expectedInput = new StringBuilder();
		expectedInput.append(".*.\n");
		expectedInput.append(".*.\n");
		expectedInput.append("...\n");
		

		scanner.close();
		assertEquals(expectedInput.toString().trim(), firstMineField.toString().trim());

	}

	/*
	 * **************************** Test cases for getOutput
	 * ****************************
	 */

	/**
	 * @Test method for {@link Controller#getOutput(java.util.List)}
	 */
	@Test
	void testGetOutputAllMines_100x100() {
		// Input: 100x100 with all mines
		StringBuilder input = new StringBuilder();
		input.append("100 100\n");
		for (int i = 0; i < 100; i++) {
			input.append("*".repeat(100)).append("\n");
		}
		input.append("0 0");

		// Expected output
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("Field #1:\n");
		for (int i = 0; i < 100; i++) {
			expectedOutput.append("*".repeat(100)).append("\n");
		}
		expectedOutput.append("\n");

		// Test logic
		Scanner scanner = new Scanner(input.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		String actualOutput = controller.getOutput(mineFields);

		scanner.close();

		// Assertion: Compare the whole output as a string
		assertEquals(expectedOutput.toString().trim(), actualOutput.trim());
	}

	/**
	 * @Test method for {@link Controller#getOutput(java.util.List)}
	 */
	@Test
	void testGetOutput_NoMines_100x100() {
		// Input: 100x100 with no mines
		StringBuilder input = new StringBuilder();
		input.append("100 100\n");
		for (int i = 0; i < 100; i++) {
			input.append(".".repeat(100)).append("\n");
		}
		input.append("0 0");

		// Expected output
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("Field #1:\n");
		for (int i = 0; i < 100; i++) {
			expectedOutput.append("0".repeat(100)).append("\n");
		}
		expectedOutput.append("\n");

		// Test logic
		Scanner scanner = new Scanner(input.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		String actualOutput = controller.getOutput(mineFields);

		scanner.close();
		assertEquals(expectedOutput.toString().trim(), actualOutput.trim());
	}

	/**
	 * @Test method for {@link Controller#getOutput(java.util.List)}
	 */
	@Test
	void testGetOutput_NoMines_1x100() {
		// Input: 1x100 with no mines
		StringBuilder input = new StringBuilder();
		input.append("1 100\n");
		input.append(".".repeat(100)).append("\n");
		input.append("0 0");

		// Expected output
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("Field #1:\n");
		expectedOutput.append("0".repeat(100)).append("\n\n");

		// Test logic
		Scanner scanner = new Scanner(input.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		String actualOutput = controller.getOutput(mineFields);

		scanner.close();
		assertEquals(expectedOutput.toString().trim(), actualOutput.trim());
	}

	/**
	 * @Test method for {@link Controller#getOutput(java.util.List)}
	 */
	@Test
	void testGetOutput_AllMines_1x100() {
		// Input: 1x100 with all mines
		StringBuilder input = new StringBuilder();
		input.append("1 100\n");
		input.append("*".repeat(100)).append("\n");
		input.append("0 0");

		// Expected output
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("Field #1:\n");
		expectedOutput.append("*".repeat(100)).append("\n\n");

		// Test logic
		Scanner scanner = new Scanner(input.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		String actualOutput = controller.getOutput(mineFields);

		scanner.close();
		assertEquals(expectedOutput.toString().trim(), actualOutput.trim());
	}

	/**
	 * @Test method for {@link Controller#getOutput(java.util.List)}
	 */
	@Test
	void testGetOutput_NoMines_1x1() {
		// Input: 1x1 with no mines
		StringBuilder input = new StringBuilder();
		input.append("1 1\n");
		input.append(".\n");
		input.append("0 0");

		// Expected output
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("Field #1:\n");
		expectedOutput.append("0\n\n");

		// Test logic
		Scanner scanner = new Scanner(input.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		String actualOutput = controller.getOutput(mineFields);

		scanner.close();
		assertEquals(expectedOutput.toString().trim(), actualOutput.trim());
	}

	/**
	 * @Test method for {@link Controller#getOutput(java.util.List)}
	 */
	@Test
	void testGetOutput_AllMines_1x1() {
		// Input: 1x1 with all mines
		StringBuilder input = new StringBuilder();
		input.append("1 1\n");
		input.append("*\n");
		input.append("0 0");

		// Expected output
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("Field #1:\n");
		expectedOutput.append("*\n\n");

		// Test logic
		Scanner scanner = new Scanner(input.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		String actualOutput = controller.getOutput(mineFields);

		scanner.close();
		assertEquals(expectedOutput.toString().trim(), actualOutput.trim());
	}

	/**
	 * @Test method for {@link Controller#getOutput(java.util.List)}
	 */
	@Test
	void testGetOutput_NoMines_100x1() {
		// Input: 100x1 with no mines
		StringBuilder input = new StringBuilder();
		input.append("100 1\n");
		for (int i = 0; i < 100; i++) {
			input.append(".\n");
		}
		input.append("0 0");

		// Expected output
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("Field #1:\n");
		for (int i = 0; i < 100; i++) {
			expectedOutput.append("0\n");
		}
		expectedOutput.append("\n");

		// Test logic
		Scanner scanner = new Scanner(input.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		String actualOutput = controller.getOutput(mineFields);

		scanner.close();
		assertEquals(expectedOutput.toString().trim(), actualOutput.trim());
	}

	/**
	 * @Test method for {@link Controller#getOutput(java.util.List)}
	 */
	@Test
	void testGetOutput_AllMines_100x1() {
		// Input: 100x1 with all mines
		StringBuilder input = new StringBuilder();
		input.append("100 1\n");
		for (int i = 0; i < 100; i++) {
			input.append("*\n");
		}
		input.append("0 0");

		// Expected output
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("Field #1:\n");
		for (int i = 0; i < 100; i++) {
			expectedOutput.append("*\n");
		}
		expectedOutput.append("\n");

		// Test logic
		Scanner scanner = new Scanner(input.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		String actualOutput = controller.getOutput(mineFields);

		scanner.close();
		assertEquals(expectedOutput.toString().trim(), actualOutput.trim());
	}

	/**
	 * @Test method for {@link Controller#getOutput(java.util.List)}
	 */
	@Test
	void testGetOutput_MixedMines_100x100() {

		String filePath = "src/inputTestFiles/100x100MixedMines.txt";

		// Create a StringBuilder to store the file content
		StringBuilder content = getData(filePath);

		String filePathOutput = "src/expectedOutputTestFiles/100x100MixedMinesOutput.txt";

		// Create a StringBuilder to store the file content
		StringBuilder expectedOutput = getData(filePathOutput);

		// Test logic
		Scanner scanner = new Scanner(content.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		String actualOutput = controller.getOutput(mineFields);

		scanner.close();

		// Assertion: Compare the whole output as a string
		assertEquals(expectedOutput.toString().trim(), actualOutput.trim());
	}

	/**
	 * @Test method for {@link Controller#getOutput(java.util.List)}
	 */
	@Test
	void testGetOutput_30PercentMines_50x50() {

		String filePath = "src/inputTestFiles/50x50MixedMines.txt";

		// Create a StringBuilder to store the file content
		StringBuilder content = getData(filePath);

		String filePathOutput = "src/expectedOutputTestFiles/50x50MixedMinesOutput.txt";

		// Create a StringBuilder to store the file content
		StringBuilder expectedOutput = getData(filePathOutput);

		// Test logic
		Scanner scanner = new Scanner(content.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		String actualOutput = controller.getOutput(mineFields);

		scanner.close();

		// Assertion: Compare the whole output as a string
		assertEquals(expectedOutput.toString().trim(), actualOutput.trim());
	}
	
	/**
	 * @Test method for {@link Controller#getOutput(java.util.List)}
	 */
	@Test
	void testGetOutput_TwoMineFields() {

		String filePath = "src/inputTestFiles/TwoMineFields.txt";

		// Create a StringBuilder to store the file content
		StringBuilder content = getData(filePath);

		String filePathOutput = "src/expectedOutputTestFiles/TwoMineFieldsOutput.txt";

		// Create a StringBuilder to store the file content
		StringBuilder expectedOutput = getData(filePathOutput);

		// Test logic
		Scanner scanner = new Scanner(content.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		String actualOutput = controller.getOutput(mineFields);

		scanner.close();

		// Assertion: Compare the whole output as a string
		assertEquals(expectedOutput.toString().trim(), actualOutput.trim());
	}
	
	/**
	 * @Test method for {@link Controller#getOutput(java.util.List)}
	 */
	@Test
	void testGetOutput_OneMineInTheCenter_3x3() {

		
		StringBuilder input = new StringBuilder();
		input.append("3 3\n");
		input.append("...\n");
		input.append(".*.\n");
		input.append("...\n");
		input.append("0 0");
				
		// Create a StringBuilder to store the file content
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("Field #1:\n");
		expectedOutput.append("111\n");
		expectedOutput.append("1*1\n");
		expectedOutput.append("111\n");


		Scanner scanner = new Scanner(input.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		String actualOutput = controller.getOutput(mineFields);
		

		scanner.close();
		assertEquals(expectedOutput.toString().trim(), actualOutput.trim());
	}
	
	/**
	 * @Test method for {@link Controller#getOutput(java.util.List)}
	 */
	@Test
	void testGetOutput_OneMineAtTopLeft_2x2() {

		
		StringBuilder input = new StringBuilder();
		input.append("2 2\n");
		input.append("*.\n");
		input.append("..\n");		
		input.append("0 0");
				
		// Create a StringBuilder to store the file content
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("Field #1:\n");
		expectedOutput.append("*1\n");
		expectedOutput.append("11\n");		



		Scanner scanner = new Scanner(input.toString());
		List<MineField> mineFields = controller.readInput(scanner);
		String actualOutput = controller.getOutput(mineFields);
		

		scanner.close();
		assertEquals(expectedOutput.toString().trim(), actualOutput.trim());
	}
	
	

	/**
	 * Ultility method to read file and return content as a StringBuilder
	 * 
	 * @param theFilePath the path of the file
	 * @return the content of the file
	 */
	public StringBuilder getData(String theFilePath) {
		// Create a StringBuilder to store the file content
		StringBuilder content = new StringBuilder();

		// Read the file line by line
		try (BufferedReader reader = new BufferedReader(new FileReader(theFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
		} catch (IOException e) {
			System.err.println("Error reading the file: " + e.getMessage());
		}

		return content;
	}

}
