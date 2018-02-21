class ComputerPlayer{
    public static final int PRINCIPLE_DIAG_CONSTANT = 0;
    public static final int SECONDARY_DIAG_CONSTANT = 1;
    public static final int COMPUTER_PLAYER = 0;

    int[][] board;

    public ComputerPlayer(){
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
        int r=0, c=0;
        IntegerTriple move;

        for(r=0; r<3; r++){
            for(c=0; c<3; c++){
                if(board[r][c] == -1) {
                    move = new IntegerTriple(COMPUTER_PLAYER, r, c);
                    updateBoardWithMove(move);
                    return move;
                }
            }
        }
        return new IntegerTriple(-2 , 0 , 0);
    }

    void updateBoardWithMove(IntegerTriple move) {
        board[move.s()][move.t()] = move.f();
    }

    int miniMax(int[][] givenBoard, int computerPerspective){

        return 0;
    }

    int twoInARow(int[][] board, int i){
        int score = 0;
        if(board[i][0] == -1){
            if(board[i][1] == 0)
                score += 10;
            else
                score -= 10;
        }
        else
        {
            if(board[i][0] == 0)
                score += 10;
            else
                score -=10;
        }
        return score;
    }

    int twoInACol(int[][] board, int i){
        int score = 0;
        if(board[0][i] == -1){
            if(board[1][i] == 0)
                score += 10;
            else
                score -= 10;
        }
        else
        {
            if(board[0][i] == 0)
                score += 10;
            else
                score -=10;
        }
        return score;
    }

    int twoInADiag(int[][] board, int diag){
        int score = 0;
        if(diag == 0){
            if(board[0][0] == -1) {
                if (board[1][1] == 0)
                    score += 10;
                else
                    score -= 10;
            }
            else
            {
                if(board[0][0] == 0)
                    score += 10;
                else
                    score -= 10;
            }
        }
        return score;
    }

    int evaluate(int[][] board){
        int score = 0;

        for(int i=0; i<3; i++){
            if(((board[i][0] == board[i][1]) && (-1 == board[i][2])) || ((board[i][0] == board[i][2]) && (-1 == board[i][1])) || ((board[i][2] == board[i][1]) && (-1 == board[i][0])) ){
                score += twoInARow(board, i);
            }

            if(((board[i][0] == board[i][1]) && (-1 == board[i][2])) || ((board[i][0] == board[i][2]) && (-1 == board[i][1])) || ((board[i][2] == board[i][1]) && (-1 == board[i][0])) ){
                score += twoInACol(board, i);
            }
        }

        if( ((board[0][0] == board[1][1]) && (-1 == board[2][2])) || ((board[0][0] == board[2][2]) && (-1 == board[1][1])) || ((board[2][2] == board[1][1]) && (-1 == board[1][1])) ){
            score += twoInADiag(board, PRINCIPLE_DIAG_CONSTANT);
        }

        if( ((board[0][2] == board[1][1]) && (-1 == board[2][0])) || ((board[2][0] == board[1][1]) && (-1 == board[0][2])) || ((board[0][2] == board[2][0]) && (board[1][1] == -1)) ){

            score += twoInADiag(board, SECONDARY_DIAG_CONSTANT);
        }
        return score;
    }

    void showBoard(){
        int i,j;
        showMessageToUser("====================\n");
        showMessageToUser("ComputerPlayer board\n");
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