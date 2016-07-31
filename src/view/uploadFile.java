package view;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.fileops.FileLoad;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class uploadFile extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private FileLoad loader;
	private FileNameExtensionFilter pdfFilter;
    JFileChooser fc;
    JButton b, b1;
    JTextField tf;
    FileInputStream in;
    Socket s;
    DataOutputStream dout;
    DataInputStream din;
    int i;
	/**
	 * Create the panel.
	 */
	public uploadFile() {
		loader = new FileLoad();
		fc = new JFileChooser();
		pdfFilter = new FileNameExtensionFilter(
					"PDF Documents", "pdf");
		fc.setFileFilter(pdfFilter);

		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(129, 50, 116, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Activity Name");
		lblNewLabel.setBounds(39, 53, 80, 14);
		add(lblNewLabel);
		
		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
				  chooseFile(); // Returns FilePath. Upload not yet implemented
				}
			});
		btnChooseFile.setBounds(129, 81, 116, 23);
		add(btnChooseFile);
		
		textField_1 = new JTextField();
		textField_1.setBounds(129, 23, 116, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblActivityNo = new JLabel("Activity No.");
		lblActivityNo.setBounds(39, 28, 80, 14);
		add(lblActivityNo);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(129, 106, 116, 23);
		add(btnSend);

	}
	
	public Path chooseFile()
	{
		int returnVal = fc.showOpenDialog(this);
		Path filePath = null;
	
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			Path path = Paths.get(fc.getSelectedFile().getAbsolutePath());
			filePath = path;
			String ext = path.toString();
			if (loader.checkerpdf(ext))
			{
				//textField.setText(ext); Please Add a Text Field/label where the path can be placed
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Not a PDF File.", "Error", JOptionPane.ERROR_MESSAGE);
				filePath = null;
			}
		}
		return filePath;
	}
	
}
