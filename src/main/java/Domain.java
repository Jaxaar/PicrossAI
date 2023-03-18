import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Domain {

    private ArrayList<boolean[]> dom;
    private int size;

    //Could use a recursive algorithm that repeatedly places it at all possible spots

    public Domain(PicrossPuzzle.PiClue pc, int size){
        dom = new ArrayList<>();
        size = this.size;

        boolean[] pos = new boolean[10];
        placeClue(pc, 0, 0, pos);

//            int initStart = 0;  //Need to cycle through each clue number and every possible spot.
//            boolean[] rowInstance = new boolean[size];
//            for(int i=0; i<pc.size(); i++) {
//                rowInstance = formInstance(pc.getClue(i), initStart, rowInstance);
//            }
        //System.out.println("Domain Constructor Not Written YET!!!");
    }

    public Domain(boolean[] instance) {
        dom.add(instance);
        size = 1;
    }


    public boolean[] getInstance(int i){
        return dom.get(i);
    }

    public ArrayList<boolean[]> getDom(){
        return dom;
    }

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
