import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DomainTest {

    @Test
    void placeClueTest() {
        ArrayList<Integer> tempClues = new ArrayList<>();
        tempClues.add(1);
        tempClues.add(2);
        tempClues.add(3);
        PicrossPuzzle.PiClue pc = new PicrossPuzzle.PiClue(tempClues);
        Domain d = new Domain(pc, 10);
        System.out.println(d);


    }

    @Test
    void fillInClueTest() {

        boolean[] solution = new boolean[]{false, false, false, true, true, false};
        boolean[] test = new boolean[]{false, false, false, false, false, false};

        test = Domain.fillInClue(3, 2, test);

        for(int i = 0; i < solution.length; i++){
            if(solution[i] != test[i]){
                assert false;
            }
        }
        assert true;
    }

    @Test
    void domainGetInstanceTest() {

        ArrayList<Integer> tempClues = new ArrayList<>();
        tempClues.add(1);
        tempClues.add(2);
        tempClues.add(3);
        PicrossPuzzle.PiClue pc = new PicrossPuzzle.PiClue(tempClues);

        Domain d = new Domain(pc, 10);
        System.out.println(d);
        System.out.println("1st Instance");
        System.out.println(Arrays.toString(d.getInstance(0)));
        d.removeInstance(1);
        System.out.println(Arrays.toString(d.getInstance(8)));

        assert true;
    }
}