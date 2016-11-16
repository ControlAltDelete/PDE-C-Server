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

public class CBRCControls {
	
	public String feedSourceCode(CASTGDTStudentTracker students, CASTGDTBuilder builder, Path path,
			ArrayList<TestCase> tc, String sID)
	{
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
	
	public String getFeedback()
	{
		return "not yet implemented";
	}
}
