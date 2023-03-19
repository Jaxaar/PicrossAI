import org.apache.tools.ant.taskdefs.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Do you want a Random board (R), a pre-generated 5x5 board (F), or a pre-generated 10x10 board? (T)");
        Boolean exitLoop = false;
        Scanner scnr = new Scanner(System.in);


        while(!exitLoop)
        {
            Character input = scnr.next().charAt(0);

            if(input.equals('R'))
            {
                System.out.println("How many rows do you want?");
                if(!scnr.hasNextInt())
                {
                    throw new Exception("Wrong Input");
                }
                int rows = scnr.nextInt();

                System.out.println("How many columns do you want?");
                if(!scnr.hasNextInt())
                {
                    throw new Exception("Wrong Input");
                }
                int cols = scnr.nextInt();

                boolean[][] board = new boolean[rows][cols];

                PicrossPuzzle puzzle = new PicrossPuzzle(rows, cols);
                board = PicrossSolver.solveFullPuzzle(puzzle);
                System.out.println(PicrossSolver.boolBoardToString(board));
                exitLoop = true;
            }
            else if (input.equals('F'))
            {
                boolean[][] board = new boolean[5][5];
                PicrossPuzzle puzzle = new PicrossPuzzle("picross5x5.csv");
                board = PicrossSolver.solveFullPuzzle(puzzle);
                System.out.println(PicrossSolver.boolBoardToString(board));
                exitLoop = true;
            }
            else if (input.equals('T'))
            {
                boolean[][] board = new boolean[10][10];
                PicrossPuzzle puzzle = new PicrossPuzzle("picross10x10.csv");
                board = PicrossSolver.solveFullPuzzle(puzzle);
                System.out.println(PicrossSolver.boolBoardToString(board));
                exitLoop = true;
            }
            else
            {
                System.out.println("That was not a correct input, please try agian.");
                System.out.println("Do you want a Random board (R), a pre-generated 5x5 board (F), or a pre-generated 10x10 board? (T)");
            }
        }
    }
}
