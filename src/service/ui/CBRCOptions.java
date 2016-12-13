package service.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.cbrc.temp.Driver;

import controller.fileops.FileLoad;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;

/**
 * Older/Prototype version of CBR-C Options.
 * 
 * @author In Yong S. Lee
 */

@Deprecated
public class CBRCOptions extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame <code>CBRCOptions</code>.
	 */
	public CBRCOptions() {
		setTitle("CBR-C Commands");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegisterNewStudent = new JButton("Register New Student");
		btnRegisterNewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sid = (String)JOptionPane.showInputDialog(null, "Input Student ID", "");
				if(sid == null) { /* do nothing */ }
				else if(sid.trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Nothing entered.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String sName = (String)JOptionPane.showInputDialog(null, "Input Student Name", "");
					if(sName == null) { /* do nothing */ }
					else if(sName.trim().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Nothing entered.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						Integer studID = 0;
						try
						{
							studID = Integer.parseInt(sid);
						}
						catch (NumberFormatException nfe)
						{
					        JOptionPane.showMessageDialog(null, "Not a number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						// Driver.registerNewStudent(br, students, builder.getSuperGoal().getDBID(), sid, sName);
						JOptionPane.showMessageDialog(null, "Student Information submitted to Database.", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnRegisterNewStudent.setBounds(10, 11, 256, 23);
		contentPane.add(btnRegisterNewStudent);
		
		JButton btnSubmitCode = new JButton("Submit Code");
		btnSubmitCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sid = (String)JOptionPane.showInputDialog(null, "Input Student ID", "");
				if(sid == null) { /* do nothing */ }
				else if(sid.trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Nothing entered.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					// Input Path To Code
					// JFileChooser, filePath.
					JFileChooser jfc = new JFileChooser();
					int value = jfc.showOpenDialog(null);
					if (value == JFileChooser.APPROVE_OPTION)
					{
						Path path = Paths.get(jfc.getSelectedFile().getAbsolutePath());
						Path filePath = path;
						String ext = path.getFileName().toString();
						FileLoad loader = new FileLoad();
						if(loader.checker(ext))
						{
							int n = JOptionPane.showConfirmDialog(null, "Is the code faulty?", "CBR-C", JOptionPane.YES_NO_OPTION);
							String faulty = "";
							if(n == JOptionPane.YES_OPTION) {
								faulty = "Y";
							}
							else if(n == JOptionPane.NO_OPTION) {
								faulty = "N";
							}
							if(!faulty.isEmpty())
							{
								Integer studID = 0;
								try
								{
									studID = Integer.parseInt(sid);
								}
								catch (NumberFormatException nfe)
								{
							        JOptionPane.showMessageDialog(null, "Not a number!", "Error", JOptionPane.ERROR_MESSAGE);
								}
								// Driver.submitNewCode(br, students, builder, path, tci, tco, studID, ext, faulty);
								JOptionPane.showMessageDialog(null, "Student Code submitted to Database.", "Success", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Not a C source code.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnSubmitCode.setBounds(10, 45, 256, 23);
		contentPane.add(btnSubmitCode);
		
		JButton btnStudentAskFor = new JButton("Student Ask For Help");
		btnStudentAskFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sid = (String)JOptionPane.showInputDialog(null, "Input Student ID", "");
				if(sid == null) { /* do nothing */ }
				else if(sid.trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Nothing entered.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{

					JOptionPane.showMessageDialog(null, "Nothing entered.", "Error", JOptionPane.ERROR_MESSAGE);
					Integer studID = 0;
					try
					{
						studID = Integer.parseInt(sid);
					}
					catch (NumberFormatException nfe)
					{
				        JOptionPane.showMessageDialog(null, "Not a number!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					//Driver.askForHelp(br, students, builder, path);
				}
				
			}
		});
		btnStudentAskFor.setBounds(10, 79, 256, 23);
		contentPane.add(btnStudentAskFor);
		
		JButton btnPrintGdt = new JButton("Print GDT");
		btnPrintGdt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Save File to where
				// JFileChooser, filePath.
				JFileChooser jfc = new JFileChooser();
				int value = jfc.showSaveDialog(null);
				if (value == JFileChooser.APPROVE_OPTION)
				{
					// Driver.printGDT(builder, path);
				}
			}
		});
		btnPrintGdt.setBounds(10, 113, 256, 23);
		contentPane.add(btnPrintGdt);
		
		JButton btnAddFaultyCase = new JButton("Add Faulty Case");
		btnAddFaultyCase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Input Path To Code
				// JFileChooser, filePath.
				JFileChooser jfc = new JFileChooser();
				int value = jfc.showOpenDialog(null);
				if (value == JFileChooser.APPROVE_OPTION)
				{
					Path path = Paths.get(jfc.getSelectedFile().getAbsolutePath());
					Path filePath = path;
					String ext = path.getFileName().toString();
					FileLoad loader = new FileLoad();
					if(loader.checker(ext))
					{
						String feedback = (String)JOptionPane.showInputDialog(null, "Input level 3 feedback", "CBR-C");
						if(feedback == null) { /* do nothing */ }
						else if(feedback.trim().isEmpty())
							JOptionPane.showMessageDialog(null, "Nothing entered.", "Error", JOptionPane.ERROR_MESSAGE);
						else;
							// Driver.addCase(br, students, builder, path, ext, feedback);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Not a C source code.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAddFaultyCase.setBounds(10, 147, 256, 23);
		contentPane.add(btnAddFaultyCase);
	}
}
