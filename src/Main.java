public class Main {
    public static void main(String[] args){
        GamePlay gamePlay = new GamePlay();
        gamePlay.setFirstPlayer();
        while(gamePlay.gameWon().f() != 1){
            gamePlay.playRound();
        }
    }
}
