import java.util.ArrayList;

public class PicrossSolver {

    public static boolean[][] solveBacktrack(PicrossPuzzle puzzle){
        boolean[][] board = new boolean[puzzle.getRows()][puzzle.getCols()];

        // While (worldWorks)
        //       AC3  thisWorld.ac3Forwarding();
        //       Choose a world
        //       Backtrack?





        return board;
    }


    /**
     * Alright yinz, where is where the chaos that is Mike's code begins.
     * @param puzzle The puzzle that is being double checked for correctness.
     * @return True if valid solution, False if not valid
     */
    public boolean checkAnswer(PicrossPuzzle puzzle)
    {
        //Reference code:
        //PicrossPuzzle pz = new PicrossPuzzle(10,10);
        //pz.getColClue(2).getClue(2);
        //boolean b = pz.getBoard()[1][2];

        int numRows = puzzle.getRows();
        int numCols = puzzle.getCols();

        //Check Columns
        for(int i=0; i<numCols; i++)
        {
            int q = 0;
            int clue = puzzle.getColClue(i).getClue(q);
            int count = 0;
            boolean outOfClues = false;

            for(int j=0; j<numRows; j++)
            {
                if(puzzle.getBoard()[j][i]) //If space is true
                {
                    if(outOfClues) //If there are more true sections than should be.
                    {
                        return false;
                    }

                    count++; //Count of true squares together.

                    if(count>=clue) //If there are more true spaces together than should be.
                    {
                        return false;
                    }
                }
                else //If space is false
                {
                    if(count==clue)
                    {
                        q++;
                        if(q>=puzzle.getColClue(i).size())
                        {
                            outOfClues = true;
                        }
                        else
                        {
                            clue = puzzle.getColClue(i).getClue(q);
                            count = 0; //Reset count as a new
                        }
                    }
                }
            }

            if(!outOfClues) //If Clues are left over when done with column.
            {
                return false;
            }
        }

        //Checking Rows
        for(int i=0; i<numRows; i++)
        {
            int q = 0;
            int clue = puzzle.getRowClue(i).getClue(q);
            int count = 0;
            boolean outOfClues = false;

            for(int j=0; j<numCols; j++)
            {
                if(puzzle.getBoard()[i][j]) //If space is true
                {
                    if(outOfClues) //If there are more true sections than should be.
                    {
                        return false;
                    }

                    count++; //Count of true squares together.

                    if(count>=clue) //If there are more true spaces together than should be.
                    {
                        return false;
                    }
                }
                else //If space is false
                {
                    if(count==clue)
                    {
                        q++;
                        if(q>=puzzle.getRowClue(i).size())
                        {
                            outOfClues = true;
                        }
                        else
                        {
                            clue = puzzle.getRowClue(i).getClue(q);
                            count = 0; //Reset count as a new
                        }
                    }
                }
            }

            if(!outOfClues) //If Clues are left over when done with column.
            {
                return false;
            }
        }

        return true; //If it made it this far everything is fine!
    }

}
