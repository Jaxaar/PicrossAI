import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PicrossWorldTest {

    @Test
    void AC3ForwardingTest() {
        PicrossPuzzle pz = new PicrossPuzzle(10,10);
        PicrossWorld pw = new PicrossWorld(pz);
        System.out.println(pw);
        pw.AC3Forwarding();
        System.out.println(pw);
    }
}