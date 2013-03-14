import java.io.*;

/**
 * BufferedReaderExample
 *
 * @author Ryan Carter
 * @version 01/07/2013
 * Acknowledgements: None
 */
public class InputType
{
	
	public static String input;
	public static String type;
	
	public static boolean isInt (String input)
	{
		int placeHolder = 0;
		
		for (int i = 0; i < input.length(); i++)
		{
			if (input.indexOf(input.charAt(i)) >= 0
					&& input.indexOf(input.charAt(i)) <= 9) 
			{
				placeHolder = placeHolder + 1;
			}
		}
		if ( placeHolder == input.length())
		{
			type = "int";
		}
		return placeHolder == input.length();
	} // isInt method
	
	public static boolean isChar (String input)
	{
		if (input.length() > 1) {
			return false;
		}
		
		if (input.indexOf(input.charAt(0)) > 9)
		{
			type = "char";
		}
		return input.indexOf(input.charAt(0)) > 9;
	} // isChar Method

	
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader
			(System.in));

		System.out.print("Please enter something: ");

		input = reader.readLine();
		
		isChar(input);
		isInt(input);
		
		System.out.println( "You entered: " + input + " " + type );

	} // method main
 	
} // class BufferedReaderExample