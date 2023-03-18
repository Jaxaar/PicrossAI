import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PicrossWorld {

    private PicrossPuzzle puzzle;
    private ArrayList<Domain> colDomains;
    private ArrayList<Domain> rowDomains;


    public PicrossWorld(PicrossPuzzle pz){
        //Add the board itself
        puzzle = pz;
        colDomains = new ArrayList<>();
        rowDomains = new ArrayList<>();

        //Add the column domains for that specific board
        for(int i = 0; i<pz.getCols(); i++) {
            Domain newDom = new Domain(pz.getColClue(i), pz.getCols());
            colDomains.add(newDom);
        }

        //Add the row domains for that specific board
        for(int i = 0; i<pz.getRows(); i++) {
            Domain newDom = new Domain(pz.getRowClue(i), pz.getRows());
            rowDomains.add(newDom);
        }
    }

    //Deep-copy
    public PicrossWorld(PicrossWorld wld){
        //Copy the entire puzzle (deep copy)
        puzzle = new PicrossPuzzle(wld.puzzle);
        colDomains = new ArrayList<>();
        rowDomains = new ArrayList<>();

        //Column Domains
        for (int i=0; i<puzzle.getCols(); i++) {
            Domain newDom = new Domain(puzzle.getColClue(i), puzzle.getCols());
            colDomains.add(newDom);
        }

        //Row Domains
        for (int i=0; i<puzzle.getRows(); i++) {
            Domain newDom = new Domain(puzzle.getRowClue(i), puzzle.getRows());
            rowDomains.add(newDom);
        }
    }

    public ArrayList<Domain> getColDomains() {
        return colDomains;
    }

    public ArrayList<Domain> getRowDomains() {
        return rowDomains;
    }

    public void setColDomains(ArrayList<Domain> d) { colDomains = d; }

    public void setRowDomains(ArrayList<Domain> d) { rowDomains = d; }

    public PicrossPuzzle getPuzzle() {
        return puzzle;
    }

    public void AC3Forwarding() {
        Queue<ArrayList<Domain>> q = new LinkedList<>();

        q.add(rowDomains);
        q.add(colDomains);

        while (!q.isEmpty()) {
            boolean isRow = false;
            Boolean hasChanged = false;

            ArrayList<Domain> target = q.poll();

            //Checks if target is the rows or the columns.
            if (target == rowDomains) {
                isRow = true;
            }

            for (int i = 0; i < target.size(); i++) {
                boolean hasTrue = false;
                boolean hasFalse = false;

                Domain focus = target.get(i);

                if (isRow) {
                    //Checks if the specified spot in the column domains has both true and false. If both are present, nothing happens to the domain of the target.
                    for (int j = 0; j < colDomains.size(); j++) {
                        for (int k = 0; k < colDomains.get(j).getDomSize(); k++) {
                            if (colDomains.get(j).getInstance(k)[i]) {
                                hasTrue = true;
                            } else {
                                hasFalse = true;
                            }
                        }

                        //If one is false, then changes are possible to the domains.
                        if (!hasTrue || !hasFalse) {
                            hasChanged = checkToRemove(focus, j, hasTrue);
                        }
                    }
                } else {
                    //Checks if the specified spot in the column domains has both true and false. If both are present, nothing happens to the domain of the target.
                    for (int j = 0; j < rowDomains.size(); j++) {
                        for (int k = 0; k < rowDomains.get(j).getDomSize(); k++) {
                            if (rowDomains.get(j).getInstance(k)[i]) {
                                hasTrue = true;
                            } else {
                                hasFalse = true;
                            }
                        }

                        //If one is false, then changes are possible to the domains.
                        if (!hasTrue || !hasFalse) {
                            hasChanged = checkToRemove(focus, j, hasTrue);
                        }
                    }
                }
            }

            //If domains have been altered, adds the perpendicular lines to the queue
            if (hasChanged) {
                if (isRow) {
                    q.add(colDomains);
                } else {
                    q.add(rowDomains);
                }
            }
        }
    }


    /**
     * Checks domains to see if anything can be removed.
     * @param focus The domains that are the focus of the removal.
     * @param column The column that is being looked at for comparison.
     * @param comparison To compare the location's variable aginst
     * @return True if there are changes, false if not
     */
    private boolean checkToRemove(Domain focus, int column, boolean comparison)
    {
        boolean hasChanged = false;

        for(int k = 0; k< focus.getDomSize(); k++)
        {
            if(focus.getDom().get(k)[column] != comparison)
            {
                focus.removeInstance(k);
                hasChanged = true;
            }
        }

        return hasChanged;
    }

    @Override
    public String toString() {
        String result = "";
        for(int i=0; i<rowDomains.size(); i++)
        {
            result += rowDomains.get(i).toString() + " \n";
        }

        for(int i=0; i<colDomains.size(); i++)
        {
            result += colDomains.get(i).toString() + " \n";
        }

        return result;
    }
}
