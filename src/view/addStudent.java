package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class addStudent extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the panel.
	 */
	public addStudent() {
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
		
		JButton btnUploadcsv = new JButton("Upload .CSV");
		btnUploadcsv.setBounds(307, 162, 101, 23);
		add(btnUploadcsv);
		
		JLabel lblYouMayUpload = new JLabel("Upload class list from MLS");
		lblYouMayUpload.setBounds(297, 86, 140, 60);
		add(lblYouMayUpload);
		
		JLabel lblSection_1 = new JLabel("Section:");
		lblSection_1.setBounds(289, 137, 46, 14);
		add(lblSection_1);
		
		textField_4 = new JTextField();
		textField_4.setBounds(338, 134, 86, 20);
		add(textField_4);
		textField_4.setColumns(10);

	}
}
