public class player {
    private char name;

    public player(char n){
        name = n;
    }

    public char getName() {
        return name;
    }

    public char getOpposite(){
        if (name == 'X') return  'O';
        else  return 'X';
    }
}
