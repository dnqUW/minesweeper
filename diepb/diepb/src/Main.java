/*
 * Bao Thinh Diep
 * TCSS 360 assignment 1 2025
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;


/**
 * This is main class of the program
 *
 * @author Bao Thinh Diep
 * @version 1.0
 */
public class Main {

	/**
	 * main method to run the program
	 * @param args the arguments that can add when running this program
	 * @throws FileNotFoundException if the file is not found
	 */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        
        if (args.length == 1){
            input = new Scanner(new File(args[0]));
        }

        // Read input data and print out the input
        Controller controller = new Controller();
        List<MineField> mineFields = controller.readInput(input);
        String output = controller.getOutput(mineFields);
        System.out.print(output);
        input.close();

    }
}
