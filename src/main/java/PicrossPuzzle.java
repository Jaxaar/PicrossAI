import java.util.ArrayList;

public class PicrossPuzzle {

    private boolean[][] board; // Could be int??
    private ArrayList<PiClue> colDescriptors;
    private ArrayList<PiClue> rowDescriptors;

    public PicrossPuzzle(int rows, int cols){

    }

    //Deep-copy
    public PicrossPuzzle(PicrossPuzzle old){

    }

    public boolean[][] getBoard() {
        return board;
    }

    public int getRows(){
        return board.length;
    }

    public int getCols(){
        return board[0].length;
    }

}
