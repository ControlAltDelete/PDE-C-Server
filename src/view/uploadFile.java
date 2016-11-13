package view;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.fileops.FileLoad;
import database.dao.ActivityDAO;
import database.objects.Activity;
import service.FileManipulation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class uploadFile extends JPanel {
	private JTextField txtActName;
	private JComboBox cmbYear, cmbMonth, cmbDay;
	private FileLoad loader;
	private FileNameExtensionFilter pdfFilter;
	private Path filePathChosen = null;
    JFileChooser fc;
    JButton b, b1;
    JTextField tf;
    FileInputStream in;
    Socket s;
    DataOutputStream dout;
    DataInputStream din;
    int i;
    private JTextField txtFilePath;
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
		
		JLabel lblActivityName = new JLabel("Activity Name");
		lblActivityName.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblActivityName = new GridBagConstraints();
		gbc_lblActivityName.gridwidth = 3;
		gbc_lblActivityName.fill = GridBagConstraints.BOTH;
		gbc_lblActivityName.insets = new Insets(0, 0, 5, 5);
		gbc_lblActivityName.gridx = 0;
		gbc_lblActivityName.gridy = 0;
		centerPanel.add(lblActivityName, gbc_lblActivityName);
		
		txtActName = new JTextField();
		GridBagConstraints gbc_txtActName = new GridBagConstraints();
		gbc_txtActName.gridwidth = 4;
		gbc_txtActName.fill = GridBagConstraints.BOTH;
		gbc_txtActName.insets = new Insets(0, 0, 5, 0);
		gbc_txtActName.gridx = 0;
		gbc_txtActName.gridy = 1;
		centerPanel.add(txtActName, gbc_txtActName);
		txtActName.setColumns(10);
		
		JLabel lblActivityDeadline = new JLabel("Activity Deadline");
		GridBagConstraints gbc_lblActivityDeadline = new GridBagConstraints();
		gbc_lblActivityDeadline.gridwidth = 3;
		gbc_lblActivityDeadline.fill = GridBagConstraints.BOTH;
		gbc_lblActivityDeadline.insets = new Insets(0, 0, 5, 5);
		gbc_lblActivityDeadline.gridx = 0;
		gbc_lblActivityDeadline.gridy = 2;
		centerPanel.add(lblActivityDeadline, gbc_lblActivityDeadline);
		
		cmbDay = new JComboBox();
		cmbDay.setModel(new DefaultComboBoxModel(new String[] {"Day"}));
		GridBagConstraints gbc_cmbDay = new GridBagConstraints();
		gbc_cmbDay.fill = GridBagConstraints.BOTH;
		gbc_cmbDay.insets = new Insets(0, 0, 5, 5);
		gbc_cmbDay.gridx = 0;
		gbc_cmbDay.gridy = 3;
		centerPanel.add(cmbDay, gbc_cmbDay);
		
		cmbMonth = new JComboBox();
		cmbMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));
		GridBagConstraints gbc_cmbMonth = new GridBagConstraints();
		gbc_cmbMonth.fill = GridBagConstraints.BOTH;
		gbc_cmbMonth.insets = new Insets(0, 0, 5, 5);
		gbc_cmbMonth.gridx = 1;
		gbc_cmbMonth.gridy = 3;
		centerPanel.add(cmbMonth, gbc_cmbMonth);
		cmbMonth.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				cmbDay.setModel(monthDayYearChecker(cmbMonth.getSelectedItem().toString(), cmbYear.getSelectedItem().toString()));
			}
		});
		
		cmbYear = new JComboBox();
		cmbYear.setModel(new DefaultComboBoxModel(new String[] {"Year", "2016", "2017", "2000", "2400", "1900"}));
		GridBagConstraints gbc_cmbYear = new GridBagConstraints();
		gbc_cmbYear.fill = GridBagConstraints.BOTH;
		gbc_cmbYear.insets = new Insets(0, 0, 5, 5);
		gbc_cmbYear.gridx = 2;
		gbc_cmbYear.gridy = 3;
		centerPanel.add(cmbYear, gbc_cmbYear);
		cmbYear.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				cmbDay.setModel(monthDayYearChecker(cmbMonth.getSelectedItem().toString(), cmbYear.getSelectedItem().toString()));
			}
		});
		
		JComboBox cmbHour = new JComboBox();
		cmbHour.setModel(new DefaultComboBoxModel(new String[] {"hour", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
		"18"}));
		GridBagConstraints gbc_cmbHour = new GridBagConstraints();
		gbc_cmbHour.fill = GridBagConstraints.BOTH;
		gbc_cmbHour.insets = new Insets(0, 0, 5, 5);
		gbc_cmbHour.gridx = 0;
		gbc_cmbHour.gridy = 4;
		centerPanel.add(cmbHour, gbc_cmbHour);
		
		JComboBox cmbMinute = new JComboBox();
		cmbMinute.setModel(new DefaultComboBoxModel(new String[] {"min", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				"30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
				"50", "51",
				"52", "53", "54", "55", "56", "57", "58", "59"}));
		GridBagConstraints gbc_cmbMinute = new GridBagConstraints();
		gbc_cmbMinute.fill = GridBagConstraints.BOTH;
		gbc_cmbMinute.insets = new Insets(0, 0, 5, 5);
		gbc_cmbMinute.gridx = 1;
		gbc_cmbMinute.gridy = 4;
		centerPanel.add(cmbMinute, gbc_cmbMinute);
		
		JLabel lblPathFile = new JLabel("Path file");
		GridBagConstraints gbc_lblPathFile = new GridBagConstraints();
		gbc_lblPathFile.gridwidth = 3;
		gbc_lblPathFile.fill = GridBagConstraints.BOTH;
		gbc_lblPathFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblPathFile.gridx = 0;
		gbc_lblPathFile.gridy = 5;
		centerPanel.add(lblPathFile, gbc_lblPathFile);
		
		txtFilePath = new JTextField();
		txtFilePath.setEditable(false);
		GridBagConstraints gbc_txtFilePath = new GridBagConstraints();
		gbc_txtFilePath.gridwidth = 3;
		gbc_txtFilePath.fill = GridBagConstraints.BOTH;
		gbc_txtFilePath.insets = new Insets(0, 0, 5, 5);
		gbc_txtFilePath.gridx = 0;
		gbc_txtFilePath.gridy = 6;
		centerPanel.add(txtFilePath, gbc_txtFilePath);
		txtFilePath.setColumns(10);
		
		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					filePathChosen = chooseFile();
				}
			});
		GridBagConstraints gbc_btnChooseFile = new GridBagConstraints();
		gbc_btnChooseFile.insets = new Insets(0, 0, 5, 0);
		gbc_btnChooseFile.fill = GridBagConstraints.BOTH;
		gbc_btnChooseFile.gridx = 3;
		gbc_btnChooseFile.gridy = 6;
		centerPanel.add(btnChooseFile, gbc_btnChooseFile);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Timestamp t = new Timestamp(Integer.parseInt(cmbYear.getSelectedItem().toString()) - 1900, cmbMonth.getSelectedIndex() - 1, Integer.parseInt(cmbDay.getSelectedItem().toString()), Integer.parseInt(cmbHour.getSelectedItem().toString()),Integer.parseInt(cmbMinute.getSelectedItem().toString()), 0, 0);
				if(new Timestamp(System.currentTimeMillis()).after(t))
				{
					JOptionPane.showMessageDialog(null, "Deadline is before the date today. Please change the values accordingly.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(!fieldChecker(txtActName, txtFilePath, cmbDay, cmbMonth, cmbYear, cmbHour, cmbMinute))
					{
						JOptionPane.showMessageDialog(null, "Please fill in the required fields to add a new activity.", "Notice", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
					  	FileManipulation fm = new FileManipulation();
						ActivityDAO adao = new ActivityDAO();
						Activity a = new Activity();
						File chosen = new File(filePathChosen.toUri());
						a.setActivityName(txtActName.getText());
						a.setActivityID(0);
						a.setActivityTimeStamp(new Timestamp(System.currentTimeMillis()));
						a.setActivityDeadline(t);
						a.setActivityFile(fm.convertToBinary(chosen));
						a.setActivityFilename(chosen.getName());
						try
						{
							adao.addActivity(a);
							JOptionPane.showMessageDialog(null, "Successfully added " + a.getActivityName() + " Activity.", "Notice", JOptionPane.INFORMATION_MESSAGE);
							resetAllFields(txtActName, txtFilePath, cmbDay, cmbMonth, cmbYear, cmbHour, cmbMinute);
						}
						catch (FileNotFoundException fnfe)
						{
							JOptionPane.showMessageDialog(null, "File not found. It may have been deleted during the process.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						catch (SQLException e) 
						{
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "Something went wrong.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
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
				  txtFilePath.setText(ext);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Not a PDF File.", "Error", JOptionPane.ERROR_MESSAGE);
				filePath = null;
			}
		}
		return filePath;
	}
	
	private DefaultComboBoxModel<String> monthDayYearChecker(String month, String year)
	{
		DefaultComboBoxModel<String> res = new DefaultComboBoxModel<String>();
		boolean leap = false;
		if((month.equals("Month") && year.equals("Year")) || month.equals("Month") || year.equals("Year"))
		{
			res = new DefaultComboBoxModel(new String[] {"Day"});
		}
		else
		{
			int y = Integer.parseInt(year);
			leap = ((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0);
			if(leap)
			{
				if(month.equals("Jan") || month.equals("Mar") || month.equals("May") || month.equals("Jul") || month.equals("Aug") || month.equals("Oct") || month.equals("Dec"))
				{
					res = new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", 
						"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
						"29", "30", "31"});
				}
				else if(month.equals("Apr") || month.equals("Jun") || month.equals("Sep") || month.equals("Nov"))
				{
					res = new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
						"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", 
						"29", "30"});
				}
				else if(month.equals("Feb"))
				{
					res = new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", 
						"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", 
						"29"});
				}
			}
			else
			{
				if(month.equals("Jan") || month.equals("Mar") || month.equals("May") || month.equals("Jul") || month.equals("Aug") || month.equals("Oct") || month.equals("Dec"))
				{
					res = new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
						"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
						"29", "30", "31"});
				}
				else if(month.equals("Apr") || month.equals("Jun") || month.equals("Sep") || month.equals("Nov"))
				{
					res = new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
						"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", 
						"29", "30"});
				}
				else if(month.equals("Feb"))
				{
					res = new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
						"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"});
				}
			}
		}
		return res;
	}
	
	private boolean fieldChecker(JTextField actname, JTextField path, JComboBox d, JComboBox m, JComboBox y,
			JComboBox hr, JComboBox min)
	{
		return !actname.getText().trim().isEmpty() && !path.getText().trim().isEmpty() && d.getSelectedIndex() !=
			0 && m.getSelectedIndex() != 0 && hr.getSelectedIndex() != 0 && min.getSelectedIndex() != 0 ? true : false;
	}
	
	private void resetAllFields(JTextField actname, JTextField path, JComboBox d, JComboBox m, JComboBox y,
			JComboBox hr, JComboBox min)
	{
		actname.setText("");
		path.setText("");
		d.setSelectedIndex(0);
		m.setSelectedIndex(0);
		y.setSelectedIndex(0);
		hr.setSelectedIndex(0);
		min.setSelectedIndex(0);
	}
}