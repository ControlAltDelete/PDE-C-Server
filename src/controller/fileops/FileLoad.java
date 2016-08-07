package controller.fileops;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opencsv.CSVReader;

public class FileLoad 
{
  private Matcher matcher;
  private Pattern pattern;
private int[] idNums;
  
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
  
  public boolean checkerCSV(String fileName)
  {
	String codePattern = "([^\\s]+(\\.(?i)(csv))$)";
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
  
  
  public void readCSV(String csv) throws IOException{
	     CSVReader reader = new CSVReader(new FileReader(csv));
	     List<String[]> nextLine;
	     int x = 0;
	     System.out.println("Initiated readCSV");
	     nextLine = reader.readAll();
		 ArrayList<Integer> idnumList = new ArrayList<Integer>();
		 ArrayList<String> lNameList = new ArrayList<String>();
		 ArrayList<String> fNameList = new ArrayList<String>();
	     for(x=10; x<nextLine.size(); x++) {
	    	 String[] elems = nextLine.get(x);
	    	 for (int j = 0; j < elems.length; j+=2)
	    	 {
	    		 idnumList.add(Integer.parseInt(elems[0].substring(9, 17)));  //IDNUMBER
	    		 System.out.println(Integer.parseInt(elems[0].substring(9, 17)));
	    		 lNameList.add(elems[0].substring(26));  //LAST NAME
	    		 System.out.println(elems[0].substring(26));
	    		 fNameList.add(elems[1].substring(1, elems[1].indexOf("  ")));  //FIRST NAME
	    		 System.out.println(elems[1].substring(1, elems[1].indexOf("  ")));
	    	 }
	    	 
	    
	    	 
	     }

  }
}
