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
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class uploadFile extends JPanel {
	private JTextField activityNo;
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
		centerPanel.setBounds(102, 71, 437, 336);
		add(centerPanel);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{95, 95, 95, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{25, 0, 25, 0, 25, 25, 23, 0, 0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		JLabel lblNewLabel = new JLabel("Activity Name");
		lblNewLabel.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		centerPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		activityNo = new JTextField();
		GridBagConstraints gbc_activityNo = new GridBagConstraints();
		gbc_activityNo.gridwidth = 4;
		gbc_activityNo.fill = GridBagConstraints.BOTH;
		gbc_activityNo.insets = new Insets(0, 0, 5, 0);
		gbc_activityNo.gridx = 0;
		gbc_activityNo.gridy = 1;
		centerPanel.add(activityNo, gbc_activityNo);
		activityNo.setColumns(10);
		
		JLabel lblActivityNo = new JLabel("Activity Deadline");
		GridBagConstraints gbc_lblActivityNo = new GridBagConstraints();
		gbc_lblActivityNo.gridwidth = 3;
		gbc_lblActivityNo.fill = GridBagConstraints.BOTH;
		gbc_lblActivityNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblActivityNo.gridx = 0;
		gbc_lblActivityNo.gridy = 2;
		centerPanel.add(lblActivityNo, gbc_lblActivityNo);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 3;
		centerPanel.add(comboBox, gbc_comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.fill = GridBagConstraints.BOTH;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 3;
		centerPanel.add(comboBox_1, gbc_comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.fill = GridBagConstraints.BOTH;
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.gridx = 2;
		gbc_comboBox_2.gridy = 3;
		centerPanel.add(comboBox_2, gbc_comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.fill = GridBagConstraints.BOTH;
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_3.gridx = 0;
		gbc_comboBox_3.gridy = 4;
		centerPanel.add(comboBox_3, gbc_comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		GridBagConstraints gbc_comboBox_4 = new GridBagConstraints();
		gbc_comboBox_4.fill = GridBagConstraints.BOTH;
		gbc_comboBox_4.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_4.gridx = 1;
		gbc_comboBox_4.gridy = 4;
		centerPanel.add(comboBox_4, gbc_comboBox_4);
		
		JLabel lblPathFile = new JLabel("Path file");
		GridBagConstraints gbc_lblPathFile = new GridBagConstraints();
		gbc_lblPathFile.gridwidth = 3;
		gbc_lblPathFile.fill = GridBagConstraints.BOTH;
		gbc_lblPathFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblPathFile.gridx = 0;
		gbc_lblPathFile.gridy = 5;
		centerPanel.add(lblPathFile, gbc_lblPathFile);
		
		activityName = new JTextField();
		activityName.setEditable(false);
		GridBagConstraints gbc_activityName = new GridBagConstraints();
		gbc_activityName.gridwidth = 3;
		gbc_activityName.fill = GridBagConstraints.BOTH;
		gbc_activityName.insets = new Insets(0, 0, 5, 5);
		gbc_activityName.gridx = 0;
		gbc_activityName.gridy = 6;
		centerPanel.add(activityName, gbc_activityName);
		activityName.setColumns(10);
		
		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
				  chooseFile(); // Returns FilePath. Upload not yet implemented
				}
			});
		GridBagConstraints gbc_btnChooseFile = new GridBagConstraints();
		gbc_btnChooseFile.insets = new Insets(0, 0, 5, 0);
		gbc_btnChooseFile.fill = GridBagConstraints.BOTH;
		gbc_btnChooseFile.gridx = 3;
		gbc_btnChooseFile.gridy = 6;
		centerPanel.add(btnChooseFile, gbc_btnChooseFile);
		
		JButton btnSend = new JButton("Send");
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.gridwidth = 4;
		gbc_btnSend.insets = new Insets(0, 0, 5, 0);
		gbc_btnSend.fill = GridBagConstraints.BOTH;
		gbc_btnSend.gridx = 0;
		gbc_btnSend.gridy = 7;
		centerPanel.add(btnSend, gbc_btnSend);
	

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