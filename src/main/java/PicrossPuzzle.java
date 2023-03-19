import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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

    public PicrossPuzzle(String csvFileName) throws FileNotFoundException {
        Scanner scn = new Scanner(new File(csvFileName));
        scn.useDelimiter(",\\s*");

        int rows = Integer.parseInt(scn.next());
        int cols = Integer.parseInt(scn.next());

        board = new boolean[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(Integer.parseInt(scn.next()) == 0){
                    board[i][j] = false;
                }
                else{
                    board[i][j] = true;
                }
            }
        }

        colClues = new ArrayList<>();
        rowClues = new ArrayList<>();
        fillClues();
    }

    //Deep-copy
    public PicrossPuzzle(PicrossPuzzle old){
        board = new boolean[old.board.length][old.board[0].length];
        colClues = new ArrayList<>();
        rowClues = new ArrayList<>();
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
        //Get the sizes of the max number of clues for appropriate spacing
        int maxColClues = 0;
        for(PiClue pc: colClues){
            maxColClues = Math.max(pc.size(), maxColClues);
        }
        int maxRowClues = 0;
        for(PiClue pc: rowClues){
            maxRowClues = Math.max(pc.size(), maxRowClues);
        }

        out.append(getColClueString(maxColClues,maxRowClues));

        // Print Board
        for(int i = 0; i < board.length; i++){
            out.append(getRowClueString(maxRowClues, i));
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j]){
                    out.append("X").append(" ");
                }
                else{
                    out.append("O").append(" ");
                }
            }
            out.append("\n");
        }

        return out.toString();
    }

    private String getColClueString(int maxColClues, int maxRowClues){
        StringBuilder out = new StringBuilder();

        for(int i = 0; i < maxColClues; i++){
            out.append(" ".repeat(maxRowClues*2 + 1)).append("|");
            for(int j = 0; j < colClues.size(); j++) {
                PiClue clue = colClues.get(j);
                if (clue.size() - maxColClues + i >= 0){
                    out.append(clue.getClues().get(clue.size() - maxColClues + i));
                }
                else {
                    out.append(" ");
                }
                out.append(" ");
            }

            out.append("\n");
        }
        out.append("_".repeat((maxRowClues*2 + 1))).append("|").append("_".repeat(colClues.size()*2)).append("\n");

        return out.toString();
    }

    private String getRowClueString(int maxRowClues, int row){
        String format = "%" + ((maxRowClues)*2) + "s |";
        if(maxRowClues == 0){
            format = "%s |";
        }

        return String.format(format, getRowClue(row));
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


    static class PiClue {

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

        public int getClue(int i){
            return clues.get(i);
        }

        public int size(){
            return clues.size();
        }

        @Override
        public String toString() {
            StringBuilder out = new StringBuilder();
            for(int i = 0; i< clues.size(); i++){
                out.append(clues.get(i)).append(" ");
            }

            return out.toString();
        }
    }
}
