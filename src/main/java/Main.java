public class Main {

    public static void main(String[] args) {
        System.out.println("Hi Lo World");

        PicrossPuzzle board = new PicrossPuzzle(10,10);
        System.out.println(board.toString());

        try {
            board = new PicrossPuzzle("picross10x10.csv");
            System.out.println(board.toString());
            assert true;

        }
        catch(Exception e){
            System.out.println(e.toString());
            assert false;
        }
    }
}
