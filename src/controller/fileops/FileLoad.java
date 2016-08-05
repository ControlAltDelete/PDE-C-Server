package controller.fileops;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
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
	     idNums = null;
	     String[] lName = null;
	     System.out.println("Initiated readCSV");
	     nextLine = reader.readAll();
	     for(x=10; x<nextLine.size(); x++) {
	    	 String[] elems = nextLine.get(x);
		     
	    	 for (int j = 0; j < elems.length; j++)
	    	 {
	    		 
	    		 elems[j] = elems[j].replace("<TR>","");
	    		 elems[j] = elems[j].replace("</TD><TD>M</TD><TD>", ",");
	    		 elems[j] = elems[j].replace("</TD><TD>F</TD><TD>", ",");
	    		 elems[j] = elems[j].replace("</TABLE>", "");
	    		 elems[j] = elems[j].replace("</TD>", ",");
	    		 elems[j] = elems[j].replace("<TD>", "");
	    		 elems[j] = elems[j].replace("'", "");
	    		 
	    		 
	    		 System.out.println(elems[j]);
	 
	    	    /*idNums[j] = Integer.parseInt(elems[j].substring(0, elems[j].indexOf(",")));
	    		 System.out.println(idNums + " " + elems[j].length());
	    		 lName[j] = elems[j].substring(elems[j].indexOf(",") + 1);
	    		 System.out.println(lName);  //last name
	    		 // lacks first name
	    		 String toGetfName = Integer.toString(idNum[j]);
	    		 //String fName = elems[j].substring(elems[j].indexOf(lName.length() + toGetfName.length() + 1), elems[j].indexOf("/n"));
	    		// int fName = elems[j].length();
	    		 //System.out.println(fName + "hey");
	    		  
	    		  */
	    		 
	    	 }
	     }
  }
  
}
