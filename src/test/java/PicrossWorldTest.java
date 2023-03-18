import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class PicrossWorldTest {

    @Test
    void AC3ForwardingTest() throws FileNotFoundException {
<<<<<<< Updated upstream
        int size = 5;
        PicrossPuzzle pz = new PicrossPuzzle(size,size);
=======
//        PicrossPuzzle pz = new PicrossPuzzle(size,size);
>>>>>>> Stashed changes
//        PicrossPuzzle pz = new PicrossPuzzle("picross10x10.csv");
        PicrossPuzzle pz = new PicrossPuzzle("picross5x5.csv");

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
            int y = pw.getRowDomains().get(i).getDomSize();
            if(x != y){
                System.out.println("works");
                assert true;
                return;
            }
            else {
                System.out.println(y);
            }

            System.out.println("PW col: " + i);
            x = pw.getColDomains().get(i).getDomSize();
            System.out.println(x);
            System.out.println("PreAC3 col: " + i);
            y = pw.getColDomains().get(i).getDomSize();
            if(x != y){
                System.out.println("works");
                assert true;
                return;
            }
            else {
                System.out.println(y);
            }
            System.out.println();

        }
        fail();
    }
}