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
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class uploadFile extends JPanel {
	private JTextField txtActName;
	private JTextField txtActNum;
    private JTextField txtFilePath;
    private JComboBox cmbDay, cmbYear, cmbMonth, cmbHour, cmbMinute;
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
		
		txtActName = new JTextField();
		txtActName.setBounds(174, 54, 260, 20);
		add(txtActName);
		txtActName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Activity Name");
		lblNewLabel.setBounds(39, 57, 125, 14);
		add(lblNewLabel);
		
		txtActNum = new JTextField();
		txtActNum.setBounds(174, 23, 260, 20);
		add(txtActNum);
		txtActNum.setColumns(10);
		
		JLabel lblActivityNo = new JLabel("Activity No.");
		lblActivityNo.setBounds(39, 28, 125, 14);
		add(lblActivityNo);
		
		txtFilePath = new JTextField();
		txtFilePath.setEditable(false);
		txtFilePath.setBounds(174, 147, 260, 20);
		add(txtFilePath);
		txtFilePath.setColumns(10);
		
		JLabel lblPathFile = new JLabel("Path File");
		lblPathFile.setBounds(39, 150, 125, 14);
		add(lblPathFile);
		
		JLabel lblActivityDeadline = new JLabel("Activity Deadline");
		lblActivityDeadline.setBounds(39, 88, 125, 14);
		add(lblActivityDeadline);
		
		cmbDay = new JComboBox();
		cmbYear = new JComboBox();
		cmbMonth = new JComboBox();
		
		cmbDay.setModel(new DefaultComboBoxModel(new String[] {"Day"}));
		cmbDay.setBounds(264, 85, 80, 20);
		add(cmbDay);
		
		cmbYear.setModel(new DefaultComboBoxModel(new String[] {"Year", "2016", "2017", "2000", "2400", "1900"}));
		cmbYear.setBounds(354, 85, 80, 20);
		cmbYear.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				cmbDay.setModel(monthDayYearChecker(cmbMonth.getSelectedItem().toString(), cmbYear.getSelectedItem().toString()));
			}
		});
		add(cmbYear);
		
		cmbMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));
		cmbMonth.setBounds(174, 85, 80, 20);
		cmbMonth.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				cmbDay.setModel(monthDayYearChecker(cmbMonth.getSelectedItem().toString(), cmbYear.getSelectedItem().toString()));
			}
		});
		add(cmbMonth);
		
		cmbHour = new JComboBox();
		cmbHour.setModel(new DefaultComboBoxModel(new String[] {"hour", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18"}));
		cmbHour.setBounds(174, 116, 80, 20);
		add(cmbHour);
		
		cmbMinute = new JComboBox();
		cmbMinute.setModel(new DefaultComboBoxModel(new String[] {"min", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		cmbMinute.setBounds(264, 116, 80, 20);
		add(cmbMinute);
		
		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
				  Path p = chooseFile(); // Returns FilePath. Upload not yet implemented
				  if(!p.equals(null))
				  {
					txtFilePath.setText(p.toString());
					filePathChosen = p;
				  }
				  else
				  {
					  
				  }
				}
			});
		btnChooseFile.setBounds(174, 178, 116, 23);
		add(btnChooseFile);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!fieldChecker(txtActNum, txtActName, txtFilePath, cmbDay, cmbMonth, cmbYear, cmbHour, cmbMinute))
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
					a.setActivityID(Integer.parseInt(txtActNum.getText()));
					a.setActivityTimeStamp(new Timestamp(System.currentTimeMillis()));
					a.setActivityDeadline(new Date(Integer.parseInt(cmbYear.getSelectedItem().toString()), cmbMonth.getSelectedIndex(), Integer.parseInt(cmbDay.getSelectedItem().toString())));
					a.setActivityFile(fm.convertToBinary(chosen));
					a.setActivityFilename(chosen.getName());
					try
					{
						adao.addActivity(a);
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
		});
		btnSend.setBounds(318, 178, 116, 23);
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
				  txtFilePath.setText(ext);
			}
			
			
			
			else
			{
				JOptionPane.showMessageDialog(null, "Not a PDF File.", "Error", JOptionPane.ERROR_MESSAGE);
				filePath = null;
			}
		}
		System.out.println(filePath);
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
					res = new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
				}
				else if(month.equals("Apr") || month.equals("Jun") || month.equals("Sep") || month.equals("Nov"))
				{
					res = new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"});
				}
				else if(month.equals("Feb"))
				{
					res = new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"});
				}
			}
			else
			{
				if(month.equals("Jan") || month.equals("Mar") || month.equals("May") || month.equals("Jul") || month.equals("Aug") || month.equals("Oct") || month.equals("Dec"))
				{
					res = new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
				}
				else if(month.equals("Apr") || month.equals("Jun") || month.equals("Sep") || month.equals("Nov"))
				{
					res = new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"});
				}
				else if(month.equals("Feb"))
				{
					res = new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"});
				}
			}
		}
		return res;
	}
	
	private boolean fieldChecker(JTextField actnum, JTextField actname, JTextField path, JComboBox d, JComboBox m, JComboBox y, JComboBox hr, JComboBox min)
	{
		try
		{
			Integer.parseInt(actnum.getText());
		}
		catch (NumberFormatException nfe)
		{
			return false;
		}
		return !actnum.getText().isEmpty() && !actname.getText().isEmpty() && !path.getText().isEmpty() && d.getSelectedIndex() != 0 && m.getSelectedIndex() != 0 && hr.getSelectedIndex() != 0 && min.getSelectedIndex() != 0 ? true : false;
	}
}
