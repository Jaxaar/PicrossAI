import java.util.ArrayList;

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

    //ONLY USE IN THE CONSTRUCTOR, Sets Domains = to all possibilites given the puzzle
    private void generateDomains(){

    }




    public void AC3Forwarding(){

    }
}
