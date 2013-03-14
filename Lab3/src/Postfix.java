import java.io.*;

/**
 * This program reads in a postfix expression consisting of two
 * one character operands followed by either the `+' or `-'
 * operator.  The operands are hexidecimal digits (lower case
 * only).  The program outputs the expression as an infix expression
 * with base 16 operands and its result as a base 10 integer.
 */
public class Postfix
{
    public static void main (String args[]) throws IOException
    {
        //	--------------------------------------------------------------
        boolean debug = false;	    // Set this to true to get debugging info.
        //	--------------------------------------------------------------
        char	operand1;	// First operand as a char
        char	operand2;	// Second operand as a char
        char	operator;	// Operator
        int 	operand1Value;	// First operand converted to an int
        int 	operand2Value;	// Second operand converted to an int
        int 	result;		// Result of expression.
	
	    BufferedReader input = new BufferedReader( 
	        new InputStreamReader( System.in ) );
        String  postfix;
	
        //-------------------
        //  Input expression.
        //-------------------
        do 
        {
        	System.out.printf ( "Enter 3-character postfix expression: " );
        
        	postfix = input.readLine();
        }
        while (postfix.length() < 3 || postfix.length() > 3);
        
        operand1 = postfix.charAt(0);
        operand2 = postfix.charAt(1);
        operator = postfix.charAt(2);

        //----------------------------------
        //  Convert first operand to an int.
        //----------------------------------
        if ( operand1 >= '0' && operand1 <= '9' )
        { 
            operand1Value = operand1 - '0';

            if ( debug )
                System.out.printf( "op1 0-9: %c=%d\n", operand1, operand1Value );
        } 
        else if ( operand1 >= 'a' && operand1 <= 'f' )
        { 
            operand1Value = 10 + operand1 - 'a';
            
            if ( debug )
                System.out.printf( "op1 a-z: %c=%d\n", operand1, operand1Value );
        } 
        else
        {
            operand1Value = 0;
            System.out.printf ( "Program Aborted: operand1 `%c' not `0'-`9' or `a'-`f'.\n", operand1 );
            System.exit(1);
        }

        //----------------------------------
        //  Convert second operand to an int.
        //----------------------------------
        if ( operand2 >= 'a' && operand2 <= 'f' )
        { 
            operand2Value = 10 + operand2 - 'a';
            
            if ( debug )
                System.out.printf( "op2 a-z: %c=%d\n", operand2, operand2Value );
        } 
        else if ( operand2 >= '0' && operand2 <= '9' )
        { 
            operand2Value = operand2 - '0';
            
            if ( debug )
                System.out.printf( "op2 0-9: %c=%d\n", operand2, operand2Value );
        } 
        else
        {
            operand2Value = 0;

            System.out.printf ( "Program Aborted: operand2 `%c' not `0'-`9' or `a'-`f'.\n", operand2 );
            System.exit(2);
	    }

        //-------------------
        //  Perform operation.
        //-------------------
        if ( operator == '+' )
        { 
            result = operand1Value + operand2Value;
            
            if ( debug )
                System.out.printf( "plus: %d %d %d\n", operand1Value, operand2Value, result );
        } 
        else if ( operator == '-' )
        { 
            result = operand1Value - operand2Value;
		
		    if ( debug )
		        System.out.printf( "plus: %d %d %d\n", operand1Value, operand2Value, result );
        } 
        else
        {
            result = 0;
            System.out.printf ( "Program Aborted: operator `%c' not `+' or `-'.\n", operator );
            System.exit(2);
        }

        //---------------
        //  System.out.println result.
        //---------------
        System.out.printf ( "%c %c %c = %d\n", operand1, operator, operand2, result) ;
    }
    
}
