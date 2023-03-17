import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PicrossSolverTest {

    @Test
    void solveFullPuzzleTest(){
        PicrossPuzzle pz = new PicrossPuzzle(10,10);
        try{
            System.out.println(Arrays.deepToString(PicrossSolver.solveFullPuzzle(pz)));
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