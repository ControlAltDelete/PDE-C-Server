package service.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.cbrc.gdt.builder.CASTGDTBuilder;
import com.cbrc.gdt.builder.CASTGDTStudentTracker;
import com.cbrc.temp.Driver;

import service.cbrc.model.TestCase;

/**
 * Consists of controller that handles the backend of CBR-C.
 * 
 * <p>
 *  <b>Warning</b>:This is still in beta and is not yet fully functional.
 * </p>
 * 
 * @author In Yong S. Lee
 */
public class CBRCControls {
	
	/**
	 * Feeds the source code accordingly.
	 * @param students The list of students
	 * @param builder The Goal Decomposition Tree Builder
	 * @param path The path for the target source code
	 * @param tc The <code>TestCase</code>s
	 * @param studentID The StudentID to use
	 * @return The Feedback Result
	 */
	public String feedSourceCode(CASTGDTStudentTracker students, CASTGDTBuilder builder, Path path,
			ArrayList<TestCase> tc, int studentID)
	{
		String sID = Integer.toString(studentID);
		// split testcases to two
		ArrayList<File> tci = new ArrayList<File>();
		ArrayList<File> tco = new ArrayList<File>();
		for(int i = 0; i < tc.size(); i++)
		{
			tci.add(tc.get(i).getTci().toFile());
			tco.add(tc.get(i).getTco().toFile());
		}
		String result = "";
		try {
			String faulty = "";
			// show code
			// Ask if the code is faulty
			int confirmed = JOptionPane.showConfirmDialog(null, 
			        "Is the code faulty?", "Question",
			        JOptionPane.YES_NO_OPTION);
			if(confirmed == JOptionPane.YES_OPTION)
				faulty = "Y";
			else faulty = "N";
			result = Driver.submitNewCode(students, builder, path.toString(), tci, tco, sID, path.toString(), faulty);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * A not yet implemented feature of CBR-C (GUI Version). It should return the feedback.
	 * @return The Feedback
	 */
	public String getFeedback()
	{
		return "not yet implemented";
	}
}
