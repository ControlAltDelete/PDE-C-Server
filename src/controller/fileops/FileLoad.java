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

import database.objects.Student;

/**
 * Handles the actual reading of C Files, Comma Separated Value files (MyLasalle CSV Format), Portable Document Format file types in PDE-C Server.
 * 
 * @author Alexander John D. Jose
 * @author In Yong S. Lee
 * @author Raymund Zebedee P. Pua
 * @author Lorenzo Miguel G. Monzon
 *
 */

public class FileLoad 
{
  private Matcher matcher;
  private Pattern pattern;
  private int[] idNums;
  
  /**
   * Creates an instance of FileLoad.
   */
  public FileLoad()
  {
		
  }

  /**
   * Loads a file using the said <code>path</code>. <br>
   * Returns the source code of the <code>path</code> loaded.
   * @param path The Path Specified
   * @return The C Source Code
   */
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
  
  /**
   * Checks if the fileName has a <code>.c</code> extension.
   * @param fileName The Absolute File to check.
   * @return <code>true</code> if the <code>fileName</code> specified is a C Source Code, <code>false</code> otherwise.
   */
  public boolean checker(String fileName)
  {
	String codePattern = "([^\\s]+(\\.(?i)(c))$)";
	pattern = Pattern.compile(codePattern);
	matcher = pattern.matcher(fileName);
	
	return matcher.matches();
  }
  
  /**
   * Checks if the fileName has a <code>.csv</code> extension.
   * @param fileName The Absolute File to check.
   * @return <code>true</code> if the <code>fileName</code> specified is a Comma Separated Value, <code>false</code> otherwise.
   */
  public boolean checkerCSV(String fileName)
  {
	String codePattern = "([^\\s]+(\\.(?i)(csv))$)";
	pattern = Pattern.compile(codePattern);
	matcher = pattern.matcher(fileName);
	
	return matcher.matches();
  }
  
  /**
   * Checks if the fileName has a <code>.pdf</code> extension.
   * @param fileName The Absolute File to check.
   * @return <code>true</code> if the <code>fileName</code> specified is a Portable Document Format, <code>false</code> otherwise.
   */
  public boolean checkerpdf(String fileName)
  {
	String codePattern = "(^.*\\.(pdf|PDF)$)";
	pattern = Pattern.compile(codePattern);
	matcher = pattern.matcher(fileName);
	
	return matcher.matches();
  }
  
  /**
   * Checks if the fileName has a <code>.csv</code> extension.
   * @param csv The <code>csv</code> File in Absolute Path
   * @param section The <code>section</code> to specify
   * @return The Student List
   * @throws IOException if the <code>csv</code> file cannot be read
   */
  public ArrayList<Student> readCSV(String csv, String section) throws IOException{
	     CSVReader reader = new CSVReader(new FileReader(csv));
	     List<String[]> nextLine;
	     int x = 0;
	     System.out.println("Initiated readCSV");
	     nextLine = reader.readAll();
	     ArrayList<Student> result = new ArrayList<Student>();
		 ArrayList<Integer> idnumList = new ArrayList<Integer>();
		 ArrayList<String> lNameList = new ArrayList<String>();
		 ArrayList<String> fNameList = new ArrayList<String>();
	     for(x=10; x<nextLine.size(); x++)
	     {
	    	 String[] elems = nextLine.get(x);
	    	 for (int j = 0; j < elems.length; j+=2)
	    	 {
	    		 if(!elems[0].contains("</TABLE>"))
	    		 {
	    			 Student s = new Student();
		    		 idnumList.add(Integer.parseInt(elems[0].substring(9, 17)));  //IDNUMBER
		    		 s.setStudentID(Integer.parseInt(elems[0].substring(9, 17)));
		    		 lNameList.add(elems[0].substring(26));  //LAST NAME
		    		 s.setStudentLastName(elems[0].substring(26));
		    		 fNameList.add(elems[1].substring(1, elems[1].indexOf("  ")));  //FIRST NAME
		    		 s.setStudentFirstName(elems[1].substring(1, elems[1].indexOf("  ")));
		    		 s.setStudentSection(section);
		    		 result.add(s); 
	    		 }
	    	 }
	     }
	     return result;
  }
  
  /**
   * Checks if the fileName has a <code>.csv</code> extension.
   * @param csv The <code>csv</code> File in Absolute Path
   * @param section The <code>section</code> to specify
   * @return The Student List
   * @throws IOException if the <code>csv</code> file cannot be read
   */
  public ArrayList<Student> readCSVfr(String csv, String section) throws IOException
  {
     CSVReader reader = new CSVReader(new FileReader(csv));
     List<String[]> nextLine;
     int x = 0;
     System.out.println("Initiated readCSV");
     nextLine = reader.readAll();
     ArrayList<Student> result = new ArrayList<Student>();
	 ArrayList<Integer> idnumList = new ArrayList<Integer>();
	 ArrayList<String> lNameList = new ArrayList<String>();
	 ArrayList<String> fNameList = new ArrayList<String>();
     for(x=10; x<nextLine.size(); x++)
     {
    	 String[] elems = nextLine.get(x);
    	 for (int j = 0; j < elems.length; j+=2)
    	 {
    		 if(!elems[0].contains("</TABLE>"))
    		 {
	    		 idnumList.add(Integer.parseInt(elems[0].substring(9, 17)));  //IDNUMBER
	    		 System.out.println(Integer.parseInt(elems[0].substring(9, 17)));
	    		 lNameList.add(elems[0].substring(26));  //LAST NAME
	    		 System.out.println(elems[0].substring(26));
	    		 fNameList.add(elems[1].substring(1, elems[1].indexOf("  ")));  //FIRST NAME
	    		 System.out.println(elems[1].substring(1, elems[1].indexOf("  ")));
    		 }
    	 }
    	 
    	 for (int j = 0; j < idnumList.size(); j++)
    	 {
	    	 Student s = new Student();
	    	 s.setStudentID(idnumList.get(j));
	    	 s.setStudentFirstName(fNameList.get(j));
	    	 s.setStudentLastName(lNameList.get(j));
	    	 s.setStudentSection(section);
	    	 result.add(s);
    	 }
    
     }
     
     return result;
  }
  
}
