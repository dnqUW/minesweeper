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

	public List<MineField> readInput(Scanner theScanner) {
		List<MineField> mineFields = new ArrayList<MineField>();
		while (true) {
			String val = theScanner.nextLine();
			Pattern pattern = Pattern.compile("\\d+\\s\\d+");
			Matcher matcher = pattern.matcher(val);

			if (matcher.matches() && !val.equals("0 0")) {
				String[] dimensionSize = val.split(" ");
				int rowNumbers = Integer.parseInt(dimensionSize[0]);
				int colNumbers = Integer.parseInt(dimensionSize[1]);
				String[][] inputArray = new String[rowNumbers][colNumbers];
				for (int i = 0; i < rowNumbers; i++) {
					String rowString = theScanner.nextLine().toString();
					for (int j = 0; j < colNumbers; j++) {

						inputArray[i][j] = rowString.substring(j, j + 1);
					}
				}
				MineField mineField = new MineField();
				mineField.setMyMineField(inputArray);
				mineField.procedureHint();
				mineFields.add(mineField);
			}
			if (val.equals("0 0"))
				break;
		}
		return mineFields;
	}

	public String getOutput(List<MineField> theMineFields) {
		int counter = 0;
		StringBuilder result = new StringBuilder();
		for (MineField mineField : theMineFields) {
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
