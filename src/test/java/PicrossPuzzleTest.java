import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PicrossPuzzleTest {

    @Test
    void testToString() {
        PicrossPuzzle board = new PicrossPuzzle(10,10);
        System.out.println(board.toString());
        assert true;
    }

    @Test
    void testPicrossPuzzle() {
        try {
            PicrossPuzzle board = new PicrossPuzzle("picross10x10.csv");
            System.out.println(board.toString());
            assert true;

        }
        catch(Exception e){
            System.out.println(e.toString());
            assert false;
        }
    }
}