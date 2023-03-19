import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Domain {

    private ArrayList<boolean[]> dom; // The list of possible solutions for any given line
    private int size;


    //Main Constructor which takes a clue and line length and generates all possible solutions
    public Domain(PicrossPuzzle.PiClue pc, int size){
        dom = new ArrayList<>();
        this.size = size;

        boolean[] pos = new boolean[size];
        placeClue(pc, 0, 0, pos); // Generates all possible solutions, Recursively
    }

    //Deep-Copy
    public Domain(boolean[] instance) {
        dom = new ArrayList<boolean[]>();
        dom.add(instance);
        size = 1;
    }

    public boolean[] getInstance(int i){
        return dom.get(i);
    }

    public ArrayList<boolean[]> getDom(){
        return dom;
    }

    //Recursively loops by filling the first segment in every place it can go and calling itself on the remaining segments
    public void placeClue(PicrossPuzzle.PiClue pc, int cluesHandled, int last1, boolean[] possibility){
        if(cluesHandled >= pc.size()){
            dom.add(possibility);
            return;
        }
        int currentClue = pc.getClue(cluesHandled);

        //Find the smallest space the remaining clues can fit into
        int minSpacesRequired = -1;
        for(int i  = cluesHandled+1; i < pc.size(); i++){
            minSpacesRequired += pc.getClue(i) + 1;
        }
        if(minSpacesRequired == -1){
            minSpacesRequired = 0;
        }
        int lastCluePlacement = possibility.length - minSpacesRequired - currentClue;
        int space = 1;
        if(cluesHandled == 0){
            space = 0;
        }
        //System.out.println("Last1: " + last1 + " lastCluePlacement: " + lastCluePlacement);
        //Puts the current clue in every possible location and recurses
        for(int i = last1 + space; i <= lastCluePlacement; i++){
            boolean[] newPos = possibility.clone();
            newPos = fillInClue(i, currentClue, newPos);
            //System.out.println("Call PC" + i);
            placeClue(pc, cluesHandled+1, i + currentClue, newPos);
        }

    }

    // Loops through the next length spaces and marks them true
    public static boolean[] fillInClue(int start, int length, boolean[] arr){
        for(int i = start; i < start+length; i++){
            arr[i] = true;
        }
        return arr;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("{");
        for(int i = 0; i < dom.size(); i++){
            out.append(Arrays.toString(dom.get(i)));
            out.append(",\n");
        }
        out.replace(out.length()-1,out.length()-1, "}");
        return out.toString();
    }

    public int getDomSize() { return dom.size(); }

    public boolean[] removeInstance(int i){
        return dom.remove(i);
    }
}
