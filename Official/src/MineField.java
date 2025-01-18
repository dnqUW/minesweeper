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

	void setMyMineField(final String[][] myMineField) {

		this.myMineField = myMineField;

	}

	/**
	 * Transforms the MineField into the final form.
	 */
	void procedureHint() {
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
	private void updateSurroundingCells(final int theXIndex, final int theYIndex) {

		// Get indexes of surrounding cells of the current cell
		final Integer[][] surroundingCells = { { theXIndex - 1, theYIndex }, { theXIndex - 1, theYIndex - 1 },
				{ theXIndex - 1, theYIndex + 1 }, { theXIndex, theYIndex - 1 }, { theXIndex, theYIndex + 1 },
				{ theXIndex + 1, theYIndex }, { theXIndex + 1, theYIndex - 1 }, { theXIndex + 1, theYIndex + 1 } };

		for (Integer[] surroundingCell : surroundingCells) {
			final int xIndex = surroundingCell[0];
			final int yIndex = surroundingCell[1];
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
	private void increaseMineCount(final int theXIndex, final int theYIndex) {
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
	boolean isValidIndex(final int theXIndex, final int theYIndex) {
		return theXIndex >= 0 && theXIndex < myMineField.length && theYIndex >= 0 && theYIndex < myMineField[0].length;
	}

	/**
	 *
	 * @return a string representation of the MineField.
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
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