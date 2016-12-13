package controller.cbrc;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Lists all of the event invocations being called by various buttons like Add Test Case, Import First Solution, etc.
 * 
 * @author In Yong S. Lee
 *
 */
public class CBRCEvent
{
	
	/**
	 * Pops up a JFileChooser that allows the professor to select a test case.
	 * 
	 * @param frame The target frame to use for location relativity.
	 * @return The absolute path of the Test Case (text file).
	 */
	public String importTestCase(JFrame frame)
	{
		String result = "";
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter(
				"Test Case (*.txt)", "txt");

		JFileChooser txtFileChooser = new JFileChooser();

		txtFileChooser.setFileFilter(txtFilter);

		int returnVal = txtFileChooser.showOpenDialog(frame);

		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			result = txtFileChooser.getSelectedFile().getAbsolutePath();
		}
		return result;
	}
	
	/**
	 * Pops up a JFileChooser that allows the professor to select a first solution (the source code) to the said problem.
	 * 
	 * @param frame The target frame to use for location relativity.
	 * @return The absolute path of the First Solution File.
	 */
	public String importFirstSolution(JFrame frame)
	{
		String result = "";
		FileNameExtensionFilter cFilter = new FileNameExtensionFilter(
				"First Solution File (*.c)", "c");

		JFileChooser cFileChooser = new JFileChooser();

		cFileChooser.setFileFilter(cFilter);

		int returnVal = cFileChooser.showOpenDialog(frame);

		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			result = cFileChooser.getSelectedFile().getAbsolutePath();
		}
		return result;
	}
}
