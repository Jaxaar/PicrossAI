import java.util.ArrayList;

public class PicrossWorld {

    private PicrossPuzzle puzzle;
    private ArrayList<Domain> colDomains;
    private ArrayList<Domain> rowDomains;


    public PicrossWorld(PicrossPuzzle pz){

    }

    //Deep-copy
    public PicrossWorld(PicrossWorld wld){

    }

    //ONLY USE IN THE CONSTRUCTOR, Sets Domains = to all possibilites given the puzzle
    private void generateDomains(){

    }




    public void AC3Forwarding(){

    }





    class Domain{

        private ArrayList<boolean[]> dom;

        public Domain(PicrossPuzzle.PiClue pc, int size){
            boolean domainsRemain = true;
            while(domainsRemain){ //This part still doesn't work;
                int initStart = 0;  //Need to cycle through each clue number and every possible spot.
                boolean[] rowInstance = new boolean[size];
                for(int i=0; i<pc.size(); i++) {
                    rowInstance = formInstance(pc.getClue(i), initStart, rowInstance);
                }
            }
            System.out.println("Domain Constructor Not Written YET!!!");
        }

        public boolean[] getInstance(int i){
            return dom.get(i);
        }

        public boolean[] formInstance(int clueSize, int start, boolean[] instance) {
            if (clueSize + start >= instance.length) {
                return instance; //If it can't be done, return it for now.
            }

            for (int i = start; i<(clueSize+start); i++) {
                instance[i] = true;
            }
            return instance;
        }
    }
}
