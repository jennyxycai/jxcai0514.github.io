import javafx.util.Pair;
import java.util.HashMap;
public class board {
    private char[][] board;
    private int numMoves;
    private char winner;
    HashMap<Integer, Pair<Integer, Integer>> h;

    public board(){
        numMoves = 0;
        board = new char[][]{{'1','2','3'},{'4','5','6'}, {'7','8','9'}};
        winner = ' ';
        h = new HashMap<Integer, Pair<Integer, Integer>>(){{
            put(1, new Pair<>(0,0));
            put(2, new Pair<>(0,1));
            put(3, new Pair<>(0,2));

            put(4, new Pair<>(1,0));
            put(5, new Pair<>(1,1));
            put(6, new Pair<>(1,2));

            put(7, new Pair<>(2,0));
            put(8, new Pair<>(2,1));
            put(9, new Pair<>(2,2));
        }};
    }

    public char[][] getBoard (){
        return board;
    }

    public String printBoard(){
        StringBuilder s = new StringBuilder();
        for (int r = 0; r<3; r++){
            for (int c = 0 ; c<3; c++){
                s.append(board[r][c]+" ");
            }
            s.append("\n");
        }

        return s.toString();
    }

    public int getNumMoves() {
        return numMoves;
    }

    public Pair<Integer, Integer> convertNumToCoordinates (int n){
        return h.get(n);
    }

    /**
     * assume all input is valid + the user doesn't go on a square that's already been chosen
     * @param p, the player
     * @param r, the row to move to
     * @param c, the column to move to
     * After making the move to coordinates r,c the board checks if a win is possible
     * @return winner
     */
    public char move(player p, int r, int c){
        board[r][c] = p.getName();
        numMoves ++;

        //checking columns
        for (int col = 0; col<3; col++){
            if (board[r][col] != p.getName())
                break;
            if (col == 2)
                winner = p.getName();

        }

        // check rows
        for (int row = 0; row <3; row++){
            if (board[row][c] != p.getName())
                break;
            if (row ==2)
                winner = p.getName();
        }

        // check diag
        if (r==c){
            for (int i = 0; i<3; i++){
                if (board[i][i]!= p.getName()) break;
                if (i == 2)
                    winner = p.getName();
            }
        }


        //check anti-diag
        if (r+c == 2){
            for (int i =0; i<3; i++){
                if (board[i][2-i] != p.getName()) break;
                if (i == 2)
                    winner = p.getName();
            }
        }

        return winner;
    }

    public void retractMove (player p, int r, int c){
        HashMap<Pair<Integer, Integer>, Integer> reverseH = new HashMap<>();
        for (int key: h.keySet()){
            reverseH.put(h.get(key), key);
        }

        int numRetracted =  reverseH.get(new Pair<>(r, c));
        board[r][c] = (char ) (numRetracted + '0'); //typecast to char
        numMoves--;
        winner = ' ';
    }

    public char getWinner(){
        return winner;
    }
}
