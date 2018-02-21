import java.util.Scanner;

class HumanPlayer{
    int[][] board;
    int score;

    private static final String TURN_NOTIFICATION_MESSAGE = "It is your turn to play.\n";
    private static final String MOVE_PROMPT_MESSAGE = "Please play a move.\n";
    private static final String MOVE_INPUT_MESSAGE = "Please choose a valid move.\n";
    private static final String MOVE_PLAYED_MESSAGE = "Your move has been played.\n";

    private static final int HUMAN_PLAYER = 1;

    public HumanPlayer(){
        //Constructor
        board = new int[3][3];

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                board[i][j] = -1;
            }
        }
    }

    void showMessageToUser(String message){
        System.out.print(message);
    }

    IntegerTriple playMove(int[][] board){
        showMessageToUser(TURN_NOTIFICATION_MESSAGE);
        showMessageToUser(MOVE_PROMPT_MESSAGE);

        Scanner sc = new Scanner(System.in);

        int r,c;

        do{
            showMessageToUser(MOVE_INPUT_MESSAGE);
            r = sc.nextInt();
            c = sc.nextInt();
        }while(board[r][c]!=-1);

        updateBoardWithMove(new IntegerTriple(HUMAN_PLAYER, r, c));
        showMessageToUser(MOVE_PLAYED_MESSAGE);
        return new IntegerTriple(HUMAN_PLAYER, r, c);
    }

    void updateBoardWithMove(IntegerTriple move) {
        board[move.s()][move.t()] = move.f();
    }

    void showBoard(){
        int i,j;
        showMessageToUser("====================\n");
        showMessageToUser("HumanPlayer board\n");
        for(i=0; i<3; i++){
            for(j=0; j<3; j++){
                if(board[i][j] == -1)
                    System.out.print("-" + " ");
                else
                    System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
        showMessageToUser("====================\n");
    }
}