import java.util.Scanner;
import javafx.util.Pair;
public class Driver {
    private static void play (){

        Scanner s = new Scanner(System.in);
        board b = new board();
        player human = new player('O');

        System.out.print("Which bot would you like to play against (1 = basic, 2 = pro): ");
        basicBot bot;
        if (s.nextLine().equals("1"))
            bot = new basicBot('X');
        else  bot = new proBot('X');
        System.out.println(b.printBoard());

        //begin playing

        do{
            //human moves
            System.out.print("Enter your move (1-9): ");
            Pair<Integer, Integer> p = b.convertNumToCoordinates(s.nextInt());
            b.move(human, p.getKey(), p.getValue());
            System.out.println(b.printBoard());

            //check if human has won
            if (b.getWinner() != ' '){
                System.out.println(b.getWinner() +" wins!");
                break;
            }
            //draw will happen when the human makes the last move
            if (b.getNumMoves() == 9) break;

            //bot moves
            System.out.println("Bot will now move.");
            bot.move(b);
            System.out.println(b.printBoard());

            //check if bot has won
            if (b.getWinner() != ' '){
                System.out.println(b.getWinner() +" wins!");
                break;
            }

        } while (b.getNumMoves()<9);

        if (b.getNumMoves() == 9 && b.getWinner()==' ')
            System.out.println("There is a draw.");
    }
    public static void main(String[] args) {

        play();
    }
}
