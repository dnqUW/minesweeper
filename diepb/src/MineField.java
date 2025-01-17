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
    private String [][] myMineField;

    public void setMyMineField(String[][] myMineField) {
        this.myMineField = myMineField;
        initializeMineCount();
    }
    
    
    /**
     * Initialize the mine count of minefield
     */
    private void initializeMineCount() {
    	for (int i = 0; i < myMineField.length; i++) {
            for (int j = 0; j < myMineField[0].length; j++) {
                if(myMineField[i][j].equals(".")) {
                	myMineField[i][j] = "0";
                }

            }
        }
    }

    /**
     * Transforms the minefield into the final form.
     */
    public void procedureHint() {
        for (int i = 0; i < myMineField.length; i++) {
            for (int j = 0; j < myMineField[0].length; j++) {
                if(myMineField[i][j].equals("*")) {
                    updateSurroundingCells(i, j);
                }

            }
        }
    }

    /**
     * Increase the mine count of surrounding cells by 1.
     * @param theXIndex the x index of current cell
     * @param theYIndex the y index of current cell
     */
    private void updateSurroundingCells(int theXIndex, int theYIndex) {

        Integer[][] surroundingCells = {{theXIndex - 1, theYIndex},
                {theXIndex - 1, theYIndex - 1},
                {theXIndex - 1, theYIndex + 1},
                {theXIndex, theYIndex - 1},
                {theXIndex, theYIndex  + 1},
                {theXIndex + 1,theYIndex},
                {theXIndex + 1, theYIndex - 1},
                {theXIndex + 1, theYIndex + 1}};

        for (Integer[] surroundingCell : surroundingCells) {
            int xIndex = surroundingCell[0];
            int yIndex = surroundingCell[1];
            if (isValidIndex(xIndex, yIndex)) {
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
        if (!theCell.equals("*")) {
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
    public  boolean isValidIndex(int theXIndex, int theYIndex) {
        return theXIndex >= 0 && theXIndex < myMineField.length && theYIndex >= 0 && theYIndex < myMineField[0].length;
    }

    /**
     * Returns a string representation of the MineField.
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