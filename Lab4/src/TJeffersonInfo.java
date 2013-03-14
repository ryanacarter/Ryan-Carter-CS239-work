import java.io.*;

public class TJeffersonInfo {

	private String longLine;
	private String[] text;
	
	public TJeffersonInfo() throws IOException
	{
		longLine = incomingLongString();
		breakString();
		
	}
	
	public void breakString()
	{
		
		//this.text = longLine.split("\\. ");
		
	}
	
	public String incomingLongString() throws IOException
	{
		File myFile = new File("tjefferson.txt");
		BufferedReader reader = new BufferedReader(new FileReader(myFile));
		int byteRead = reader.read();
		String longLine = "";
		
		while (byteRead != -1)
		{
			longLine = longLine + (char)byteRead;
			byteRead = reader.read();		
		}
		
		reader.close();
		
		return longLine;

	}
	
	public String outputResults()
	{
		
		return null;
		
	}
	public static void main(String[] args) throws IOException
	{
		TJeffersonInfo one = new TJeffersonInfo();
	}

}
