import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PicrossPuzzleTest {

    @Test
    void testToString() {
        PicrossPuzzle board = new PicrossPuzzle(10,10);
        System.out.println(board.toString());
        assert true;
    }
}