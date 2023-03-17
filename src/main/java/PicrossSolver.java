import java.util.ArrayList;

public class PicrossSolver {
    public static boolean[][] solveFullPuzzle(PicrossPuzzle puzzle) throws Exception {
        PicrossWorld trialWorld = new PicrossWorld(puzzle);
        trialWorld.AC3Forwarding();
        ArrayList<Domain> worldCols = trialWorld.getColDomains();
        ArrayList<Domain> worldRows = trialWorld.getRowDomains();
        for (int i = 0; i<worldCols.size(); i++) {
            Domain tempDom = worldCols.get(i);
            if (tempDom.getDomSize() < 1) {
                throw new Exception("AC3 has deemed this to be impossible, something went wrong.");
            }
        }

        for (int i = 0; i<worldRows.size(); i++) {
            Domain tempDom = worldRows.get(i);
            if (tempDom.getDomSize() < 1) {
                throw new Exception("AC3 has deemed this to be impossible, something went wrong.");
            }
        }

        boolean[][] finalResult = solveBacktrack(trialWorld);
        if (finalResult == null) {
            throw new Exception("Backtracking has deemed this to be impossible, something went wrong.");
        }

        return finalResult;
    }

    public static boolean[][] solveBacktrack(PicrossWorld world) {
        boolean[][] board = new boolean[world.getPuzzle().getRows()][world.getPuzzle().getCols()];
        try {
            board = translateToArray(world.getRowDomains(), world.getColDomains());
        }
        catch(Exception e) {
            return null;
        }

        if (checkAnswer(world.getPuzzle(), board)) {
            return board;
        }

        //ADD THE REST OF THE BACKTRACK CALC HERE (NOTE SOME OF ABOVE MAY BE DUE TO CHANGE)
        //ADD THE REST OF THE BACKTRACK CALC HERE (NOTE SOME OF ABOVE MAY BE DUE TO CHANGE)
        //ADD THE REST OF THE BACKTRACK CALC HERE (NOTE SOME OF ABOVE MAY BE DUE TO CHANGE)
        return solveBacktrack(world);
    }


    /**
     * Alright yinz, where is where the chaos that is Mike's code begins.
     * @param puzzle The puzzle that is being double checked for correctness.
     * @return True if valid solution, False if not valid
     */
    public static boolean checkAnswer(PicrossPuzzle puzzle, boolean[][] board)
    {
        //Reference code:
        //PicrossPuzzle pz = new PicrossPuzzle(10,10);
        //pz.getColClue(2).getClue(2);
        //boolean b = pz.getBoard()[1][2];

        int numRows = board.length;
        int numCols = board[1].length;

        //Check Columns
        for(int i=0; i<numCols; i++)
        {
            int q = 0;
            int clue = puzzle.getColClue(i).getClue(q);
            int count = 0;
            boolean outOfClues = false;

            for(int j=0; j<numRows; j++)
            {
                if(board[j][i]) //If space is true
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
                if(board[i][j]) //If space is true
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

    /**
     *
     * @param rowDomain
     * @param colDomain
     * @return A 2D array of the board, as determined by the remaining domains.
     * @throws Exception if there is more than one domain for a row or column
     */
    public static boolean[][] translateToArray(ArrayList<Domain> rowDomain, ArrayList<Domain> colDomain) throws Exception
    {
        for(int i=0; i<rowDomain.size(); i++)
        {
            if(rowDomain.get(i).getDomSize() != 1)
            {
                throw new Exception("Row " + i + "Domain not chosen");
            }

            if(colDomain.get(i).getDomSize() != 1)
            {
                throw new Exception("Column " + i + "Domain not chosen");
            }
        }

        boolean[][] board = new boolean[rowDomain.size()][colDomain.size()];

        for(int i=0; i<rowDomain.size(); i++)
        {
            Domain d = rowDomain.get(i);

            for(int j=0; j<d.getDomSize(); j++)
            {
                board[i][j] = d.getInstance(0)[j];
            }
        }

        return board;
    }

}
