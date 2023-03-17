import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class PicrossWorldTest {

    @Test
    void AC3ForwardingTest() throws FileNotFoundException {
        PicrossPuzzle pz = new PicrossPuzzle(10,10);
//        PicrossPuzzle pz = new PicrossPuzzle("picross10x10.csv");
        PicrossWorld pw = new PicrossWorld(pz);
//        System.out.println(pw.toString());
        PicrossWorld preAC3 = new PicrossWorld(pw);
        pw.AC3Forwarding();

        for(int i=0; i<10; i++)
        {
            System.out.println();
        }

//        System.out.println(pw.toString());

        for(int i = 0; i < 10; i++){
            System.out.println("PW row: " + i);
            System.out.println(pw.getRowDomains().get(i).getDomSize());
            System.out.println("PreAC3 row: " + i);
            System.out.println(preAC3.getRowDomains().get(i).getDomSize());
        }
    }
}