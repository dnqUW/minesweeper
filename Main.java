import java.util.Scanner;

// @author quachd Derek Quach
public class Main {
    public static void main(String[] args)  {

        // Scanner to scan the input
        Scanner scanner = new Scanner(System.in);
        int fieldNum = 1; // Current field number
        while(scanner.hasNextInt()) { // Essentially takes in field by field
            int rows = scanner.nextInt(); // rows should be the first thing parsed
            int columns = scanner.nextInt(); // columns next
            if (rows != 0 && columns != 0) { // if n = m = 0
                String[][] minefield = minefieldSetup(rows, columns, scanner);
                printMinefield(minefield, fieldNum);  
                fieldNum++;
            }
        }
    }
    
    // This method sets up the minefield with a buffer on the outer ring of 
    // the minefield. It returns a 2d string array to represent the minefield
    // It takes in int rows, int columns, and the scanner
    public static String[][] minefieldSetup(int rows, int columns, Scanner scanner) {
    	String[][] minefield = new String[rows + 2][columns + 2]; // +2 for buffers
    	
    	// Populates the minefield with x's
    	for(int i = 0; i < rows + 2; i++) {
    	    for(int j = 0; j < columns + 2; j++) {
    	        minefield[i][j] = "x";
    	    }
    	}
    	
        // Skip the newline after reading rows and columns
        scanner.nextLine();  

        // Read each row and populate the minefield
        for (int i = 1; i <= rows; i++) {
            String next = scanner.nextLine().trim();  // Read the next row of the minefield
            for (int j = 0; j < next.length(); j++) {
                minefield[i][j + 1] = "" + next.charAt(j);  // Fill the inner minefield
            }
            
        }
    	return minefield;
    }
    
    // This method reads the current character in the minefield and returns
    // an int representing how many mines are surrounding it.
    // It takes in an ints that represent the row and column, and a 2d string
    // array that represents the minefield
    public static int produceHint(int row, int column, String[][] minefield) {
    	int hint = 0; // hint will represent the num of mines surrounding a '.'
    	if(minefield[row - 1][column - 1].equals("*")) hint++; // top left
    	if(minefield[row - 1][column].equals("*")) hint++; // top
    	if(minefield[row - 1][column + 1].equals("*")) hint++; // top right
    	if(minefield[row][column - 1].equals("*")) hint++; // left
    	if(minefield[row][column + 1].equals("*")) hint++; // right
    	if(minefield[row + 1][column - 1].equals("*")) hint++; // bot left
        if(minefield[row + 1][column].equals("*")) hint++; // bot
        if(minefield[row + 1][column + 1].equals("*")) hint++; // bot right
    	return hint;
    }
    
    // Prints the current minefield and field number takes in a 2d String array that 
    // represents the minefield and the current int field number
    public static void printMinefield(String[][] minefield, int fieldNum) {
        System.out.println("Field #" + fieldNum + ":");
        for(int i = 1; i < minefield.length - 1; i++) {
            for(int j = 1; j < minefield[i].length - 1; j++) {
                if(minefield[i][j].equals(".")) {
                    minefield[i][j] = "" + produceHint(i, j, minefield);
                }
                System.out.print(minefield[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}