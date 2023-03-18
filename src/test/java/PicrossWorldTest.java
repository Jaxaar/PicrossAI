import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class PicrossWorldTest {

    @Test
    void AC3ForwardingTest() throws FileNotFoundException {
        int size = 10;
        PicrossPuzzle pz = new PicrossPuzzle(size,size);
//        PicrossPuzzle pz = new PicrossPuzzle("picross10x10.csv");
        PicrossWorld pw = new PicrossWorld(pz);
//        System.out.println(pw.toString());
        PicrossWorld preAC3 = new PicrossWorld(pw);
        pw.AC3Forwarding();

        for(int i=0; i<size -1 ; i++)
        {
            System.out.println();
        }

//        System.out.println(pw.toString());

        for(int i = 0; i < size; i++){
            System.out.println("PW row: " + i);
            int x = pw.getRowDomains().get(i).getDomSize();
            System.out.println(x);
            System.out.println("PreAC3 row: " + i);
            int y = pw.getRowDomains().get(i).getDomSize();
            if(x != y){
                System.out.println(x);
                assert true;
            }
            else {
                System.out.println(y);
            }
        }
        assert false;
    }
}