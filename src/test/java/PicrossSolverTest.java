import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PicrossSolverTest {

    @Test
    void solveFullPuzzleTest() throws FileNotFoundException {
        PicrossPuzzle pz = new PicrossPuzzle(10,10);
//        PicrossPuzzle pz = new PicrossPuzzle("picross5x5.csv");

        try{
            boolean[][] board = PicrossSolver.solveFullPuzzle(pz);
//            System.out.println(Arrays.deepToString(board));
            System.out.println(pz);
            System.out.println(PicrossSolver.boolBoardToString(board));

            if (PicrossSolver.checkAnswer(pz, board)){
                assert true;
            }
            else{
                fail();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Test
    void solveBacktrack() {
    }

    @Test
    void checkAnswer() {
    }

    @Test
    void translateToArray() {
    }
}