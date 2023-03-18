import java.util.ArrayList;
import java.util.Arrays;

public class PicrossSolver {
    public static boolean[][] solveFullPuzzle(PicrossPuzzle puzzle) throws Exception {
        PicrossWorld trialWorld = new PicrossWorld(puzzle);
        PicrossWorld solutionWorld = solveBacktrack(trialWorld);
        return translateToArray(solutionWorld.getRowDomains(), solutionWorld.getColDomains());
    }

    public static PicrossWorld solveBacktrack(PicrossWorld world) {
        world.AC3Forwarding();

        ArrayList<Domain> worldAllCols = world.getColDomains();
        ArrayList<Domain> worldAllRows = world.getRowDomains();
        int colPossibilityCount = 0;
        int rowPossibilityCount = 0;
        for (int i = 0; i<worldAllCols.size(); i++) {
            Domain tempDom = worldAllCols.get(i);
            if (tempDom.getDomSize() < 1) {
                return null; //If any of them are equal to size 0, then it won't work.
            }
            colPossibilityCount += tempDom.getDomSize();
        }

        for (int i = 0; i<worldAllRows.size(); i++) {
            Domain tempDom = worldAllRows.get(i);
            if (tempDom.getDomSize() < 1) {
                return null; //If any of them are equal to size 0, then it won't work.
            }
            rowPossibilityCount += tempDom.getDomSize();
        }

        if (colPossibilityCount == worldAllCols.size() && rowPossibilityCount == worldAllRows.size()) {
            return world; //BASE CASE BAYEBEEE
        }

        //ADD THE REST OF THE BACKTRACK CALC HERE (NOTE SOME OF ABOVE MAY BE DUE TO CHANGE)
        //ADD THE REST OF THE BACKTRACK CALC HERE (NOTE SOME OF ABOVE MAY BE DUE TO CHANGE)
        //ADD THE REST OF THE BACKTRACK CALC HERE (NOTE SOME OF ABOVE MAY BE DUE TO CHANGE)
        for(int j = 0; j<world.getColDomains().size(); j++) {
            Domain d = world.getColDomains().get(j);
            if (d.getDomSize() > 1) {
                for (int i = 0; i < d.getDomSize(); i++) {
                    //Make a new world, replace one domain with a single
                    PicrossWorld newWorld = new PicrossWorld(world);
                    ArrayList<Domain> getColDom = newWorld.getColDomains();
                    getColDom.remove(j);

                    boolean[] instance = d.getInstance(i);
                    Domain newDom = new Domain(instance);
                    getColDom.add(j, newDom);
                    newWorld.setColDomains(getColDom);

                    PicrossWorld temp = solveBacktrack(newWorld);
                    if (temp != null) {
                        return temp;
                    }
                }
            }
        }
        return null;
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
     * @param rowDomain The domains for each row
     * @param colDomain The domains for each column
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
            System.out.println(d);

            for(int j=0; j<colDomain.size(); j++)
            {
                System.out.println(d.getInstance(0)[j]);
                board[i][j] = d.getInstance(0)[j];
            }
        }

        return board;
    }

    public static String boolBoardToString(boolean[][] board){
        StringBuilder out = new StringBuilder();
        //Get the sizes of the max number of clues for appropriate spacing

        System.out.println(Arrays.deepToString(board));
        // Print Board
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j]){
                    out.append("X").append(" ");
                }
                else{
                    out.append("O").append(" ");
                }
            }
            out.append("\n");
        }
        return out.toString();
    }

}
