import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class PicrossWorldTest {

    @Test
    void AC3ForwardingTest() throws Exception {

        PicrossPuzzle pz = new PicrossPuzzle(10,10);

//        PicrossPuzzle pz = new PicrossPuzzle("picross10x10.csv");
//        PicrossPuzzle pz = new PicrossPuzzle("picross5x5.csv");

        System.out.println(pz);

        int size = pz.getCols();


        PicrossWorld pw = new PicrossWorld(pz);
        PicrossWorld preAC3 = new PicrossWorld(pw);
        pw.AC3Forwarding();

        for(int i=0; i<size -1 ; i++)
        {
            System.out.println();
        }

//        System.out.println(preAC3.toString());
        System.out.println(pw.toString());

        for(int i = 0; i < size; i++){
            System.out.println("PW row: " + i);
            int x = pw.getRowDomains().get(i).getDomSize();
            System.out.println(x);
            System.out.println("PreAC3 row: " + i);
            int y = preAC3.getRowDomains().get(i).getDomSize();
            System.out.println(y);
            if(x != y){
//                System.out.println("works");
//                assert true;
//                return;
            }
            else {
//                System.out.println(y);
            }

            System.out.println("PW col: " + i);
            x = pw.getColDomains().get(i).getDomSize();
            System.out.println(x);
            System.out.println("PreAC3 col: " + i);
            y = preAC3.getColDomains().get(i).getDomSize();
            System.out.println(y);

            if(x != y){
//                System.out.println("works");
//                assert true;
//                return;
            }
            else {
//                System.out.println(y);
            }
            System.out.println();

        }
        try {
            boolean[][] board = PicrossSolver.translateToArray(pw.getRowDomains(), pw.getColDomains());
            System.out.println(PicrossSolver.boolBoardToString(board));
        }
        catch(Exception e){
            System.out.println(pw.getRowDomains());
            System.out.println("No Solution found: See Backtracking");
        }
        System.out.println("END");
//        fail();
    }
}