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
            boolean hasChanged = false;

            ArrayList<Domain> target = q.poll();

            //Checks if target is the rows or the columns.
            if (target == rowDomains) {
                isRow = true;
            }

            for (int i = 0; i < target.size(); i++) {

                Domain focus = target.get(i);

                if (isRow)
                {
                    if(searchDomains(focus, i, colDomains))
                    {
                        hasChanged = true;
                    }
                }
                else
                {
                    if(searchDomains(focus, i, colDomains))
                    {
                        hasChanged = true;
                    }
                }
            }

            //If domains have been altered, adds the perpendicular lines to the queue
            if (hasChanged) {
                if (isRow) {
                    System.out.println("Hello Col");
                    q.add(colDomains);
                } else {
                    System.out.println("Hello Row");
                    q.add(rowDomains);
                }
            }
        }
    }

    /**
     * Checks if the specified spot in the column domains has both true and false. If both are present, nothing happens to the domain of the target.
     * @param focus The domain the changes are focused on.
     * @param line Target line
     * @param lineDomains domains to compare against
     * @return True if changes are made, False otherwise
     */
    private boolean searchDomains(Domain focus, int line, ArrayList<Domain> lineDomains)
    {
        boolean hasChanged = false;

        for (int j = 0; j < lineDomains.size(); j++) {
            boolean hasTrue = false;
            boolean hasFalse = false;

            for (int k = 0; k < lineDomains.get(j).getDomSize(); k++) {
                if (lineDomains.get(j).getInstance(k)[line]) {
                    hasTrue = true;
                } else {
                    hasFalse = true;
                }

                if(hasTrue && hasFalse) {break;}
            }


            //If one is false, then changes are possible to the domains.
            if (hasTrue != hasFalse) {
                hasChanged = checkToRemove(focus, j, hasTrue);
                System.out.println(hasChanged);
            }
        }

        return hasChanged;
    }


    /**
     * Checks domains to see if anything can be removed.
     * @param focus The domains that are the focus of the removal.
     * @param line The column that is being looked at for comparison.
     * @param comparison To compare the location's variable aginst
     * @return True if there are changes, false if not
     */
    private boolean checkToRemove(Domain focus, int line, boolean comparison)
    {
        boolean hasChanged = false;

        for(int k = 0; k< focus.getDomSize(); k++)
        {
            if(focus.getDom().get(k)[line] != comparison)
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
            result += "Row " + i + "\n";
            result += rowDomains.get(i).toString() + " \n";
        }

        for(int i=0; i<colDomains.size(); i++)
        {
            result += "Col " + i + "\n";
            result += colDomains.get(i).toString() + " \n";
        }

        return result;
    }
}
