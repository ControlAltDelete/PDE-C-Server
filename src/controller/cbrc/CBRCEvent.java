package controller.cbrc;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CBRCEvent
{
	
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
