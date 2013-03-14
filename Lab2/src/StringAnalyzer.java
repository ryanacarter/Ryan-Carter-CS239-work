import java.io.*;
import java.util.*;

public class StringAnalyzer {
	
	public final String VOWELS = "aeiou";
	public final String CHARS = "abcdefghijklmnopqrstuvwxyz";
	public final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";
	public final String PUNCTUATION = "\",./?><;':[]{}|+=_-)(*&^%$#@!~`\\";
	private String input;
	private int numOfWords;
	private int numOfVowels;
	private int numOfChars;
	private int numOfConst;
	private int numOfPunc;
	
	/**
	 * The driver method is user to control the flow of the program.
	 * 
	 * @throws IOException
	 */
	public void driver() throws IOException
	{
		userInput();
		setNumWords();
		setNumVowels();
		setNumChars();
		setNumConst();
		setNumPunc();
		userOutput();
	} // method driver

	/**
	 * The setNumChars method is used to set the number of characters that are
	 * in the String input that was created by the user not including the spaces.
	 */
	public void setNumChars()
	{
		int counter = 0;
		
		for(int i = 0; i < input.length(); i++)
		{
			for (int j = 0; j < CHARS.length(); j++)
			{
				if (input.charAt(i) == CHARS.charAt(j))
				{
					counter++;
				} // end if
				
			} // end nested for
			
		} // end for loop
		
		numOfChars = counter;
		
	} // method setNumChars
	
	/**
	 * The setNumConst method is used to set the numOfConst variable to the
	 * correct number of constants that are held within the String variable
	 * input.
	 */
	public void setNumConst()
	{
		int counter = 0;
		
		for(int i = 0; i < input.length(); i++)
		{
			for (int j = 0; j < CONSONANTS.length(); j++)
			{
				if (input.charAt(i) == CONSONANTS.charAt(j))
				{
					counter++;
				} // end if
				
			} // end nested for
			
		} // end for loop
		
		numOfConst = counter;
	} // method setNumConst
	
	/**
	 * The setNumPunc is a method that is used to set the number of
	 * punctuation marks that are in the user's input String.
	 */
	public void setNumPunc()
	{
		int counter = 0;
		
		for(int i = 0; i < input.length(); i++)
		{
			for (int j = 0; j < PUNCTUATION.length(); j++)
			{
				if (input.charAt(i) == PUNCTUATION.charAt(j))
				{
					counter++;
				} // end if
				
			} // end nested for
			
		} // end for loop
		
		numOfPunc = counter;
	} // method setNumPunc
	
	/**
	 * The setNumWords method takes in the user input and uses the
	 * StringTokenizer to the the number of words that are in the input 
	 * phrase.
	 */
	public void setNumWords()
	{
		StringTokenizer words = new StringTokenizer( input );
		
		int counter = 0;
		
		while(words.hasMoreTokens() )
		{
			words.nextToken();
			counter++;
		
		} // end while
		
		numOfWords = counter;
	} // method setNumWords
	
	/**
	 * the setNumVowels method looks at the input variable and returns the
	 * number of vowels that the String contains.
	 */
	public void setNumVowels()
	{
		int counter = 0;
		
		for(int i = 0; i < input.length(); i++)
		{
			for (int j = 0; j < VOWELS.length(); j++)
			{
				if (input.charAt(i) == VOWELS.charAt(j))
				{
					counter++;
				} // end if
				
			} // end nested for
			
		} // end for loop
		
	numOfVowels = counter;
		
	} // method setNumVowels
	
	/**
	 * The userInput method is used to collect the input from the user and
	 * save that String to the class variable "input".
	 * 
	 * @throws IOException
	 */
	public void userInput() throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader
				(System.in));
		
		System.out.print("Enter a prase: ");
		
		input = reader.readLine().toLowerCase();
		
	} // method userInput
	
	/**
	 * The userOutput method takes the variables from the class and returns
	 * them to the screen for users readability.
	 * 
	 * @throws IOException
	 */
	public void userOutput() throws IOException
	{
		System.out.println("The phrase you entered has\n1: " + numOfWords
				+ " words\n2: " + numOfChars + " Characters\n3: "
				+ numOfVowels + " vowels\n4: " + numOfConst + " Consonats\n5: "
				+ numOfPunc + " Punctuation Marks");
		
	} // method userOutput
	
	
	
	
	/***************************** static methods*****************************/

	//-------------------------------------------------------------------------
	// main
	//
	// "ignition" for the class - This is used to create an instance of the
	// BufferedReader class and the call to the correct methods.
	//-------------------------------------------------------------------------
	public static void main(String[] args) throws IOException
	{
		StringAnalyzer one = new StringAnalyzer();
		
		one.driver(); // Kick starts the class
		
	} // end main

} // end StringAnalyzer Class
