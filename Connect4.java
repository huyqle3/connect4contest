/* File: Connect4.java
 * Date: 4/28/13
 * Author: Huy Le(huyle333@bu.edu)
 * Michael Lyons (mnlyons@bu.edu)
 * Kevin Amorim (kramorim@bu.edu)
 * Class: CS 112, Spring 2013
 * Introduction: Literate programs that are understandable by human
 * Homework submitted using WebSubmit
 * Homework submitted under my name
 * Tested submitted code carefully with a unit test
 * Purpose: Connect4.java: You should provide a GUI which allows humans to play the game; the machine will play 
 * by calling the method Player.move(board,10). The human goes first. You can use my GUI as inspiration, but feel
 * free to do something else as long as it is thoughtful and useful and elegant. 
 * Related files: Player.java
 */

//import data
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.Color;

//Connect4.java
public class Connect4{    
    
    //private variables - counts how many spaces are left
    private static int spaces = 64;
    
    //private variables for the different colors I used
    private static Color backgroundColor = new Color(211, 211, 211);
    private static Color blue = new Color(110, 110, 255);
    private static Color winningBlue = new Color(0, 0, 250);
    private static Color red = new Color(255, 110, 110);
    private static Color winningRed = new Color(250, 0, 0);
    
    //private variables for the board array and button setup
    private static int[][] buttonBoard = new int[8][8];
    private static JButton[][] buttonSetup = new JButton[8][8];
    private static JLabel outcome = new JLabel("Playing...");
    
    //private variable that keeps track if the board is empty or not
    private static boolean boolean_empty = false;
    
    //main method calls on creating the game frame
    public static void main(String args[]){
        createGameFrame();
    }
    
    //createGameFrame creates the entire board
    private static void createGameFrame(){
        //frame = "Connect Four"; we create the game frame for Connect Four
        JFrame frame = new JFrame("Connect Four");
        //this size fits great on my computer
        frame.setSize(1000, 725);
        //setting the close function for the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setting the simple background color for the frame
        frame.setBackground(backgroundColor);
        
        //Setting the player opponent to a new instance
        Player14 opponent = new Player14();
        
        //Label Connect Four
        JLabel l1 = new JLabel("Connect Four");
        l1.setFont(new Font("HELVETICA", 1, 28));
        l1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black,2),
                                                        BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red,2),
                                                                                           BorderFactory.createLineBorder(Color.black,2)))); 
        l1.setForeground(Color.black);
        
        //Button for the Reset Button
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("HELVETICA", 1, 20));
        resetButton.setBackground(Color.black); 
        resetButton.setForeground(Color.white);
        
        //Button for the Quit Button
        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("HELVETICA", 1, 20));
        quitButton.setBackground(Color.black); 
        quitButton.setForeground(Color.white);
        
        //Label for the outcome - who wins? is it still running?
        outcome.setFont(new Font("HELVETICA", 1, 20));
        
        //must set the frame to be visible
        frame.setVisible(true);
        
        //the for loop calculates the buttons for all the spaces available
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++) {
                buttonSetup[row][col] = new JButton();
                JBox.setSize(buttonSetup[row][col], 65, 65);
                buttonSetup[row][col].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                buttonSetup[row][col].setBackground(Color.white);
            }
        }
        
        //Jbox body contains all the hbox buttons for every row by column 8 by 8; it contains the vspaces for the title, reset, outcome
        JBox body = JBox.vbox(0.5F, new Component[]{
            JBox.vspace(10), l1, JBox.vspace(10), 
                JBox.hbox(0.5F, new Component[]{buttonSetup[0][0], buttonSetup[0][1], buttonSetup[0][2], buttonSetup[0][3], buttonSetup[0][4], buttonSetup[0][5], buttonSetup[0][6], buttonSetup[0][7]}),
                JBox.hbox(0.5F, new Component[] { buttonSetup[1][0], buttonSetup[1][1], buttonSetup[1][2], buttonSetup[1][3], buttonSetup[1][4], buttonSetup[1][5],buttonSetup[1][6], buttonSetup[1][7] }), 
                JBox.hbox(0.5F, new Component[] { buttonSetup[2][0], buttonSetup[2][1], buttonSetup[2][2], buttonSetup[2][3], buttonSetup[2][4], buttonSetup[2][5], buttonSetup[2][6], buttonSetup[2][7] }),
                JBox.hbox(0.5F, new Component[] { buttonSetup[3][0], buttonSetup[3][1], buttonSetup[3][2], buttonSetup[3][3], buttonSetup[3][4], buttonSetup[3][5], buttonSetup[3][6], buttonSetup[3][7] }), 
                JBox.hbox(0.5F, new Component[] { buttonSetup[4][0], buttonSetup[4][1], buttonSetup[4][2], buttonSetup[4][3], buttonSetup[4][4], buttonSetup[4][5], buttonSetup[4][6], buttonSetup[4][7] }), 
                JBox.hbox(0.5F, new Component[] { buttonSetup[5][0], buttonSetup[5][1], buttonSetup[5][2], buttonSetup[5][3], buttonSetup[5][4], buttonSetup[5][5], buttonSetup[5][6], buttonSetup[5][7] }), 
                JBox.hbox(0.5F, new Component[] { buttonSetup[6][0], buttonSetup[6][1], buttonSetup[6][2], buttonSetup[6][3], buttonSetup[6][4], buttonSetup[6][5], buttonSetup[6][6], buttonSetup[6][7] }), 
                JBox.hbox(0.5F, new Component[] { buttonSetup[7][0], buttonSetup[7][1], buttonSetup[7][2], buttonSetup[7][3], buttonSetup[7][4], buttonSetup[7][5], buttonSetup[7][6], buttonSetup[7][7] }), 
                JBox.vspace(10), 
                JBox.hbox(0.5F,new Component[]{quitButton, JBox.hspace(100), resetButton, JBox.hspace(100), outcome}), 
                JBox.vspace(10)});
        
        //adding the frame to the body and setting it to true visibility
        frame.add(body);
        frame.setVisible(true);
        
        //setting the border of the body with a nice compound border
        body.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black,2),
                                                          BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black,2),
                                                                                             BorderFactory.createLineBorder(Color.black,2)))); 
        
        Player14 ai = new Player14();
        int player = 10;
        
        //the actual game board array
        int [][] board = new int[8][8];
        
        //setting up the eventqueue to be interactive; both listen to a reset and quit buttons
        JEventQueue events = new JEventQueue();
        events.listenTo(resetButton, "reset");
        events.listenTo(quitButton, "quit");
        
        //using a nested for loop to set an event for all the buttons that are created
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++){
                events.listenTo(buttonSetup[row][col], row + " " + col);
            }
        }
        //while true 
        while (true){
            //create event objects and get the names for the event
            EventObject event = events.waitEvent();
            String name = JEventQueue.getName(event);
            
            //if the name equals quit
            if(name.equals("quit")){
                //use System.exit(1) to get out of the program
                System.exit(1);
                //if the name equals reset
            }else if (name.equals("reset")) {
                //run the reset method
                reset();
                //else simply go through each of the board and perform the move
                boolean_empty = true;
            }else{
                int row = name.charAt(0) - '0';
                int col = name.charAt(2) - '0';
                
                //if the board does not equal to an empty space; simply continue
                if (buttonBoard[row][col] != 0) {
                    continue;
                }
                //the available space decreases by one
                spaces -= 1;
                
                //the buttonBoard at that slot becomes 10 which is you the player and becomes blue and opaque
                buttonBoard[row][col] = 10;
                buttonSetup[row][col].setBackground(blue);
                buttonSetup[row][col].setOpaque(true);
                
                //perform the animation after going down the column
                for (row += 1; (row < 8) && (buttonBoard[row][col] == 0); row++) {
                    try {
                        Thread.sleep(100L);
                    }catch (InterruptedException e){
                    }
                    //as the animation goes down, the row at the column returns to be empty
                    buttonBoard[(row - 1)][col] = 0;
                    //set the color of the preceding row back to the backgroundColor
                    buttonSetup[(row - 1)][col].setBackground(backgroundColor);
                    //it returns to not being opaque
                    buttonSetup[(row - 1)][col].setOpaque(false);
                    
                    //make the row at that column to red and to the player's number of 10
                    buttonBoard[row][col] = 10;
                    buttonSetup[row][col].setBackground(blue);
                    buttonSetup[row][col].setOpaque(true);
                }
                
            }
            
            //the player wins
            if (playerWins(10)) {
                //say that the player wins
                outcome.setText("Blues Wins! Horray!");
                //pause for a while
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                }
                //say that the game is resetting
                outcome.setText("Resetting...");
                try {
                    Thread.sleep(3000L); } catch (InterruptedException e) {
                    }
                    reset();
            }else{
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException e){
                }
                //the column will be the number referring to the Player.java's move function using the buttonBoard
                int col = opponent.move(buttonBoard);
                
                //when reset, let red go first instead
                if(boolean_empty){
                    Random r = new Random();
                    col = r.nextInt(2) + 2;  
                    boolean_empty = false;
                }
                
                //one available space decreases
                spaces -= 1;
                
                //this part of the code is simply a reiteration of the above part of the code except for opponent who
                //is red
                buttonBoard[0][col] = 1;
                buttonSetup[0][col].setBackground(red);
                buttonSetup[0][col].setOpaque(true);
                
                for (int row = 1; (row < 8) && (buttonBoard[row][col] == 0); row++) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                    }
                    buttonBoard[(row - 1)][col] = 0;
                    buttonSetup[(row - 1)][col].setBackground(backgroundColor);
                    buttonSetup[(row - 1)][col].setOpaque(false);
                    
                    buttonBoard[row][col] = 1;
                    buttonSetup[row][col].setBackground(red);
                    buttonSetup[row][col].setOpaque(true);
                }
                
                //this part of the code is simply a reiteration for the code describing red's winning
                if (playerWins(1)) {
                    outcome.setText("Red Wins! Huzzah!");
                    try {
                        Thread.sleep(3000L); 
                    } catch (InterruptedException e) {
                    }
                    outcome.setText("Resetting...");
                    try {
                        Thread.sleep(3000L); 
                    } catch (InterruptedException e) {
                    }
                    reset();
                }
                //implementation in case the game ends in a draw
                else if (spaces == 0) {
                    outcome.setText("Draw!");
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                    }
                    outcome.setText("Resetting...");
                    try {
                        Thread.sleep(3000L); 
                    } catch (InterruptedException e) {
                    }
                    reset();
                }
            }
        }
    }
    
    
    //creates buttons with certain characteristics to be used for the 8x8 grid
    private static JButton createButtonHelper(int bSize) {
        JButton q = new JButton(); JBox.setSize(q, bSize, bSize);
        q.setBorder(BorderFactory.createRaisedBevelBorder());
        q.setBackground(Color.white);
        return q;
        
    }
    
    //function that changes the color of the button
    private static int changeColor(int player, JButton btn){
        if (player == 10){
            btn.setBackground(Color.blue);
        }
        else {
            btn.setBackground(Color.red);
        }        
        return player = changePlayer(player);
        
    }
    
    //function that changes the player
    private static int changePlayer(int player){
        if (player == 10){
            return 1;
        }
        else{ 
            return 10;
        }
    }
    
    //function that calculates if the player wins by tracking down the four in a rows
    private static boolean playerWins(int player){
        //each nested loop calculates horizontal, vertical, and diagonals
        //the function using other methods to assist in this process
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 5; col++) {
                if (winCalc(buttonBoard, player, row, col, 0, 1)) {
                    showWin(buttonBoard, player, row, col, 0, 1);
                    return true;
                }
            }
        }
        
        //each nested loop calculates horizontal, vertical, and diagonals possibilites of winning
        //there is only a certain amount of combinations that a player will win in Connect Four
        for (int row = 0; row < 5; row++){
            for (int col = 0; col < 8; col++) {
                if (winCalc(buttonBoard, player, row, col, 1, 0)) {
                    showWin(buttonBoard, player, row, col, 1, 0);
                    return true;
                }
            }
        }
        
        //each nested loop calculates horizontal, vertical, and diagonals possibilites of winning
        for (int row = 3; row < 8; row++) {
            for (int col = 0; col < 5; col++) {
                if (winCalc(buttonBoard, player, row, col, -1, 1)) {
                    showWin(buttonBoard, player, row, col, -1, 1);
                    return true;
                }
            }
            
        }
        
        //each nested loop calculates horizontal, vertical, and diagonals possibilites of winning
        for (int row = 0; row < 5; row++){
            for (int col = 0; col < 5; col++){
                if (winCalc(buttonBoard, player, row, col, 1, 1)){
                    showWin(buttonBoard, player, row, col, 1, 1);
                    return true;
                }
            }
        }
        return false;
    }
    
    //the winCalc function helps the playerWins function to evaluate the board and see if the player has a four in a row
    private static boolean winCalc(int[][] board, int player, int row, int col, int rowUp, int colUp){
        for (int k = 0; k < 4; k++) {
            if (board[row][col] != player)
                return false;
            row += rowUp;
            col += colUp;
        }
        return true;
    }
    
    
    //the showWin function helps the playerWins function to evaluate the board and see if the player has a four in a row
    //by checking the colors and buttonSetup array
    private static void showWin(int[][] board, int player, int row, int col, int rowUp, int colUp){
        Color r = winningRed;
        if (player == 10) {
            r = winningBlue;
        }
        //sets the colors
        for (int k = 0; k < 4; k++) {
            buttonSetup[row][col].setBackground(r);
            buttonSetup[row][col].setOpaque(true);
            row += rowUp;
            col += colUp;
        }
    }
    
    //the reset button function resets the entire board
    //using two for loops. the function sets everything back to empty and the colors to the background color
    private static void reset(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                buttonBoard[i][j] = 0;
            }
        }
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                buttonSetup[i][j].setBackground(backgroundColor);
                buttonSetup[i][j].setOpaque(true);
            }
        }
        //sets the text back to playing...
        outcome.setText("Playing...");
        //sets the spaces to the original amount of 64
        spaces = 64;
    }
}
            
            
