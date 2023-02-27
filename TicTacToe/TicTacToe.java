import java.util.Scanner;

public class TicTacToe {

    private String[][] board;
    private final int size = 3;
    private int turn;
    private String winner;
    private String player;
    private static int gamesPlayed = 0;

    public TicTacToe() {
        board = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "-";
            }
        }
        gamesPlayed += 1;
    }
    public void gameCount() {
        System.out.println(gamesPlayed);
    }

    public void playGame() {
        System.out.println("Welcome to Tic-Tac-Toe! If you ever want to quit, simply enter '-1' as an X or Y position.");
        printBoard();
        while (winner == null) {
            playRound();
        }
        System.out.println("The winner is: " + winner + "!!! Thanks for playing!");
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void hasThree(int column, int row, String player) {
        int colCount = 0;
        int rowCount = 0;
        int diagonalCount = 0;
        int sideDiagCount = 0;
        for (int i = 0; i < size; i++) {
            if (board[i][column] == player) {
                colCount += 1;
            }
            if (board[row][i] == player) {
                rowCount += 1;
            }
        }
        if (column == row) {
            for (int i = 0; i < size; i++) {
                if (board[i][i] == player) {
                    diagonalCount += 1;
                }
            }
        }
        if (column + row == size - 1) {
            for (int i = 0; i < size; i++) {
                if (board[i][size - 1 - i] == player) {
                    sideDiagCount += 1;
                }
            }
        }
        if (rowCount == size || colCount == size || diagonalCount == size || sideDiagCount == size) {
            if (player == "X") {
                winner = "Player 1";
            } else {
                winner = "Player 2";
            }
        }

    }
    public void playRound() {
        if (turn % 2 == 0) {
            player = "X";
            System.out.println("It is Player 1's Turn.");
        } else {
            player = "O";
            System.out.println("It is Player 2's Turn.");
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter an X position:");
        int column = scan.nextInt();
        System.out.println("Enter a Y position:");
        int row = scan.nextInt();
        if (column == -1 || row == -1) {
            winner = "Nobody! It's a draw!";
        } else if ((column > 2 || column < 0) || (row > 2 || row < 0) || board[row][column] != "-") {
            System.out.println("Not a valid position! Either out of bounds or already filled. Try again.");
            printBoard();
            playRound();
        } else {
            board[row][column] = player;
            printBoard();
            turn += 1;
            hasThree(column, row, player);
        }
    }
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
}
