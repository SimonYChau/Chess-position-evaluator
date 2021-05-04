package Evaluator;

public class SimpleEvaluator {
  
  public static int[][] whiteToBlackTable(int[][] table) {
    int[][] new_table = new int[Values.boardSize][Values.boardSize];
    for(int i = 0; i < table.length / 2; i++)
    {
        int[] temp = table[i];
        new_table[i] = table[table.length - i - 1];
        new_table[table.length - i - 1] = temp;
    }
    return new_table;
  }

  public static boolean isEndGame(char[][] chessBoard) {
    
    int queenNum = 0;
    
    for (int row = 0; row < Values.boardSize; row++) {
      for (int col = 0; col < Values.boardSize; col++) {
        if (chessBoard[row][col] == 'q' || chessBoard[row][col] == 'Q') {
          queenNum++;
        }
        if (queenNum > 0) {
          return false;
        }
      }
    }
    return true;
  }
  
  public static int whiteEvaluation(char[][] chessBoard) {
    int evaluationTotal = 0;
    for (int row = 0; row < Values.boardSize; row++) {
      for (int col = 0; col < Values.boardSize; col++) {
        if (chessBoard[row][col] == 'p') {
          evaluationTotal += Values.pawnValue;
          evaluationTotal += Values.pawnPositionTable[row][col];
        } else if (chessBoard[row][col] == 'n') {
          evaluationTotal += Values.knightValue;
          evaluationTotal += Values.knightPositionTable[row][col];
        } else if (chessBoard[row][col] == 'b') {
          evaluationTotal += Values.bishopValue;
          evaluationTotal += Values.bishopPositionTable[row][col];
        } else if (chessBoard[row][col] == 'r') {
          evaluationTotal += Values.rookValue;
          evaluationTotal += Values.rookPositionTable[row][col];
        } else if (chessBoard[row][col] == 'q') {
          evaluationTotal += Values.queenValue;
          evaluationTotal += Values.queenPositionTable[row][col];
        } else if (chessBoard[row][col] == 'k') {
          evaluationTotal += Values.kingValue;
          if (isEndGame(chessBoard)) {
            evaluationTotal += Values.kingPositionTableEndGame[row][col];
          } else {
            evaluationTotal += Values.kingPositionTableMidGame[row][col];
          }
        }
      }
    }
    return evaluationTotal;
  }
 
  public static int blackEvaluation(char[][] chessBoard) {
    int evaluationTotal = 0;
    for (int row = 0; row < Values.boardSize; row++) {
      for (int col = 0; col < Values.boardSize; col++) {
        if (chessBoard[row][col] == 'P') {
          evaluationTotal += Values.pawnValue;
          evaluationTotal += whiteToBlackTable(Values.pawnPositionTable)[row][col];
        } else if (chessBoard[row][col] == 'N') {
          evaluationTotal += Values.knightValue;
          evaluationTotal += whiteToBlackTable(Values.knightPositionTable)[row][col];
        } else if (chessBoard[row][col] == 'B') {
          evaluationTotal += Values.bishopValue;
          evaluationTotal += whiteToBlackTable(Values.bishopPositionTable)[row][col];
        } else if (chessBoard[row][col] == 'R') {
          evaluationTotal += Values.rookValue;
          evaluationTotal += whiteToBlackTable(Values.rookPositionTable)[row][col];
        } else if (chessBoard[row][col] == 'Q') {
          evaluationTotal += Values.queenValue;
          evaluationTotal += whiteToBlackTable(Values.queenPositionTable)[row][col];
        } else if (chessBoard[row][col] == 'K') {
          evaluationTotal += Values.kingValue;
          if (isEndGame(chessBoard)) {
            evaluationTotal += whiteToBlackTable(Values.kingPositionTableEndGame)[row][col];
          } else {
            evaluationTotal += whiteToBlackTable(Values.kingPositionTableMidGame)[row][col];
          }
        }
      }
    }
    return evaluationTotal;
  }
  
  public static void main(String[] args) {
    char[][] chessBoard = 
      {{'R', 'N', 'B', 'K', 'Q', 'B', 'N', 'R'},
      {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
      {'-', '-', '-', '-', '-', '-', '-', '-'},
      {'-', '-', '-', '-', '-', '-', '-', '-'},
      {'-', '-', '-', '-', '-', '-', '-', '-'},
      {'-', '-', '-', '-', '-', '-', '-', '-'},
      {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
      {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'}};
    
    int whiteEvaluation = whiteEvaluation(chessBoard);
    int blackEvaluation = blackEvaluation(chessBoard);
    if (isEndGame(chessBoard)) {
      System.out.println("We have an end game");
    } else {
      System.out.println("We do not have an end game");
    }
    if (whiteEvaluation == blackEvaluation) {
      System.out.println("White and Black have the same evaluation");
    } else if (whiteEvaluation > blackEvaluation) {
      System.out.println("White is better");
    } else {
      System.out.println("Black is better");
    }
    System.out.println("White evaluation: " + whiteEvaluation);
    System.out.println("Black evaluation: " + blackEvaluation);
  }
}
