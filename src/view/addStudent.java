package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.fileops.FileLoad;
import database.dao.StudentDAO;
import database.objects.Student;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class addStudent extends JPanel {
	private JTextField txtStudentLastName;
	private JTextField txtStudentIDNumber;
	private JTextField txtStudentSection;
	private JTextField txtStudentFirstName;
	private JTextField txtCSVSection;
	private FileLoad loader;
	private JFileChooser fc;
	private String ext;
	private FileNameExtensionFilter csvFilter;
	private JTextField txtCSVPath;
	private String[] idNumbers = null;
	/**
	 * Create the panel.
	 */
	public addStudent() {
		
		loader = new FileLoad();
		fc = new JFileChooser();
		csvFilter = new FileNameExtensionFilter(
					"CSV", "csv");
		fc.setFileFilter(csvFilter);
		
		setLayout(null);
		
		JLabel lblName = new JLabel("Last Name:");
		lblName.setBounds(28, 81, 80, 14);
		add(lblName);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(28, 109, 55, 14);
		add(lblFirstName);
		
		JLabel lblIdNumber = new JLabel("ID Number:");
		lblIdNumber.setBounds(28, 137, 55, 14);
		add(lblIdNumber);
		
		JLabel lblSection = new JLabel("Section:");
		lblSection.setBounds(38, 162, 46, 14);
		add(lblSection);
		
		txtStudentLastName = new JTextField();
		txtStudentLastName.setBounds(111, 78, 94, 20);
		add(txtStudentLastName);
		txtStudentLastName.setColumns(10);
		
		txtStudentIDNumber = new JTextField();
		txtStudentIDNumber.setBounds(111, 134, 94, 20);
		add(txtStudentIDNumber);
		txtStudentIDNumber.setColumns(10);
		
		txtStudentSection = new JTextField();
		txtStudentSection.setColumns(10);
		txtStudentSection.setBounds(111, 159, 94, 20);
		add(txtStudentSection);
		
		txtStudentFirstName = new JTextField();
		txtStudentFirstName.setColumns(10);
		txtStudentFirstName.setBounds(111, 106, 94, 20);
		add(txtStudentFirstName);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean emptyChecker = txtStudentIDNumber.getText().replaceAll("\\s", "").isEmpty() || 
						txtStudentFirstName.getText().replaceAll("\\s", "").isEmpty() ||
						txtStudentLastName.getText().replaceAll("\\s", "").isEmpty() ||
						txtStudentSection.getText().replaceAll("\\s", "").isEmpty();
				if(emptyChecker)
				{
					JOptionPane.showMessageDialog(null, "Please fill in the required fields to add a new student.", "Notice", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					try
					{
						int idnum = Integer.parseInt(txtStudentIDNumber.getText().replaceAll("\\s", ""));
						String fname = txtStudentFirstName.getText();
						String lname = txtStudentLastName.getText();
						String section = txtStudentSection.getText().replaceAll("\\s", "");
						StudentDAO sdao = new StudentDAO();
						Student s = new Student(idnum, "", fname, lname, section);
						sdao.addStudent(s);
						JOptionPane.showMessageDialog(null, "Successfully Added Student.", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					catch (NumberFormatException nfe)
					{
						JOptionPane.showMessageDialog(null, "Invalid input! Must be number for id number!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					catch (SQLException sqle)
					{
						JOptionPane.showMessageDialog(null, "No Connection to SQL!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAddStudent.setBounds(111, 196, 94, 23);
		add(btnAddStudent);
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setBounds(241, 109, 46, 14);
		add(lblOr);
		
		JButton btnBrowseCSV = new JButton("Browse Class List");
		btnBrowseCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseFile();
			}
		});
		btnBrowseCSV.setBounds(289, 218, 135, 23);
		add(btnBrowseCSV);
		
		JLabel lblYouMayUpload = new JLabel("Upload class list from MLS");
		lblYouMayUpload.setBounds(289, 106, 171, 20);
		add(lblYouMayUpload);
		
		JLabel lblSection_1 = new JLabel("Section:");
		lblSection_1.setBounds(289, 137, 46, 14);
		add(lblSection_1);
		
		txtCSVSection = new JTextField();
		txtCSVSection.setBounds(338, 134, 86, 20);
		add(txtCSVSection);
		txtCSVSection.setColumns(10);
		
		txtCSVPath = new JTextField();
		txtCSVPath.setEditable(false);
		txtCSVPath.setBounds(289, 187, 135, 20);
		add(txtCSVPath);
		txtCSVPath.setColumns(10);
		

		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtCSVPath.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "No CSV to upload.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try 
					{
						ArrayList<Student> studs = loader.readCSV(ext);
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
		btnNewButton.setBounds(289, 245, 135, 23);
		add(btnNewButton);
		
		JLabel lblClassListFile = new JLabel("Class List File:");
		lblClassListFile.setBounds(289, 162, 135, 14);
		add(lblClassListFile);

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
