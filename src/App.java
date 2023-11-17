import java.util.Arrays;

public class App {

    private static final int GRID_SIZE = 9;

    private static boolean isInRow(int[][] board, int numberToFind, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == numberToFind) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInColumn(int[][] board, int numberToFind, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == numberToFind) {
                return true;
            }

        }
        return false;
    }

    private static boolean isInBox(int[][] board, int numberToFind, int row, int column) {
        int miniRow = row - row % 3;
        int miniColumn = column - column % 3;

        for (int i = miniRow; i < miniRow + 3; i++) {
            for (int j = miniColumn; j < miniColumn + 3; j++) {
                if (board[i][j] == numberToFind) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValid(int[][] board, int numberToFind, int row, int column) {
        return !isInBox(board, numberToFind, row, column) && !isInColumn(board, numberToFind, column)
                && !isInRow(board, numberToFind, row);
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

     
  private static boolean solveBoard(int[][] board) {
    for (int row = 0; row < GRID_SIZE; row++) {
      for (int column = 0; column < GRID_SIZE; column++) {
        if (board[row][column] == 0) {
          for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
            if (isValid(board, numberToTry, row, column)) {
              board[row][column] = numberToTry;
              
              if (solveBoard(board)) {
                return true;
              }
              else {
                board[row][column] = 0;
              }
            }
          }
          return false;
        }
      }
    }
    return true;
  }
  
  
  

    public static void main(String[] args) throws Exception {

        String[] datosTexto = {
                "5 3 0 0 7 0 0 0 0",
                "6 0 0 1 9 5 0 0 0",
                "0 9 8 0 0 0 0 6 0",
                "8 0 0 0 6 0 0 0 3",
                "4 0 0 8 0 3 0 0 1",
                "7 0 0 0 2 0 0 0 6",
                "0 6 0 0 0 0 2 8 0",
                "0 0 0 4 1 9 0 0 5",
                "0 0 0 0 8 0 0 7 9"
        };

        int[][] board = new int[datosTexto.length][datosTexto.length];
        
        

        for (int i = 0; i < datosTexto.length; i++) {
            String[] datosComoTexto = datosTexto[i].split(" ");

            for(int j = 0; j<datosComoTexto.length; j++){
                board[i][j] = Integer.parseInt(datosComoTexto[j]);
             }
        }
         

          printBoard(board);

         
     if (solveBoard(board)) {
        System.out.println("Solved successfully!");
      }
      else {
        System.out.println("Unsolvable board :(");
      }
         
      printBoard(board);

    }

}
