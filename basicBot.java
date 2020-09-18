import javafx.util.Pair;

import java.util.Random;

public class basicBot extends player{

    public basicBot (char n){
        super(n);
    }

    public Pair<Integer, Integer> canWin (char[][]b,  player p){
        char n = p.getName();

        //check backwards diagonal
        int count = 0;
        int open = -1;
        for (int i =0; i<3; i++){
            if (b[i][2-i] == n)
                count ++;
            else if (b[i][2-i] != p.getOpposite())
                open = i;
        }

        if (count == 2 && open!=-1)
            return new Pair<>(open, 2-open);

        //check if can win in a column
        for (int col = 0; col < 3; col++){
            count = 0;
            int openRow = -1;

            for (int row = 0; row<3; row++){
                if (b[row][col] == n) count++;
                else if (b[row][col] != p.getOpposite()) openRow = row;
            }
            if (count == 2 && openRow!=-1)
                return new Pair<>(openRow, col);

        }

        //check if can win in a row
        for (int row = 0; row < 3; row++){
            count = 0;
            int openCol = -1;

            for (int col = 0; col<3; col++){
                if (b[row][col] == n) count++;
                else if (b[row][col] != p.getOpposite()) openCol = col;
            }
            if (count == 2 && openCol != -1)
                return new Pair<>(row, openCol);

        }

        //check forward diagonal
        count = 0;
        open = -1;
        for (int r =0; r<3; r++){
            if (b[r][r] == n) count++;
            else if (b[r][r] != p.getOpposite()) open = r;
        }

        if (count == 2 && open!=-1)
            return new Pair<>(open, open);


        return null;
    }

    public void move(board b){
        if (canWin(b.getBoard(), this) != null){
            Pair<Integer, Integer> p = canWin(b.getBoard(), this);
            b.move(this, p.getKey(), p.getValue());
        }
        else if (canWin(b.getBoard(), new player('O'))!= null){
            Pair<Integer, Integer> p = canWin(b.getBoard(), new player('O'));
            b.move(this, p.getKey(), p.getValue());
        }
        else
            randomMove(b);
    }

    private void  randomMove(board b){
        Random r = new Random();
        int row = r.nextInt(3);
        int col = r.nextInt(3);

        while (b.getBoard()[row][col] != 'X' && b.getBoard()[row][col] != 'O'){
            row = r.nextInt(3);
            col = r.nextInt(3);
        }

        b.move(this, row, col);
    }

}
