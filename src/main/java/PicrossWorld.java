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

        public Domain(PicrossPuzzle.PiClue pc){
            boolean domainsRemain = true;
            while(domainsRemain){

            }
            System.out.println("Domain Constructor Not Written YET!!!");
        }

        public boolean[] getInstance(int i){
            return dom.get(i);
        }

    }




}
