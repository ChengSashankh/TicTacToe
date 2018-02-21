import java.util.Scanner;

class GamePlay{

    int score;
    int turnCounter;
    int[][] board;
    int allCellsFull;
    HumanPlayer humanPlayer;
    ComputerPlayer computerPlayer;
    IntegerTriple currentMove;

    private static final String GAME_START_MESSAGE = "Do you want to start the game?(y/n) : ";
    private static final String HUMAN_START_MESSAGE = "Human player will start the game\n";
    private static final String COMPUTER_START_MESSAGE = "Computer will start the game\n";
    private static final String GAME_END_MESSAGE = "The game has ended\n";
    private static final String GAME_WINNER_MESSAGE = "The game was won by: ";
    private static final String HUMAN_PLAYER_NAME = "puny human.\n";
    private static final String COMPUTER_PLAYER_NAME = "Sashankh's bot.\n";
    private static final String GAME_DRAWN_MESSAGE = "This game was drawn.\n";

    private static final int HUMAN_PLAYER = 1;
    private static final int COMPUTER_PLAYER = 0;

    public GamePlay(){
        board = new int[3][3];
        score = 0;
        allCellsFull = 0;
        turnCounter = 0;

        computerPlayer = new ComputerPlayer();
        humanPlayer = new HumanPlayer();

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                board[i][j] = -1;
            }
        }

    }

    void showMessageToUser(String message){
        System.out.print(message);
    }

    void setFirstPlayer(){
        showMessageToUser(GAME_START_MESSAGE);
        Scanner sc = new Scanner(System.in);

        if(sc.next().charAt(0) == 'y'){
            showMessageToUser(HUMAN_START_MESSAGE);
            turnCounter = HUMAN_PLAYER;
        }
        else{
            showMessageToUser(COMPUTER_START_MESSAGE);
            turnCounter = COMPUTER_PLAYER;
        }
    }

    boolean allMovesPlayed(){
        return (allCellsFull == 1);
    }

    IntegerTriple gameWon(){
        for(int i=0; i<3; i++){
            if((board[i][0] == board[i][1]) && (board[i][1] == board[i][2]) && (board[i][0] != -1))
                return new IntegerTriple(1,board[i][0], -1);

            if((board[0][i] == board[1][i]) && (board[1][i] == board[2][i]) && (board[0][i] != -1))
                return new IntegerTriple(1,board[0][i], -1);
        }

        if((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) && (board[0][0] != -1))
            return new IntegerTriple(1,board[1][1], -1);

        if((board[0][2] == board[1][1]) && (board[1][1] == board[2][0]) && (board[0][2] != -1))
            return new IntegerTriple(1,board[1][1], -1);

        return new IntegerTriple(0,-1,-1);
    }

    void playRound(){
        if(allMovesPlayed())
        {
            showGameResult();
            System.exit(0);
        }

        if(turnCounter%2 == HUMAN_PLAYER) {
            currentMove = humanPlayer.playMove(board);
            computerPlayer.updateBoardWithMove(currentMove);
        }
        else {
            currentMove = computerPlayer.playMove(board);
            humanPlayer.updateBoardWithMove(currentMove);
        }

        if(currentMove.f() == -2) {
            showMessageToUser("Error! Computer played invalid move.\n");
            showMessageToUser("Exiting game\n");
            System.exit(-1);
        }

        updateBoardWithNewMove();

        turnCounter++;

        if(turnCounter == 9)
            allCellsFull = 1;

        humanPlayer.showBoard();
        computerPlayer.showBoard();
        showBoard();
    }

    void updateBoardWithNewMove(){
        board[currentMove.s()][currentMove.t()] = currentMove.f();
    }

    void showGameResult(){
        showMessageToUser(GAME_END_MESSAGE);
        IntegerTriple result = gameWon();

        if(result.f() == 1){
            showMessageToUser(GAME_WINNER_MESSAGE);

            if(result.s() == HUMAN_PLAYER)
                showMessageToUser(HUMAN_PLAYER_NAME);
            else
                showMessageToUser(COMPUTER_PLAYER_NAME);
        }
        else
        {
            showMessageToUser(GAME_DRAWN_MESSAGE);
        }
    }

    void showBoard(){
        int i,j;
        showMessageToUser("====================\n");
        showMessageToUser("Gameplay board\n");
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

