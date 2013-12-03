/* File: Player14.java
 * Date: 4/28/13
 * Author: Huy Le(huyle333@bu.edu)
 * Michael Lyons (mnlyons@bu.edu)
 * Kevin Amorim (kramorim@bu.edu)
 * Class: CS 112, Spring 2013
 * Introduction: Literate programs that are understandable by human
 * Homework submitted using WebSubmit
 * Homework submitted under my name
 * Tested submitted code carefully with a unit test
 * Purpose: Player14.java:
 * For this problem, you are going to write a program to play a simple variation of a game called Connect 4. 
 * Unlike the usual version of Connect-4, this version will be played on an 8x8 board. 
 * The rules are simple: two players, Red and Blue, take turns coloring squares on an 8x8 board, and the first player 
 * to get a sequence of 4 (in a row, a column, or a diagonal) wins. You must write your program as a single class 
 * which accepts a board as input and returns a integer in the range 0..7 indicating the column of the move. 
 * Your program will always play as Blue, but either player may go first. 
 * Related files: Connect4.java
 */

//Out player14 class represents our player
import java.util.*;
public class Player14 implements PlayerClass{
    
    //Private variables for the maximum depth, maximum score, and minimum score
    private static int maxDepth = 8;
    private static int maxScore = 10000;
    private static int minScore = -10000;
    
    //Player piece value set to 10
    private static int player = 10;
    
    //AI piece value set to 1
    private static int ai = 1;
    
    //Practice board for main method
    private static int[][] board = new int[8][8];
    
    // eval - Main evaluation method, checks the board from the bottom
    // to the top, left to right, assigning the player a negative value
    // and the AI a positive value. Returns +||- 10000 in the case of a win.
    // Stops checking when it reaches the highest placed piece. Returns the
    // sum of the player's piece values and the AI's piece values.
    private static int eval(int[][] board, int player, int ai) {
        int value = 0;
        int height;
        for (int i = board.length - 1; i >= 0; i--) {
            height = i;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == player) {
                    if (posEval(board, i, j, player, ai) == 10000)
                        return -10000;
                    height = i + 1;
                    value -= posEval(board, i, j, player, ai);
                } else if (board[i][j] == ai) {
                    if (posEval(board, i, j, ai, player) == 10000)
                        return 10000;
                    height = i + 1;
                    value += posEval(board, i, j, ai, player);
                }
            }
            if (height == i) {
                break;
            }
        }
        return value;
    }
    
    
    // posEval - Checks the surroundings of a single piece. Stops checking in a given
    // direction when running into a wall or an opposing piece. Returns a value based on
    // the piece's surrounding empty spaces and amount of friendly pieces within
    // victory range. Returns 10000 in the case of 4 in a row.
    static int posEval(int[][] board, int row, int col, int turn, int opp) {
        
        int value = 0;
        int nextTo;
        
        if (col != 0) {
            nextTo = 0;
            for (int i = col - 1; i > col - 4; i--) {
                if (board[row][i] == opp) {
                    break;
                } else if (board[row][i] == turn) {
                    ++nextTo;
                } else {
                    ++value;
                    break;
                }
                if (i == 0) {
                    break;
                }
            }
            if (nextTo == 1) {
                value += 5;
            } else if (nextTo == 2) {
                value += 15;
            } else if (nextTo == 3) {
                value = 10000;
            }
        }
        if (value == 10000) {
            return value;
        }      
        
        if (col != 0 && row != 0) {
            nextTo = 0;
            int curRow = row;
            for (int i = col - 1; i > col - 4; i--) {
                curRow--;
                if (board[curRow][i] == opp) {
                    break;
                } else if (board[curRow][i] == turn) {
                    ++nextTo;
                } else {
                    ++value;
                    break;
                }
                if (i == 0 || curRow == 0) {
                    break;
                }
            }
            if (nextTo == 1) {
                value += 5;
            } else if (nextTo == 2) {
                value += 15;
            } else if (nextTo == 3) {
                value = 10000;
            }
        }
        if (value == 10000) {
            return value;
        }        
        
        if (row != 0) {
            nextTo = 0;
            for (int i = row - 1; i > row - 4; i--) {
                if (board[i][col] == opp) {
                    break;
                } else if (board[i][col] == turn) {
                    ++nextTo;
                } else {
                    ++value;
                    break;
                }
                if (i == 0) {
                    break;
                }
            }
            if (nextTo == 1) {
                value += 5;
            } else if (nextTo == 2) {
                value += 15;
            } else if (nextTo == 3) {
                value = 10000;
            }
        }
        if (value == 10000) {
            return value;
        }       
        
        if (col != 7 && row != 0) {
            nextTo = 0;
            int curRow = row;
            for (int i = col + 1; i < col + 4; i++) {
                curRow--;
                if (board[curRow][i] == opp) {
                    break;
                } else if (board[curRow][i] == turn) {
                    ++nextTo;
                } else {
                    ++value;
                    break;
                }
                if (i == 7 || curRow == 0) {
                    break;
                }
            }
            if (nextTo == 1) {
                value += 5;
            } else if (nextTo == 2) {
                value += 15;
            } else if (nextTo == 3) {
                value = 10000;
            }
        }
        if (value == 10000) {
            return value;
        }
        
        if (col != 7) {
            nextTo = 0;
            for (int i = col + 1; i < col + 4; i++) {
                if (board[row][i] == opp) {
                    break;
                } else if (board[row][i] == turn) {
                    ++nextTo;
                } else {
                    ++value;
                    break;
                }
                if (i == 7) {
                    break;
                }
            }
            if (nextTo == 1) {
                value += 5;
            } else if (nextTo == 2) {
                value += 15;
            } else if (nextTo == 3) {
                value = 10000;
            }
        }
        if (value == 10000) {
            return value;
        }
        
        // Commented out for effiency. These checks are redundant, but may have
        // value for accuracy.
        
//        if (col != 7 && row != 7) {
//            nextTo = 0;
//            int curRow = row;
//            for (int i = col + 1; i < col + 4; i++) {
//                curRow++;
//                if (board[curRow][i] == opp) {
//                    break;
//                } else if (board[curRow][i] == turn) {
//                    ++nextTo;
//                } else {
//                    ++value;
//                }
//                if (i == 7 || curRow == 7) {
//                    break;
//                }
//            }
//             if (nextTo == 1) {
//                value += 5;
//            } else if (nextTo == 2) {
//                value += 15;
//            } else if (nextTo == 3) {
//                value = 10000;
//            }
//        }
//        if (value == 10000) {
//            return value;
//        }
//        
//        if (col != 0 && row != 7) {
//            nextTo = 0;
//            int curRow = row;
//            for (int i = col - 1; i > col - 4; i--) {
//                curRow++;
//                if (board[curRow][i] == opp) {
//                    break;
//                } else if (board[curRow][i] == turn) {
//                    ++nextTo;
//                } else {
//                    ++value;
//                }
//                if (i == 0 || curRow == 7) {
//                    break;
//                }
//            }
//             if (nextTo == 1) {
//                value += 5;
//            } else if (nextTo == 2) {
//                value += 15;
//            } else if (nextTo == 3) {
//                value = 10000;
//            }
//        }
//        if (value == 10000) {
//            return value;
//        }
//        
//        if (row != 7) {
//            nextTo = 0;
//            for (int i = row + 1; i < row + 4; i++) {
//                if (board[i][col] == opp) {
//                    break;
//                } else if (board[i][col] == turn) {
//                    ++nextTo;
//                } else {
//                    ++value;
//                }
//                if (i == 7) {
//                    break;
//                }
//            }
//             if (nextTo == 1) {
//                value += 5;
//            } else if (nextTo == 2) {
//                value += 15;
//            } else if (nextTo == 3) {
//                value = 10000;
//            }
//        }
//        if (value == 10000) {
//            return value;
//        }    
        
        return value;
    }
    
    //method calculates the row of the move based on what column j
    static int rowFunction(int[][] board, int j){
        //i represents the rows
        for (int i = 7; i >= 0; i--){
            if (board[i][j] == 0){
                return i; //return the row of the column that you are looking for that is empty
            }
        }
        //not found
        return -1;
    }
    
    
    //inspiration taken from the Enumeration.java
    //basic idea: place is empty: make a move for piece: continue to find the minMax: find which value is bigger: undo
    //the move and continue
    static int minMax(int[][] board, int player1, int depth, int alpha, int beta){
        //first priority is to assign the value of the board using the eval function
        int valueOfBoard = eval(board, player, ai);
        
        //boolean flag keeps track whether or not the board is already full because if it is full, then the value of board
        //remains consistent with whatever it is at the time the board is full
        boolean boolean_noMoreMoves = true;
        for(int i = 0; i < 8; i++){
            //checks the column of the board to see if it's assigned 0 which also means it is empty, so noMoreMoves would be false
            if(board[0][i] == 0){
                boolean_noMoreMoves = false;
            }
        }
        
        
        //finds the child that gave the root its value
        //if the value of the board is the max score or min score, return whatever eval function calculated of the board
        //if the depth of the tree is the max depth, return whatever eval function calculated of the board
        //if there are no more moves, return whatever eval function calculated of the board
        if((boolean_noMoreMoves) || (depth == maxDepth) || (valueOfBoard == minScore) || (valueOfBoard == maxScore)){
            return valueOfBoard;
        }
        
        //if the depth is even: moves on even-numbered moves, and the other moves on odd number of moves
        if (depth % 2 == 0){
            //starts with the worst value; player wants min value
            int value = -10000;
            //for statement goes through the columns
            for (int col = 0; col < 8; col++)
                //if the board at the column is equal to 0 which means that if it is still empty
                if (board[0][col] == 0){
                //rowFunction finds the row of the move
                int row = rowFunction(board, col);
                //board where the place is empty, leave the board at the row and col = the opponent's piece; drop a piece
                //into the column
                board[row][col] = 1;
                //returns the higher value going down a depth of one recursively until finds the maximum value
                //if the value is greater than the max, then the value is the new maximum value and return that value
                value = Math.max(value, minMax(board, player1, depth + 1, alpha, beta));
                //remove the piece that was dropped into the board at that column
                board[row][col] = 0;
                //alpha-beta pruning; sees which value is higher
                alpha = Math.max(alpha, value);
                //avoid useless nodes
                if (beta < alpha){
                    break;
                }
                //else leave the row at col empty
                board[row][col] = 0;
            }
            return value;
        }
        //value is best score if the value is not the minimum score
        //computer wants maximum score
        int value = 10000;
        //going through each column
        for (int col = 0; col < 8; col++)
            //if the board at row 0, column
            if (board[0][col] == 0) {
            //the row equals the row of that column
            int row = rowFunction(board, col);
            //set the player piece on the board; test the piece by dropping it into the board at the column and row
            board[row][col] = 10;
            //using recursion, find the maximum value from the maximum depth
            //if the value is a maximum, then use that new value
            value = Math.min(value, minMax(board, player1, depth + 1, alpha, beta));
            //rempty that area
            board[row][col] = 0;
            //avoid useless nodes
            beta = Math.min(beta, value);
            //cuts off alpha-beta because beta is less than alpha; ignores remaining moves 
            if (beta < alpha){
                break;
            }
        }
        //return the value
        return value;
    }
    
    //calculates the move done with just the board
    //Source: lecture notes
    public int move(int[][] board){
        int max = -10000;
        //best move for the computer AI
        int best = -1;
        
        //goes through each column
        for (int col = 0; col < 8; col++){
            //if the board at that row of 0, column is empty, then perform the rowFunction to find the row
            if (board[0][col] == 0) {
                int row = rowFunction(board, col);
                //put in the piece
                board[row][col] = 1;
                //test the value for the minMax and set it to that value
                int value = minMax(board, player, 1, -10000, 10000);
                //re-empty that slot
                board[row][col] = 0;
                
                //if the value is bigger than the max, then the new best move is at that column
                if (value > max) {
                    best = col;
                    max = value;
                }
            }
        }
        //if the best move is out of range; fill in
        if(best < 0 || best > 7){
            for(int col = 0; col < 8; col++){
                for(int row = 0; row < 8; row++){
                    if(board[row][col] == 0){
                        return col;
                    }
                }
            }
        }
        //return
        return best;
    }
    
    //calculates the move done with parameters of the board and player
    //Source: lecture notes / piazza
    private int move(int[][] board, int player1) {
        //the opponent is signed as 1
        if (player1 == 1) {
            return move(board);
        }
        //creates a new board
        int[][] columnsAndRows = new int[8][8];
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                //if the piece is in that row and column the opponents or yours, switch it up
                if (board[r][c] == 1){
                    columnsAndRows[r][c] = 10;
                }else if (board[r][c] == 10){
                    columnsAndRows[r][c] = 1;
                }
            }
        }
        //return the board, columnsAndRows
        return move(columnsAndRows);
    }
    
    //unit test for Player.java
    public static void main(String[] args){
        Player14 P = new Player14();
        System.out.println(P.eval(board, player, ai));
        
        //A test of the eval function. Prints the board and eval value.
        board[7][2] = ai;
        board[7][3] = player;
        board[7][4] = player;
        board[7][5] = ai;
        board[6][3] = player;
        board[6][4] = ai;
        board[5][3] = ai;
        board[5][4] = player;
        board[7][0] = ai;
        board[7][1] = player;
        board[6][1] = ai;
        board[6][5] = player;
        board[7][6] = ai;
        board[4][3] = player;
        board[5][5] = player;
        board[6][2] = ai;
        board[4][5] = player;
        board[3][5] = ai;
        board[4][4] = player;
        board[7][6] = ai;
        board[6][6] = player;
        board[3][3] = ai;
        board[5][6] = player;
        board[4][6] = ai;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 1) {
                    System.out.print(board[i][j] + "  ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }   
            System.out.println();
        }
        System.out.println(eval(board, player, ai));
        
    }
}
            
