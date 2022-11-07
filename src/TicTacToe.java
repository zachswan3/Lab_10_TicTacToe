import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    final private static String[][] BOARD = new String[ROW][COL];

    public static void main(String[] args) {

        String player1 = "X";
        String player2 = "O";
        int rowMove;
        int colMove;
        Scanner pipe = new Scanner(System.in);
        boolean done = false;
        do {
            clearBoard();
            display();
            boolean won = false;
            boolean tied = false;
            done = false;
            boolean finished = false;
            boolean valid = false;
            String currentPlayer = player1;
            do{
                do {
                    rowMove = SafeInput.getRangedInt(pipe, "Enter the row value of your move", 1, 3);
                    rowMove = rowMove - 1;
                    colMove = SafeInput.getRangedInt(pipe, "Enter the column value of your move", 1, 3);
                    colMove = colMove - 1;
                    valid = isValidMove(rowMove,colMove);
                    if(!valid){
                        display();
                        System.out.println("That space was already filled. Please try again.");
                    }
                }while(!valid);
                BOARD[rowMove][colMove] = currentPlayer;
                display();
                won = isRowWin(currentPlayer);
                if(!won){
                    won = isColWin(currentPlayer);
                    if(!won){
                        won = isDiagonalWin(currentPlayer);
                        if(!won){
                            tied = isTie();
                            if(tied){
                                finished = true;
                            }
                        }
                    }
                }
                if(won){
                    System.out.println("Player " + currentPlayer + " has won!");
                    finished = true;
                }
                if(tied){
                    System.out.println("The game has tied...");
                }
                if(currentPlayer.equals(player1)){
                    currentPlayer = player2;
                }else{
                    currentPlayer = player1;
                }
            }while(!finished);
            done = SafeInput.getYNConfirm(pipe,"Would you like to play again? [Y/N]");
        }while(!done);
    }

    private static void clearBoard() {
        for (int row = 0; row < BOARD.length; row++) {
            for (int col = 0; col < BOARD[row].length; col++) {
                BOARD[row][col] = " ";
            }
        }
    }
    private static void display(){
        for(int row = 0; row < BOARD.length; row++){
            for(int col = 0; col < BOARD[row].length; col++){
                System.out.printf("|%2s ", BOARD[row][col]);
            }
            System.out.println("|");
        }
    }
    private static boolean isValidMove(int row, int col){
        return BOARD[row][col].equals(" ");
    }
    private static boolean isColWin(String currentPlayer){
        boolean retVal = false;
        for(int col = 0; col < BOARD.length; col++){
            if(BOARD[0][col].equals(currentPlayer) && BOARD[1][col].equals(currentPlayer) && BOARD[2][col].equals(currentPlayer)){
                retVal = true;
                break;
            }
        }
        return retVal;
    }
    private static boolean isRowWin(String currentPlayer){
        boolean retVal = false;
        for(int row = 0; row < BOARD.length; row++){
            if(BOARD[row][0].equals(currentPlayer) && BOARD[row][1].equals(currentPlayer) && BOARD[row][2].equals(currentPlayer)){
                retVal = true;
                break;
            }
        }
        return retVal;
    }
    private static boolean isDiagonalWin(String currentPlayer){
        boolean retVal = false;
        if(BOARD[1][1].equals(currentPlayer)){
            if(BOARD[0][0].equals(currentPlayer) && BOARD[2][2].equals(currentPlayer)){
                retVal = true;
            }else if(BOARD[2][0].equals(currentPlayer) && BOARD[0][2].equals(currentPlayer)){
                retVal = true;
            }
        }
        return retVal;
    }
    private static boolean isTie(){
        boolean retVal = true;
        for(int row = 0; row < BOARD.length; row++) {
            for (int col = 0; col < BOARD[row].length; col++) {
                if(BOARD[row][col].equals(" ")){
                    retVal = false;
                    break;
                }
            }
        }
        return retVal;
    }
}
