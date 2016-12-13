package view;

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
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.fileops.FileLoad;
import database.dao.StudentDAO;
import database.objects.Student;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

/**
 * The popup frame of Add Student View.
 * 
 * @author In Yong S. Lee
 * @author Raymund Zebedee P. Pua
 */
public class addStudentFr extends JFrame {
	
	private FileLoad loader;
	private JFileChooser fc;
	private String ext;
	private FileNameExtensionFilter csvFilter;
	private JFrame frame;
	private JTextField txtStudentSection;
	private JTextField txtStudentFirstName;
	private JTextField txtStudentIDNumber;
	private JTextField txtStudentLastName;
	private JTextField txtCSVPath;
	private JTextField txtCSVSection;;

	/**
	 * Shows the <code>addStudentFr</code> frame. It will show the Add Student for the Professor to use.
	 */
	public void showFrame() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

	/**
	 * Create the frame <code>addStudentFr</code>.
	 */
	public addStudentFr(){
		initialize();
	}

	/**
	 * Initializes the components of the given <code>frame</code>. 
	 */
	public void initialize() {
		loader = new FileLoad();
		fc = new JFileChooser();
		csvFilter = new FileNameExtensionFilter(
					"CSV", "csv");
		fc.setFileFilter(csvFilter);
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 420, 262);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{35, 101, 0, 39, 72, 0, 41, 46, 46, 0, 126, 0};
		gridBagLayout.rowHeights = new int[]{0, 20, 26, 1, 17, 20, 1, 29, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblAddAStudent = new JLabel("ADD A STUDENT MANUALLY");
		lblAddAStudent.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblAddAStudent = new GridBagConstraints();
		gbc_lblAddAStudent.fill = GridBagConstraints.BOTH;
		gbc_lblAddAStudent.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddAStudent.gridwidth = 3;
		gbc_lblAddAStudent.gridx = 2;
		gbc_lblAddAStudent.gridy = 2;
		frame.getContentPane().add(lblAddAStudent, gbc_lblAddAStudent);
		
		JLabel lblUploadClassList = new JLabel("UPLOAD CLASS LIST");
		lblUploadClassList.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblUploadClassList = new GridBagConstraints();
		gbc_lblUploadClassList.fill = GridBagConstraints.BOTH;
		gbc_lblUploadClassList.insets = new Insets(0, 0, 5, 5);
		gbc_lblUploadClassList.gridwidth = 2;
		gbc_lblUploadClassList.gridx = 7;
		gbc_lblUploadClassList.gridy = 2;
		frame.getContentPane().add(lblUploadClassList, gbc_lblUploadClassList);
		
		JLabel lblStudentLastName = new JLabel("Last Name:");
		GridBagConstraints gbc_lblStudentLastName = new GridBagConstraints();
		gbc_lblStudentLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblStudentLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblStudentLastName.gridx = 2;
		gbc_lblStudentLastName.gridy = 3;
		frame.getContentPane().add(lblStudentLastName, gbc_lblStudentLastName);
		
		txtStudentLastName = new JTextField();
		txtStudentLastName.setColumns(10);
		GridBagConstraints gbc_txtStudentLastName = new GridBagConstraints();
		gbc_txtStudentLastName.gridwidth = 2;
		gbc_txtStudentLastName.anchor = GridBagConstraints.NORTH;
		gbc_txtStudentLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStudentLastName.insets = new Insets(0, 0, 5, 5);
		gbc_txtStudentLastName.gridx = 3;
		gbc_txtStudentLastName.gridy = 3;
		frame.getContentPane().add(txtStudentLastName, gbc_txtStudentLastName);
		
		JLabel lblCSVClassList = new JLabel("Class List File:");
		GridBagConstraints gbc_lblCSVClassList = new GridBagConstraints();
		gbc_lblCSVClassList.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCSVClassList.insets = new Insets(0, 0, 5, 5);
		gbc_lblCSVClassList.gridx = 7;
		gbc_lblCSVClassList.gridy = 3;
		frame.getContentPane().add(lblCSVClassList, gbc_lblCSVClassList);
		
		JButton btnCSVBrowse = new JButton("Browse");
		btnCSVBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseFile();
			}
		});
		GridBagConstraints gbc_btnCSVBrowse = new GridBagConstraints();
		gbc_btnCSVBrowse.anchor = GridBagConstraints.SOUTH;
		gbc_btnCSVBrowse.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCSVBrowse.insets = new Insets(0, 0, 5, 5);
		gbc_btnCSVBrowse.gridx = 8;
		gbc_btnCSVBrowse.gridy = 3;
		frame.getContentPane().add(btnCSVBrowse, gbc_btnCSVBrowse);
		
		JLabel lblStudentFirstName = new JLabel("First Name:");
		GridBagConstraints gbc_lblStudentFirstName = new GridBagConstraints();
		gbc_lblStudentFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblStudentFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblStudentFirstName.gridx = 2;
		gbc_lblStudentFirstName.gridy = 4;
		frame.getContentPane().add(lblStudentFirstName, gbc_lblStudentFirstName);
		
		txtStudentFirstName = new JTextField();
		txtStudentFirstName.setColumns(10);
		GridBagConstraints gbc_txtStudentFirstName = new GridBagConstraints();
		gbc_txtStudentFirstName.gridwidth = 2;
		gbc_txtStudentFirstName.anchor = GridBagConstraints.NORTH;
		gbc_txtStudentFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStudentFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_txtStudentFirstName.gridx = 3;
		gbc_txtStudentFirstName.gridy = 4;
		frame.getContentPane().add(txtStudentFirstName, gbc_txtStudentFirstName);
		
		txtCSVPath = new JTextField();
		txtCSVPath.setEnabled(false);
		txtCSVPath.setColumns(10);
		GridBagConstraints gbc_txtCSVPath = new GridBagConstraints();
		gbc_txtCSVPath.gridwidth = 2;
		gbc_txtCSVPath.anchor = GridBagConstraints.SOUTH;
		gbc_txtCSVPath.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCSVPath.insets = new Insets(0, 0, 5, 5);
		gbc_txtCSVPath.gridx = 7;
		gbc_txtCSVPath.gridy = 4;
		frame.getContentPane().add(txtCSVPath, gbc_txtCSVPath);
		
		JLabel lblStudentIDNum = new JLabel("ID Number:");
		GridBagConstraints gbc_lblStudentIDNum = new GridBagConstraints();
		gbc_lblStudentIDNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblStudentIDNum.insets = new Insets(0, 0, 5, 5);
		gbc_lblStudentIDNum.gridx = 2;
		gbc_lblStudentIDNum.gridy = 5;
		frame.getContentPane().add(lblStudentIDNum, gbc_lblStudentIDNum);
		
		txtStudentIDNumber = new JTextField();
		txtStudentIDNumber.setColumns(10);
		GridBagConstraints gbc_txtStudentIDNumber = new GridBagConstraints();
		gbc_txtStudentIDNumber.gridwidth = 2;
		gbc_txtStudentIDNumber.anchor = GridBagConstraints.NORTH;
		gbc_txtStudentIDNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStudentIDNumber.insets = new Insets(0, 0, 5, 5);
		gbc_txtStudentIDNumber.gridx = 3;
		gbc_txtStudentIDNumber.gridy = 5;
		frame.getContentPane().add(txtStudentIDNumber, gbc_txtStudentIDNumber);
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblOr = new GridBagConstraints();
		gbc_lblOr.gridwidth = 2;
		gbc_lblOr.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblOr.insets = new Insets(0, 0, 5, 5);
		gbc_lblOr.gridx = 5;
		gbc_lblOr.gridy = 5;
		frame.getContentPane().add(lblOr, gbc_lblOr);
						
						JLabel lblStudentSection = new JLabel("Section:");
						GridBagConstraints gbc_lblStudentSection = new GridBagConstraints();
						gbc_lblStudentSection.fill = GridBagConstraints.HORIZONTAL;
						gbc_lblStudentSection.insets = new Insets(0, 0, 5, 5);
						gbc_lblStudentSection.gridx = 2;
						gbc_lblStudentSection.gridy = 6;
						frame.getContentPane().add(lblStudentSection, gbc_lblStudentSection);
						
						txtStudentSection = new JTextField();
						txtStudentSection.setColumns(10);
						GridBagConstraints gbc_txtStudentSection = new GridBagConstraints();
						gbc_txtStudentSection.gridwidth = 2;
						gbc_txtStudentSection.anchor = GridBagConstraints.NORTH;
						gbc_txtStudentSection.fill = GridBagConstraints.HORIZONTAL;
						gbc_txtStudentSection.insets = new Insets(0, 0, 5, 5);
						gbc_txtStudentSection.gridx = 3;
						gbc_txtStudentSection.gridy = 6;
						frame.getContentPane().add(txtStudentSection, gbc_txtStudentSection);
						
						JLabel lblCSVSection = new JLabel("Section:");
						GridBagConstraints gbc_lblCSVSection = new GridBagConstraints();
						gbc_lblCSVSection.fill = GridBagConstraints.HORIZONTAL;
						gbc_lblCSVSection.insets = new Insets(0, 0, 5, 5);
						gbc_lblCSVSection.gridx = 7;
						gbc_lblCSVSection.gridy = 6;
						frame.getContentPane().add(lblCSVSection, gbc_lblCSVSection);
						
						txtCSVSection = new JTextField();
						txtCSVSection.setColumns(10);
						GridBagConstraints gbc_txtCSVSection = new GridBagConstraints();
						gbc_txtCSVSection.fill = GridBagConstraints.HORIZONTAL;
						gbc_txtCSVSection.anchor = GridBagConstraints.SOUTH;
						gbc_txtCSVSection.insets = new Insets(0, 0, 5, 5);
						gbc_txtCSVSection.gridx = 8;
						gbc_txtCSVSection.gridy = 6;
						frame.getContentPane().add(txtCSVSection, gbc_txtCSVSection);
						
						JButton btnAddStudent = new JButton("Add Student");
						GridBagConstraints gbc_btnAddStudent = new GridBagConstraints();
						gbc_btnAddStudent.gridwidth = 3;
						gbc_btnAddStudent.anchor = GridBagConstraints.NORTH;
						gbc_btnAddStudent.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnAddStudent.insets = new Insets(0, 0, 5, 5);
						gbc_btnAddStudent.gridx = 2;
						gbc_btnAddStudent.gridy = 7;
						frame.getContentPane().add(btnAddStudent, gbc_btnAddStudent);
						
								
								
								JButton btnCSVSubmit = new JButton("Submit");
								GridBagConstraints gbc_btnCSVSubmit = new GridBagConstraints();
								gbc_btnCSVSubmit.insets = new Insets(0, 0, 5, 5);
								gbc_btnCSVSubmit.anchor = GridBagConstraints.NORTH;
								gbc_btnCSVSubmit.fill = GridBagConstraints.HORIZONTAL;
								gbc_btnCSVSubmit.gridwidth = 2;
								gbc_btnCSVSubmit.gridx = 7;
								gbc_btnCSVSubmit.gridy = 7;
								frame.getContentPane().add(btnCSVSubmit, gbc_btnCSVSubmit);
								btnCSVSubmit.addActionListener(new ActionListener() {
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
												JOptionPane.showMessageDialog(null, "Successfully Added Students from the CSV.", "Success", JOptionPane.INFORMATION_MESSAGE);
												frame.dispose();
												StudentList sl = StudentList.getInstance();
												sl.refreshData();
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
								frame.dispose();
								StudentList sl = StudentList.getInstance();
								sl.refreshData();
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

	}
	
	/**
	 * Chooses a CSV file through <code>JFileChooser</code>.
	 * @return the absolute <code>Path</code> of CSV file. 
	 */
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
