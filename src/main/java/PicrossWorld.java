import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PicrossWorld {

    private PicrossPuzzle puzzle;
    private ArrayList<Domain> colDomains;
    private ArrayList<Domain> rowDomains;


    public PicrossWorld(PicrossPuzzle pz){
        //Add the board itself
        puzzle = pz;

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
        Queue<ArrayList<Domain>> q = new PriorityQueue<>();

        q.add(rowDomains);
        q.add(colDomains);

        while(!q.isEmpty())
        {
            boolean isRow = false;
            Boolean hasChanged = false;

            ArrayList<Domain> target = q.poll();

            //Checks if target is the rows or the columns.
            if(target == rowDomains)
            {
               isRow = true;
            }

            for(int i=0; i<target.size(); i++)
            {
                boolean hasTrue = false;
                boolean hasFalse = false;

                Domain focus = target.get(i);

                //Checks if the specified spot in the column domains has both true and false. If both are present, nothing happens to the domain of the target.
                for(int j=0; j<colDomains.size(); j++)
                {
                    if(colDomains.get(j).getInstance(j)[i])
                    {
                        hasTrue = true;
                    }
                    else
                    {
                        hasFalse = true;
                    }

                    //If one is false, then changes are possible to the domains.
                    if(!hasTrue || !hasFalse)
                    {
                        //If true was what was present in the col domain
                        if(hasTrue)
                        {
                            ArrayList<boolean[]> domain = focus.getDom();
                            ArrayList<boolean[]> newList = new ArrayList<>();

                            for(int k = 0; k<domain.size(); k++)
                            {
                                if(domain.get(k)[j])
                                {
                                    newList.add(domain.get(k));
                                    hasChanged = true;
                                }
                            }

                            focus.setDom(newList);
                        }
                        else //If false was what was present in the col domain
                        {
                            ArrayList<boolean[]> domain = focus.getDom();
                            ArrayList<boolean[]> newList = new ArrayList<>();

                            for(int k = 0; k<domain.size(); k++)
                            {
                                if(!domain.get(k)[j])
                                {
                                    newList.add(domain.get(k));
                                    hasChanged = true;
                                }
                            }

                            focus.setDom(newList);
                        }
                    }
                }
            }

            //If domains have been altered, adds the perpendicular lines to the queue
            if(hasChanged)
            {
                if(isRow)
                {
                    q.add(colDomains);
                }
                else
                {
                    q.add(rowDomains);
                }
            }
        }
    }
}
