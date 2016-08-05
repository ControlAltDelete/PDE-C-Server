package controller.fileops;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opencsv.CSVReader;

public class FileLoad 
{
  private Matcher matcher;
  private Pattern pattern;
  
  public FileLoad()
  {
		
  }

  public String loadFile(Path path)
  {
	Charset charset = Charset.forName("UTF-8");
	String line = null;
	String cCode = new String();
	  
	try (BufferedReader reader = Files.newBufferedReader(path, charset))
	{		
	  while ((line = reader.readLine()) != null)
	  {
		if (cCode.isEmpty())
		{
	      cCode += line;
		}
		  
		else
		{
		  cCode = cCode + "\n" + line;
		}	  
	  }
	}
	  
	catch (IOException ex)
	{
	  ex.printStackTrace();
	}
	  
	return cCode;
  }
  
  public boolean checker(String fileName)
  {
	String codePattern = "([^\\s]+(\\.(?i)(c))$)";
	pattern = Pattern.compile(codePattern);
	matcher = pattern.matcher(fileName);
	
	return matcher.matches();
  }
  
  public boolean checkerpdf(String fileName)
  {
	String codePattern = "([^\\s]+(\\.(?i)(pdf))$)";
	pattern = Pattern.compile(codePattern);
	matcher = pattern.matcher(fileName);
	
	return matcher.matches();
  }
  
  public boolean checkercsv(String fileName)
  {
	String codePattern = "([^\\s]+(\\.(?i)(csv))$)";
	pattern = Pattern.compile(codePattern);
	matcher = pattern.matcher(fileName);
	
	return matcher.matches();
  }
  
  public void readCSV(String pathChosen) throws IOException
  {  	
	  
	CSVReader reader = new CSVReader(new FileReader("C://Users//Raymund Pua//Desktop//1.csv"));
  	System.out.println("Initiatated readCSV");
    java.util.List<String[]> myEntries = reader.readAll();
    System.out.println(myEntries.indexOf(myEntries));
  }
  
}
