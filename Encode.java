// File: Encode.java  Author: Wayne Snyder  Date: 5/15/13
// Purpose: Prints out the hashCode() of a single BU ID; 
//   To use, replace fake ID below with your ID, in the same format. 

import java.util.Arrays; 

public class Encode {
   
   private static String ID = "U08-29-9200"; 
   
   public static void main( String [] args ) {
      
      System.out.println( ID.hashCode() );
      
   }
}