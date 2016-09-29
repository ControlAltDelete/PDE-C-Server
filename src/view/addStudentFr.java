package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.fileops.FileLoad;
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
	private JTextField textField_10;
	private JTextField textField_11;;

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
		frame.setBounds(100, 100, 800, 600);
		
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
		button_1.setBounds(426, 176, 117, 23);
		frame.getContentPane().add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					loader.readCSV(ext);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JButton button_2 = new JButton("Upload .CSV");
		button_2.setBounds(426, 149, 117, 23);
		frame.getContentPane().add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseFile();
			}
		});
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(426, 118, 117, 20);
		frame.getContentPane().add(textField_10);
		
		JLabel label_4 = new JLabel("Upload class list from MLS");
		label_4.setBounds(416, 39, 166, 60);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Section:");
		label_5.setBounds(408, 90, 46, 14);
		frame.getContentPane().add(label_5);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(457, 87, 86, 20);
		frame.getContentPane().add(textField_11);

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
