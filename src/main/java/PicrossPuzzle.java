import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PicrossPuzzle {

    private boolean[][] board; // Could be int??
    private ArrayList<PiClue> colClues;
    private ArrayList<PiClue> rowClues;

    public PicrossPuzzle(int rows, int cols){
        board = new boolean[rows][cols];
        Random random = new Random();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                board[i][j] = random.nextBoolean();
            }
        }

        colClues = new ArrayList<>();
        rowClues = new ArrayList<>();
        fillClues();

    }

    //Deep-copy
    public PicrossPuzzle(PicrossPuzzle old){
        board = new boolean[old.board.length][old.board[0].length];
        for(int i  = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = old.board[i][j];
            }
        }

        for(int i = 0; i < old.rowClues.size(); i++){
            rowClues.add(new PiClue(old.getRowClue(i)));
        }
        for(int i = 0; i < old.colClues.size(); i++){
            colClues.add(new PiClue(old.getColClue(i)));
        }
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

    public PiClue getColClue(int i){
        return colClues.get(i);
    }

    public PiClue getRowClue(int i){
        return rowClues.get(i);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j]){
                    System.out.print(1 + ", ");
                }
                else{
                    System.out.print(0 + ", ");
                }
            }
            System.out.println();
        }
        out.append("Rows: ").append(rowClues).append("\n");
        out.append("Cols: ").append(colClues);

        return out.toString();
    }

    private void fillClues(){
        //Rows
        for(int i = 0; i < board.length; i++){
            int counter = 0;
            ArrayList<Integer> count = new ArrayList<>();
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j]){
                    counter++;
                }
                else{
                    if(counter != 0){
                        count.add(counter);
                        counter = 0;
                    }
                }
            }
            if(counter != 0){
                count.add(counter);
                counter = 0;
            }
            rowClues.add(new PiClue(count));
        }

        //Cols
        for(int j = 0; j < board[0].length; j++){
            int counter = 0;
            ArrayList<Integer> count = new ArrayList<>();
            for(int i = 0; i < board.length; i++){
                if(board[i][j]){
                    counter++;
                }
                else{
                    if(counter != 0){
                        count.add(counter);
                        counter = 0;
                    }
                }
            }
            if(counter != 0){
                count.add(counter);
                counter = 0;
            }
            colClues.add(new PiClue(count));
        }
    }


    class PiClue {

        private ArrayList<Integer> clues;

        public PiClue(ArrayList<Integer> clues){
            this.clues = clues;
        }

        public PiClue(PiClue old){
            this.clues = (ArrayList<Integer>) old.clues.clone();
        }

        public ArrayList<Integer> getClues(){
            return clues;
        }

        @Override
        public String toString() {
            return "PiClue{" +
                    "clues=" + clues +
                    '}';
        }
    }
}
