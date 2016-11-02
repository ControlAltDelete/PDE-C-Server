package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.fileops.FileLoad;
import database.dao.StudentDAO;
import database.objects.Student;

import java.awt.GridLayout;
import javax.swing.BoxLayout;

public class addStudentFr extends JFrame {

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private FileLoad loader;
	private JFileChooser fc;
	private String ext;
	private FileNameExtensionFilter csvFilter;
	private JTextField textField_5;
	private String[] idNumbers = null;
	private JFrame frame;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField txtCSVPath;
	private JTextField txtCSVSection;;

	/**
	 * Launch the application.
	 */
	public void showFrame() {
		frame.setVisible(true);
		}

	/**
	 * Create the frame.
	 */
	public addStudentFr(){
		initialize();
	}
	
	public void initialize() {
		loader = new FileLoad();
		fc = new JFileChooser();
		csvFilter = new FileNameExtensionFilter(
					"CSV", "csv");
		fc.setFileFilter(csvFilter);
		
		frame = new JFrame();
		
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 570, 300);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(146, 135, 94, 20);
		frame.getContentPane().add(textField_6);
		
		JLabel label = new JLabel("Section:");
		label.setBounds(52, 141, 84, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("ID Number:");
		label_1.setBounds(35, 113, 101, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("First Name:");
		label_2.setBounds(35, 88, 101, 14);
		frame.getContentPane().add(label_2);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(146, 82, 94, 20);
		frame.getContentPane().add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(146, 110, 94, 20);
		frame.getContentPane().add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(146, 54, 94, 20);
		frame.getContentPane().add(textField_9);
		
		JLabel label_3 = new JLabel("Last Name:");
		label_3.setBounds(37, 60, 99, 14);
		frame.getContentPane().add(label_3);
		
		JButton button = new JButton("Add Student");
		button.setBounds(146, 176, 94, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Submit");
		button_1.setBounds(344, 176, 135, 23);
		frame.getContentPane().add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sec = txtCSVSection.getText().replaceAll("\\s", "");
				if(sec.isEmpty() && txtCSVPath.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Fill in the required details to complete this operation.", "Notice", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(sec.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "No Section Added.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(txtCSVPath.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "No CSV to upload.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try 
					{
						ArrayList<Student> studs = loader.readCSVfr(ext, sec);   //RAYMUND VERSION
						StudentDAO sdao = new StudentDAO();
						for(int s = 0; s < studs.size(); s++)
							sdao.addStudent(studs.get(s));
					}
					catch (IOException ioe) 
					{
						ioe.printStackTrace();
					} 
					catch (SQLException sqle)
					{
						sqle.printStackTrace();
					}
				}				
			}
		});
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setBounds(281, 113, 46, 14);
		frame.getContentPane().add(lblOr);
		
		JButton button_2 = new JButton("Browse Class List");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseFile();
			}
		});
		button_2.setBounds(344, 147, 135, 23);
		frame.getContentPane().add(button_2);
		
		txtCSVPath = new JTextField();
		txtCSVPath.setEditable(false);
		txtCSVPath.setColumns(10);
		txtCSVPath.setBounds(344, 116, 135, 20);
		frame.getContentPane().add(txtCSVPath);
		
		JLabel label_4 = new JLabel("Class List File:");
		label_4.setBounds(344, 91, 135, 14);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Section:");
		label_5.setBounds(344, 66, 46, 14);
		frame.getContentPane().add(label_5);
		
		txtCSVSection = new JTextField();
		txtCSVSection.setColumns(10);
		txtCSVSection.setBounds(393, 63, 86, 20);
		frame.getContentPane().add(txtCSVSection);
		
		JLabel lblUploadClassList = new JLabel("UPLOAD CLASS LIST FROM MLS");
		lblUploadClassList.setBounds(329, 21, 171, 20);
		frame.getContentPane().add(lblUploadClassList);
		
		JLabel lblAddAStudent = new JLabel("ADD A STUDENT MANUALLY");
		lblAddAStudent.setBounds(62, 21, 171, 20);
		frame.getContentPane().add(lblAddAStudent);

	}
	
	public Path chooseFile()
	{
		int returnVal = fc.showOpenDialog(this);
		Path filePath = null;
	
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			Path path = Paths.get(fc.getSelectedFile().getAbsolutePath());
			filePath = path;
			ext = path.toString();
			txtCSVPath.setText(ext);
			
			/*
			if (loader.checker(ext))
			{
				  textField_2.setText(ext);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Not a CSV File.", "Error", JOptionPane.ERROR_MESSAGE);
				filePath = null;
			}
			*/
		}
		return filePath;
	}
	
}
