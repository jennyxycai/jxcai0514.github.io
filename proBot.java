import javafx.util.Pair;

public class proBot extends basicBot {
    private Pair<Integer, Integer> moveLoc;
    public proBot(char n){
        super(n);
        moveLoc = new Pair<>(-1, -1);
    }

    public int minMax (board b, player player){
        int tempMax = Integer.MIN_VALUE;
        int tempMin = Integer.MAX_VALUE;
        Pair<Integer, Integer> tempMove = new Pair<>(-1, -1);
        char[][] brd = b.getBoard();

        //if terminal state on board, return appropriate score
        if (b.getWinner()!=' '){
            if (b.getWinner()=='X')
                return 10;
            else
                return -10;
        }
        else if (b.getNumMoves() == 9)
            return 0;

        //for all open spots on the board
        for (int r =0; r<3; r++){
            for (int c = 0; c<3; c++){
                if (brd[r][c]!= 'X' && brd[r][c]!='O'){

                    //move to an open spot
                    b.move(player, r, c);

                    //bot's turn
                    if (player.getName() == 'X'){
                        //will play through the entire scenario
                        int score = minMax(b, new player('O'));

                        if (score >= tempMax){
                           // System.out.println(score + " " + r + ", " + c);
                            tempMax = score;
                            tempMove = new Pair<>(r,c);
                        }
                    }

                    //human's turn
                    else {
                        int score = minMax(b,this);
                        //System.out.println(score + " " + r + ", " + c);
                        tempMin = Math.min(tempMin, score);
                    }

                    //retract the player's move from the board
                    b.retractMove(player, r, c);

                }
            }
        }

        moveLoc = tempMove;

        if (player.getName() == 'X')
            return tempMax;

        return tempMin;
    }

    @Override
    public void move (board b){
        minMax(b, this);
        b.move(this, moveLoc.getKey(), moveLoc.getValue());
    }

}
