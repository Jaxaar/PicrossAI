import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class PicrossWorldTest {

    @Test
    void AC3ForwardingTest() {
        PicrossPuzzle pz = new PicrossPuzzle(10,10);
        PicrossWorld pw = new PicrossWorld(pz);
        //System.out.println(pw.toString());
        pw.AC3Forwarding();

        for(int i=0; i<10; i++)
        {
            System.out.println();
        }

        //System.out.println(pw.toString());
    }
}