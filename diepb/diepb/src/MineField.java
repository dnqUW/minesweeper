/*
 * Bao Thinh Diep
 * TCSS 360 assignment 1 2025
 */

/**
 * This class represent the mine field.
 *
 * @author Bao Thinh Diep
 * @version 1.0
 */
public class MineField {
	private String[][] myMineField;

	public void setMyMineField(String[][] myMineField) {

		this.myMineField = myMineField;

	}

	/**
	 * Transforms the MineField into the final form.
	 */
	public void procedureHint() {
		for (int i = 0; i < myMineField.length; i++) {
			for (int j = 0; j < myMineField[0].length; j++) {

				// If the current cell is a mine then update the mine count for surrounding
				// cells.
				if (myMineField[i][j].equals("*")) {
					updateSurroundingCells(i, j);

				} else if (myMineField[i][j].equals(".")) { // If cell is "." then initialize the mine count to 0
					myMineField[i][j] = "0";
				}

			}
		}
	}

	/**
	 * Increase the mine count of surrounding cells by 1.
	 * 
	 * @param theXIndex the x index of current cell
	 * @param theYIndex the y index of current cell
	 */
	private void updateSurroundingCells(int theXIndex, int theYIndex) {

		// Get indexes of surrounding cells of the current cell
		Integer[][] surroundingCells = { { theXIndex - 1, theYIndex }, { theXIndex - 1, theYIndex - 1 },
				{ theXIndex - 1, theYIndex + 1 }, { theXIndex, theYIndex - 1 }, { theXIndex, theYIndex + 1 },
				{ theXIndex + 1, theYIndex }, { theXIndex + 1, theYIndex - 1 }, { theXIndex + 1, theYIndex + 1 } };

		for (Integer[] surroundingCell : surroundingCells) {
			int xIndex = surroundingCell[0];
			int yIndex = surroundingCell[1];
			if (isValidIndex(xIndex, yIndex)) {
				// If the index is valid then increase the mine count
				increaseMineCount(xIndex, yIndex);
			}
		}
	}

	/**
	 * Increase the mine count of the cell by 1.
	 *
	 * @param theXIndex the x index of current cell
	 * @param theYIndex the y index of current cell
	 */
	private void increaseMineCount(int theXIndex, int theYIndex) {
		String theCell = myMineField[theXIndex][theYIndex];

		// If the cell is not a mine then increase the mine count
		if (!theCell.equals("*")) {
			// If the cell is "." then initialize the mine count to 0
			if (theCell.equals(".")) {
				theCell = "0";
			}
			int intValue = Integer.parseInt(theCell);
			myMineField[theXIndex][theYIndex] = String.valueOf(++intValue);
		}
	}

	/**
	 * Checks if the index is valid.
	 *
	 * @param theXIndex the x index of current cell
	 * @param theYIndex the y index of current cell
	 * @return true if the index is valid, false otherwise
	 */
	public boolean isValidIndex(int theXIndex, int theYIndex) {
		return theXIndex >= 0 && theXIndex < myMineField.length && theYIndex >= 0 && theYIndex < myMineField[0].length;
	}

	/**
	 *
	 * @return a string representation of the MineField.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String[] strings : myMineField) {
			for (int j = 0; j < myMineField[0].length; j++) {
				sb.append(strings[j]);
			}
			sb.append("\n");
		}
		// remove last empty line
		sb.deleteCharAt(sb.length() - 1);

		return sb.toString();
	}
}