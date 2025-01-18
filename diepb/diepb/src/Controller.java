/*
 * Bao Thinh Diep
 * TCSS 360 assignment 1 2025
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is controller class of the program
 *
 * @author Bao Thinh Diep
 * @version 1.0
 */
public class Controller {

	/**
	 * Read the input and save the input data to a list of MineField object
	 * 
	 * @param theScanner the Scanner object use to read the input
	 * @return the list of MinedFields objects that created from the input data
	 */
	List<MineField> readInput(final Scanner theScanner) {
		final List<MineField> mineFields = new ArrayList<MineField>();
		while (true) {
			final String val = theScanner.nextLine();

			// check for first line of each MineField
			final Pattern pattern = Pattern.compile("\\d+\\s\\d+");
			final Matcher matcher = pattern.matcher(val);

			// Start processing for each MineField
			if (matcher.matches() && !val.equals("0 0")) {
				final String[] dimensionSize = val.split(" ");

				// Get dimension of MineField
				final int rowNumbers = Integer.parseInt(dimensionSize[0]);
				final int colNumbers = Integer.parseInt(dimensionSize[1]);

				// Extract the input data to an Array object
				final String[][] inputArray = new String[rowNumbers][colNumbers];
				for (int i = 0; i < rowNumbers; i++) {
					final String rowString = theScanner.nextLine().toString();
					for (int j = 0; j < colNumbers; j++) {

						inputArray[i][j] = rowString.substring(j, j + 1);
					}
				}

				// create a MineField object with specific input data and add it to the list
				final MineField mineField = new MineField();
				mineField.setMyMineField(inputArray);
				mineFields.add(mineField);
			}

			// Finish the input
			if (val.equals("0 0"))
				break;
		}
		return mineFields;
	}

	/**
	 * Generate a final string for the output with the right format
	 * 
	 * @param theMineFields list of MineField objects from input data
	 * @return a final string for the output with the right format
	 */
	String getOutput(final List<MineField> theMineFields) {
		int counter = 0;
		final StringBuilder result = new StringBuilder();

		// Generate presentation for each MineFiled and append to the StringBuilder
		// object
		for (MineField mineField : theMineFields) {
			mineField.procedureHint();
			result.append("Field #");
			result.append(String.valueOf(++counter));
			result.append(":\n");
			result.append(mineField.toString());
			result.append("\n");
			result.append("\n");

		}
		return result.toString();
	}
}
