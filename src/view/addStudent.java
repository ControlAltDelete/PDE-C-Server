package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.fileops.FileLoad;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class addStudent extends JPanel {
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
		
		textField = new JTextField();
		textField.setBounds(111, 78, 94, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(111, 134, 94, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(111, 159, 94, 20);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(111, 106, 94, 20);
		add(textField_3);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.setBounds(111, 196, 94, 23);
		add(btnAddStudent);
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setBounds(241, 109, 46, 14);
		add(lblOr);
		
		JButton btnUploadcsv = new JButton("Browse CSV");
		btnUploadcsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseFile();
			}
		});
		btnUploadcsv.setBounds(360, 168, 117, 23);
		add(btnUploadcsv);
		
		JLabel lblYouMayUpload = new JLabel("Upload class list from MLS");
		lblYouMayUpload.setBounds(311, 30, 166, 60);
		add(lblYouMayUpload);
		
		JLabel lblSection_1 = new JLabel("Section:");
		lblSection_1.setBounds(302, 109, 46, 14);
		add(lblSection_1);
		
		textField_4 = new JTextField();
		textField_4.setBounds(358, 106, 119, 20);
		add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(358, 134, 119, 20);
		add(textField_5);
		textField_5.setColumns(10);
		
	
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					System.out.println("About to initiate .readCSV");
					System.out.println(ext);
					loader.readCSV(ext);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
		});
		btnNewButton.setBounds(360, 196, 117, 23);
		add(btnNewButton);

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
			textField_5.setText(ext);
			/*if (loader.checkercsv(ext))
			{
				  textField_2.setText(ext);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Not a CSV File.", "Error", JOptionPane.ERROR_MESSAGE);
				filePath = null;
			}*/
		}
		return filePath;
	}
}
