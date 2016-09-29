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
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Color;

public class uploadFile extends JPanel {
	private JTextField activityNo;
	private JTextField pathFile;
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
    private JTextField activityName;
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
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setBounds(195, 122, 287, 200);
		add(centerPanel);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Activity Name");
		lblNewLabel.setBackground(Color.WHITE);
		centerPanel.add(lblNewLabel);
		
		activityNo = new JTextField();
		centerPanel.add(activityNo);
		activityNo.setColumns(10);
		
		JLabel lblActivityNo = new JLabel("Activity No.");
		centerPanel.add(lblActivityNo);
		
		pathFile = new JTextField();
		centerPanel.add(pathFile);
		pathFile.setColumns(10);
		
		JLabel lblPathFile = new JLabel("Path file");
		centerPanel.add(lblPathFile);
		
		activityName = new JTextField();
		centerPanel.add(activityName);
		activityName.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		centerPanel.add(btnSend);
		
		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
				  chooseFile(); // Returns FilePath. Upload not yet implemented
				}
			});
		centerPanel.add(btnChooseFile);
	

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
				  activityName.setText(ext);
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