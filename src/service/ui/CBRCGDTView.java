package service.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.cbrc.gdt.builder.CASTGDTBuilder;
import com.cbrc.temp.Driver;

import controller.fileops.FileSave;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

/**
 * Shows the Goal Decomposition Tree of the current problem in CBR-C.
 * 
 * <p>
 *  This is the current effective port at the moment, as a feature request by Mr. Ryan Samuel Dimaunahan.
 * </p>
 * @author In Yong S. Lee
 */
public class CBRCGDTView {

	private JFrame frame;

	/**
	 * Create the frame <code>CBRCInitialProblem</code> using the current builder.
	 * @param builder The current GDT builder to use
	 */
	public CBRCGDTView(CASTGDTBuilder builder) {
		initialize(builder);
		frame.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CASTGDTBuilder builder) {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel pnlGDTForm = new JPanel();
		frame.getContentPane().add(pnlGDTForm, BorderLayout.CENTER);
		pnlGDTForm.setLayout(new BorderLayout(0, 0));
		
		JTextArea txtGDTForm = new JTextArea();
		txtGDTForm.setEditable(false);
		txtGDTForm.setText(initExtractGDT(builder));
		pnlGDTForm.add(txtGDTForm, BorderLayout.CENTER);
		
		JPanel pnlController = new JPanel();
		frame.getContentPane().add(pnlController, BorderLayout.SOUTH);
		pnlController.setLayout(new BorderLayout(0, 0));
		
		JButton btnExportGdt = new JButton("Export GDT");
		btnExportGdt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter gdtFilter = new FileNameExtensionFilter(
						"Text Files (.txt)", "txt");
				jfc.setFileFilter(gdtFilter);
				int res = jfc.showSaveDialog(frame);
				if(res == JFileChooser.APPROVE_OPTION)
				{
					String fileName = jfc.getSelectedFile().getAbsolutePath();
					Path path = Paths.get(fileName);
					if(fileName.endsWith(".txt"))
					{
						if(!fileName.substring(0, fileName.indexOf(".txt")).endsWith("\\"))
						{
							FileSave fs = new FileSave();
							fs.writeFile(path, txtGDTForm.getText());
							JOptionPane.showMessageDialog(null, "Saved to " + fileName, "Success", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Enter a file name.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Not a text file.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		pnlController.add(btnExportGdt, BorderLayout.WEST);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		pnlController.add(btnClose, BorderLayout.EAST);
		
		JPanel pnlControllerCtr = new JPanel();
		FlowLayout fl_pnlControllerCtr = (FlowLayout) pnlControllerCtr.getLayout();
		fl_pnlControllerCtr.setVgap(0);
		fl_pnlControllerCtr.setHgap(0);
		pnlController.add(pnlControllerCtr, BorderLayout.CENTER);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				extractGDT(builder, txtGDTForm);
			}
		});
		pnlControllerCtr.add(btnRefresh);
		String currentOS = System.getProperty("os.name").toLowerCase();
	  	if (currentOS.indexOf("win") >= 0)
	  	{
	  		txtGDTForm.setFont(new Font("Consolas", Font.PLAIN, 13));
	  	}
	  	else if (currentOS.indexOf("mac") >= 0)
	  	{
	  		txtGDTForm.setFont(new Font("Monaco", Font.PLAIN, 13));
	  	}
	}

	/**
	 * Initializes the extraction of the <code>builder</code> being used.
	 * @param builder The current Goal Decomposition Tree builder to use
	 * @return the string containing the Goal Decomposition Tree
	 */
	public String initExtractGDT(CASTGDTBuilder builder)
	{
		try
		{
			return Driver.showGDT(builder).toString();
		}
		catch(Exception e)
		{
			return "Error retrieving GDT";
		}
	}
	
	/**
	 * Extracts the Goal Decomposition Tree using <code>builder</code> to <code>txtArea</code>.
	 * @param builder The current Goal Decomposition Tree builder to use
	 * @param txtArea the target <code>JTextArea</code> to use for Goal Decomposition Tree display.
	 */
	public void extractGDT(CASTGDTBuilder builder, JTextArea txtArea)
	{
		try {
			StringBuilder sb = Driver.showGDT(builder);
			txtArea.setText(sb.toString());
		} catch (Exception ex) {
			txtArea.setText("Error retrieving GDT");
			ex.printStackTrace();	
		}
	}

}
