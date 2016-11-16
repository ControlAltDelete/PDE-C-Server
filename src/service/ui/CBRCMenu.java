package service.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import com.cbrc.ast.utils.StudentListRecoveryUtility;
import com.cbrc.db.utils.DerbyUtils;
import com.cbrc.gdt.builder.CASTCodeAnnotator;
import com.cbrc.gdt.builder.CASTGDTBuilder;
import com.cbrc.gdt.builder.CASTGDTStudentTracker;
import com.cbrc.temp.Driver;

import service.cbrc.model.CBRCProblem;
import service.cbrc.model.TestCase;

import javax.swing.JSeparator;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class CBRCMenu extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblPname;
	private JLabel lblPdesc;
	private JLabel lblFirstsolnc;
	private JButton btnAddNewTest;
	private DefaultTableModel testcases;
	private CASTGDTStudentTracker students;
	private CASTGDTBuilder builder;
	private CBRCProblem prob;
	private int newGoalKey = 0;
	private static CBRCMenu menu = null;
	public static boolean feedOnGoing = false;
	
	/**
	 * Create the frame.
	 */
	private CBRCMenu() 
	{
		initialize();
	}
	
	public static CBRCMenu getInstance()
	{
		if(menu == null)
		{
			menu = new CBRCMenu();
		}
		return menu;
	}
	
	/**
	 * @return the prob
	 */
	public CBRCProblem getProb() {
		return prob;
	}

	/**
	 * @param prob the prob to set
	 */
	public void setProb(CBRCProblem prob) {
		this.prob = prob;
		lblPname.setText(prob.getProblemName());
		lblPdesc.setText(prob.getProblemDesc());
		lblFirstsolnc.setText(prob.getFirstSolution().toString());
		ArrayList<TestCase> tc = prob.getTc();
		String tciContent = "";
		String tcoContent = "";
		int i;
		for(i = 0; i < tc.size(); i++)
		{
			try
			{
				Scanner sc = new Scanner(tc.get(i).getTci().toFile());
				StringBuilder sb = new StringBuilder();
				while(sc.hasNextLine())
					sb.append(sc.nextLine());
				tciContent = sb.toString();
				sc.close();
			}
			catch(FileNotFoundException fnfe)
			{
				fnfe.printStackTrace();
			}
			
			try
			{
				Scanner sc = new Scanner(tc.get(i).getTco().toFile());
				StringBuilder sb = new StringBuilder();
				while(sc.hasNextLine())
					sb.append(sc.nextLine());
				tcoContent = sb.toString();
				sc.close();
			}
			catch(FileNotFoundException fnfe)
			{
				fnfe.printStackTrace();
			}
		}
		testcases.addRow(new Object[]{Integer.toString(i), tciContent, tcoContent});
		table.setModel(testcases);
		table.getTableHeader().setReorderingAllowed(false);
		feedOnGoing = true;
	}

	/**
	 * @return the testcases
	 */
	public DefaultTableModel getTestcases() {
		return testcases;
	}

	/**
	 * @param testcases the testcases to set
	 */
	public void setTestcases(DefaultTableModel testcases) {
		this.testcases = testcases;
		table.setModel(testcases);
	}
	
	public void resetMe(){
		// cbr-c initialisation methods
		setCBRC();
		// ui controls
		btnAddNewTest.setEnabled(true);
	}
	
	public void setCBRC()
	{
		// CBR-C
		newGoalKey = 0;
		students = new CASTGDTStudentTracker();
		try
		{
			newGoalKey = DerbyUtils.addNewGoal(prob.getProblemName(), prob.getProblemDesc());
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		CASTCodeAnnotator codeAnnotator = new CASTCodeAnnotator(prob.getFirstSolution().toFile());
		try {
			codeAnnotator.annotateCode();
			builder = new CASTGDTBuilder(newGoalKey, prob.getProblemDesc());
			builder.processFirstCode(codeAnnotator.getHeadNode(), "");
			builder.setDebug(true);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// END CBR-C
	}

	private void initialize()
	{
		
		setTitle("CBR-C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{162, 771, 0};
		gbl_contentPane.rowHeights = new int[]{23, 2, 0, 23, 23, 312, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnStartNewProblem = new JButton("Start New Problem");
		btnStartNewProblem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null, 
				        "If you start a new problem, then the previous problem will be invalid. Do you want to continue?", "Caution",
				        JOptionPane.YES_NO_OPTION);
				if(confirmed == JOptionPane.YES_OPTION)
					new CBRCInitalProblem();
			}
		});
		GridBagConstraints gbc_btnStartNewProblem = new GridBagConstraints();
		gbc_btnStartNewProblem.anchor = GridBagConstraints.NORTH;
		gbc_btnStartNewProblem.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStartNewProblem.insets = new Insets(0, 0, 5, 5);
		gbc_btnStartNewProblem.gridx = 0;
		gbc_btnStartNewProblem.gridy = 0;
		contentPane.add(btnStartNewProblem, gbc_btnStartNewProblem);
		
		JPanel pnlDetails = new JPanel();
		pnlDetails.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlDetails = new GridBagConstraints();
		gbc_pnlDetails.fill = GridBagConstraints.BOTH;
		gbc_pnlDetails.gridheight = 6;
		gbc_pnlDetails.gridx = 1;
		gbc_pnlDetails.gridy = 0;
		contentPane.add(pnlDetails, gbc_pnlDetails);
		GridBagLayout gbl_pnlDetails = new GridBagLayout();
		gbl_pnlDetails.columnWidths = new int[]{0, 0, 0, 389, 0};
		gbl_pnlDetails.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlDetails.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlDetails.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlDetails.setLayout(gbl_pnlDetails);
		
		JLabel lblProblemName = new JLabel("Problem Name:");
		GridBagConstraints gbc_lblProblemName = new GridBagConstraints();
		gbc_lblProblemName.anchor = GridBagConstraints.WEST;
		gbc_lblProblemName.insets = new Insets(0, 0, 5, 5);
		gbc_lblProblemName.gridx = 1;
		gbc_lblProblemName.gridy = 1;
		pnlDetails.add(lblProblemName, gbc_lblProblemName);
		
		lblPname = new JLabel("");
		GridBagConstraints gbc_lblPname = new GridBagConstraints();
		gbc_lblPname.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPname.insets = new Insets(0, 0, 5, 0);
		gbc_lblPname.gridx = 3;
		gbc_lblPname.gridy = 1;
		pnlDetails.add(lblPname, gbc_lblPname);
		
		JLabel lblProblemDescription = new JLabel("Problem Description:");
		GridBagConstraints gbc_lblProblemDescription = new GridBagConstraints();
		gbc_lblProblemDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblProblemDescription.anchor = GridBagConstraints.WEST;
		gbc_lblProblemDescription.gridx = 1;
		gbc_lblProblemDescription.gridy = 3;
		pnlDetails.add(lblProblemDescription, gbc_lblProblemDescription);
		
		lblPdesc = new JLabel("");
		GridBagConstraints gbc_lblPdesc = new GridBagConstraints();
		gbc_lblPdesc.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPdesc.insets = new Insets(0, 0, 5, 0);
		gbc_lblPdesc.gridx = 3;
		gbc_lblPdesc.gridy = 3;
		pnlDetails.add(lblPdesc, gbc_lblPdesc);
		
		JLabel lblPathOfFirst = new JLabel("Path of First Solution:");
		GridBagConstraints gbc_lblPathOfFirst = new GridBagConstraints();
		gbc_lblPathOfFirst.anchor = GridBagConstraints.WEST;
		gbc_lblPathOfFirst.insets = new Insets(0, 0, 5, 5);
		gbc_lblPathOfFirst.gridx = 1;
		gbc_lblPathOfFirst.gridy = 5;
		pnlDetails.add(lblPathOfFirst, gbc_lblPathOfFirst);
		
		lblFirstsolnc = new JLabel("");
		GridBagConstraints gbc_lblFirstsolnc = new GridBagConstraints();
		gbc_lblFirstsolnc.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFirstsolnc.insets = new Insets(0, 0, 5, 0);
		gbc_lblFirstsolnc.gridx = 3;
		gbc_lblFirstsolnc.gridy = 5;
		pnlDetails.add(lblFirstsolnc, gbc_lblFirstsolnc);
		
		JLabel lblTestCases = new JLabel("Test Cases:");
		GridBagConstraints gbc_lblTestCases = new GridBagConstraints();
		gbc_lblTestCases.insets = new Insets(0, 0, 5, 5);
		gbc_lblTestCases.anchor = GridBagConstraints.WEST;
		gbc_lblTestCases.gridx = 1;
		gbc_lblTestCases.gridy = 7;
		pnlDetails.add(lblTestCases, gbc_lblTestCases);
		
		testcases = new DefaultTableModel(new Object[][] {},
		new String[] {
			"Test Case #", "Sample Input", "Expected Output"
		});
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			
		));
		
		JScrollPane scrollPane = new JScrollPane(table);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 8;
		pnlDetails.add(scrollPane, gbc_scrollPane);
		
		btnAddNewTest = new JButton("Add New Test Case");
		btnAddNewTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TestCaseBuilder().showFrame();
			}
		});
		GridBagConstraints gbc_btnAddNewTest = new GridBagConstraints();
		gbc_btnAddNewTest.anchor = GridBagConstraints.EAST;
		gbc_btnAddNewTest.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddNewTest.gridx = 3;
		gbc_btnAddNewTest.gridy = 10;
		pnlDetails.add(btnAddNewTest, gbc_btnAddNewTest);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.anchor = GridBagConstraints.NORTH;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		contentPane.add(separator, gbc_separator);
		
		JButton btnStartSession = new JButton("Start Session");
		btnStartSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!feedOnGoing)
				{
					btnAddNewTest.setEnabled(false);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Problem feeding is on going.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnStartSession = new GridBagConstraints();
		gbc_btnStartSession.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStartSession.insets = new Insets(0, 0, 5, 5);
		gbc_btnStartSession.gridx = 0;
		gbc_btnStartSession.gridy = 2;
		contentPane.add(btnStartSession, gbc_btnStartSession);
		
		JButton btnRegStudent = new JButton("Register Student ID");
		btnRegStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sid = (String)JOptionPane.showInputDialog(null, "Input Student ID", "");
				if(sid == null) { /* do nothing */ }
				else if(sid.trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Nothing entered.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String sName = (String)JOptionPane.showInputDialog(null, "Input Student Name", "");
					if(sName == null) { /* do nothing */ }
					else if(sName.trim().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Nothing entered.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						Integer studID = 0;
						try
						{
							studID = Integer.parseInt(sid);
							Driver.registerNewStudent(students, builder.getSuperGoal().getDBID(), Integer.toString(studID), sName);
							JOptionPane.showMessageDialog(null, "Student Information submitted to Database.", "Success", JOptionPane.INFORMATION_MESSAGE);
						}
						catch (NumberFormatException nfe)
						{
					        JOptionPane.showMessageDialog(null, "Not a number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						catch (SQLException sqle)
						{
							sqle.printStackTrace();
						}
						catch (IOException ioe)
						{
							ioe.printStackTrace();
						}
					}
				}
			}
		});
		GridBagConstraints gbc_btnRegStudent = new GridBagConstraints();
		gbc_btnRegStudent.anchor = GridBagConstraints.NORTH;
		gbc_btnRegStudent.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRegStudent.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegStudent.gridx = 0;
		gbc_btnRegStudent.gridy = 3;
		contentPane.add(btnRegStudent, gbc_btnRegStudent);
		
		JButton btnRecStudent = new JButton("Recover Student List");
		btnRecStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gid = (String)JOptionPane.showInputDialog(null, "Input Goal ID", "");
				if(gid == null) { /* do nothing */ }
				else if(gid.trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Nothing entered.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Integer goalID = 0;
					try
					{
						goalID = Integer.parseInt(gid);
						StudentListRecoveryUtility slru =  new StudentListRecoveryUtility();
						students = slru.recoverStudents(goalID, newGoalKey);
						JOptionPane.showMessageDialog(null, "Successfully Recovered Students", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					catch (NumberFormatException nfe)
					{
				        JOptionPane.showMessageDialog(null, "Not a number!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					catch (SQLException sqle)
					{
						sqle.printStackTrace();
					}
				}
			}
		});
		GridBagConstraints gbc_btnRecStudent = new GridBagConstraints();
		gbc_btnRecStudent.anchor = GridBagConstraints.NORTH;
		gbc_btnRecStudent.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRecStudent.insets = new Insets(0, 0, 5, 5);
		gbc_btnRecStudent.gridx = 0;
		gbc_btnRecStudent.gridy = 4;
		contentPane.add(btnRecStudent, gbc_btnRecStudent);
		
		JButton btnPrintGDT = new JButton("Print GDT");
		btnPrintGDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CBRCGDTView(builder);
			}
		});
		GridBagConstraints gbc_btnPrintGDT = new GridBagConstraints();
		gbc_btnPrintGDT.anchor = GridBagConstraints.NORTH;
		gbc_btnPrintGDT.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPrintGDT.insets = new Insets(0, 0, 0, 5);
		gbc_btnPrintGDT.gridx = 0;
		gbc_btnPrintGDT.gridy = 5;
		contentPane.add(btnPrintGDT, gbc_btnPrintGDT);
		
		setVisible(true);
	}

}
