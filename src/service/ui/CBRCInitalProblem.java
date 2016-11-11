package service.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.cbrc.CBRCEvent;
import service.cbrc.model.CBRCProblem;
import service.cbrc.model.TestCase;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CBRCInitalProblem extends JFrame {

	private JPanel contentPane;
	private JTextField txtProblemName;
	private JTextField txtProblemDesc;
	private JTextField txtFirstSolutionFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CBRCInitalProblem();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CBRCInitalProblem() {
		CBRCEvent ev = new CBRCEvent();
		Color clrError = new Color(255, 0, 0);
		Color clrOk = new Color(255, 255, 255);
		Color clrDisabledOk = new Color(238, 238, 238);
		DefaultListModel<String> lmtci = new DefaultListModel<String>();
		DefaultListModel<String> lmtco = new DefaultListModel<String>();
		setResizable(false);
		setTitle("CBR-C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chckbxDebugMode = new JCheckBox("Debug Mode");
		chckbxDebugMode.setBounds(6, 7, 97, 23);
		contentPane.add(chckbxDebugMode);
		
		JLabel lblProblemName = new JLabel("Problem Name:");
		lblProblemName.setBounds(6, 37, 128, 14);
		contentPane.add(lblProblemName);
		
		JLabel lblProblemDescription = new JLabel("Problem Description:");
		lblProblemDescription.setBounds(6, 62, 128, 14);
		contentPane.add(lblProblemDescription);
		
		txtProblemName = new JTextField();
		txtProblemName.setBounds(144, 34, 240, 20);
		contentPane.add(txtProblemName);
		txtProblemName.setColumns(10);
		
		txtProblemDesc = new JTextField();
		txtProblemDesc.setColumns(10);
		txtProblemDesc.setBounds(144, 59, 240, 20);
		contentPane.add(txtProblemDesc);
		
		JLabel lblFirstSolutionFile = new JLabel("First Solution File:");
		lblFirstSolutionFile.setBounds(6, 113, 128, 14);
		contentPane.add(lblFirstSolutionFile);
		
		txtFirstSolutionFile = new JTextField();
		txtFirstSolutionFile.setEditable(false);
		txtFirstSolutionFile.setColumns(10);
		txtFirstSolutionFile.setBounds(144, 110, 141, 20);
		contentPane.add(txtFirstSolutionFile);
		
		JButton btnNewButton = new JButton("Browse...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filePath = ev.importFirstSolution(null);
				if(!filePath.isEmpty())
					txtFirstSolutionFile.setText(filePath);
			}
		});
		btnNewButton.setBounds(295, 109, 89, 23);
		contentPane.add(btnNewButton);
		
		JPanel panelTCI = new JPanel();
		panelTCI.setBounds(6, 138, 189, 209);
		contentPane.add(panelTCI);
		panelTCI.setLayout(null);
		
		JLabel lblTestCaseInput = new JLabel("Test Case Input");
		lblTestCaseInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestCaseInput.setBounds(10, 11, 169, 14);
		panelTCI.add(lblTestCaseInput);
		
		JList<String> listTCI = new JList<String>();
		listTCI.setBounds(10, 36, 169, 128);
		panelTCI.add(listTCI);
		
		JButton btnTCIAdd = new JButton("+");
		btnTCIAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// file browse
				String absolutePath = ev.importTestCase(null);
				if(!absolutePath.isEmpty())
				{
					lmtci.addElement(absolutePath);
					listTCI.setModel(lmtci);
				}
			}
		});
		btnTCIAdd.setBounds(10, 175, 48, 23);
		panelTCI.add(btnTCIAdd);
		
		JButton btnTCIDel = new JButton("-");
		btnTCIDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> selected = listTCI.getSelectedValuesList();
				if(selected.size() > 0)
				{
					for(int i = 0; i < selected.size(); i++)
						lmtci.removeElement(selected.get(i).toString());
					listTCI.setModel(lmtci);
				}
			}
		});
		btnTCIDel.setBounds(131, 175, 48, 23);
		panelTCI.add(btnTCIDel);
		
		JPanel panelTCO = new JPanel();
		panelTCO.setBounds(195, 138, 189, 209);
		contentPane.add(panelTCO);
		panelTCO.setLayout(null);
		
		JLabel lblTestCaseOutput = new JLabel("Test Case Output");
		lblTestCaseOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestCaseOutput.setBounds(10, 11, 169, 14);
		panelTCO.add(lblTestCaseOutput);
		
		JList<String> listTCO = new JList<String>();
		listTCO.setBounds(10, 36, 169, 128);
		panelTCO.add(listTCO);
		JButton btnTCOAdd = new JButton("+");
		btnTCOAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String absolutePath = ev.importTestCase(null);
				if(!absolutePath.isEmpty())
				{
					lmtco.addElement(absolutePath);
					listTCO.setModel(lmtco);
				}
			}
		});
		btnTCOAdd.setBounds(10, 175, 48, 23);
		panelTCO.add(btnTCOAdd);
		
		JButton btnTCODel = new JButton("-");
		btnTCODel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> selected = listTCO.getSelectedValuesList();
				if(selected.size() > 0)
				{
					for(int i = 0; i < selected.size(); i++)
						lmtco.removeElement(selected.get(i).toString());
					listTCO.setModel(lmtco);
				}
			}
		});
		btnTCODel.setBounds(131, 175, 48, 23);
		panelTCO.add(btnTCODel);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// check if Problem Name contains something
				if(txtProblemName.getText().trim().isEmpty())
				{
					txtProblemName.setBackground(clrError);
				}
				else
				{
					txtProblemName.setBackground(clrOk);
				}
				// check if Problem Description contains something
				if(txtProblemDesc.getText().trim().isEmpty())
				{
					txtProblemDesc.setBackground(clrError);
				}
				else
				{
					txtProblemDesc.setBackground(clrOk);
				}
				// check if First Solution File contains something
				if(txtFirstSolutionFile.getText().isEmpty())
				{
					txtFirstSolutionFile.setBackground(clrError);
				}
				else
				{
					txtFirstSolutionFile.setBackground(clrDisabledOk);
				}
				// check if TCI/TCO have the same number
				if(listTCI.getModel().getSize() > 0)
				{
					listTCI.setBackground(clrError);
				}
				else
				{
					listTCI.setBackground(clrOk);
				}
				if(listTCO.getModel().getSize() > 0)
				{
					listTCO.setBackground(clrError);
				}
				else
				{
					listTCO.setBackground(clrOk);
				}
				if(listTCI.getModel().getSize() != listTCO.getModel().getSize())
				{
					JOptionPane.showMessageDialog(null, "Test Cases Inputs/Outputs must be the same.", "Error", JOptionPane.ERROR_MESSAGE);
					listTCI.setBackground(clrError);
					listTCO.setBackground(clrError);
				}
				else
				{
					if(listTCI.getModel().getSize() == 0 && listTCO.getModel().getSize() == 0)
					{
						JOptionPane.showMessageDialog(null, "Test Cases must not be empty.", "Error", JOptionPane.ERROR_MESSAGE);
						listTCI.setBackground(clrError);
						listTCO.setBackground(clrError);
					}
					else
					{
						listTCI.setBackground(clrOk);
						listTCO.setBackground(clrOk);
					}
				}
				if(txtProblemName.getBackground() == clrOk && 
					txtProblemDesc.getBackground() == clrOk && 
					txtFirstSolutionFile.getBackground() == clrDisabledOk && 
					listTCI.getBackground() == clrOk && listTCO.getBackground() == clrOk)
				{
					ArrayList<TestCase> tc = new ArrayList<TestCase>();
					for(int i = 0; i < lmtci.getSize(); i++)
					{
						TestCase tcio = new TestCase(Paths.get(lmtci.get(i)), Paths.get(lmtco.get(i)));
						tc.add(tcio);
					}
					CBRCProblem prob = new CBRCProblem(txtProblemName.getText(), txtProblemDesc.getText(), Paths.get(txtFirstSolutionFile.getText()), tc);
					CBRCMenu c = CBRCMenu.getInstance();
					DefaultTableModel testcases = new DefaultTableModel(new Object[][] {},
					new String[] {
						"Test Case #", "Sample Input", "Expected Output"
					});
					c.setTestcases(testcases);
					c.setProb(prob);
					setVisible(false);
				}
			}
		});
		btnSubmit.setBounds(295, 7, 89, 23);
		contentPane.add(btnSubmit);
		chckbxDebugMode.setVisible(false);
		if(!isVisible())
			setVisible(true);
	}
	
	public CBRCProblem newWindow()
	{
		CBRCProblem prob = new CBRCProblem();
		
		return prob;
	}
}
